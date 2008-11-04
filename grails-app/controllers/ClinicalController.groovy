import grails.converters.*
import gov.nih.nci.cma.clinical.*;
import java.text.SimpleDateFormat;
import gov.nih.nci.cma.services.rembrandt.RembrandtClinicalService;

class ClinicalController {
	
	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
	//def clinicalService;
	def rembrandtClinicalService;
	
    def index = { 
		def patLists = defaultListLoaderService.getPatientLists(session.id, false);
//		def genderList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.gender)
//		def diseaseList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.disease)
//		def raceList = clinicalService.getPermissibleValues(RembrandtClinicalKeys.race)
		def genderList = rembrandtClinicalService.getPermissibleValues(RembrandtClinicalKeys.gender)
		def diseaseList = rembrandtClinicalService.getPermissibleValues(RembrandtClinicalKeys.disease)
		def raceList = rembrandtClinicalService.getPermissibleValues(RembrandtClinicalKeys.race)

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

		if(params.sampleGroup == null)	{
    		//flash and redirect
    		flash.message = "Please select a Sample Group"
            redirect(controller:"clinical")
            return
    	}
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
    		//List reportBeansList = clinicalService.getClinicalData(request);
    	    List reportBeansList = rembrandtClinicalService.getClinicalData(request);
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
    	if(params.groupNameOne == params.groupNameCompare)	{
    		//flash and redirect
    		flash.message = "Groups can not be the same"
            redirect(controller:"clinical")
            return
    	}
		//process form, set model and forward
		def sreq = params as JSON
    	def path = request.getContextPath()
    	def sessionId = session.getId()
    	
    	render(view:"clinicKmPlot", model:[plot:params.plot,listItems:sreq, sessionId:sessionId])
    }
    
    def clinicalSampleGroup =	{
    		
   		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MMddyyyy");
		def qname = "clinical_" + dateformatMMDDYYYY.format(new Date())
   		List reportBeansList = null;
   		
		if(params.ids != null)	{
			List samList = Arrays.asList(params.ids.split(","));
			//reportBeansList = clinicalService.getClinicalData(samList)
			reportBeansList = rembrandtClinicalService.getClinicalData(samList)
		}
		else	{
			//reportBeansList = clinicalService.getClinicalDataForGroup(params.taskId);
			reportBeansList = rembrandtClinicalService.getClinicalDataForGroup(params.taskId);
			
		}
		session.setAttribute(qname, reportBeansList);
   		redirect(action:'clinicalReportDisplay', params:[taskId:qname, noBack:'true'])
    }
    
    //called from a remote call to save samples
    def saveSamplesFromClinical = { 
    		//get the list of reportbeans
    		def idList = [] 
    		String commaList = "";
    		List rbs = new ArrayList()
    		rbs = session.getAttribute(params.sessionKey)
    		if(rbs!=null)	{
    			rbs.each{
    				idList << it.getSampleId()
    			}
    			commaList = idList.join(",")
    		}
    		
    		//call dynamic list helper - pass/fail return
    		String results = gov.nih.nci.cma.web.ajax.DynamicListHelper.saveSamplesWithSession(commaList, params.listName, request.getSession())
    		render results
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
