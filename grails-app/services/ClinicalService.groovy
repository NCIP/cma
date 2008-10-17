import gov.nih.nci.cma.domain.CmaRembClin
import gov.nih.nci.cma.util.ClinParmsComparator
import gov.nih.nci.cma.clinical.RembrandtClinicalKeys
import gov.nih.nci.cma.clinical.RembrandtClinicalReportBean

class ClinicalService {

    boolean transactional = true
    
    def cmaRembClin 
    def parmsComparator = new ClinParmsComparator()

    //def co = new CmaRembClin()
    
    def serviceMethod() {

    }
    
    
    def getClinicalData =  { request -> 
    
         //request is the http request
         //request.getParameter()
         //request.getParameterValues() array of strings
    	
    	
    }
    
    
    private String getIntStr(Integer theValue) {
      if (theValue == null) {
         return null;
      }
      
      return theValue.toString();
      
    }
    
    public RembrandtClinicalReportBean loadReportBean(List sampleData) {
      
    	//groovy switch statement
    	String parm = null;
    	RembrandtClinicalReportBean rb = new RembrandtClinicalReportBean()
    	sampleData.each  { sd -> 
    	  rb.setSampleId(sd.getSampleId())
    	  parm = sd.getParm()
    	  switch (parm) {    	  
    	  case "AGE" :
    		  rb.setAgeAtDx(getIntStr(sd.getParmNumValue()))
    		  break
    	  case 'INSTITUTION_NAME' :
    		  rb.setInstitution(sd.getParmCharValue())
    		  break
    	  case 'FOLLOWUP_MONTH' :
    	      rb.setFollowupMonth(sd.getParmCharValue())
    	      break
    	  case 'P_FRACTION_NUMBER' :
    	      rb.setPriorTherapyRadiationFractionNumber(getIntStr(sd.getParmNumValue()))
    	      break
    	  case 'C_PROCEDURE_TITLE' :
    	      rb.setPriorTherapySurgeryProcedureTitle(sd.getParmCharValue())
    	      break
    	  case 'DISEASE_TYPE' :
    	      rb.setDisease(sd.getParmCharValue())
    	      break    	      
    	  case 'ANTI_CONVULSANT_STATUS' :
    	      rb.setAntiConvulsantStatus(sd.getParmCharValue())
    	      break    	      
    	  case 'C_FRACTION_NUMBER' :
    	     rb.setOnStudyTherapyRadiationFractionNumber(getIntStr(sd.getParmNumValue()))    	  
    	     break
    	  
    	  case 'C_NEUROSIS_STATUS' :
    	     rb.setOnStudyTherapyRadiationNeurosisStatus(sd.getParmCharValue())    	  
    	     break
    	  case 'P_COURSE_COUNT' :
    	     rb.setPriorTherapyChemoCourseCount(getIntStr(sd.getParmNumValue()))
    	     break
    	  case 'C_HISTO_DIAGNOSIS' :
    	     rb.setOnStudyTherapySurgeryHistoDiagnosis(sd.getParmCharValue())
    	     break
    	  case 'P_RADIATION_TYPE' :
    	     rb.setPriorTherapyRadiationType(sd.getParmCharValue())
    	     break    	     
    	  case 'P_SURGERY_OUTCOME' :
     	     rb.setPriorTherapySurgeryOutcome(sd.getParmCharValue())
    	     break
    	  case 'C_AGENT_NAME' :
    	     rb.setOnStudyTherapyChemoAgentName(sd.getParmCharValue())
    	     break    	     
    	  case 'SURVIVAL_LENGTH' :
    	     rb.setSurvivalMonths(getIntStr(sd.getParmNumValue()))
    	     break
    	  case 'RACE' :
    	     rb.setRace(sd.getParmCharValue())
    	     break    	     
    	  case 'NEURO_EXAM_DESC' :
    	    rb.setNeurologicalExamOutcome(sd.getParmCharValue())
    	    break
    	  case 'STEROID_DOSE_STATUS' :
    	    rb.setSteroidDoseStatus(sd.getParmCharValue())
    	    break
    	  case 'C_FRACTION_DOSE' :
    	    rb.setOnStudyTherapyRadiationFractionDose(sd.getParmCharValue())
    	    break
    	  case 'C_RADIATION_SITE' :
    	    rb.setOnStudyTherapyRadiationSite(sd.getParmCharValue())
    	    break
    	  case 'C_RADIATION_TYPE' :
    	    rb.setOnStudyTherapyRadiationType(sd.getParmCharValue())
    	    break
    	  case 'P_RADIATION_SITE' :
    	    rb.setPriorTherapyRadiationSite(sd.getParmCharValue())
    	    break
    	  case 'P_TUMOR_HISTOLOGY' :
    	    rb.setPriorTherapySurgeryTumorHistology(sd.getParmCharValue())
    	    break    	      	      	      	      	      	      	      	      	      	      	      	
    	  case 'C_COURSE_COUNT' :
    	    rb.setOnStudyTherapyChemoCourseCount(getIntStr(sd.getParmNumValue()))
    	  	break
    	  case 'C_SURGERY_OUTCOME' :
    	    rb.setOnStudyTherapySurgeryOutcome(sd.getParmCharValue())
    	  	break
    	  case 'AGE_GROUP' :
    	  	break
    	  case 'GENDER' :
    	    rb.setGender(sd.getParmCharValue())
    	  	break
    	  case 'SURVIV_LENGTH_RANGE' :
    	    rb.setSurvivalLengthRange(sd.getParmCharValue())
    	  	break
    	  case 'KARNOFSKY_SCORE' :
    	    rb.setKarnofsky(getIntStr(sd.getParmNumValue()))
    	  	break
    	  case 'MRI_CT_SCORE_DESC' :
    	    rb.setMriDesc(sd.getParmCharValue())
    	  	break
    	  case 'P_FRACTION_DOSE' :
    	    rb.setPriorTherapyRadiationFractionDose(getIntStr(sd.getParmNumValue()))
    	  	break
    	  case 'P_AGENT_NAME' :
    	    rb.setPriorTherapyChemoAgentName(sd.getParmCharValue())
    	  	break
    	  case 'P_PROCEDURE_TITLE' :
    	    rb.setPriorTherapySurgeryProcedureTitle(sd.getParmCharValue())
    	  	break
    	  case 'C_INDICATION' :
    	    rb.setOnStudyTherapySurgeryIndication(sd.getParmCharValue())
    	  	break
    	  default: 
    		 System.out.println("Error: unrecognized clinical parm value")    		     		     	      	      	      	      	      	  
    	  }    	
    	}
    	return rb
    }
    
