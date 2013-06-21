/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.util;



import gov.nih.nci.caintegrator.application.cache.BusinessCacheManager;
import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.security.PublicUserPool;
import gov.nih.nci.caintegrator.service.task.Task;

import java.io.File;
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
		String sessionId = event.getSession().getId();
		System.out.println("Session destroyed cleaning cache for id=" + sessionId );
				
        BusinessCacheManager.getInstance().removeSessionCache(sessionId);
		
        Collection<Task> allTasks = PresentationCacheManager.getInstance().getAllSessionTasks(sessionId);
        BusinessCacheManager.getInstance().removeSessionCacheForTasks(allTasks);
        
        PresentationCacheManager.getInstance().removeSessionCache(sessionId);
        
        String gpUser = (String)event.getSession().getAttribute(PublicUserPool.PUBLIC_USER_NAME);
    	PublicUserPool pool = (PublicUserPool)event.getSession().getAttribute(PublicUserPool.PUBLIC_USER_POOL);
    	
    	if (gpUser != null && pool != null){
			pool.returnPublicUser(gpUser);
    	}               
    	
    	//clean up the temp files associated with this session
    	String tmpDirStr = System.getProperty("java.io.tmpdir");
    	System.out.println("Deleting files in tmpDir=" + tmpDirStr + " for session=" + sessionId);
    	File tmpDir = new File(tmpDirStr);
    	String[] tmpFiles = tmpDir.list();
    	for (int i=0; i < tmpFiles.length; i++) {
    	  String fileName = tmpFiles[i];
    	  if (fileName.startsWith(sessionId)) {
    	    //delete the file
    		String fileNameToDelete = tmpDir + System.getProperty("file.separator") + fileName;
    		try {
	    		File fileToDelete = new File(fileNameToDelete);
	    		System.out.println("Deleting file: " + fileNameToDelete);
	    		fileToDelete.delete();
	    	} catch(Exception ex) {
	          log.error(ex);
	    	}    	
    	  }
    	}
	} 
} 


