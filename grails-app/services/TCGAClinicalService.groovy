import gov.nih.nci.cma.domain.tcga.ClinicalNew;
import gov.nih.nci.cma.clinical.TCGAClinicalReportBean;
import org.apache.log4j.Logger;

import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import org.springframework.web.context.request.RequestContextHolder;

class TCGAClinicalService {
	
	//gender: ANY
	//karnofskyScoreUpper: 110
	//informedConsentAcquired: yes
	//dodfuMinusDopLower: 0
	//dodMinusDopLower: 0
	//dodfuMinusDopUpper: 110
	//sampleGroup: ALL_PATIENTS
	//tumorTissueSite: ANY
	//vitalStatus: ANY
	//karnofskyScoreLower: 0
	//patientId:
	//dodMinusDopUpper: 110
	//queryName: Q3


    boolean transactional = false
    
    private static Logger logger = Logger.getLogger(TCGAClinicalService.class);

    def serviceMethod() {

    }
    
    
    
    public List getColumnNames() {
       return Collections.emptyList()
    }
    
    public List<String> getPermissibleValues(String paramName) {
       return Collections.emptyList()    	    	
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
          retSet.add(d.getPtid())  
        }
        else {
          logger.warn("getIdList got null domain object.")
        }
      }
      return new ArrayList(retSet)
    }
           
    public List<String> getIdsForKarnofskyScore(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByKarnofskyScoreBetween(lower, upper)
    	return getIdList(clinList)
    }
      
    public List<String> getIdsForDodMinusDop(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodMinusDopBetween(lower, upper)
    	return getIdList(clinList)
    }

   
    public List<String> getIdsForDodfuMinusDop(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodfuMinusDopBetween(lower, upper)
    	return getIdList(clinList)   	
    }
    
    public List<String> getIdsForInformedConsentAcquired(String value) {
    	List clinList = ClinicalNew.findAllByInformedConsentAcquired(value)
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForGender(String gender) {
    	List clinList = ClinicalNew.findAllByGenderLike(gender)
    	return getIdList(clinList)
    }
      
    
    def getClinicalData = { clinicalForm -> 
               
	    clinicalForm.getParameterNames().each	{
	    	println(it + ": " + clinicalForm.getParameter(it))
	    }
	    	    
		String gender = (String)clinicalForm.getParameter("gender")				
		Integer karnofskyLower = clinicalForm.getParameter("karnofskyScoreLower")!=null ? Integer.valueOf(clinicalForm.getParameter("karnofskyScoreLower")) : null
		Integer karnofskyUpper = clinicalForm.getParameter("karnofskyScoreUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("karnofskyScoreUpper")) : null 
		String informedConsentAcquired = (String)clinicalForm.getParameter("informedConsentAcquired")
		String tumorTissueSite = (String) clinicalForm.getParameter("tumorTissueSite")
	    String vitalStatus = (String) clinicalForm.getParameter("vitalStatus")
	    String sampleGroup = (String) clinicalForm.getParameter("sampleGroup")
	    String patientId = (String) clinicalForm.getParameter("patientId")
		Integer dodMinusDopLower = clinicalForm.getParameter("dodMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopLower")) : null
		Integer dodMinusDopUpper = clinicalForm.getParameter("dodMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopUpper")) : null
		Integer dodfuMinusDopLower = clinicalForm.getParameter("dodfuMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopLower")) : null
		Integer dodfuMinusDopUpper = clinicalForm.getParameter("dodfuMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopUpper")) : null
	
				
		List ids = getIdsForSampleGroup(sampleGroup)
		
		println("getIdsForSampleGroup returned numIds=${ids.size()}")
		
		
		Set idSet = new HashSet(ids)
			
		/*
		if ((patientId != null ) && (!patientId.equals("ANY"))) {
			  List idl = new ArrayList()
			  idl.add(patientId)
			  idSet.retainAll(idl)
		}
		*/
					
		if ((gender != null) && (!gender.equals("ANY"))) {
			List genderIds = getIdsForGender(gender)
	        idSet.retainAll(genderIds)
		}
		
		if ((karnofskyLower != null) && (karnofskyUpper != null)) {
		  List kIds = getIdsForKarnofskyScore(karnofskyLower, karnofskyUpper)
		  idSet.retainAll(kIds)
		}
		
		if ((informedConsentAcquired != null) && (!informedConsentAcquired.equals("ANY"))) {
		  List icIds = getIdsForInformedConsentAcquired(informedConsentAcquired)
		  idSet.retainAll(icIds)
		}
		
		if ((tumorTissueSite != null) && (!tumorTissueSite.equals("ANY"))) {
		  List ttsIds = getIdsForTumorTissueSite(tumorTissueSite)
		  idSet.retainAll(ttsIds)
		}
		
        if ((vitalStatus != null) && (!vitalStatus.equals("ANY"))) {
          List vsIds = getIdsForVitalStatus(vitalStatus)
          idSet.retainAll(vsIds)        
        }
        
        if ((dodMinusDopLower != null) && (dodMinusDopUpper != null)) {
          List dodIds = getIdsForDodMinusDop(dodMinusDopLower, dodMinusDopUpper)
          idSet.retainAll(dodids)
        }
        
        if ((dodfuMinusDopLower != null) && (dodfuMinusDopUpper != null)) {
          List dodfids = getIdsForDodfuMinusDop(dodfuMinusDopLower, dodfuMinusDopUpper)
          idSet.retainAll(dodfids)
        }
 		
        List lookupIds = new ArrayList(idSet)
                
        return getClinicalData(lookupIds) 
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
    
    public TCGAClinicalReportBean getRptBean(ClinicalNew cn) {
      TCGAClinicalReportBean rptBean = new TCGAClinicalReportBean()
      rptBean.setPatientId(cn.patientId)
      rptBean.setTumorTissueSite(cn.tumorTissueSite)
      rptBean.setVitalStatus(cn.vitalStatus)
      rptBean.setDob(cn.dob)
      rptBean.setDod(cn.dod)
      rptBean.setLastFollowUp(cn.lastFollowUp)
      rptBean.setFirstProcedure(cn.firstProcedure)
      rptBean.setFirstExam(cn.firstExam)
      rptBean.setKarnofskyScore(cn.karnofskyScore)
      rptBean.setFirstRadiation(cn.firstRadiation)
      rptBean.setDodMinusDop(cn.dodMinusDop)
      rptBean.setDodfuMinusDop(cn.dodfuMinusDop)
      rptBean.setCfId(cn.cfId)
      rptBean.setGender(cn.gender)
      rptBean.setInformedConsentAcquired(cn.informedConsentAcquired)
      rptBean.setPtid(cn.ptid)
      return rptBean
    	
    }
    
    public List getClinicalData(List patientIds) { 
    	
    	if ((patientIds == null) || (patientIds.isEmpty())) {
    	  println("Warning empty list passed to getClinicalData")
    	  return Collections.emptyList()
    	}
    	
    	Set idSet = new HashSet(patientIds)
    	String idStr = getIdString(idSet)
    	String cnQS = "From gov.nih.nci.cma.domain.tcga.ClinicalNew cn where cn.id in ${idStr}"
    	println("getClinicalData cnQS=${cnQS}")
    	List cl = ClinicalNew.findAll(cnQS)
    	List rptBeanList = new ArrayList()
    	cl.each { c ->
    	  rptBeanList.add(getRptBean(c))    		
    	}
    	
    	return rptBeanList
    }
    
    
    public List getIdsForSampleGroup(String sampleGroup) {
    	Set idSet = new HashSet()
    	println("getIdsForSampleGroup sampleGroup=${sampleGroup}")
    	if ((sampleGroup != null) && (sampleGroup.size() > 0)) {      	  
      	    def webRequest= RequestContextHolder.currentRequestAttributes()        	  
            def session = webRequest.session    	 

            UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session);
    	    UserList ul = userListBeanHelper.getUserList(sampleGroup);
    	    logger.debug("Got user list bean !")
    	    java.util.List listItems = ul.getListItems()
    	    listItems.each { li -> 
    	      println("adding item ${li.getName()}")
    	      idSet.add(li.getName())
    	    }    	        	        	  
        }    	    	
    	return new ArrayList(idSet)
    }
    
    
    public List getClinicalDataForGroup(String groupName) {
       List idList = getIdsForSampleGroup(groupName)
       println("getClinicalDataForGroup groupName=${groupName} returned numIds=${idList.size()}")
       return getClinicalData(idList)
    }
   
}
