package gov.nih.nci.cma.domain.tcgaovarian

class ClinicalOvarian {
	
		String[] sampleGroups
		String patientId
		String tumorTissueSite
		//String priorGlioma
		String vitalStatus
		Date dob
		Date dod
		Date lastFollowUp
		String race
		//String smokingHistory
		//String alcoholConsumption
		//String experimentalExposure
		String gender
		String informedConsentAcquired
		//String histologicalType
		//String bcrSiteId
		//String revision
		//String pretreatmentTherapy
		//String radiationTherapy
		Date initPathologicDxDate
		String initPathologicDxMethod
		String chemotherapy
		String immunotherapy
		//String hormonalTherapy
		String targetedMolecularTherapy
		Date tumorPrgr
		Date tumorRecur
		//String ethnicity
		String additionalRadiationTherapy
		String additionalChemotherapy
		String additionalImmunotherapy
		String additionalHormoneTherapy
		String additionalDrugTherapy
		String anatomicOrganSubdivision
		String personNeoplasmStatus
		String siteOfTumorFirstRecurrence
		String tumorStage
		String tumorGrade
		String tumorResidualDisease
		String primaryTherapyOutcomeSuccess
		String jewishOrigin
		Date surProcPrfm
		String uncOsEvent
		String uncOsDuration
		Integer dodfuMinusDx
		Integer dodMinusDx
		Integer dodMinusDop
		Integer dodfuMinusDop
		String ptid

		static mapping = {
		    table 'clinical_sft'
		    id column:'ptid'
		    columns {
		        patientId 						column:'BCRPATIENTBARCODE'
		        tumorTissueSite   				column:'TUMORTISSUESITE'
		        //priorGlioma       			column:'PRIORGLIOMA'
		        vitalStatus       				column:'VITALSTATUS'
		        dob               				column:'DOB'
		        dod               				column:'DOD'
		        lastFollowUp      				column:'LAST_FLUP_DT'
		        race              				column:'RACE'
		        //smokingHistory    			column:'SMOKINGHISTORY'
		        //alcoholConsumption 			column:'ALCOHOLCONSUMPTION'
		        //environmentalExposure 		column:'ENVIRONMENTALEXPOSURE'
		        gender 							column:'GENDER'
		        informedConsentAcquired 		column:'INFORMEDCONSENTACQUIRED'
		        //histologicalType 				column:'HISTOLOGICALTYPE'
		        //bcrSiteId 					column:'BCRSITEID'
		        //revision 						column:'REVISION'
		        //pretreatmentHistory 			column:'PRETREATMENTHISTORY'
		        //radiationTherapy 				column:'RADIATIONTHERAPY'
		        initPathologicDxMethod		 	column:'INIT_PATHOLOGIC_DX_METHOD'
		        initPathologicDxDate		 	column:'INIT_PATH_DX_DT'
		        chemotherapy 					column:'CHEMOTHERAPY'
		        immunotherapy 					column:'IMMUNOTHERAPY'
		        targetedMolecularTherapy 		column:'TARGETEDMOLECULARTHERAPY'
		        tumorRecur 						column:'TUMOR_RECUR_DT'
		        tumorPrgr 						column:'TUMOR_PRGR_DT'
		        //ethnicity 					column:'ETHNICITY'
		        additionalRadiationTherapy 		column:'ADDITIONALRADIATIONTHERAPY'
		        additionalChemotherapy 			column:'ADDITIONALCHEMOTHERAPY'
		        additionalImmunotherapy 		column:'ADDITIONALIMMUNOTHERAPY'
		        additionalHormoneTherapy 		column:'ADDITIONALHORMONETHERAPY'
		        additionalDrugTherapy 			column:'ADDITIONALDRUGTHERAPY'
		        anatomicOrganSubdivision 		column:'ANATOMICORGANSUBDIVISION'
		        personNeoplasmStatus 			column:'PERSONNEOPLASMSTATUS'
		        siteOfTumorFirstRecurrence 		column:'SITEOFTUMORFIRSTRECURRENCE'
		        tumorStage 						column:'TUMORSTAGE'
		        tumorGrade 						column:'TUMORGRADE'
		        tumorResidualDisease 			column:'TUMORRESIDUALDISEASE'
		        primaryTherapyOutcomeSuccess 	column:'PRIMARYTHERAPYOUTCOMESUCCESS'
		        jewishOrigin 					column:'JEWISHORIGIN'
		        surProcPrfmDt 					column:'SUR_PROC_PRFM_DT'
		        uncOsEvent 						column:'UNC_OSEVENT'
		        uncOsDuration 					column:'UNC_OSDURATION'
		        dodMinusDx 						column:'DOD_MINUS_DX'
		        dodfuMinusDx 					column:'DODFU_MINUS_DX'
		        dodMinusDop 					column:'DOD_MINUS_DOP'
		        dodfuMinusDop 					column:'DODFU_MINUS_DOP'
		        ptid 							column:'PTID'
		    }
		}
		
