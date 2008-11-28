
import gov.nih.nci.cma.domain.rembrandt.PatientData
import gov.nih.nci.cma.domain.rembrandt.NeurologicalEvaluation
import gov.nih.nci.cma.domain.rembrandt.PriorChemotherapy
import gov.nih.nci.cma.domain.rembrandt.PriorRadiationtherapy
import gov.nih.nci.cma.domain.rembrandt.PriorSurgery
import gov.nih.nci.cma.domain.rembrandt.PtChemotherapy
import gov.nih.nci.cma.domain.rembrandt.PtRadiationtherapy
import gov.nih.nci.cma.domain.rembrandt.PtSurgery
import org.apache.log4j.Logger;

import gov.nih.nci.cma.clinical.RembrandtClinicalKeys
import gov.nih.nci.cma.clinical.RembrandtClinicalReportBean
import gov.nih.nci.cma.util.RbtNeuroComparator;

import gov.nih.nci.caintegrator.application.lists.UserList
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListItem
import gov.nih.nci.caintegrator.application.lists.ListType
import org.springframework.web.context.request.RequestContextHolder

class RembrandtClinicalService {

    boolean transactional = false
    private static Logger logger = Logger.getLogger(RembrandtClinicalService.class);

    def serviceMethod() {

    }
    
    /**
     * Extracts a list of unique ids from a list of domin objects
     */
    private List getIdList(List domainObjs) {
      if (domainObjs == null) {
    	logger.warn("getIdList passed null parameter")
    	return Collections.emptyList()
      }
      Set retSet = new HashSet()
      domainObjs.each { d ->
        if (d != null) {
          retSet.add(d.getId())  
        }
        else {
          logger.warn("getIdList got null domain object.")
        }
      }
      return new ArrayList(retSet)
    }
    
    /**
     * 
     */
    public List getPatientDIDsForSampleIds(Set idSet) {      
      String idString = getIdString(idSet)
      String pdQS = "From gov.nih.nci.cma.domain.rembrandt.PatientData pd where pd.institutionId=8 and pd.sampleId in " 
      
      pdQS += idString
      
      def patients = PatientData.findAll(pdQS)
      Set pdidsIds = new HashSet()
      patients.each { p ->
    	  pdidsIds.add(p.getId())
      }
      return new ArrayList(pdidsIds)
    }
    
    /**
     * getIdsForSampleGroups
     */
    public List getIdsForSampleGroups(List sampleGroups) {
    	  Set idSet = new HashSet()
          Set groupNames = new HashSet(sampleGroups)
          
          if ((sampleGroups != null) && (sampleGroups.size() > 0)) {
        	  
        	  def webRequest= RequestContextHolder.currentRequestAttributes()        	  
              def session = webRequest.session    	 

              UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session);
      	      List<UserList> lists = userListBeanHelper.getLists(ListType.PatientDID);
              java.util.List listItems = null
      	      lists.each { ul ->
      	         logger.debug("Checking listName=${ul.getName()}")
      	         if (groupNames.contains(ul.getName())) {
      	           listItems = ul.getListItems()
      	           listItems.each { li -> 
      	        	  idSet.add(li.getName())
      	           }
      	         }
      	      }    	      	  
          }
    	  
