import gov.nih.nci.cma.domain.ApplicationUser;
import gov.nih.nci.caintegrator.application.analysis.AnalysisServerClientManager
import gov.nih.nci.caintegrator.application.configuration.SpringContext
import java.util.Properties
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.groovy.grails.commons.*



class BootStrap {

    def init = { servletContext ->
    
        def config = ConfigurationHolder.config
        
        println("BootStrap: dataContext=${config.cma.dataContext}")
        
     	//load the properties file and put props into system properties
     	//Load the the application properties and set them as system properties
  		Properties cmaPortalProperties = new Properties();
     	
     	//Make the app properties system variables. Grails probably has a better way to 
     	//handle this but alot of existing code requires the system props.
     	//need to fix this. When deploying in JBoss there is no problem because
     	//properties-service.xml will define the location of the app properties file
     	//for now just hardcode the path. 
  		//String appPropertiesFileName = "C:\\local\\content\\cma\\config\\cma-tcga.properties"
  		     
  		
  		//String appPropertiesFileName = "/local/content/cma/config/cma-tcga.properties"
  		//String appPropertiesFileName = "/local/content/cma/config/cma-rembrandt.properties"
  		  		
  		
  		FileInputStream inputStream
  		
  		inputStream = new FileInputStream(config.cma.appPropertiesFile);
  		cmaPortalProperties.load(inputStream);
  		   
  		if (cmaPortalProperties.isEmpty()) {
  		   log.error("Error: no properties found when loading properties file: " + appPropertiesFileName);
  		}
  		    		   
  		String key = null;
  		String val = null;
  		for (Iterator i = cmaPortalProperties.keySet().iterator(); i.hasNext(); ) {
  		  key = (String) i.next();
  		  val = cmaPortalProperties.getProperty(key);
  		  System.setProperty(key, val);
  		}
  		
  		
  		//Initialize the analysis server
  		AnalysisServerClientManager am = AnalysisServerClientManager.getInstance();
  		String jmsProviderURL = System.getProperty("gov.nih.nci.cma.jms.jboss_url");
	    String jndiFactoryName = System.getProperty("gov.nih.nci.cma.jms.factory_jndi");
		String requestQueueName = System.getProperty("gov.nih.nci.cma.jms.analysis_request_queue");
		String responseQueueName = System.getProperty("gov.nih.nci.cma.jms.analysis_response_queue");
			   
		am.setJMSparameters(jmsProviderURL, jndiFactoryName, requestQueueName, responseQueueName);
			   
		am.establishQueueConnection();
  		
		final String BACKUP_ADMIN =  System.getProperty("gov.nih.nci.cma.backup_admin_login")
		String backup_admin_pass = System.getProperty("gov.nih.nci.cma.backup_admin_password")
	    if (!ApplicationUser.findByUserId(BACKUP_ADMIN)) {
	     	new ApplicationUser(userId:BACKUP_ADMIN,password:"${backup_admin_pass}").save()
	    }
		
		
		//init the SpringContext helper - legacy code uses this to lookup some beans if outside of spring
		SpringContext.initialize(servletContext);
     		     		     	     	    
     }
     def destroy = {
     }
     
      def dwrconfig = { 

		create(creator:'new', javascript:'JDate') { 
			param (name:'class') { 'java.util.Date' } 
		}
		create(creator:'new', javascript:'DynamicListHelper')	{
			param (name:'class') { 'gov.nih.nci.cma.web.ajax.DynamicListHelper' }
		}
		create(creator:'new', javascript:'UserListHelper')	{
			param (name:'class') { 'gov.nih.nci.caintegrator.application.lists.UserListBeanHelper' }
		}
		create(creator:'new', javascript:'RegHelper')	{
			param (name:'class') { 'gov.nih.nci.cma.web.ajax.RegHelper' }
		}
		create(creator:'new', javascript:'GenePatternHelper')	{
			param (name:'class') { 'gov.nih.nci.cma.web.ajax.GenePatternHelper' }
		}
				
	 }

} 