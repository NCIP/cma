class ClinicalService {

    boolean transactional = true

    def serviceMethod() {

    }
    
    
    public List getClinicalData(List patientIds) {
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
