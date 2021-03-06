

class ClinicalServiceTests extends GroovyTestCase {

	def clinicalService
	
    void testSomething() {
    	
    	
    	def cols = clinicalService.getColumnNames()
    	
    	System.out.println(cols)
//    	List ids = new ArrayList()
//    	ids.add("E09910")
//    	ids.add("HF1292")
//    	ids.add("E09928")
//    	ids.add("E07733")
//    	ids.add("E09358")
    	
    	//List ids = clinicalService.getIdsForAgeAtDx(new Integer(40), new Integer(60))
    	//List ids = clinicalService.getIdsForGender("F")
    	
//    	String[] patientGroups = new String[2]
//    	patientGroups[0] = "GBM"
//    	patientGroups[1] = "NON_TUMOR"

		//List<String> patientGroups = new ArrayList<String>()
    	//patientGroups.add("GBM")
    	//patientGroups.add("ASTROCYTOMA")
    	
    	//List ids = clinicalService.getIdsForSampleGroups(patientGroups)
    	
    	//System.out.println("getIdsForSampleGroups returned ids:")
    	//ids.each { id ->
    	//	System.out.println("${id}")
    		
    	//}
    	
        //List data = clinicalService.getClinicalData(ids)
        
        List data = clinicalService.getClinicalDataForGroup("GBM")
        
        System.out.println("Got back numResults=${data?.size()}")
        System.out.println("==== CLINICAL REPORT ====")
    	data.each { d -> 
    	        System.out.println(d)    	        
    	}
    	System.out.println("===== END CLINICAL REPORT ====")
    	System.out.println()
    	
    	
    	/*
    	
        List data = clinicalService.getClinicalData(ids)
        System.out.println("Got back numResults=${data?.size()}")
        System.out.println("==== CLINICAL REPORT ====")
    	data.each { d -> 
    	        System.out.println(d)    	        
    	}
    	System.out.println("===== END CLINICAL REPORT ====")
    	System.out.println()
    	
    	List diseaseValues = clinicalService.getPermissibleValues("disease")
    	System.out.println("Got disease values:")
    	diseaseValues.each { d -> System.out.println("${d},") }
    	System.out.println()
    	*/
    	
    	
    	
    	
    	assert true
    }
}
