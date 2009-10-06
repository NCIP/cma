  import java.util.Collections;

  import gov.nih.nci.cma.domain.target.TargetClinical;
  import gov.nih.nci.cma.clinical.TARGETClinicalReportBean;
  
  import org.apache.log4j.Logger;

class TARGETClinicalService extends AbstractClinicalService {
	
    boolean transactional = false
    
    private static Logger logger = Logger.getLogger(TARGETClinicalService.class);
		 
    
    /**
     * Extracts a list of unique ids from a list of domain objects
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
	  * Get ids for gender
	  */
	  public List<String> getIdsForGender(String value) {
		  println("getIdsForGender searching gender=" + value)
	      List clinList = TargetClinical.findAllByGenderLike(value)
		  return getIdList(clinList)		    	
	  }
    
	 /**
	  * Get Ids for race
	  */
	  public List<String> getIdsForRace(String value) {
		List clinList = TargetClinical.findAllByRaceLike(value)
		return getIdList(clinList)	 		 
	  }
    
	 /**
	  * Get Ids for ethnicity
	  */
	  public List<String> getIdsForEthnicity(String value) {
		List clinList = TargetClinical.findAllByEthnicityLike(value)
		return getIdList(clinList)	 		 
	  }
    
	 /**
	  * Get Ids for age
	  */
	  public List<String> getIdsForAge(Integer ageLower, Integer ageUpper) {
		List clinList = TargetClinical.findAllByAgeBetween(ageLower, ageUpper)
		return getIdList(clinList)	 		 
	  }
	   	 
	 /**
	  * Get Ids for WBC
	  */
	 public List<String> getIdsForWbc(String value) {
	   List clinList = TargetClinical.findAllByWbcLike(value)
	   return getIdList(clinList)	 		 
	 }
	   	 
	 /**
	  * Get Ids for CNS
	  */
	 public List<String> getIdsForCns(String value) {
	   List clinList = TargetClinical.findAllByCnsLike(value)
	   return getIdList(clinList)	 		 
	 }
	 
	 /**
	  * Get ids for Testicular Involvement
	  */
	  public List<String> getIdsForTesticular(String value) {
		 List clinList = TargetClinical.findAllByTesticularLike(value)
		 return getIdList(clinList)		  		  
	  }
	
	 /**
	  * Get Ids for minimal residual disease day 8
	  */
	 public List<String> getIdsForMrdDay8(String value) {
	   List clinList = TargetClinical.findAllByMrdDay8Like(value)
	   return getIdList(clinList)	 		 
	 }
	 
	 /**
	  * Get Ids for minimal residual disease day 29
	  */
	 public List<String> getIdsForMrdDay29(String value) {
	   List clinList = TargetClinical.findAllByMrdDay29Like(value)
	   return getIdList(clinList)	 		 
	 }
	 
	 /**
	  * Get ids for event
	  */
	 public List<String> getIdsForEvent(String value) {
	   List clinList = TargetClinical.findAllByEventLike(value)
	   return getIdList(clinList)		 
	 }
	 
	 /**
	  * Get ids for years to event
	  */
	 public List<String> getIdsForYearsToEvent(Integer lowerValue, Integer upperValue) {
	   List clinList = TargetClinical.findAllByYearsToEventBetween(lowerValue, upperValue)
	   return getIdList(clinList)		 
	 }
	 
	 /**
	  * Get ids for vital status
	  */
	 public List<String> getIdsForVitalStatus(String value) {
	   List clinList = TargetClinical.findAllByVitalStatusLike(value)
	   return getIdList(clinList)		 
	 }
	 
	 /**
	  * Get ids for years to death
	  */
	 public List<String> getIdsForYearsToDeath(Integer lowerValue, Integer upperValue) {
	   List clinList = TargetClinical.findAllByYearsToDeathBetween(lowerValue, upperValue)
	   return getIdList(clinList)		 
	 }

