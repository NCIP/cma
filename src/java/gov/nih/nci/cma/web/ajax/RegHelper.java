package gov.nih.nci.cma.web.ajax;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import gov.nih.nci.caintegrator.application.mail.Mail;
import gov.nih.nci.caintegrator.application.mail.MailProps;
import gov.nih.nci.caintegrator.exceptions.ValidationException;

public class RegHelper {
	
	private static Logger logger = Logger.getLogger(RegHelper.class);

	public static String pReg(String ln, String fn, String em, String in, String ca, String ph, String de)	{
       	//look @ the captcha
		JSONObject jso = new JSONObject();
		String status = "pass";
		String msg = "";
		WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();
		
		String k = session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY) != null ? (String) session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY) : null;
		
		if(k!=null && k.equals(ca.trim()))	{

			try	{
	        	//need to clean the generalFeedback field, also put this in the props as a template
	        	MailProps mp = new MailProps();
	        	
	        	//make sure its only 1 email
	        	em = em.replace(",", "").replace(";","");
	        	//send the mail to APP support
	        	/*
	        	String fdbk = fn + " " + ln + " is requesting an account for the CMA Application. \n\n";
	        	fdbk += "Their email is: " + em + " \n";
	        	fdbk += "Their institution is: " + in + " \n";
	        	fdbk += "\n\nThis is an automated email sent from the CMA Application.\n";
	        	*/
	        	String fdbk = System.getProperty("cma.register.template.support");
	        	fdbk = fdbk.replace("{last_name}", (ln!=null)? ln : "None");
	        	fdbk = fdbk.replace("{first_name}", (fn!=null)? fn : "None");
	        	fdbk = fdbk.replace("{email}", (em!=null)? em : "None");
	        	fdbk = fdbk.replace("{phone}", (ph!=null)? ph : "None");
	        	fdbk = fdbk.replace("{department}", (de!=null)? de : "None");
	        	fdbk = fdbk.replace("{institution}", (in!=null)? in : "None");
	        	
	        	mp.setBody(fdbk);
	        	//the fields below should be in a props file
	         	mp.setSmtp(System.getProperty("cma.feedback.mailSMPT"));
	        	mp.setSubject(System.getProperty("cma.register.mailSubject.support"));
	        	mp.setMailTo(System.getProperty("cma.register.mailTo.support"));
	        	mp.setMailFrom(System.getProperty("cma.feedback.mailFrom"));

				// TODO UNCOMMENT!
	    		//Mail.sendMail(mp);
	    		
	    		//send the mail to the user
	    		mp = new MailProps();
	    		fdbk = System.getProperty("cma.register.template.user");
	        	fdbk = fdbk.replace("{last_name}", (ln!=null)? ln : "None");
	        	fdbk = fdbk.replace("{first_name}", (fn!=null)? fn : "None");
	    		/*
	        	fdbk = "Dear " + fn + " " + ln + ",\n Thanks for registering for access to the CMA Application.  You will receive your official account information via email shortly.  Please contact ncicb@pop.nci.nih.gov for further assistance.\n";
	    		fdbk += "\n\nSincerely,\n-The CMA Team";
	    		*/
	    		mp.setBody(fdbk);
	    		mp.setSmtp(System.getProperty("cma.feedback.mailSMPT"));
	        	mp.setSubject(System.getProperty("cma.register.mailSubject.user"));
	        	mp.setMailTo(em.trim());
	        	mp.setMailFrom(System.getProperty("cma.feedback.mailFrom"));

	        	// TODO UNCOMMENT!
	        	//Mail.sendMail(mp);
	    	}
// TODO UNCOMMENT catch below when Mail.sendMail is uncommented!
			
//	    	catch (ValidationException e) {
//System.out.println("In first catch. e = " + e.toString());
//	    		logger.error("mail did not send from regHelper");
//	    		logger.error(e);
//	    		status = "failed";
//	    		msg = "SEND_FAILED";
//	    	}
	    	catch (Exception e) {
	    		logger.error("mail did not send from regHelper");
	    		logger.error(e);
	    		status = "failed";
	    		msg = "SEND_FAILED_GENERIC";
	    	}
		}
		else	{
			status = "fail";
			msg = "BAD_CAPTCHA";
		}
		
		jso.put("status", status);
		jso.put("msg", msg);
		
		if(status.equals("pass"))	{
			// Uncomment the two lines below to enable login after registration (more steps are needed).
			// Should change the login parameters too
			//jso.put("un", "RBTuser");
			//jso.put("ps", "RBTpass");
		}
		
		return jso.toString();
	}
}
