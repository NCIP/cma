package gov.nih.nci.cma.web.ajax;

  import java.util.List

  import org.codehaus.groovy.grails.commons.ConfigurationHolder;
  
  import gov.nih.nci.cma.domain.IdMapping

class PatientIdHelper {
    
    public List translateIds(List ids) {
    
		System.out.println("\n******************************************************************\n");
		System.out.println("\nEntering PatientIdHelper::translateId....");
    
		Map propsMap = (Map) ConfigurationHolder.getFlatConfig();
    	String dataContext = (String) propsMap.get("cma.dataContext"); 
		
		System.out.println("\nPatientIdHelper::translateId  The Data Context is => " + dataContext);
		System.out.println("\nPatientIdHelper::translateId  Ids received => " + ids);
     	
		List<String> translatedIds = new ArrayList<String>();
		
		if ( dataContext.equals("TCGA") || dataContext.equals("TCGAOvarian") ) {
		
	      	ids.each { id ->
				System.out.println("\nLeaving TestHelper::translateId  Translated Id " + id + " to => " + id.substring(0,12));
			    translatedIds.add(id.substring(0,12))  
	        }
		
		} else if ( dataContext.equals("TARGET")  || dataContext.equals("Rembrandt") ) {
		
			List idMappingObjs = gov.nih.nci.cma.domain.IdMapping.list()			
	      	ids.each { id ->
	      		idMappingObjs.each { idMapping ->
			        if (idMapping.getAnalysisFileId() == id) {
						System.out.println("\nLeaving TestHelper::translateId  Translated Id " + id + " to => " + idMapping.getPdid());
			          	translatedIds.add(idMapping.getPdid())  
			        }
		        }
	        }
		} else {
			translatedIds = ids;
		}

		System.out.println("\nLeaving PatientIdHelper::translateId....");
		System.out.println("\n******************************************************************\n");
				
		return translatedIds;
    }
}