	 /**
	  * Get ids for trisomies
	  */
	 public List<String> getIdsForTrisomies(String value) {
	   List clinList = TargetClinical.findAllByTrisomiesLike(value)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for mll status
	  */
	 public List<String> getIdsForMllStatus(String value) {
	   List clinList = TargetClinical.findAllByMllStatusLike(value)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for e2aStatus
	  */
	 public List<String> getIdsForE2aStatus(String value) {
	   List clinList = TargetClinical.findAllByE2aStatusLike(value)
	   return getIdList(clinList)			
	 }
	 
	 /**
	  * Get ids for bcrStatus
	  */
	  public List<String> getIdsForBcrStatus(String value) {
		List clinList = TargetClinical.findAllByBcrStatusLike(value)
		return getIdList(clinList)		  		  
	  }
 	 
	 	 		
	/**
	 * Get permissible values for a given parameter
	 */
	public List<String> getPermissibleValues(String paramName) {
		        
          List<String> permValues = new ArrayList();
          
          if (paramName.equals("gender")) {
            permValues.add("MALE");
            permValues.add("FEMALE");        	  
          }
          else if (paramName.equals("event")) {
            permValues.add("Censored");
            permValues.add("Relapse");
            permValues.add("Death");                    	  
          }
          else if (paramName.equals("race")) {
            permValues.add("CHINESE");
            permValues.add("GUAMANIAN, NOS");
            permValues.add("AMERICAN INDIAN, ALEUTIAN, ESKIMO");                    	  
            permValues.add("WHITE");
            permValues.add("ASIAN INDIAN, PAKISTANI");
            permValues.add("OTHER ASIAN, INCL ASIAN NOS AND ORIENTAL NOS");                    	  
            permValues.add("UNKNOWN");                    	  
          }
          else if (paramName.equals("ethnicity")) {
            permValues.add("NON-SPANISH, NON-HISPANIC");
            permValues.add("MEXICAN (INCL CHICANO)");
            permValues.add("OTHER SPECIFIED SPANISH/HISPANIC ORIGIN (INLCUDES EUROPEAN)");                    	  
            permValues.add("SPANISH SURNAME ONLY-NO CONTRARY EVIDENCE THAT THE PT IS NOT HISPANIC");
            permValues.add("SPANISH NOS, HISPANIC NOS, LATINO NOS (BASED ON MORE THAN JUST SURNAME");
            permValues.add("UNKNOWN WHETHER SPANISH ONLY");                    	  
            permValues.add("PUERTO RICAN");                    	  
            permValues.add("SOUTH OR CENTRAL AMERICAN (EXCEPT BRAZIL)");                    	  
          }
          else if (paramName.equals("vitalStatus")) {
            permValues.add("Censored");
            permValues.add("Death");                    	  
          }
          else if (paramName.equals("event")) {
            permValues.add("Censored");
            permValues.add("Relapse");
            permValues.add("Death");                    	  
          }
		  else if (paramName.equals("trisomies_4_10")) {
			permValues.add("Negative");
			permValues.add("Positive");			
	      }
		  else if (paramName.equals("mllStatus")) {
		    permValues.add("Negative");
		    permValues.add("Positive");
	      }
		  else if (paramName.equals("e2aStatus")) {
			permValues.add("Negative");
			permValues.add("Positive");
		  }
		  else if (paramName.equals("bcrStatus")) {
			permValues.add("Negative");
			permValues.add("Positive");
		  }
		  else if (paramName.equals("cns")) {
			permValues.add("No CNS disease"); 
			permValues.add("<5 CSF WBC/ul with blasts on cytospin"); 
			permValues.add(">5 CSF WBC/ul with blasts on cytospin and/or eye involvement, cranial nerve involvement, parenchymal brain involvement"); 
		  }
		  else if (paramName.equals("testicular")) {
			permValues.add("No");
			permValues.add("Yes");
			permValues.add("NA / Unknown");
		  }
		  else if (paramName.equals("day8mrd")) {			 
			  permValues.add("Positive");
			  permValues.add("Negative");
			  permValues.add("Unknown");
		  }
		  else if (paramName.equals("day29mrd")) {
			  permValues.add("Positive");
			  permValues.add("Negative");
			  permValues.add("Unknown");
		  }
		  else if (paramName.equals("wbc")) {
			  permValues.add("10,000-49,999");
			  permValues.add(">= 100,000");
			  permValues.add("< 10,000");
			  permValues.add("50,000-99,999");
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
	
	public TARGETClinicalReportBean getRptBean(TargetClinical cs) { 
	
	  TARGETClinicalReportBean rb = new TARGETClinicalReportBean()
	  
	  rb.setPatientId(cs.patientId);
	  rb.setTargetId(cs.targetId);
	  rb.setGender(cs.gender);
	  rb.setRace(cs.race);
	  rb.setEthnicity(cs.ethnicity);
	  rb.setAge(cs.age);
	  rb.setWbc(cs.wbc);
	  rb.setCns(cs.cns);
	  rb.setTesticular(cs.testicular);
	  rb.setMrdDay8(cs.mrdDay8);
	  rb.setMrdDay29(cs.mrdDay29);
	  rb.setEvent(cs.event);
	  rb.setTimeToEvent(cs.yearsToEvent);
	  rb.setVitalStatus(cs.vitalStatus);
	  rb.setTimeToDeath(cs.yearsToDeath);
	  rb.setTrisomies_4_10(cs.trisomies);
	  rb.setMllStatus(cs.mllStatus);
	  rb.setE2aStatus(cs.e2aStatus);
	  rb.setBcrStatus(cs.bcrStatus);
	  
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
	   String[] sampleGroups = (String[])clinicalForm.getParameterValues("sampleGroups")
	   String gender = (String)clinicalForm.getParameter("gender")	
	   String race = (String)clinicalForm.getParameter("race")	
	   String ethnicity = (String)clinicalForm.getParameter("ethnicity")	
	   Integer ageLower = clinicalForm.getParameter("ageLower")!=null ? Integer.valueOf(clinicalForm.getParameter("ageLower")) : null
	   Integer ageUpper = clinicalForm.getParameter("ageUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("ageUpper")) : null 
	   Integer yteLower = clinicalForm.getParameter("yteLower")!=null ? Integer.valueOf(clinicalForm.getParameter("yteLower")) : null
	   Integer yteUpper = clinicalForm.getParameter("yteUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("yteUpper")) : null 
	   Integer ytdLower = clinicalForm.getParameter("ytdLower")!=null ? Integer.valueOf(clinicalForm.getParameter("ytdLower")) : null
	   Integer ytdUpper = clinicalForm.getParameter("ytdUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("ytdUpper")) : null 
	   String day8mrd  = clinicalForm.getParameter("day8mrd")	   
	   String day29mrd = clinicalForm.getParameter("day29mrd")	    					  
	   String  event = (String)clinicalForm.getParameter("event")
	   String  vitalStatus = (String)clinicalForm.getParameter("vitalStatus")	
	   String  wbc = (String)clinicalForm.getParameter("wbc")	   	   
	   String  trisomies = (String)clinicalForm.getParameter("trisomies")	   	   
	   String  mllStatus = (String)clinicalForm.getParameter("mllStatus")	   	   		   
	   String  e2aStatus = (String)clinicalForm.getParameter("e2aStatus")	   	   		   
	   String  bcrStatus = (String)clinicalForm.getParameter("bcrStatus")	
	   String  cns  = (String)clinicalForm.getParameter("cnsStatus")
	   String  testicular  = (String)clinicalForm.getParameter("testicular")
	   
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
	   
	   if ((race != null) && (!race.equals("ANY"))) {
		    String raceCode = getParamCode(race)
			List raceIds = getIdsForRace(raceCode)
	        idSet.retainAll(raceIds)
	   }
	   
	   if ((ethnicity != null) && (!ethnicity.equals("ANY"))) {
		    String ethnicityCode = getParamCode(ethnicity)
			List ethnicityIds = getIdsForEthnicity(ethnicityCode)
	        idSet.retainAll(ethnicityIds)
	   }
	   
	   if ((wbc != null) && (!wbc.equals("ANY"))) {
		    String wbcCode = getParamCode(wbc)
			List wbcIds = getIdsForWbc(wbcCode)
	        idSet.retainAll(wbcIds)
	   }
	   
	   if ((event != null) && (!event.equals("ANY"))) {
		    String eventCode = getParamCode(event)
			List eventIds = getIdsForEvent(eventCode)
	        idSet.retainAll(eventIds)
	   }
	   
	   if ((vitalStatus != null) && (!vitalStatus.equals("ANY"))) {
		    String vitalStatusCode = getParamCode(vitalStatus)
			List vitalStatusIds = getIdsForVitalStatus(vitalStatusCode)
	        idSet.retainAll(vitalStatusIds)
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
	   
	   if ((cns != null) && (!cns.equals("ANY"))) {
		    String cnsCode = getParamCode(cns)
			List cnsIds = getIdsForCns(cnsCode)
	        idSet.retainAll(cnsIds)
	   }
	   
	   if ((testicular != null) && (!testicular.equals("ANY"))) {
		    String tCode = getParamCode(testicular)
			List tIds = getIdsForTesticular(tCode)
	        idSet.retainAll(tIds)
	   }
	   
	   if ((ageLower != null) && (ageUpper != null)) {
		    //Integer ageLowerDays = ageLower*365;
		    //Integer ageUpperDays = ageUpper*365;
	        List ageIds = getIdsForAge(ageLower, ageUpper)
	        idSet.retainAll(ageIds)
	   }
	   
	   if ((yteLower != null) && (yteUpper != null)) {
	        List yteIds = getIdsForYearsToEvent(yteLower, yteUpper)
	        idSet.retainAll(yteIds)
	   }
	   
	   if ((ytdLower != null) && (ytdUpper != null)) {
	        List ytdIds = getIdsForYearsToDeath(ytdLower, ytdUpper)
	        idSet.retainAll(ytdIds)
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
    	String clinQS = "From gov.nih.nci.cma.domain.target.TargetClinical tc where tc.targetId in ${idStr}"
    	println("getClinicalData clinQS=${clinQS}")
    	List cl = TargetClinical.findAll(clinQS)

    	List rptBeanList = new ArrayList()
    	cl.each { c ->
    	  println("\n\n>>>>>>>>>")
    	  println("\n\nTargetClinical=${c}")
     	  println("\n\n<<<<<<<<<")
    	  rptBeanList.add(getRptBean(c))    		
    	}
    	
    	return rptBeanList
    }
}