    	  return getPatientDIDsForSampleIds(idSet)                   
    }
    
    /**
     * getIdsForDiseaseType
     */
    public List getIdsForDiseaseType(String diseaseType) {
    	def patients = PatientData.findAllByDiseaseTypeLikeAndInstitutionId(diseaseType, 8)
    	logger.debug("getIdsForDiseaseType returned numRows=${patients.size()} for diseaseType=${diseaseType}")
    	return getIdList(patients)
    }
    
    /**
     * getIdsForGender
     */
    public List getIdsForGender(String gender) {
    	def patients = PatientData.findAllByGenderLikeAndInstitutionId(gender, 8)
    	logger.debug("getIdsForGender returned numRows=${patients.size()} for gender=${gender}")
    	return getIdList(patients)
    }
    
    public List getIdsForGrade(String grade) {
        return Collections.emptyList()	
    }
    
    /**
     * getIdsForRace
     */
    public List getIdsForRace(String race) { 
    	    	
    	def patients = PatientData.findAllByRaceLikeAndInstitutionId(race, 8)
    	logger.debug("getIdsForRace returned numRows=${patients.size()} for race=${race}")
    	return getIdList(patients)
    }
    
    /**
     * getIdsForAgeAtDx
     */
    public List getIdsForAgeAtDx(Integer ageAtDxLower, Integer ageAtDxUpper) {
    	def patients = PatientData.findAllByInstitutionIdAndAgeBetween(8,ageAtDxLower, ageAtDxUpper)
    	logger.debug("getIdsForAgeAtDx returned numRows=${patients.size()} for lower=${ageAtDxLower} upper=${ageAtDxUpper}")
    	return getIdList(patients)
    }
    
    /**
     * getIdsForSurvival
     */
    public List getIdsForSurvival(Integer survivalLower, Integer survivalUpper) {
    	def patients = PatientData.findAllByInstitutionIdAndSurvivalLengthBetween(8,survivalLower, survivalUpper)
    	logger.debug("getIdsForSurvival returned numRows=${patients.size()} for lower=${survivalLower} upper=${survivalUpper}")
    	return getIdList(patients)
    }
    
    
    /**
     * getClinicalDataForGroup
     */
    public List getClinicalDataForGroup(String groupName) { 
        List groupList = new ArrayList()
        groupList.add(groupName)
        List ids = getIdsForSampleGroups(groupList)
        return getClinicalData(ids)
    }
    
    
    private void loadReportBeanWithPriorChemoData(RembrandtClinicalReportBean crb, List pc) {
    	 pc.each { d ->
         	//println("pdid=${d.getPatientDid()} timepoint=${d.getTimePoint()} agentName=${d.getAgentName()}")
         
        crb.appendPriorTherapyChemoAgentName(d.getAgentName())	 
        crb.appendPriorTherapyChemoCourseCount(d.getCourseCount())	 
      }
    }
    
    /**
     * PriorRadiation -
     * 
     *    Can there be more than one value?
     */
    private void loadReportBeanWithPriorRadData(RembrandtClinicalReportBean crb, List pr) {
    	 //StringBuffer sb = new StringBuffer()
    	 
    	 pr.each { d ->
    	   //sb.append("${d.getFractionDose()} ")
         //println("pdid=${d.getPatientDid()} timepoint=${d.getTimePoint()} fractionDose=${d.getFractionDose()}")
         
           crb.appendPriorTherapyRadiationSite(d.getRadiationSite())
           crb.appendPriorTherapyRadiationFractionDose(d.getFractionDose().toString())
           crb.appendPriorTherapyRadiationFractionNumber(d.getFractionNumber().toString())
           crb.appendPriorTherapyRadiationType(d.getRadiationType())
         
        }

    }
    private void loadReportBeanWithPriorSurgData(RembrandtClinicalReportBean crb, List ps) {
    	 ps.each { d ->
         	//println("pdid=${d.getPatientDid()}  timepoint=${d.getTimePoint()} procedureTitle=${d.getProcedureTitle()}")
    	    crb.appendPriorTherapySurgeryProcedureTitle(d.getProcedureTitle())	 
    		crb.appendPriorTherapySurgeryTumorHistology(d.getTumorHistology())	 
    		crb.appendPriorTherapySurgeryOutcome(d.getSurgeryOutcome())         
    	 }
    }
    private void loadReportBeanWithPtChemoData(RembrandtClinicalReportBean crb, List ptChemo) {
    	 ptChemo.each { d ->
         	//println("pdid=${d.getPatientDid()} timepoint=${d.getTimePoint()}  agentName=${d.getAgentName()}")
         	crb.appendOnStudyTherapyChemoAgentName(d.getAgentName())	 
    		crb.appendOnStudyTherapyChemoCourseCount(d.getCourseCount().toString())	          	
      	 }
    }
    private void loadReportBeanWithPtRadData(RembrandtClinicalReportBean crb, List ptRad) {
    	 ptRad.each { d ->
         	//println("pdid=${d.getPatientDid()}  timepoint=${d.getTimePoint()} fractionDose=${d.getFractionDose()}")
         	crb.appendOnStudyTherapyRadiationSite(d.getRadiationSite())
         	crb.appendOnStudyTherapyRadiationNeurosisStatus(d.getNeurosisStatus())
         	crb.appendOnStudyTherapyRadiationFractionDose(d.getFractionDose().toString())
         	crb.appendOnStudyTherapyRadiationFractionNumber(d.getFractionNumber().toString())
         	crb.appendOnStudyTherapyRadiationType(d.getRadiationType())
    	 }
    }
    private void loadReportBeanWithPtSurgData(RembrandtClinicalReportBean crb, List ptSurg) {
    	 ptSurg.each { d ->
           //println("pdid=${d.getPatientDid()}  timepoint=${d.getTimePoint()} procedureTitle=${d.getProcedureTitle()}")
    	   crb.appendOnStudyTherapySurgeryProcedureTitle(d.getProcedureTitle())
    	   crb.appendOnStudyTherapySurgeryIndication(d.getIndication())
    	   crb.appendOnStudyTherapySurgeryHistoDiagnosis(d.getHistoDiagnosis())
    	   crb.appendOnStudyTherapySurgeryOutcome(d.getSurgeryOutcome())
    	 }
    	
    }
     
    
    private void loadReportBeanWithNeuroData(RembrandtClinicalReportBean rptBean, List neuroData) {
      //may want to order the list
      //order by followup month
      RbtNeuroComparator neuroComparator = new RbtNeuroComparator();
      Collections.sort(neuroData, neuroComparator);
            
      neuroData.each { nd ->
         //println("pdid=${nd.getPatientDid()} timepoint=${nd.getTimePoint()}  karnofskyScore=${nd.getKarnofskyScore()}")
         
         rptBean.appendKarnofsky(nd?.getKarnofskyScore().toString())
         rptBean.appendNeurologicalExamOutcome(nd.getNeuroExam().toString())
         rptBean.appendMriDesc(nd.getMriCtScoreDesc())
         rptBean.appendAntiConvulsantStatus(nd.getAntiConvulsantStatus())
         rptBean.appendSteroidDoseStatus(nd.getSteroidDoseStatus())                 
      }
    	    	
    }
    
    private void loadReportBeanWithPatientData(RembrandtClinicalReportBean rptBean, PatientData ptData) {    	
    	rptBean.setSampleId(ptData.getSampleId())    	
    	rptBean.setAgeGroup(ptData.getAgeGroup())
    	rptBean.setSurvivalLengthRange(ptData.getSurvivLengthRange())
    	rptBean.setGender(ptData.getGender())
    	rptBean.setDisease(ptData.getDiseaseType())
    	rptBean.setGrade(ptData.getWhoGrade())
    	rptBean.setRace(ptData.getRace())
    	rptBean.setInstitution(ptData.getInstitutionName())    	    	
 	}    	    
    	
    	
    	
   private Map createPatientDataMap(List dataList) {
       Map dataMap = new HashMap()
       List itemList = null
       dataList.each { d ->
         if (d != null) {
           itemList = dataMap.get(d.getPatientDid()) 
           if (itemList == null) {
             itemList = new ArrayList()
             dataMap.put(d.getPatientDid(), itemList)
           }
    	   itemList.add(d)
         }
       }    	
       return dataMap
   }
    	 
    
    /**
     * This method takes a list of PatientData domain objects and
     * converts it into a list of RembrandtClinicalReportBeans
     */
    private List assembleClinicalData(List patientData, List neuroData, List priorChemoData, List priorRadData, 
    		                          List priorSurgData, List ptChemoData, List ptRadData, List ptSurgData ) {
       Map patientMap = new HashMap()
       RembrandtClinicalReportBean crb
       
       patientData.each { pd ->
    	   crb = patientMap.get(pd.getId().toString())
    	   if (crb == null) {
    	      crb = new RembrandtClinicalReportBean()    	      
    	      patientMap.put(pd.getId().toString(), crb)   
    	      //println("Created crb for pdid=${pd.getId()}")
    	   }
    	   loadReportBeanWithPatientData(crb, pd)
       }
                     
       //load neuroData
       Map neuroDataMap = createPatientDataMap(neuroData)       
       neuroDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         
         if (crb != null) {
           List nd = neuroDataMap.get(pdid)
           loadReportBeanWithNeuroData(crb, nd)
         }
         else {
           logger.warn("Warning: No patient data for pdid=${pdid}")
         }
       
       }
       
       //load priorChemoData 
       Map priorChemoDataMap = createPatientDataMap(priorChemoData)       
       priorChemoDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         if (crb != null) {
        	 List pc = priorChemoDataMap.get(pdid)
        	 loadReportBeanWithPriorChemoData(crb, pc)
         }
         else {
             logger.warn("Warning: No patient data for pdid=${pdid}")
           }
       }
       
       //load priorRadData 
       Map priorRadDataMap = createPatientDataMap(priorRadData)       
       priorRadDataMap.keySet().each{ pdid ->
       	 crb = patientMap.get(pdid.toString())
       	 if (crb != null) {
        	 List pr = priorRadDataMap.get(pdid)
        	 loadReportBeanWithPriorRadData(crb, pr)
       	 }
       	 else {
             logger.warn("Warning: No patient data for pdid=${pdid}")
         }
       }
       
       
       
       //load priorSurgData
       Map priorSurgDataMap = createPatientDataMap(priorSurgData)       
       priorSurgDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         if (crb != null) {
        	 List ps = priorSurgDataMap.get(pdid)
        	 loadReportBeanWithPriorSurgData(crb, ps)
         }
         else {
        	 logger.warn("Warning: No patient data for pdid=${pdid}") 
         }
       }
       
       //load ptChemoData
       Map ptChemoDataMap = createPatientDataMap(ptChemoData)       
       ptChemoDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         if (crb != null) {        	          
           List ptChemo = ptChemoDataMap.get(pdid)
           loadReportBeanWithPtChemoData(crb, ptChemo)
         }
         else {
        	 logger.warn("Warning: No patient data for pdid=${pdid}")
         }
       }
       
       //load ptRadData
       Map ptRadDataMap = createPatientDataMap(ptRadData)       
       ptRadDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         if (crb != null) {
        	 List ptRad = ptRadDataMap.get(pdid)
        	 loadReportBeanWithPtRadData(crb, ptRad)
         }
         else {
        	 logger.warn("Warning: No patient data for pdid=${pdid}")
         }
       }
       
       //load ptSurgData
       Map ptSurgDataMap = createPatientDataMap(ptSurgData)       
       ptSurgDataMap.keySet().each{ pdid ->
         crb = patientMap.get(pdid.toString())
         if (crb != null) {        	         
           List ptSurg = ptSurgDataMap.get(pdid)
           loadReportBeanWithPtSurgData(crb, ptSurg)
         }
         else {
        	 logger.warn("Warning: No patient data for pdid=${pdid}")
         }
       }
       
       return new ArrayList(patientMap.values())
    }
    
    private String getIdString(Set idSet) {
    	int ind = 0;
    	int numIds  = idSet.size()
    	StringBuffer idSB = new StringBuffer()
    	
    	idSB.append("(")
    			
    	idSet.each { id -> 
    	         
    	         if (ind == (numIds-1))  {
    	        	 idSB.append("'${id}'")    	    	        	 
    	         }
    	         else {
    	        	idSB.append("'${id}',")    	    	        	
    	         }
                 ind++
    	}
    	idSB.append(")")
    	return idSB.toString()
    }
    
    /**
     * Get a list of report beans for a list of patientIds
     */
    public List getClinicalData(List patientIds) { 
           	     	    	    	    	    	   
    	    	if ((patientIds == null) || (patientIds.size() == 0)) {
    	    	  logger.warn("Warning no patientIds passed to getClinicalData!")
    	          return Collections.emptyList()
    	    	}
    	    	
    	    	Set idSet = new HashSet(patientIds)
    	    	StringBuffer idSB = new StringBuffer()
    	    	String pdQS = "From gov.nih.nci.cma.domain.rembrandt.PatientData pd where pd.institutionId=8 and pd.id in " 
    	    	String neuroQS = "From gov.nih.nci.cma.domain.rembrandt.NeurologicalEvaluation neuroExam where neuroExam.institutionId=8 and neuroExam.patientDid in "
    	    	String priorChemoQS = "From gov.nih.nci.cma.domain.rembrandt.PriorChemotherapy priorChemo where  priorChemo.institutionId=8 and priorChemo.patientDid in "		
    	    	String priorRadQS = "From gov.nih.nci.cma.domain.rembrandt.PriorRadiationtherapy prad where prad.institutionId=8 and prad.patientDid in "		
    	    	String priorSurgQS = "From gov.nih.nci.cma.domain.rembrandt.PriorSurgery psurg where psurg.institutionId=8 and psurg.patientDid in "		
    	    	String ptChemoQS = 	"From gov.nih.nci.cma.domain.rembrandt.PtChemotherapy ptChemo where ptChemo.institutionId=8 and ptChemo.patientDid in "
    	    	String ptRadQS = "From gov.nih.nci.cma.domain.rembrandt.PtRadiationtherapy ptRad where ptRad.institutionId=8 and ptRad.patientDid in "
    	    	String ptSurgQS = "From gov.nih.nci.cma.domain.rembrandt.PtSurgery ptsurg where ptsurg.institutionId=8 and ptsurg.patientDid in "			
    	    			
    	    	
    	    	String idString = getIdString(idSet)
    	       
    	    	pdQS += idString    	    	  
    	    	neuroQS += idString 
    	    	priorChemoQS += idString
    	    	priorRadQS += idString
    	    	priorSurgQS += idString
    	    	ptChemoQS += idString
    	    	ptRadQS += idString
    	    	ptSurgQS += idString   	    			    	    	
    	    	    	    	
    	    	logger.debug("QueryStr=${pdQS}")
    	    	logger.debug("QueryStr=${neuroQS}")
    	    	logger.debug("QueryStr=${priorChemoQS}")
    	    	logger.debug("QueryStr=${priorRadQS}")
    	    	logger.debug("QueryStr=${priorSurgQS}")
    	    	logger.debug("QueryStr=${ptChemoQS}")
    	    	logger.debug("QueryStr=${ptRadQS}")
    	    	logger.debug("QueryStr=${ptSurgQS}")
    	    	
    	    	
    	    	
    	    	//Get the raw data
    	        List patientData = PatientData.findAll(pdQS)
    	        List neuroData = NeurologicalEvaluation.findAll(neuroQS)
    	        List priorChemoData = PriorChemotherapy.findAll(priorChemoQS)
    	        List priorRadData = PriorRadiationtherapy.findAll(priorRadQS)
	            List priorSurgData = PriorSurgery.findAll(priorSurgQS)
	            List ptChemoData = PtChemotherapy.findAll(ptChemoQS)
	            List ptRadData = PtRadiationtherapy.findAll(ptRadQS)
	            List ptSurgData = PtSurgery.findAll(ptSurgQS)
	        
    	        
    	        logger.debug("Got back patientData numRows=${patientData?.size()}")
    	        
    	        List clinData = assembleClinicalData(patientData, neuroData, priorChemoData, priorRadData, priorSurgData, ptChemoData, ptRadData, ptSurgData );
    	    	    	    	    	 
    	    	//return the data.
    	    	return clinData
    	     	 
    }
     
    def getClinicalData =  { clinicalForm -> 
	    clinicalForm.getParameterNames().each	{
	    	logger.debug(it + ": " + clinicalForm.getParameter(it))
	    }

		String[] sampleGroups = (String[])clinicalForm.getParameterValues("sampleGroup")
		Integer ageAtDxLower = clinicalForm.getParameter("ageAtDxLower")!=null ? Integer.valueOf(clinicalForm.getParameter("ageAtDxLower")) : null
		Integer ageAtDxUpper = clinicalForm.getParameter("ageAtDxUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("ageAtDxUpper")) : null
		String gender = (String)clinicalForm.getParameter("gender")
		Integer survivalLower = clinicalForm.getParameter("survivalLower")!=null ? Integer.valueOf(clinicalForm.getParameter("survivalLower")) : null
		Integer survivalUpper = clinicalForm.getParameter("survivalUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("survivalUpper")) : null 
		String disease = (String)clinicalForm.getParameter("disease")
		  //String grade = request.getParameter("grade")
		String race = (String)clinicalForm.getParameter("race")
		  
		if (sampleGroups != null) {
		  for (int i=0; i < sampleGroups.length; i++) {    		  
			     String groupName = (String) sampleGroups[i]
				 logger.debug("sampleGroup=${groupName}")    		  
		  }      
		}
		  
		logger.debug("ageAtDxLower=${ageAtDxLower}")
		logger.debug("ageAtDxUpper=${ageAtDxUpper}")
	    logger.debug("gender=${gender}")
        logger.debug("survivalLower=${survivalLower}")
	    logger.debug("survivalUpper=${survivalUpper}")
	    logger.debug("disease=${disease}")
	    logger.debug("race=${race}")
	    
	     List groupNames = new ArrayList()
      if ((sampleGroups != null) && (sampleGroups.length > 0)) {
    	  for (int i=0; i < sampleGroups.length ; i++) {
    		 groupNames.add(sampleGroups[i])
    	  }
      }
      else {
    	  groupNames.add("ALL_PATIENTS")
      }
      
      List ids = getIdsForSampleGroups(groupNames)
      
      Set idSet = new HashSet(ids)

      if ((gender != null) && (!gender.equals("ANY"))) {
        List genderIds = getIdsForGender(gender)
        idSet.retainAll(genderIds)
      }
      
      if ((race != null) && (!race.equals("ANY"))) {
        List raceIds = getIdsForRace(race)
        idSet.retainAll(raceIds)    	      	  
      }
      
      if ((ageAtDxLower != null) && (ageAtDxUpper != null)) {
        List ageIds = getIdsForAgeAtDx(ageAtDxLower, ageAtDxUpper)
        idSet.retainAll(ageIds)
      }
      
      if ((survivalLower != null) && (survivalUpper != null)) {
    	  Integer survivalLower_days = survivalLower * 30;
    	  Integer survivalUpper_days = survivalUpper * 30;
          List survivalIds = getIdsForSurvival(survivalLower_days,survivalUpper_days)
          idSet.retainAll(survivalIds)
      }
      
      if ((disease != null) && (!disease.equals("ANY"))) {
        List diseaseIds = getIdsForDiseaseType(disease)
        idSet.retainAll(diseaseIds)
      }
      
      List lookupIds = new ArrayList(idSet)
      
      
      return getClinicalData(lookupIds)
    }
    
    /**
     * Will get the column names for the clinical report
     * When the view is ready this may be generated on the fly 
     * for now just handing back hard coded values
     */
    public List getColumnNames() {
    	 
    	List<String> columns = new ArrayList<String>();
    	
    	columns.add("Sample")
    	columns.add("Age Group (years)")	 
    	columns.add("Gender")
    	columns.add("Survival (months)")	 
    	columns.add("Disease")	 
    	columns.add("Grade")	 
    	columns.add("Race")	 
    	columns.add("Institution")	 
    	columns.add("Karnofsky")	 
    	columns.add("Neurological Exam Outcome")	 
    	columns.add("MRI Desc")	 
    	columns.add("Followup Month")	 
    	columns.add("Steroid Dose Status")	 
    	columns.add("Anti-Convulsant Status")	 
    	columns.add("Prior Therapy Radiation Site")	 
    	columns.add("Prior Therapy Radiation Fraction Dose")	 
    	columns.add("Prior Therapy Radiation Fraction Number")	 
    	columns.add("Prior Therapy Radiation Type")	 
    	columns.add("Prior Therapy Chemo Agent Name")	 
    	columns.add("Prior Therapy Chemo Course Count")	 
    	columns.add("Prior Therapy Surgery Procedure Title")	 
    	columns.add("Prior Therapy Surgery Tumor Histology")	 
    	columns.add("Prior Therapy Surgery Outcome")	 
    	columns.add("OnStudy Therapy Radiation Site")	 
    	columns.add("OnStudy Therapy Radiation Neurosis Status")	 
    	columns.add("OnStudy Therapy Radiation Fraction Dose")
    	columns.add("OnStudy Therapy Radiation Fraction Number")	 
    	columns.add("OnStudy Therapy Radiation Type")	 
    	columns.add("OnStudy Therapy Chemo Agent Name")	 
    	columns.add("OnStudy Therapy Chemo Course Count")	 
    	columns.add("OnStudy Therapy Surgery Procedure Title")	 
    	columns.add("OnStudy Therapy Surgery Indication")	 
    	columns.add("OnStudy Therapy Surgery Histo Diagnosis")	 
    	columns.add("OnStudy Therapy Surgery Outcome")																															
    	return columns
    }
    
    /**
     * This method is hard coded for now with Rembrandt values.  This needs 
     * to be refactored to read permissible values from the DB. 
     */
    public List<String> getPermissibleValues(String paramName) {
    	
    	List<String> values = new ArrayList<String>();
    	     	 
    	if (paramName.equals(RembrandtClinicalKeys.disease)) {
    		values.add("ALL_GLIOMA");
    		values.add("ASTROCYTOMA");
    		values.add("CELL_LINE");
    		values.add("GBM");
    		values.add("MIXED");
    		values.add("NON_TUMOR");
    		values.add("OLIGODENDROGLIOMA");
    		values.add("UNKNOWN");    	
    	}
    	else if (paramName.equals(RembrandtClinicalKeys.gender)) {
    		values.add("M");
    		values.add("F");
    		values.add("O");    		
    	}
    	else if (paramName.equals(RembrandtClinicalKeys.race)) {    		
    		values.add("WHITE")
    		values.add("BLACK")
    		values.add("NATIVE HAWAIIAN")
    		values.add("ASIAN NOS")
    		values.add("OTHER")
    		values.add("UNKNOWN")    		
    	}
    	else if (paramName.equals(RembrandtClinicalKeys.grade)) {
    		values.add("I");
    		values.add("II");
    		values.add("III");
    		values.add("IV");    		    		
    	}
    	
    	return values;
    }
}
