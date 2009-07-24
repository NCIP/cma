package gov.nih.nci.cma.services.tcga



class TCGAClinicalServiceTests extends GroovyTestCase {

	def TCGAClinicalService
	
    void testSomething() {
    	
		List ids = TCGAClinicalService.getIdsForKarnofskyScore(70,90)
		
		List cd = TCGAClinicalService.getClinicalData(ids)
		println("getIdsForKarnofskyScore 70,90 returned numIds=${ids?.size()}")
    	//try searching and getting a list of ids
    	cd.each {  c ->
    	  println(c)    		
    	}
    	

    }
}
