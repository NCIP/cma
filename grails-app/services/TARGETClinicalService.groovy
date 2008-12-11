import java.util.Collections;


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
	  rb.setPtId(cs.getPtId());
	  rb.setTargetID(cs.getTargetId());
	  rb.setGender(cs.getGender());
	  rb.setNaaccrRace(cs.getNaaccrRace());
	  rb.setNaaccrEthnicity(cs.getNaaccrEthnicity());
	  rb.setCongenitalAbnormality(cs.getCongenitalAbnormality());
	  rb.setAge(cs.getAge());
	  rb.setPbWbc(cs.getPbWbc());
	  rb.setCns(cs.getCns());
	  rb.setTesticular(cs.getTesticular());
	  rb.setKaryotype(cs.getkaryotype());
	  rb.setMrdDay8(cs.getMrdDay8());
	  rb.setMrdDay29(cs.getMrdDay29());
	  rb.setBmaBlastsDay8(cs.getBmaBlastsDay8());
	  rb.setBmaBlastsDay29(cs.getBmaBlastsDay29());
	  rb.setEvent(cs.getEvent());
	  rb.setTimeToEvent(cs.getTimeToEvent());
	  rb.setDeath(cs.getDeath());
	  rb.setTimeToDeath(cs.getTimeToDeath());
	  rb.setSiteOfRelapse(cs.getSiteOfRelapse());
	  rb.setTelStatus(cs.getTelStatus());
	  rb.setTrisomies_4_10(cs.getTrisomies_4_10());
	  rb.setMllStatus(cs.getMllStatus());
	  rb.setE2aStatus(cs.getE2aStatus());
	  rb.setBcrStatus(cs.getBcrStatus());
	  rb.setDnaIndex(cs.getDnaIndex());
	  return rb;
	  	  	  	  	  	  
	}
	
	/**
	 * Get the clinical data given a clinical form.
	 */
	def getClinicalData = { clinicalForm -> 
	   List clinBeans = new ArrayList();
	   List clinData = TargetClinicalStg.list();
	   
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