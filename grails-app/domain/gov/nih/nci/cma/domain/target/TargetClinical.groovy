package gov.nih.nci.cma.domain.target

class TargetClinical {
		String[] sampleGroups
		String patientId  
		String targetId                
		String gender    
		String race
		String ethnicity
		Integer age 
		String wbc
		String cns 
		String testicular 
		String mrdDay8
		String mrdDay29 
		String event
		Integer yearsToEvent
		String vitalStatus 
		Integer yearsToDeath 
		String trisomies
		String mllStatus 
		String e2aStatus 
		String bcrStatus 
		
    	static transients = ["sampleGroups"]

		static mapping = {
		    table 'target_clinical'
		    columns {
		        patientId   			column:'PATIENT_ID'
		        targetId   				column:'TARGET_ID'
		        gender       			column:'GENDER'
		        race       				column:'RACE'
		        ethnicity               column:'ETHNICITY'
		        age               		column:'AGE_AT_DIAGNOSIS'
		        wbc      				column:'WBC_AT_DIAGNOSIS'
		        cns              		column:'CNS_STATUS_AT_DIAGNOSIS'
		        testicular    			column:'TESTICULAR_INVOLVEMENT'
		        mrdDay8 				column:'MRD_DAY8'
		        mrdDay29 				column:'MRD_DAY29'
		        event			 		column:'FIRST_EVENT'
		        yearsToEvent 			column:'YEARS_TO_EVENT'
		        vitalStatus 			column:'VITAL_STATUS'
		        yearsToDeath 			column:'YEARS_TO_DEATH'
		        trisomies	 			column:'TRISOMIES_4_10_STATUS'
		        mllStatus	 			column:'MLL_STATUS'
		        e2aStatus	 			column:'E2A_PBX1_STATUS'
		        bcrStatus	 			column:'BCR_ABL_STATUS'
		    }
		}

		/**
		  PATIENT_ID              VARCHAR2(20 BYTE),
		  TARGET_ID               VARCHAR2(20 BYTE),
		  GENDER                  VARCHAR2(20 BYTE),
		  RACE                    VARCHAR2(100 BYTE),
		  ETHNICITY               VARCHAR2(150 BYTE),
		  AGE_AT_DIAGNOSIS        VARCHAR2(20 BYTE),
		  WBC_AT_DIAGNOSIS        VARCHAR2(20 BYTE),
		  CNS_STATUS_AT_DIAGNOSIS VARCHAR2(250 BYTE),
		  TESTICULAR_INVOLVEMENT  VARCHAR2(20 BYTE),
		  MRD_DAY8                VARCHAR2(20 BYTE),
		  MRD_DAY29               VARCHAR2(20 BYTE),
		  FIRST_EVENT             VARCHAR2(20 BYTE),
		  YEARS_TO_EVENT          NUMBER(5,3),
		  TIME_TO_DEATH           NUMBER(5,3),
		  VITAL_STATUS            VARCHAR2(20 BYTE),
		  TRISOMIES_4_10_STATUS   VARCHAR2(20 BYTE),
		  MLL_STATUS              VARCHAR2(20 BYTE),
		  E2A_STATUS              VARCHAR2(20 BYTE),
		  BCR_STATUS              VARCHAR2(20 BYTE)
		 */
}
