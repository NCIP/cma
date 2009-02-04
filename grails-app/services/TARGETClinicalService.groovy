import java.util.Collections;
import gov.nih.nci.cma.domain.target.TargetClinicalStg;
import gov.nih.nci.cma.clinical.TARGETClinicalReportBean;


class TARGETClinicalService {
	
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
                                                  		  		 			
          return permValues;
	}
	
	public TARGETClinicalReportBean getRptBean(TargetClinicalStg cs) { 
	  TARGETClinicalReportBean rb = new TARGETClinicalReportBean()
	  rb.setPtId(cs.ptId);
	  rb.setTargetId(cs.targetId);
	  rb.setGender(cs.gender);
	  rb.setNaaccrRace(cs.naaccrRace);
	  rb.setNaaccrEthnicity(cs.naaccrEthnicity);
	  rb.setCongenitalAbnormality(cs.congenitalAbnormality);
	  
	  //println("getRptBean gorm id=${cs.id} ptId=${cs.ptId} gender=${cs.gender} abnorm=${cs.congenitalAbnormality}")
		 
	  
	  rb.setAge(cs.age);
	  rb.setPbWbc(cs.pbWbc);
	  rb.setCns(cs.cns);
	  rb.setTesticular(cs.testicular);
	  rb.setKaryotype(cs.karyotype);
	  rb.setMrdDay8(cs.mrdDay8);
	  rb.setMrdDay29(cs.mrdDay29);
	  rb.setBmaBlastsDay8(cs.bmaBlastsDay8);
	  rb.setBmaBlastsDay29(cs.bmaBlastsDay29);
	  rb.setEvent(cs.event);
	  rb.setTimeToEvent(cs.timeToEvent);
	  rb.setDeath(cs.death);
	  rb.setTimeToDeath(cs.timeToDeath);
	  rb.setSiteOfRelapse(cs.siteOfRelapse);
	  rb.setTelStatus(cs.telStatus);
	  rb.setTrisomies_4_10(cs.trisomies_4_10);
	  rb.setMllStatus(cs.mllStatus);
	  rb.setE2aStatus(cs.e2aStatus);
	  rb.setBcrStatus(cs.bcrStatus);
	  rb.setDnaIndex(cs.dnaIndex);
	  return rb;
	  	  	  	  	  	  
	}
	
	/**
	 * Get the clinical data given a clinical form.
	 */
	def getClinicalData = { clinicalForm -> 
	   List clinBeans = new ArrayList();
	   List clinData = TargetClinicalStg.findAll()
	   
	   println("Call to clinData.findAll returned numItems=${clinData.size()}")
	   
	   TARGETClinicalReportBean rb;
	   clinData.each { cd ->
		 rb = getRptBean(cd)
		 clinBeans.add(rb)
	   }
	   	   	    
	   return clinBeans
	
	}
	
	 /**
	  * Get the cli
	  * nical data for a sample group
	  */
	 public List getClinicalDataForGroup(String groupName) {
		 
		 return Collections.emptyList();
	 }
}