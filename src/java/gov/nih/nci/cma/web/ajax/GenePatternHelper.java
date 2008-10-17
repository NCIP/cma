package gov.nih.nci.cma.web.ajax;


import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import javax.servlet.http.HttpSession;

import org.genepattern.client.GPServer;

public class GenePatternHelper {
    public GenePatternHelper() {
    }

	public String checkGPStatus(String sid) {
		//System.out.println("checkGPStatus called...");
		WebContext wctx = WebContextFactory.get();
		HttpSession session = wctx.getSession();
		GPServer gpServer = (GPServer)session.getAttribute("genePatternServer");
	    	
		int jobNumber = Integer.parseInt(sid);
		boolean done = false;
		String message = null;
		try {
			done = gpServer.isComplete(jobNumber);
		} catch (Exception e) {//(WebServiceException wse){
			message = "error";
		}
		if (message == null)
			message = done?"completed":"running";
		
		//System.out.println("checkGPStatus message = " + message);
		
		return message;
	}
}

