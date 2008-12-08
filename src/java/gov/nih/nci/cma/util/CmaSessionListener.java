package gov.nih.nci.cma.util;



import gov.nih.nci.caintegrator.application.cache.BusinessCacheManager;
import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.security.PublicUserPool;
import gov.nih.nci.caintegrator.service.task.Task;

import java.util.Collection;

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
		System.out.println("Session destroyed cleaning cache for id=" + event.getSession().getId() );
				
        BusinessCacheManager.getInstance().removeSessionCache(event.getSession().getId());
		
        Collection<Task> allTasks = PresentationCacheManager.getInstance().getAllSessionTasks(event.getSession().getId());
        BusinessCacheManager.getInstance().removeSessionCacheForTasks(allTasks);
        
        PresentationCacheManager.getInstance().removeSessionCache(event.getSession().getId());
        
        String gpUser = (String)event.getSession().getAttribute(PublicUserPool.PUBLIC_USER_NAME);
    	PublicUserPool pool = (PublicUserPool)event.getSession().getAttribute(PublicUserPool.PUBLIC_USER_POOL);
    	
    	if (gpUser != null && pool != null){
			pool.returnPublicUser(gpUser);
    	}               
	} 
} 


