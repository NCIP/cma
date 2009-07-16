import java.util.Collections;
import gov.nih.nci.cma.domain.target.TargetClinicalStg;
import gov.nih.nci.cma.clinical.TARGETClinicalReportBean;
import org.apache.log4j.Logger;

class TARGETClinicalService extends AbstractClinicalService {
	
    boolean transactional = false
    
    private static Logger logger = Logger.getLogger(TARGETClinicalService.class);
		 
    
    /**
     * Extracts a list of unique ids from a list of domin objects
     */
    private List getIdList(List clinObjs) {
      if (clinObjs == null) {
    	logger.warn("getIdList passed null parameter")
    	return Collections.emptyList()
      }
      Set retSet = new HashSet()
      clinObjs.each { c ->
        if (c != null) {
          retSet.add(c.getTargetId())  
        }
        else {
          logger.warn("getIdList got null domain object.")
        }
      }
      return new ArrayList(retSet)
    }
    
    
	 /**
	  * Get Ids for age
	  */
	  public List<String> getIdsForAge(Integer ageLower, Integer ageUpper) {
		List clinList = TargetClinicalStg.findAllByAgeBetween(ageLower, ageUpper)
		return getIdList(clinList)	 		 
	  }
	   	 
	 /**
	  * Get Ids for WBC
	  */
	 public List<String> getIdsForWBC(Integer wbcLower, Integer wbcUpper) {
	   List clinList = TargetClinicalStg.findAllByPbWbcBetween(wbcLower, wbcUpper)
	   return getIdList(clinList)	 		 
	 }
	
	 /**
	  * Get Ids for minimal residual disease day 8
	  */
	 public List<String> getIdsForMrdDay8(String day8mrdValue) {
	   List clinList = TargetClinicalStg.findAllByMrdDay8Like(day8mrdValue)
	   return getIdList(clinList)	 		 
	 }
	 
	 /**
	  * Get Ids for minimal residual disease day 29
	  */
	 public List<String> getIdsForMrdDay29(String day29mrdValue) {
	   List clinList = TargetClinicalStg.findAllByMrdDay29Like(day29mrdValue)
	   return getIdList(clinList)	 		 
	 }
	 
	 /**
	  * Get ids for event
	  */
	 public List<String> getIdsForEvent(String eventValue) {
	   List clinList = TargetClinicalStg.findAllByEventLike(eventValue)
	   return getIdList(clinList)		 
	 }
	 
	 /**
	  * Get ids for death event
	  */
	 public List<String> getIdsForDeath(String eventValue) {
	   List clinList = TargetClinicalStg.findAllByDeathLike(eventValue)
	   return getIdList(clinList)		 
	 }
	 
