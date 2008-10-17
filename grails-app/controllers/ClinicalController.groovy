import gov.nih.nci.cma.clinical.*;

class ClinicalController {
	
	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
	def clinicalService;
	
    def index = { 
		def patLists = defaultListLoaderService.getPatientLists(session.id, false);
    	render(view:'main', model:[patLists:patLists])	
    }
    
    def clinicalSubmit = {
    	//submit the query, put in cache, forward to inbox
    }
    
	//TEST TEST
//    def clinicalReport = {
//    	def cols = clinicalService.getColumnNames()
//    	def report = clinicalService.getClinicalData(new ArrayList())
//    	
//    	render(view:'clinicalReport', model:[cols:cols, rows:report])	
//    }
    //END TEST TEST
    
    def clinicalReport = {
		//simulate a call to the clinical service
		//def reportBeansList = clinicalService.doSomeMethod();
		//returns List<RembrandtClinicalReportBean>
		//we may also want a sep method for pulling completed reports from cache?
		
		//generate this List artificially
		//this view is a JSP using the DisplayTag for report rendering
    		
		//random string gen
    	def availChars = []  
		('A'..'Z').each { availChars << it.toString() }  
    	// even it out to about the same odds of getting a char or a number  
    	3.times { (0..9).each { availChars << it.toString() } }  
    	def generateRandomString = { length ->   
	    	def max = availChars.size      
	    	def rnd = new Random()  
	    	def sb = new StringBuilder()  
	    	length.times { sb.append(availChars[rnd.nextInt(max)]) }  
	    		sb.toString()  
    	} 
    	//remove if after testing
    	if(session.getAttribute("reportBeansList") == null)	{
	    	//generate the fake list of reportBeans
	    	List reportBeansList = new ArrayList()
	    	20.times {
	    		RembrandtClinicalReportBean rb = new RembrandtClinicalReportBean();
	    		rb.setSampleId("E" + generateRandomString(5));
	    		rb.setDisease(generateRandomString(6))
	    		rb.setGrade(generateRandomString(6))
	    		rb.setMriDesc(generateRandomString(6))
	    		rb.setKarnofsky(generateRandomString(6))
	    		reportBeansList.add(rb);
	    	}
	    	
	    	//put the reportBeansList somewhere..session for now, cache would be better, keyed as taskid (Query name)
	    	session.setAttribute("reportBeansList", reportBeansList);
    	}
    	render(view:'clinicalReportTable')
    }
}
