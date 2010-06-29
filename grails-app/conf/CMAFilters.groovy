class CMAFilters {
   def filters = {
		   /*
        manageLicenseAcceptance(controller:'*') {
			before = {
				//println(" *** CMAFilter")
				//println("controllerName = " + controllerName)
				//println("actionName = " + actionName)
				
				// 'applicationUser' means the user is logging in. Bypass
				// the license check for this controller.
				if (controllerName != "applicationUser") {
					// Try session
					def acceptedLicense = session.acceptedLicense
					//println("session: acceptedLicense = " + acceptedLicense)
					// Try params (set in legal.gsp)
					if (acceptedLicense == null) {
						acceptedLicense = params.acceptedLicense
						//println("params: acceptedLicense = " + acceptedLicense)
					}
					if (acceptedLicense == null) {
						// Go to license. Have to set the user's controller choice
						// in session; setting in request or params doesn't stick
						session.uController = controllerName					
						redirect(uri:'/legal.gsp')
		                return false
					}
					else {
						session.acceptedLicense = "true"
						session.uController = null
					}
				}
				// else allow to proceed 
			}
		}
       */ 
   }
}

