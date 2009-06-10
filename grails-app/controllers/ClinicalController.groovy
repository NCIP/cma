  import grails.converters.*
  
  import gov.nih.nci.cma.clinical.*;
  
  import java.text.SimpleDateFormat;
  
  import org.apache.log4j.Logger;
  

class ClinicalController {
	
	private static Logger logger = Logger.getLogger(ClinicalController.class);
    
    // Define the ClinicalView domain class/bean  
    ClinicalView clinicalView
    RembrandtClinicalView rembrandtClinicalView
	
	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
		
	def getClinicalService = {
			def dc = grailsApplication.config.cma.dataContext
			def dataServiceName = "${dc}ClinicalService"
			def clinicalServiceClass = grailsApplication.getArtefact("Service", dataServiceName)
			logger.debug("getClinicalService returning instance of ${clinicalServiceClass.getName()}")
			println("getClinicalService returning instance of ${clinicalServiceClass.getName()}")
			return clinicalServiceClass.newInstance()					
	}
	
    def index = { 
			
		def clinSrv = getClinicalService()	
						
		def patLists = defaultListLoaderService.getPatientLists(session.id, false);
		def genderList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.gender)
		def diseaseList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.disease)
		def raceList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.race)

		def tumorTissueSiteList = clinSrv.getPermissibleValues("tumorTissueSite");
		def vitalStatusList = clinSrv.getPermissibleValues("vitalStatus");
			
	    def eventList = clinSrv.getPermissibleValues("event");
	    def deathList = clinSrv.getPermissibleValues("death");
		def congenitalAbnormalityList = clinSrv.getPermissibleValues("congenitalAbnormality");
		def telStatusList = clinSrv.getPermissibleValues("telStatus");
		def trisomiesList = clinSrv.getPermissibleValues("trisomies_4_10");
		def mllStatusList = clinSrv.getPermissibleValues("mllStatus");
		def e2aStatusList = clinSrv.getPermissibleValues("e2aStatus");
		def bcrStatusList = clinSrv.getPermissibleValues("bcrStatus");
		def cnsStatusList = clinSrv.getPermissibleValues("cns");
		def testicularStatusList = clinSrv.getPermissibleValues("testicular");
		  
		def day8mrd = clinSrv.getPermissibleValues("day8mrd");
		def day29mrd = clinSrv.getPermissibleValues("day29mrd");
		  
		// TCGA-Ovarian
		def yesNoList = clinSrv.getPermissibleValues("yesNo");
		def initPathologicDxMethodList = clinSrv.getPermissibleValues("initPathologicDxMethod");
		def ethnicityList = clinSrv.getPermissibleValues("ethnicity");
		def anatomicOrganSubdivisionList = clinSrv.getPermissibleValues("anatomicOrganSubdivision");
		def personNeoplasmStatusList = clinSrv.getPermissibleValues("personNeoplasmStatus");
		def siteOfTumorFirstRecurrenceList = clinSrv.getPermissibleValues("siteOfTumorFirstRecurrence");
		def tumorStageList = clinSrv.getPermissibleValues("tumorStage");
		def tumorGradeList = clinSrv.getPermissibleValues("tumorGrade");
		def tumorResidualDiseaseList = clinSrv.getPermissibleValues("tumorResidualDisease");
		def primaryTherapyOutcomeSuccessList = clinSrv.getPermissibleValues("primaryTherapyOutcomeSuccess");
		def histologicalTypeList = clinSrv.getPermissibleValues("histologicalType");
		def jewishOriginList = clinSrv.getPermissibleValues("jewishOrigin");
		  		 
		//TESTing - clear tmp report
		session.setAttribute("reportBeansList", null)
		
		def ds = grailsApplication.config.cma.dataContext ?: ""
		if ( ds == "TCGAOvarian" ) {
			render(view:"${ds}Main", model:[patLists:patLists, genderList:genderList, 
			   diseaseList:diseaseList, raceList:raceList, tumorTissueSiteList:tumorTissueSiteList,
			   vitalStatusList:vitalStatusList, yesNoList:yesNoList, initPathologicDxMethodList:initPathologicDxMethodList,
			   ethnicityList:ethnicityList, anatomicOrganSubdivisionList:anatomicOrganSubdivisionList, 
			   personNeoplasmStatusList:personNeoplasmStatusList, siteOfTumorFirstRecurrenceList:siteOfTumorFirstRecurrenceList,
			   tumorStageList:tumorStageList, tumorGradeList:tumorGradeList, tumorResidualDiseaseList:tumorResidualDiseaseList,
			   primaryTherapyOutcomeSuccessList:primaryTherapyOutcomeSuccessList, histologicalTypeList:histologicalTypeList,
			   jewishOriginList:jewishOriginList])		
		} else {
			render(view:"${ds}Main", model:[patLists:patLists, genderList:genderList, 
			   diseaseList:diseaseList, raceList:raceList, tumorTissueSiteList:tumorTissueSiteList,
			   vitalStatusList:vitalStatusList, eventList:eventList, deathList:deathList, congenitalAbnormalityList:congenitalAbnormalityList,
			   telStatusList:telStatusList, trisomiesList:trisomiesList, mllStatusList:mllStatusList,
			   e2aStatusList:e2aStatusList, bcrStatusList:bcrStatusList, cnsStatusList:cnsStatusList,
			   testicularStatusList:testicularStatusList, day29mrd:day29mrd, day8mrd:day8mrd])		
			}
    }
    
    def clinicalSubmit = {
    	//submit the query, put in cache, forward to inbox
    }
    
    
    def clinicalReport = {
        def validClinicalEnties
        	
    	// Bind request parameters onto properties of the appropriate Clinical bean
    	if ( params.clinicalFormType == "TCGA-GBM" || params.clinicalFormType == "TCGA-Ovarian"  ) {
	  		clinicalView = new ClinicalView(params) 
	  		validClinicalEnties = clinicalView.validate()
	  	} else {
	  		rembrandtClinicalView = new RembrandtClinicalView(params)
	  		validClinicalEnties = rembrandtClinicalView.validate()
	  	}
	  		  	  	
	  	if( validClinicalEnties ) {
	  		  	  	
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
	
	    	def clinSrv = getClinicalService()	
	        List reportBeansList = clinSrv.getClinicalData(request);
	    	session.setAttribute(qname, reportBeansList);
	    
	    	// Forward as to not repost on refresh
	    	redirect(action:'clinicalReportDisplay', params:[taskId:qname])
	    } else {
	        List selectedSampleGrpList
	        if ( request.getParameterValues("sampleGroups") != null ) {
		        selectedSampleGrpList = Arrays.asList(request.getParameterValues("sampleGroups"))
		    }
	        if ( request.getParameterValues("sampleGroup") != null ) {
		        selectedSampleGrpList = Arrays.asList(request.getParameterValues("sampleGroup"))
		    }
							
		    def clinSrv = getClinicalService()	
			defaultListLoaderService.loadDefaultLists()
						
			def patLists = defaultListLoaderService.getPatientLists(session.id, false);
			def genderList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.gender)
			def diseaseList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.disease)
			def raceList = clinSrv.getPermissibleValues(RembrandtClinicalKeys.race)
	
			def tumorTissueSiteList = clinSrv.getPermissibleValues("tumorTissueSite");
			def vitalStatusList = clinSrv.getPermissibleValues("vitalStatus");
						
		    def eventList = clinSrv.getPermissibleValues("event");
		    def deathList = clinSrv.getPermissibleValues("death");
		    def congenitalAbnormalityList = clinSrv.getPermissibleValues("congenitalAbnormality");
		    def telStatusList = clinSrv.getPermissibleValues("telStatus");
		    def trisomiesList = clinSrv.getPermissibleValues("trisomies_4_10");
		    def mllStatusList = clinSrv.getPermissibleValues("mllStatus");
		    def e2aStatusList = clinSrv.getPermissibleValues("e2aStatus");
		    def bcrStatusList = clinSrv.getPermissibleValues("bcrStatus");
		    def cnsStatusList = clinSrv.getPermissibleValues("cns");
		    def testicularStatusList = clinSrv.getPermissibleValues("testicular");
		  
		    def day8mrd = clinSrv.getPermissibleValues("day8mrd");
		    def day29mrd = clinSrv.getPermissibleValues("day29mrd");
		  
			// TCGA-Ovarian
			def yesNoList = clinSrv.getPermissibleValues("yesNo");
			def initPathologicDxMethodList = clinSrv.getPermissibleValues("initPathologicDxMethod");
			def ethnicityList = clinSrv.getPermissibleValues("ethnicity");
			def anatomicOrganSubdivisionList = clinSrv.getPermissibleValues("anatomicOrganSubdivision");
			def personNeoplasmStatusList = clinSrv.getPermissibleValues("personNeoplasmStatus");
			def siteOfTumorFirstRecurrenceList = clinSrv.getPermissibleValues("siteOfTumorFirstRecurrence");
			def tumorStageList = clinSrv.getPermissibleValues("tumorStage");
			def tumorGradeList = clinSrv.getPermissibleValues("tumorGrade");
			def tumorResidualDiseaseList = clinSrv.getPermissibleValues("tumorResidualDisease");
			def primaryTherapyOutcomeSuccessList = clinSrv.getPermissibleValues("primaryTherapyOutcomeSuccess");
			def histologicalTypeList = clinSrv.getPermissibleValues("histologicalType");
			def jewishOriginList = clinSrv.getPermissibleValues("jewishOrigin");
		  		 		    
			def dc = grailsApplication.config.cma.dataContext
			if ( dc == "TCGAOvarian" ) {
				render(view:"${dc}Main", model:[clinicalView:clinicalView, rembrandtClinicalView:rembrandtClinicalView,
				   patLists:patLists, genderList:genderList, 
				   diseaseList:diseaseList, raceList:raceList, tumorTissueSiteList:tumorTissueSiteList,
				   vitalStatusList:vitalStatusList, yesNoList:yesNoList, initPathologicDxMethodList:initPathologicDxMethodList,
				   ethnicityList:ethnicityList, anatomicOrganSubdivisionList:anatomicOrganSubdivisionList, 
				   personNeoplasmStatusList:personNeoplasmStatusList, siteOfTumorFirstRecurrenceList:siteOfTumorFirstRecurrenceList,
				   tumorStageList:tumorStageList, tumorGradeList:tumorGradeList, tumorResidualDiseaseList:tumorResidualDiseaseList,
				   primaryTherapyOutcomeSuccessList:primaryTherapyOutcomeSuccessList, histologicalTypeList:histologicalTypeList,
			   	   jewishOriginList:jewishOriginList])		
			} else {
				render(view:"${dc}Main", model:[clinicalView:clinicalView, rembrandtClinicalView:rembrandtClinicalView,
				   patLists:patLists, genderList:genderList, 
				   diseaseList:diseaseList, raceList:raceList, tumorTissueSiteList:tumorTissueSiteList,
				   vitalStatusList:vitalStatusList, eventList:eventList, deathList:deathList, congenitalAbnormalityList:congenitalAbnormalityList,
				   telStatusList:telStatusList, trisomiesList:trisomiesList, mllStatusList:mllStatusList,
				   e2aStatusList:e2aStatusList, bcrStatusList:bcrStatusList, cnsStatusList:cnsStatusList,
				   testicularStatusList:testicularStatusList, day29mrd:day29mrd, day8mrd:day8mrd])		
			}
	    }
    }
    
    def clinicalReportDisplay = {
		def ds = grailsApplication.config.cma.dataContext ?: ""
    	render(view:"${ds}ClinicalReportTable")
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
   		
		def clinSrv = getClinicalService()	
		
		if(params.ids != null)	{
			List samList = Arrays.asList(params.ids.split(","));
			reportBeansList = clinSrv.getClinicalData(samList)
		}
		else	{
			reportBeansList = clinSrv.getClinicalDataForGroup(params.taskId);
			
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
    				idList << it.getId()
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
