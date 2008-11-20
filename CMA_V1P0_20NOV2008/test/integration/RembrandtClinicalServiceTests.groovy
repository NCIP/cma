package gov.nih.nci.cma.services.rembrandt


class RembrandtClinicalServiceTests extends GroovyTestCase {

	def rembrandtClinicalService
	
    void testSomething() {
//    	Try getting ids for race
    	List ids = rembrandtClinicalService.getIdsForRace('WHITE')
    	println("Query returned numResults=${ids.size()}")
    	
    	//get clinical data for ids
    	List clinData = rembrandtClinicalService.getClinicalData(ids)
    	
    	clinData.each { cd ->
    	
    	     println("${cd.toString()}")
    		    		
    	}
    }
}