    public List assembleClinicalData(List rawData) {
    
       Map sampleMap = new HashMap()
       List sampleData
       List reportBeanList = new ArrayList()
    	
       
       rawData.each{ d -> 
          //System.out.println(d.toString());
          sampleData = sampleMap.get(d.sampleId)
          if (sampleData == null) {
             sampleData = new ArrayList()
             sampleMap.put(d.sampleId, sampleData)
          }   
          sampleData.add(d)
       }
       
       //order the lists by parm
       for (sampleId in sampleMap.keySet()) {
    	  sampleData = sampleMap.get(sampleId)
    	  Collections.sort(sampleData, parmsComparator)    	   
       }
       
       //now print out the data
       System.out.println("==== RAW ORGANIZED DATA ===")
       for (sampleId in sampleMap.keySet()) {
    	  sampleData = sampleMap.get(sampleId)
    	  sampleData.each { d -> 
    	      System.out.println(d)
    	  }    	       	   
       }
       
       //Create list of Rembrandt clinical report beans
       RembrandtClinicalReportBean rptBean = null
       for (sampleId in sampleMap.keySet()) {
    	  sampleData = sampleMap.get(sampleId)
    	  rptBean = loadReportBean(sampleData)
    	  reportBeanList.add(rptBean)    	      	  
       }
       
       
       return reportBeanList;
    }
    
    
    public List getClinicalData(List patientIds) {
    	
    	/*
    	List names = new ArrayList();
    	names.add("Izi");
    	names.add("Fritz");
    	Query q = sess.createQuery("from DomesticCat cat where cat.name in (:namesList)");
    	q.setParameterList("namesList", names);
    	List cats = q.list();
    	*/
    	
    	
    	String queryStr = "From gov.nih.nci.cma.domain.CmaRembClin rc where rc.sampleId in (" 
    	
        int ind = 0;
    	int numIds  = patientIds.size()
    			
    	patientIds.each { id -> 
    	         
    	         if (ind == (numIds-1))  {
    	        	 queryStr += "'${id}')"
    	         }
    	         else {
    	        	queryStr += "'${id}'," 
    	         }
                 ind++
    	}
    	
    	
    	System.out.println("QueryStr=${queryStr}")
    	
        List rawClinData = CmaRembClin.findAll(queryStr)
        
        System.out.println("Got back rawClinData numRows=${rawClinData?.size()}")
        
        List clinData = assembleClinicalData(rawClinData);
    	
    	
    	    	
    	//return the data.
    	return clinData
    	
 
    	
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
    		values.add("Male");
    		values.add("Female");
    		values.add("Other");    		
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
    
    public List getClinicalDataDummy(List patientIds) {
    	List clinicalData = new ArrayList()
    	List r1 = new ArrayList() //row 1 of dummy data
    	List r2 = new ArrayList() //row 2 of dummy data
    	
    	//row 1
    	r1.add("E007")	 
    	r1.add("65-69")	 
    	r1.add("M")	 
    	r1.add("12-18M")	 
    	r1.add("GBM")	 
    	r1.add("--")	 
    	r1.add("WHITE")	 
    	r1.add("DANA-FARBER CANCER INSTITUTE")	
    	r1.add("80,60")		 
    	r1.add("POSSIBLY WORSE") 	 
    	r1.add("DEFINITELY WORSE (PD)") 	
    	r1.add("0,5")	 
    	r1.add("STABLE") 	 
    	r1.add("YES") 	 
    	r1.add("--")	
    	r1.add("6300")	
    	r1.add("35")	 
    	r1.add("PHOTON") 	 
    	r1.add("TEMOZOLOMIDE") 	
    	r1.add("1")
    	r1.add("CRANIOTOMY OPEN RESECTION") 	 
    	r1.add("GLIOBLASTOMA MULTIFORME") 	 
    	r1.add("CR - COMPLETE RESECTION") 	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("THALIDOMIDE, CELEBREX(CELECOXIB), CYCLOPHOSPHAMIDE, ETOPOSIDE") 	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")	 
    	r1.add("--")
    	clinicalData.add(r1)
    	
    	//row 2
    	r2.add("E009")	 
    	r2.add("40-44")	 
    	r2.add("M")	 
    	r2.add(">60M")	 
    	r2.add("ASTROCYTOMA")	 
    	r2.add("--")	 
    	r2.add("WHITE")	 
    	r2.add("UCSF")	
    	r2.add("70,70")	 
    	r2.add("DEFINITELY WORSE") 	 
    	r2.add("DEFINITELY WORSE (PD)") 	
    	r2.add("0,6") 
    	r2.add("INCREASE") 	 
    	r2.add("NO") 	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("THALIDOMIDE	 13-CIS RETINOIC ACID	 TEMOZOLOMIDE	 BCNU") 	
    	r2.add("2, 1, 1, 2")	 
    	r2.add("--")	 
    	r2.add("ANAPLASTIC ASTROCYTOMA") 	 
    	r2.add("CR - COMPLETE RESECTION") 	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("--")	 
    	r2.add("--")
    	r2.add("CARBOPLATIN	 ETOPOSIDE") 	 
    	r2.add("--")	 
    	r2.add("--")
    	r2.add("--")	
    	r2.add("--")	 
    	r2.add("--")																						
    	clinicalData.add(r2)
    	
    	
    	return clinicalData
    }
    
    /**
     * Will get the column names for the clinical report
     * When the view is ready this may be generated on the fly 
     * for now just handing back hard coded values
     */
    public List getColumnNames() {
    	 
    	List<String> columns = new ArrayList<String>();
    	
    	columns.add("Sample")
    	columns.add("Age at Dx (years)")	 
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
    
    
    
}
