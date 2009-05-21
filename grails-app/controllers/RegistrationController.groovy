  import gov.nih.nci.caintegrator.application.mail.Mail;
  import gov.nih.nci.caintegrator.application.mail.MailProps;
  import gov.nih.nci.caintegrator.exceptions.ValidationException;

class RegistrationController {
    
    // Define the Registration domain class/bean  
    Registration registration
    StringBuffer selectedContextsStr
    
    // The register action will only accept POST requests
    static allowedMethods = [register:'POST']

    def index = { 
    	render(view:'create') 
    }
    
    def send = {
    	
    	// Bind request parameters onto properties of the Registration bean
	  	registration = new Registration(params) 
	  			
		try	{
			// Send Request to NCI CBITT App Support
			def mp = new MailProps()
	        def fdbk = grailsApplication.config.cma.register.template.support	
				
	        fdbk = fdbk.replace("{first_name}", "${registration.firstName}")
	        fdbk = fdbk.replace("{last_name}", "${registration.lastName}")
	        fdbk = fdbk.replace("{contexts}", "${registration.contexts}")
	        fdbk = fdbk.replace("{email}", "${registration.email}")
	        fdbk = fdbk.replace("{phone}", "${registration.phone}")
	        fdbk = fdbk.replace("{department}", "${registration.department}")
	        fdbk = fdbk.replace("{institution}", "${registration.institution}")

	        mp.setBody(fdbk);
	        
	        //the fields below should be in a props file
	        mp.setSmtp(grailsApplication.config.cma.feedback.mailSMPT)
	        mp.setSubject(grailsApplication.config.cma.register.mailSubject.support)
	        mp.setMailTo(grailsApplication.config.cma.register.mailTo.support)
	        mp.setMailFrom(grailsApplication.config.cma.feedback.mailFrom)
	    	Mail.sendMail(mp);
	    		
			// Send Notification of Request to User
		    
	    	mp = new MailProps();
	    	fdbk = grailsApplication.config.cma.register.template.user
	    	
	        fdbk = fdbk.replace("{first_name}", "${registration.firstName}")
	        fdbk = fdbk.replace("{last_name}", "${registration.lastName}")
	    	
	    	mp.setBody(fdbk);
	    	
	    	mp.setSmtp(grailsApplication.config.cma.feedback.mailSMPT)
	        mp.setSubject(grailsApplication.config.cma.register.mailSubject.user)
	        mp.setMailTo("${registration.email}")
	        mp.setMailFrom(grailsApplication.config.cma.feedback.mailFrom)

	        Mail.sendMail(mp);			

            // Go back to home page
            redirect(uri: "/index.gsp")
					
		} catch (Exception e) {
		}
    }


    def register = {

	  	String userCode
	  	
    	// Pull the captcha verifcation str from the current session
    	def verificationStr = session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY)
    	
    	// Bind request parameters onto properties of the Registration bean
	  	registration = new Registration(params) 
	  	
	  	// Create a list of the selected contexts from the request parameter string
		def selectedContextList = []
	  	if (request.getParameter("contexts") != null ) {
		    def pattern
		    
			selectedContextsStr = new StringBuffer()	    
	    	grailsApplication.config.cma.dataContexts.each {
		    	pattern = ~(it.key)
		    	if ( registration.contexts =~ pattern ) {
		    		selectedContextList.add("${it.key}")
		    		selectedContextsStr.append("${it.key}  ")
		    	} 
		    }
	    }
	  	  	
	  	// Validate the entered values
	  	if(registration.validate()) {
	    	if ( verificationStr != null ) { 
				verificationStr = (String) session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY)
				userCode = "${registration.verificationCode}" 
				if ( verificationStr != null && verificationStr.equals(userCode) ) {
		            flash.message = "${registration.firstName}, your registration request has been successfully processed!"
		            redirect(controller:"registration",action:"send",params:params)
				} else { 
		    		flash.message = "Access code did not match." 
		    		render(view:'create',model:[registration:registration, selectedContextList:selectedContextList])
	    		} 
	    	}
        }
        else {
            render(view:'create',model:[registration:registration, selectedContextList:selectedContextList])
        }
    }
    
             
   
}