		/*
		 * 
		  BCRPATIENTBARCODE             VARCHAR2(50 BYTE),
		  TUMORTISSUESITE               VARCHAR2(50 BYTE),
		  PRIORGLIOMA                   VARCHAR2(50 BYTE),
		  VITALSTATUS                   VARCHAR2(50 BYTE),
		  DOB                           DATE,
		  DOD                           DATE,
		  LAST_FLUP_DT                  DATE,
		  RACE                          VARCHAR2(50 BYTE),
		  SMOKINGHISTORY                VARCHAR2(50 BYTE),
		  ALCOHOLCONSUMPTION            VARCHAR2(50 BYTE),
		  ENVIRONMENTALEXPOSURE         VARCHAR2(50 BYTE),
		  GENDER                        VARCHAR2(50 BYTE),
		  INFORMEDCONSENTACQUIRED       VARCHAR2(50 BYTE),
		  HISTOLOGICALTYPE              VARCHAR2(50 BYTE),
		  BCRSITEID                     VARCHAR2(50 BYTE),
		  REVISION                      VARCHAR2(50 BYTE),
		  PRETREATMENTHISTORY           VARCHAR2(50 BYTE),
		  RADIATIONTHERAPY              VARCHAR2(50 BYTE),
		  INIT_PATH_DX_DT               DATE,
		  CHEMOTHERAPY                  VARCHAR2(50 BYTE),
		  IMMUNOTHERAPY                 VARCHAR2(50 BYTE),
		  HORMONALTHERAPY               VARCHAR2(50 BYTE),
		  TARGETEDMOLECULARTHERAPY      VARCHAR2(50 BYTE),
		  TUMOR_PRGR_DT                 DATE,
		  TUMOR_RECUR_DT                DATE,
		  ETHNICITY                     VARCHAR2(50 BYTE),
		  ADDITIONALRADIATIONTHERAPY    VARCHAR2(50 BYTE),
		  ADDITIONALCHEMOTHERAPY        VARCHAR2(50 BYTE),
		  ADDITIONALIMMUNOTHERAPY       VARCHAR2(50 BYTE),
		  ADDITIONALHORMONETHERAPY      VARCHAR2(50 BYTE),
		  ADDITIONALDRUGTHERAPY         VARCHAR2(50 BYTE),
		  ANATOMICORGANSUBDIVISION      VARCHAR2(50 BYTE),
		  INIT_PATHOLOGIC_DX_METHOD     VARCHAR2(50 BYTE),
		  PERSONNEOPLASMSTATUS          VARCHAR2(50 BYTE),
		  SITEOFTUMORFIRSTRECURRENCE    VARCHAR2(50 BYTE),
		  TUMORSTAGE                    VARCHAR2(50 BYTE),
		  TUMORGRADE                    VARCHAR2(50 BYTE),
		  TUMORRESIDUALDISEASE          VARCHAR2(50 BYTE),
		  PRIMARYTHERAPYOUTCOMESUCCESS  VARCHAR2(50 BYTE),
		  JEWISHORIGIN                  VARCHAR2(10 BYTE),
		  SUR_PROC_PRFM_DT              DATE,
		  UNC_OSEVENT                   VARCHAR2(50 BYTE),
		  UNC_OSDURATION                VARCHAR2(50 BYTE),
		  DOD_MINUS_DX                  NUMBER,
		  DODFU_MINUS_DX                NUMBER,
		  DOD_MINUS_DOP                 NUMBER,
		  DODFU_MINUS_DOP               NUMBER,
		  PTID                          VARCHAR2(4 BYTE),
		  ID                            NUMBER,
		  VERSION                       NUMBER		 
		 */

}