	 /**
	  * Get ids for congenitalAbnormality
	  */
	 public List<String> getIdsForCongenitalAbnormality(String congenitalAbnormalityValue) {
	   List clinList = TargetClinicalStg.findAllByCongenitalAbnormalityLike(congenitalAbnormalityValue)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for telStatus
	  */
	 public List<String> getIdsForTelStatus(String telStatus) {
	   List clinList = TargetClinicalStg.findAllByTelStatusLike(telStatus)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for trisomies
	  */
	 public List<String> getIdsForTrisomies(String trisomiesStatus) {
	   List clinList = TargetClinicalStg.findAllByTrisomies_4_10Like(trisomiesStatus)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for mll status
	  */
	 public List<String> getIdsForMllStatus(String mllStatus) {
	   List clinList = TargetClinicalStg.findAllByMllStatusLike(mllStatus)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for e2aStatus
	  */
	 public List<String> getIdsForE2aStatus(String e2aStatus) {
	   List clinList = TargetClinicalStg.findAllByE2aStatusLike(e2aStatus)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for bcrStatus
	  */
	  public List<String> getIdsForBcrStatus(String bcrStatus) {
		List clinList = TargetClinicalStg.findAllByBcrStatusLike(bcrStatus)
		return getIdList(clinList)		  		  
	  }
	 
	  /**
	   * Get ids for bcrStatus
	   */
	   public List<String> getIdsForCns(String cns) {
	     List clinList = TargetClinicalStg.findAllByCnsLike(cns)
		 return getIdList(clinList)		  		  
	   }
	 
	  /**
	   * Get ids for Testicular Status
	   */
	   public List<String> getIdsForTesticularStatus(String testicularStatus) {
		 List clinList = TargetClinicalStg.findAllByTesticularLike(testicularStatus)
		 return getIdList(clinList)		  		  
	   }
	 
	  /**
	   * Get ids for gender
	   */
	   public List<String> getIdsForGender(String gender) {
		  println("getIdsForGender serching gender=" + gender)
	      List clinList = TargetClinicalStg.findAllByGenderLike(gender)
		  return getIdList(clinList)		    	
	   }
 	 
	 	 		
	/**
	 * Get permissible values for a given parameter
	 */
	public List<String> getPermissibleValues(String paramName) {
		        
          List<String> permValues = new ArrayList();
          
          if (paramName.equals("gender")) {
            permValues.add("Male (1)");
            permValues.add("Female (2)");        	  
          }
          else if (paramName.equals("event")) {
            permValues.add("Censored (0)");
            permValues.add("Relapse (1)");
            permValues.add("Second malignant neoplasm (2)");
            permValues.add("Death (3)");                    	  
          }
          else if (paramName.equals("death")) {
        	permValues.add("Censored (0)");
        	permValues.add("Death (1)");        	  
          }
		  else if (paramName.equals("congenitalAbnormality")) {
			permValues.add("None (0)");
			permValues.add("Down Syndrome (1)")
			permValues.add("Other (9)")					
	      }
		  else if (paramName.equals("telStatus")) {
		    permValues.add("Negative (0)");
		    permValues.add("Positive (1)");
		  }
		  else if (paramName.equals("trisomies_4_10")) {
			permValues.add("Negative (0)");
			permValues.add("Positive (1)");			
	      }
		  else if (paramName.equals("mllStatus")) {
		    permValues.add("Negative (0)");
		    permValues.add("Positive (1)");
	      }
		  else if (paramName.equals("e2aStatus")) {
			permValues.add("Negative (0)");
			permValues.add("Positive (1)");
		  }
		  else if (paramName.equals("bcrStatus")) {
			permValues.add("Negative (0)");
			permValues.add("Positive (1)");
		  }
		  else if (paramName.equals("cns")) {
			permValues.add("No CNS disease (1)"); 
			permValues.add("<5 CSF WBC/ul with blasts on cytospin (2)"); 
			permValues.add(">=5 CSF WBC/ul with blasts on cytospin and/or other involvement (3)"); 
		  }
		  else if (paramName.equals("testicular")) {
			permValues.add("No (0)");
			permValues.add("Yes (1)");
			permValues.add("NA / Unknown (2)");
		  }
		  else if (paramName.equals("day8mrd")) {			 
			  permValues.add("MRD Negative (sensitivity = 0.01%) (1)");
			  permValues.add("0.01% < MRD <= 0.1% (2)");
			  permValues.add("0.1% < MRD <= 1.0% ) (3)");
			  permValues.add("1.0% < MRD <= 10.0% (4)");
			  permValues.add("MRD > 10% (5)");
			  permValues.add("MRD Negative (sensitivity = 0.1%) (100)");
			  permValues.add("Indeterminate (999)");			  
		  }
		  else if (paramName.equals("day29mrd")) {
			  permValues.add("MRD Negative (sensitivity = 0.01%) (1)");
			  permValues.add("0.01% < MRD <= 0.1% (2)");
			  permValues.add("0.1% < MRD <= 1.0% ) (3)");
			  permValues.add("1.0% < MRD <= 10.0% (4)");
			  permValues.add("MRD > 10% (5)");
			  permValues.add("MRD Negative (sensitivity = 0.1%) (100)");
			  permValues.add("Indeterminate (999)");
		  }
                                                  		  		 			
          return permValues;
	}
	
	private String getParamCode(String paramValue) {
	  String codeStr = null;
	  String pval = paramValue.trim()
	  if (pval == null) return null;
	  if (paramValue.endsWith(")")) {
	    //strip out the value
	    int ind = pval.lastIndexOf("(") + 1;
	    codeStr = pval.substring(ind, paramValue.length()-1).trim();
	    return codeStr;
	  }
	  return paramValue;
	}
	
	public TARGETClinicalReportBean getRptBean(TargetClinicalStg cs) { 
	  TARGETClinicalReportBean rb = new TARGETClinicalReportBean()
	  rb.setPtId(cs.ptId);
	  rb.setTargetId(cs.targetId);
	  rb.setGender(cs.gender);
	  rb.setGenderStr(cs.genderStr);
	  rb.setNaaccrRace(cs.naaccrRace);
	  rb.setRaceStr(cs.raceStr);
	  rb.setNaaccrEthnicity(cs.naaccrEthnicity);
	  rb.setEthnicityStr(cs.ethnicityStr);
	  rb.setCongenitalAbnormality(cs.congenitalAbnormality);
	  rb.setCongenitalAbStr(cs.congenitalAbStr);
	  
	  //println("getRptBean gorm id=${cs.id} ptId=${cs.ptId} gender=${cs.gender} abnorm=${cs.congenitalAbnormality}")
		 
	  rb.setAge(cs.age);
	  rb.setPbWbc(cs.pbWbc);
	  rb.setCns(cs.cns);
	  rb.setCnsStr(cs.cnsStr);
	  rb.setTesticular(cs.testicular);
	  rb.setTesticularStr(cs.testicularStr);
	  rb.setKaryotype(cs.karyotype);
	  rb.setMrdDay8(cs.mrdDay8);
	  rb.setMrdDay8Str(cs.mrdDay8Str);
	  rb.setMrdDay29(cs.mrdDay29);
	  rb.setMrdDay29Str(cs.mrdDay29Str);
	  rb.setBmaBlastsDay8(cs.bmaBlastsDay8);
	  rb.setBmaBlastsDay29(cs.bmaBlastsDay29);
	  rb.setEvent(cs.event);
	  rb.setEventStr(cs.eventStr);
	  rb.setTimeToEvent(cs.timeToEvent);
	  rb.setDeath(cs.death);
	  rb.setDeathStr(cs.deathStr);
	  rb.setTimeToDeath(cs.timeToDeath);
	  rb.setSiteOfRelapse(cs.siteOfRelapse);
	  rb.setTelStatus(cs.telStatus);
	  rb.setTelStatusStr(cs.telStatusStr);
	  rb.setTrisomies_4_10(cs.trisomies_4_10);
	  rb.setTrisomies_4_10Str(cs.trisomies_4_10Str);
	  rb.setMllStatus(cs.mllStatus);
	  rb.setMllStatusStr(cs.mllStatusStr);
	  rb.setE2aStatus(cs.e2aStatus);
	  rb.setE2aStatusStr(cs.e2aStatusStr);
	  rb.setBcrStatus(cs.bcrStatus);
	  rb.setBcrStatusStr(cs.bcrStatusStr);
	  rb.setDnaIndex(cs.dnaIndex);
	  return rb;
	  	  	  	  	  	  
	}
	
	/**
	 * Get the clinical data given a clinical form.
	 */
	def getClinicalData = { clinicalForm -> 
	   //List clinBeans = new ArrayList();
	   //List clinData = TargetClinicalStg.findAll()	   
	   //println("Call to clinData.findAll returned numItems=${clinData.size()}")
	   
	   clinicalForm.getParameterNames().each	{
	    	println(it + ": " + clinicalForm.getParameter(it))
	   }
	   	  	   
	   //Get the parameter values
	   String[] sampleGroups = (String[])clinicalForm.getParameterValues("sampleGroup")
	   String gender = (String)clinicalForm.getParameter("gender")	
	   Integer ageLower = clinicalForm.getParameter("ageLower")!=null ? Integer.valueOf(clinicalForm.getParameter("ageLower")) : null
	   Integer ageUpper = clinicalForm.getParameter("ageUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("ageUpper")) : null 
	   Integer wbcLower = clinicalForm.getParameter("wbcLower")!=null ? Integer.valueOf(clinicalForm.getParameter("wbcLower")) : null
	   Integer wbcUpper = clinicalForm.getParameter("wbcUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("wbcUpper")) : null 
	   String day8mrd  = clinicalForm.getParameter("day8mrd")	   
	   String day29mrd = clinicalForm.getParameter("day29mrd")	    					  
	   String  event = (String)clinicalForm.getParameter("event")
	   String  death = (String)clinicalForm.getParameter("death")	
	   String  congenitalAbnormality = (String)clinicalForm.getParameter("congenitalAbnormality")
	   String  telStatus = (String)clinicalForm.getParameter("telStatus")	   
	   String  trisomies = (String)clinicalForm.getParameter("trisomies")	   	   
	   String  mllStatus = (String)clinicalForm.getParameter("mllStatus")	   	   		   
	   String  e2aStatus = (String)clinicalForm.getParameter("e2aStatus")	   	   		   
	   String  bcrStatus = (String)clinicalForm.getParameter("bcrStatus")	
	   String  cnsStatus  = (String)clinicalForm.getParameter("cnsStatus")
	   String  testicularStatus  = (String)clinicalForm.getParameter("testicularStatus")
	   
	   Set idSet = new HashSet()
	   	   	   
	   List groupNames = new ArrayList()
	   
       if ((sampleGroups != null) && (sampleGroups.length > 0)) {
         for (int i=0; i < sampleGroups.length ; i++) {
    		 groupNames.add(sampleGroups[i])
    	 }
    	 List ids = getIdsForSampleGroups(groupNames)    	  
    	 idSet.addAll(ids)    	     	     	     	
       }
	   
	   if ((gender != null) && (!gender.equals("ANY"))) {
		    String genderCode = getParamCode(gender)
			List genderIds = getIdsForGender(genderCode)
	        idSet.retainAll(genderIds)
	   }
	   
	   if ((event != null) && (!event.equals("ANY"))) {
		    String eventCode = getParamCode(event)
			List eventIds = getIdsForEvent(eventCode)
	        idSet.retainAll(eventIds)
	   }
	   
	   if ((death != null) && (!death.equals("ANY"))) {
		    String deathCode = getParamCode(death)
			List deathIds = getIdsForDeath(deathCode)
	        idSet.retainAll(deathIds)
	   }
	   
	   if ((congenitalAbnormality != null) && (!congenitalAbnormality.equals("ANY"))) {
		    String caCode = getParamCode(congenitalAbnormality)
			List caIds = getIdsForCongenitalAbnormality(caCode)
	        idSet.retainAll(caIds)
	   }
	   
	   if ((telStatus != null) && (!telStatus.equals("ANY"))) {
		    String tsCode = getParamCode(telStatus)
			List tsIds = getIdsForTelStatus(tsCode)
	        idSet.retainAll(tsIds)
	   }
	   
	   if ((trisomies != null) && (!trisomies.equals("ANY"))) {
		    String trCode = getParamCode(trisomies)
			List trIds = getIdsForTrisomies(trCode)
	        idSet.retainAll(trIds)
	   }
	   
	   if ((mllStatus != null) && (!mllStatus.equals("ANY"))) {
		    String mllCode = getParamCode(mllStatus)
			List mllIds = getIdsForMllStatus(mllCode)
	        idSet.retainAll(mllIds)
	   }
	   
	   if ((e2aStatus != null) && (!e2aStatus.equals("ANY"))) {
		    String e2aCode = getParamCode(e2aStatus)
			List e2aIds = getIdsForE2aStatus(e2aCode)
	        idSet.retainAll(e2aIds)
	   }
	   
	   if ((bcrStatus != null) && (!bcrStatus.equals("ANY"))) {
		    String bcrCode = getParamCode(bcrStatus)
			List bcrIds = getIdsForBcrStatus(bcrCode)
	        idSet.retainAll(bcrIds)
	   }
	   
	   if ((cnsStatus != null) && (!cnsStatus.equals("ANY"))) {
		    String cnsCode = getParamCode(cnsStatus)
			List cnsIds = getIdsForCns(cnsCode)
	        idSet.retainAll(cnsIds)
	   }
	   
	   if ((testicularStatus != null) && (!testicularStatus.equals("ANY"))) {
		    String tsCode = getParamCode(testicularStatus)
			List tsIds = getIdsForTesticularStatus(tsCode)
	        idSet.retainAll(tsIds)
	   }
	   
	   if ((ageLower != null) && (ageUpper != null)) {
		    Integer ageLowerDays = ageLower*365;
		    Integer ageUpperDays = ageUpper*365;
	        List ageIds = getIdsForAge(ageLowerDays, ageUpperDays)
	        idSet.retainAll(ageIds)
	   }
	   
	   if ((wbcLower != null) && (wbcUpper != null)) {
	        List wbcIds = getIdsForWBC(wbcLower, wbcUpper)
	        idSet.retainAll(wbcIds)
	   }
	   
	   if ((day8mrd != null) && (!day8mrd.equals("ANY"))) {
		    String day8mrdCode = getParamCode(day8mrd)
	        List day8mrdIds = getIdsForMrdDay8(day8mrdCode)
	        idSet.retainAll(day8mrdIds)
	   }
	   
	   if ((day29mrd != null) && (!day29mrd.equals("ANY"))) {
		    String day29mrdCode = getParamCode(day29mrd)
	        List day29mrdIds = getIdsForMrdDay29(day29mrdCode)
	        idSet.retainAll(day29mrdIds)
	   }
	   
	   
//	   TARGETClinicalReportBean rb;
//	   clinData.each { cd ->
//		 rb = getRptBean(cd)
//		 clinBeans.add(rb)
//	   }

	   List lookupIds = new ArrayList(idSet)
	   
	   return getClinicalData(lookupIds)
	   	   	   
	}
	
	 /**
	  * Get the clinical data for a sample group
	  */
	 public List getClinicalDataForGroup(String groupName) {
		 
		 return Collections.emptyList();
	 }
	
	 /**
	  * Get clinical data for a list of patient ids
	  */
	 public List getClinicalData(List patientIds) { 
    	
    	if ((patientIds == null) || (patientIds.isEmpty())) {
    	  println("Warning empty list passed to getClinicalData")
    	  return Collections.emptyList()
    	}
    	
    	Set idSet = new HashSet(patientIds)
    	String idStr = getIdString(idSet)
    	String clinQS = "From gov.nih.nci.cma.domain.target.TargetClinicalStg tc where tc.targetId in ${idStr}"
    	println("getClinicalData clinQS=${clinQS}")
    	List cl = TargetClinicalStg.findAll(clinQS)
    	List rptBeanList = new ArrayList()
    	cl.each { c ->
    	  rptBeanList.add(getRptBean(c))    		
    	}
    	
    	return rptBeanList
    }
}