class ClinicalServiceTests extends GroovyTestCase {

	def clinicalService
	
    void testSomething() {
    	
    	
    	def cols = clinicalService.getColumnNames()
    	
    	System.out.println(cols)
    	List ids = new ArrayList()
    	ids.add("E007")
    	ids.add("E009")
        List data = clinicalService.getClinicalData(ids)
    	data.each { d -> 
    	        System.out.print(d)
    	        System.out.print("\t")
    	}
    	System.out.println()
    	assert true
    }
}
