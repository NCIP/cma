package gov.nih.nci.cma.controller

import gov.nih.nci.security.SecurityServiceProvider; 
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.cma.domain.ApplicationUser;


class ApplicationUserController extends BaseController {

    def beforeInterceptor = [action:this.&auth, except:['login', 'logout']]
    
    def index = { redirect(action:list,params:params) }
    
    def defaultListLoaderService
    
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
    		def user = ApplicationUser.findByUserIdAndPassword(params.userId,
    					params.password)
    					
    		boolean loginOK  = (user != null)
    					
    		if (!loginOK) {
    		  //login not found in local DB now check LDAP using CSM
    		  System.out.println("Creating new user: params.userId=" + params.userId);
    		  user = new ApplicationUser()
    		  user.userId = params.userId
    		  user.password = params.password
    		    	
    		  try{
    			AuthenticationManager authenticationManager = SecurityServiceProvider.getAuthenticationManager(grailsApplication.config.cma.authenticationManagerContext); 
    			loginOK = authenticationManager.login(params.userId, params.password); 
    		  } catch (CSException cse){ 
    			loginOK = false
    			System.out.println("Caught CSException when trying to login.. ");
    			cse.printStackTrace(System.out);
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
                 
                 //use the injected service to load the def lists
                 defaultListLoaderService.loadDefaultLists()
                   
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
		//session.invalidate(); //dont invalidate, but rather start a new one - more to do here
	    //need to clean the session entirely, lists, cache, temp files, ect....this is not enough
	
	    flash['message'] = 'Successfully logged out'
		
		//reload the default lists
		defaultListLoaderService.loadDefaultLists()
		
	    redirect(uri: "/");
   }
}
