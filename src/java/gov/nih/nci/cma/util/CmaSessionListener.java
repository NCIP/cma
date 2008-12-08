package gov.nih.nci.cma.util;



import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSessionListener; 
import javax.servlet.http.HttpSessionEvent;

public class CmaSessionListener implements HttpSessionListener { 
		private static final Log log = LogFactory.getLog(CmaSessionListener.class);

	public void sessionCreated(HttpSessionEvent event) { 
		System.out.println("Session created id=" + event.getSession().getId()); 
	}
	
	public void sessionDestroyed(HttpSessionEvent event) { 
		System.out.println("Session destroyed id=" + event.getSession().getId() ); 
	} 
} 


