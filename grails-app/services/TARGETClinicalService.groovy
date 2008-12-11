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
	
	/**
	 * Get the clinical data given a clinical form.
	 */
	def getClinicalData = { clinicalForm -> 
	
	  return Collections.emptyList();
	}
	
	 /**
	  * Get the cli
	  * nical data for a sample group
	  */
	 public List getClinicalDataForGroup(String groupName) {
		 
		 return Collections.emptyList();
	 }
}