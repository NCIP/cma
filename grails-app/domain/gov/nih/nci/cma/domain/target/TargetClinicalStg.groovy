package gov.nih.nci.cma.domain.target


/**
 * PT_ID                   VARCHAR2(255 BYTE),
  TARGET_ID               VARCHAR2(255 BYTE),
  GENDER                  VARCHAR2(255 BYTE),
  NAACCR_RACE             VARCHAR2(255 BYTE),
  NAACCR_ETHNICITY        VARCHAR2(255 BYTE),
  CONGENITAL_ABNORMALITY  VARCHAR2(255 BYTE),
  AGE                     VARCHAR2(255 BYTE),
  PB_WBC                  VARCHAR2(255 BYTE),
  CNS                     VARCHAR2(255 BYTE),
  TESTICULAR              VARCHAR2(255 BYTE),
  KARYOTYPE               VARCHAR2(255 BYTE),
  MRD_DAY8                VARCHAR2(255 BYTE),
  MRD_DAY29               VARCHAR2(255 BYTE),
  BMA_BLASTS_DAY8         VARCHAR2(255 BYTE),
  BMA_BLASTS_DAY29        VARCHAR2(255 BYTE),
  EVENT                   VARCHAR2(255 BYTE),
  TIME_TO_EVENT           VARCHAR2(255 BYTE),
  DEATH                   VARCHAR2(255 BYTE),
  TIME_TO_DEATH           VARCHAR2(255 BYTE),
  SITE_OF_RELAPSE         VARCHAR2(4000 BYTE),
  TEL_STATUS              VARCHAR2(255 BYTE),
  TRISOMIES_4_10          VARCHAR2(255 BYTE),
  MLL_STATUS              VARCHAR2(255 BYTE),
  E2A_STATUS              VARCHAR2(255 BYTE),
  BCR_STATUS              VARCHAR2(255 BYTE),
  DNA_INDEX               VARCHAR2(255 BYTE),
  ID                      NUMBER(19),
  VERSION                 NUMBER(19)
)

 */

class TargetClinicalStg {
	   String ptId                  
	   String targetId              
	   String gender    
	   String naaccrRace 
	   String naaccrEthnicity
	   String congenitalAbnormality 
	   String age 
	   String pbWbc
	   String cns 
	   String testicular 
	   String karyotype 
	   String mrdDay8
	   String mrdDay29 
	   String bmaBlastsDay8 
	   String bmaBlastsDay29 
	   String event
	   String timeToEvent
	   String death 
	   String timeToDeath 
	   String siteOfRelapse 
	   String telStatus 
	   String trisomies_4_10
	   String mllStatus 
	   String e2aStatus 
	   String bcrStatus 
	   String dnaIndex
}
