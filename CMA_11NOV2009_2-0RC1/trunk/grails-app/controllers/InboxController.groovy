import gov.nih.nci.caintegrator.application.cache.*;
import gov.nih.nci.caintegrator.service.task.*;

class InboxController {

    def index = { 
    		//get the tasks and sessionId
    		PresentationTierCache ptc = CacheFactory.getPresentationTierCache();							
    		Collection tasks;
    		def sid = session.getId();
    		try	{
    			//get all tasks from presentation tier
				tasks = ptc.getAllSessionTasks(sid);
    		}
    		catch(Exception e)	{
    			tasks = new ArrayList();
    		}
    		
    		render(view:'inbox', model:[tasks:tasks, sessionId:sid]) 
    }
}
