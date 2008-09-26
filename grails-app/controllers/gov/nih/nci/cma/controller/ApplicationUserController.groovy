package gov.nih.nci.cma.controller

import gov.nih.nci.security.SecurityServiceProvider; 
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.cma.domain.ApplicationUser;
import gov.nih.nci.caintegrator.application.lists.UserListBean;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.cma.CacheConstants;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.ListOrigin;
import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.application.cache.PresentationTierCache;


class ApplicationUserController extends BaseController {

    def beforeInterceptor = [action:this.&auth, except:['login', 'logout']]
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ userList: ApplicationUser.list( params ) ]
    }

    def show = {
        def user = ApplicationUser.get( params.id )

        if(!user) {
            flash.message = "User not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ user : user ] }
    }

    def delete = {
        def user = ApplicationUser.get( params.id )
        if(user) {
            user.delete()
            flash.message = "User ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "User not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def user = ApplicationUser.get( params.id )

        if(!user) {
            flash.message = "User not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ user : user ]
        }
    }

    def update = {
        def user = ApplicationUser.get( params.id )
        if(user) {
            user.properties = params
            if(!user.hasErrors() && user.save()) {
                flash.message = "User ${params.id} updated"
                redirect(action:show,id:user.id)
            }
            else {
                render(view:'edit',model:[user:user])
            }
        }
        else {
            flash.message = "User not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def user = new ApplicationUser()
        user.properties = params
        return ['user':user]
    }

    def save = {
        def user = new ApplicationUser(params)
        if(!user.hasErrors() && user.save()) {
            flash.message = "User ${user.id} created"
            redirect(action:show,id:user.id)
        }
        else {
            render(view:'create',model:[user:user])
        }
    }

    
    //CSM based login 
    def login = {
    	if (request.method == "GET") {
    	  session.userId = null
    	  def user = new ApplicationUser()
    	}
    	else {
    		//UserCredentials credentials = new UserCredentials(); 
    		//credentials.setPassword(params.password); 
    		//credentials.setUsername(params.userId); 
    		//Get the user credentials from the database and login
    		
    		
    		//check the local DB for the login
    		def user =
    			ApplicationUser.findByUserIdAndPassword(params.userId,
    					params.password)
    					
    		boolean loginOK  = (user != null)
    					
    		if (!loginOK) {
    		  //login not found in local DB now check LDAP using CSM
    		  System.out.println("Creating new user: params.userId=" + params.userId);
    		  user = new ApplicationUser()
    		  user.userId = params.userId
    		  user.password = params.password
    		    	
    		  try{ 
    			AuthenticationManager authenticationManager = SecurityServiceProvider.getAuthenticationManager('cma'); 
    			loginOK = authenticationManager.login(params.userId, params.password); 
    		  } catch (CSException cse){ 
    			loginOK = false
    			System.out.println("Caught CSException when trying to login.. ");
    			ese.printStackTrace(System.out);
    		  } catch (java.lang.SecurityException ex) {
    			loginOK = false
    			System.out.println("Caught SecurityException when trying to login..");
    			//could not login and configuration not correct for CSM
    			ex.printStackTrace(System.out);
    		  }
    		}
    		
    		if (loginOK) {
      		     //successful login
      		     System.out.println("Successfully logged in!");
      			 session.userId = user.userId
                 /*
                 def redirectParams = session.originalRequestParams ?
                    session.originalRequestParams : [controller:'sampleInfo', action:'search']
      			 redirect(redirectParams)    				    		
                 */
                 flash['message'] = 'Login Successful!'
                 
                 PresentationTierCache presentationCacheManager = PresentationCacheManager.getInstance();
                 //Login is successful now load the lists
                 // Load user lists if necessary (patient lists and their permissable values)
                 //UserListBean userListBean = (UserListBean) presentationCacheManager.getNonPersistableObjectFromSessionCache(request.getSession().getId(), CacheConstants.USER_LISTS);
                 UserListBean userListBean = (UserListBean) session.getAttribute(CacheConstants.USER_LISTS);
                 if (userListBean == null) {
            	   userListBean = new UserListBean();
            	   //session.setAttribute(CacheConstants.USER_LISTS, userListBean);
            	   //presentationCacheManager.addNonPersistableToSessionCache(request.getSession().getId(), CacheConstants.USER_LISTS, userListBean);
            	   
            	   // load user lists 
            	   // we are loading using grails and then converting to 
            	   // list objects in application-commons so that we can reuse the list management 
            	   // functionality w/o rewriting it in grail (for now).                   
                   // List<UserList> userLists = (List<UserList>) listLoader.loadUserLists();
                   def listObjs = gov.nih.nci.cma.domain.List.list()
                   
                   java.util.List<UserList> userLists = new ArrayList<UserList>();
                   UserList ul;
                   List<ListItem> items;
                   
                   listObjs.each{ gl ->
                   
                      def gItems = gl.listItems
                      items = new ArrayList<ListItem>();
                      gItems.each{ gi ->      
                      ListItem i = new ListItem(gi.itemName, gi.listName)
                    	  i.setId(gi.id);
                    	  i.setRank(gi.rank)
                    	  i.setNotes(gi.itemDescription)                    	                      	 
                    	  items.add(i)
                      }
                                            
                      ListType lt = ListType.valueOf(gl.type)
                	  ul = new UserList(gl.name, lt, items, Collections.emptyList())
                	  ul.setAuthor(gl.author)
                	  ul.setCategory(gl.category)
                	  ul.setInstitute(gl.institution)
                	  ul.setDateCreated(gl.creationDate)
                	  
                	  if (gl.subtype != null) {
                	    ListSubType lst = ListSubType.valueOf(gl.subtype)
                	    ul.setListSubType(lst)
                	  }
                	  
                	  ul.setId(gl.id)
                	  
                	  if (gl.origin != null) {
                	    ListOrigin lo = ListOrigin.valueOf(gl.origin)
                	    ul.setListOrigin(lo)
                	  }
                	  
                	  userLists.add(ul)                	                   	  
                   }
                                                         
//                   for (UserList ulist: userLists) {
//                     userListBean.addList(ulist);
//                   }	
                   
                   //orig way
//                   UserListBean userListBean = new UserListBean();
		           presentationCacheManager.addNonPersistableToSessionCache(request.getSession().getId(), 
		        		   CacheConstants.USER_LISTS, userListBean);
		            
                   UserListBeanHelper userListBeanHelper = new UserListBeanHelper(request.getSession().getId());
                   userListBeanHelper.addBean(request.getSession().getId(),CacheConstants.USER_LISTS,userListBean);            
//                   List<UserList> userLists = (List<UserList>) listLoader.loadUserLists();
                   for(UserList legacyul: userLists){
                       userListBeanHelper.addList(legacyul);
                   }
                   //end orig
                   
                }                
                System.out.println("Successfully stored UserListBean in the session")
    		}
    		else {
    		  //login failed
    		  flash['message'] = 'Please enter a valid user ID and password' 
    	    }

            //always go back to home page
            redirect(uri: "/index.gsp")
    	}    		
    }
    
    /*
    def login = {
	if (request.method == "GET") {
		session.userId = null
		def user = new ApplicationUser()
	}
	else {
		def user =
		ApplicationUser.findByUserIdAndPassword(params.userId,
			params.password)
		if (user) {
			session.userId = user.userId
                  def redirectParams = session.originalRequestParams ?
			session.originalRequestParams : [controller:'sampleInfo']
			redirect(redirectParams)
		}
		else {
			flash['message'] = 'Please enter a valid user ID and password'
		}
	}
   }
   */

   def logout = {
	session.userId = null
	session.invalidate();
    //need to clean the session entirely, lists, cache, temp files, ect....this is not enough
	flash['message'] = 'Successfully logged out'
	//redirect(controller:'applicationUser', action:'login')
     redirect(uri: "/");
   }
}
