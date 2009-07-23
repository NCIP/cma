package gov.nih.nci.cma.controller

  // CSM Imports
  import gov.nih.nci.security.SecurityServiceProvider; 
  import gov.nih.nci.security.AuthenticationManager;
  import gov.nih.nci.security.AuthorizationManager;
  
  import gov.nih.nci.security.exceptions.CSException;
  
  import gov.nih.nci.security.authorization.domainobjects.User;
  
  // CMA Imports
  import gov.nih.nci.cma.domain.ApplicationUser;


class ApplicationUserController extends BaseController {

    def beforeInterceptor = [action:this.&auth, except:['login', 'logout']]
    
    def index = { redirect(action:list,params:params) }
    
    def defaultListLoaderService
    
    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

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

//    def delete = {
//        def user = ApplicationUser.get( params.id )
//        if(user) {
//            user.delete()
//            flash.message = "User ${params.id} deleted"
//            redirect(action:list)
//        }
//        else {
//            flash.message = "User not found with id ${params.id}"
//            redirect(action:list)
//        }
//    }
//
//    def edit = {
//        def user = ApplicationUser.get( params.id )
//
//        if(!user) {
//            flash.message = "User not found with id ${params.id}"
//            redirect(action:list)
//        }
//        else {
//            return [ user : user ]
//        }
//    }
//
//    def update = {
//        def user = ApplicationUser.get( params.id )
//        if(user) {
//            user.properties = params
//            if(!user.hasErrors() && user.save()) {
//                flash.message = "User ${params.id} updated"
//                redirect(action:show,id:user.id)
//            }
//            else {
//                render(view:'edit',model:[user:user])
//            }
//        }
//        else {
//            flash.message = "User not found with id ${params.id}"
//            redirect(action:edit,id:params.id)
//        }
//    }
//
//    def create = {
//        def user = new ApplicationUser()
//        user.properties = params
//        return ['user':user]
//    }
//
//    def save = {
//        def user = new ApplicationUser(params)
//        if(!user.hasErrors() && user.save()) {
//            flash.message = "User ${user.id} created"
//            redirect(action:show,id:user.id)
//        }
//        else {
//            render(view:'create',model:[user:user])
//        }
//    }

    
    //CSM based login 
    def login = {
    	if ( request.method == "GET" ) {
    	  session.userId = null
    	  def user = new ApplicationUser()
    	}
    	else {
    		
    		// Is user a public/guest user
    		def user = ApplicationUser.findByUserIdAndPassword( params.userId, params.password )
    					
	    	boolean authenticationOK = false
	    	boolean authorizationOK = false
    		boolean publicUser  = (user != null)
    					
    		if (!publicUser) {

	     		System.out.println("\n***************************************");
	     		System.out.println("\nCreating New User: params.userId = " + params.userId);
	     		System.out.println("\n***************************************");
	    		user = new ApplicationUser()
	    		user.userId = params.userId
	    		user.password = params.password
			    	
	    		try {
	    			AuthenticationManager authenticationManager = SecurityServiceProvider.getAuthenticationManager(grailsApplication.config.cma.authenticationManagerContext); 
	    			authenticationOK = authenticationManager.login(params.userId, params.password); 
	    			
	    			if (!authenticationOK ) {
				    	flash['message'] = 'Please enter a valid User ID and Password' 
	    			}
	     		} catch (CSException cse){ 
	    			authenticationOK = false
				    flash['message'] = 'Please enter a valid User ID and Password' 
	    			System.out.println("Caught CSException when trying to authenticate user against LDAP... ");
	    			cse.printStackTrace(System.out);
	    		} catch (java.lang.SecurityException ex) {
	    			authenticationOK = false
				    flash['message'] = 'Exception occurred during Authentication.  Please contact NCICB App Support.' 
	    			System.out.println("Caught SecurityException when trying to authenticate user against LDAP...");
	    			ex.printStackTrace(System.out);
	    		}
			    	
		    	if ( authenticationOK ) {
		    		try {
			    		// Authorize via CSM
			    		AuthorizationManager authorizationManager = SecurityServiceProvider.getAuthorizationManager(grailsApplication.config.cma.authenticationManagerContext);
						User cmaUser = authorizationManager.getUser(params.userId);
						authorizationOK = (cmaUser != null);
						if ( !authorizationOK ) {
				    		flash['message'] = 'You are NOT authorized to use this application.  Please send a request to NCICB App Support.' 
				    	}
			    	} catch (CSException cse){ 
	    				authorizationOK = false
				    	flash['message'] = 'Exception occurred during Authorization.  Please contact NCICB App Support.' 
	    				System.out.println("Caught CSException when trying to authorize user against CSM... ");
	    				cse.printStackTrace(System.out);
	    			} catch (java.lang.SecurityException ex) {
	    				authorizationOK = false
				    	flash['message'] = 'Exception occurred during Authorization.  Please contact NCICB App Support.' 
	    				System.out.println("Caught SecurityException when trying to authorize user against CSM...");
	    				ex.printStackTrace(System.out);
	    			}
	    		}
	    	}
	    		
	    	if ( publicUser || (authenticationOK && authorizationOK) ) {
	        	System.out.println("Successfully logged in!");
	      		session.userId = user.userId
	            flash['message'] = 'Login Successful!'
	                 
	            //use the injected service to load the def lists
	            defaultListLoaderService.loadDefaultLists()
	                   
	            System.out.println("Successfully stored UserListBean in the session")
	    	}
	
	        //always go back to home page
	        redirect(uri: "/index.gsp")
    	}    		

    		
    		/*
    		// Is user a public/guest user
    		def user = ApplicationUser.findByUserIdAndPassword( params.userId, params.password )
    					
    		boolean publicUser  = (user != null)
    					
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

                 //def redirectParams = session.originalRequestParams ?
                 //   session.originalRequestParams : [controller:'sampleInfo', action:'search']
      			 //redirect(redirectParams)    				    		

                 flash['message'] = 'Login Successful!'
                 
                 //use the injected service to load the def lists
                 defaultListLoaderService.loadDefaultLists()
                   
                System.out.println("Successfully stored UserListBean in the session")
    		}
    		else {
    		  //login failed
    		  flash['message'] = 'Please enter a valid user ID and password' 
    	    }
    	    */
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
		session.acceptedLicense = null
		//invalidating the session will cause sessionDestroyed to 
		//be called in the CMASessionTracker. sessionDestroyed will 
		//clean up the cache etc..
	    session.invalidate()
	    
	    flash['message'] = 'Successfully logged out'
		
		//reload the default lists
		//defaultListLoaderService.loadDefaultLists()
		
	    redirect(uri: "/");
   }
}
