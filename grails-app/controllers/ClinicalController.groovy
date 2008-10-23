import grails.converters.*
import gov.nih.nci.cma.clinical.*;
import java.text.SimpleDateFormat;

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
		def genderList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.gender)
		def diseaseList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.disease)
		def raceList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.race)

		//TESTing - clear tmp report
		session.setAttribute("reportBeansList", null)
		
		render(view:'main', model:[patLists:patLists, genderList:genderList, diseaseList:diseaseList, raceList:raceList])	
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

		//TODO: we will also want a sep method for pulling completed reports from cache?
		//this view is a JSP using the DisplayTag for report rendering
		//check query name
		def qname = params.queryName //this will be the key to accessing the report in cache/session
		if(params.queryName == null || params.queryName == "")	{
			SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MMddyyyy");
			qname = "clinical_" + dateformatMMDDYYYY.format(new Date())
			params.queryName = qname;
		}
    	qname = gov.nih.nci.cma.util.SafeHTMLUtil.clean(qname)

    	//	if(session.getAttribute(qname) == null)	{ //one already exists, overwrite
    		List reportBeansList = clinicalService.getClinicalData(request);
	    	//put the reportBeansList somewhere..session for now, cache would be better, keyed as taskid (Query name)
	    	session.setAttribute(qname, reportBeansList);
   // 	}
    
    	//forward as to not repost on refresh
    	redirect(action:'clinicalReportDisplay', params:[taskId:qname])
    }
    
    def clinicalReportDisplay = {
    	render(view:"clinicalReportTable")
    }
    
    def clinicalKM = {
		//process form, set model and forward
		def sreq = params as JSON
    	def path = request.getContextPath()
    	def sessionId = session.getId()
    	
    	render(view:"clinicKmPlot", model:[plot:params.plot,listItems:sreq, sessionId:sessionId])
    }
    
    def clinicalReportTest = {
    		
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
     
        	if(session.getAttribute("reportBeansList") == null)	{
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
