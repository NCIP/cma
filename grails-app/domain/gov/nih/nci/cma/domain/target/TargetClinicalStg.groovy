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
  
  GENDER_STR              VARCHAR2(10 CHAR),
  RACE_STR                VARCHAR2(255 BYTE),
  ETHNICITY_STR           VARCHAR2(255 BYTE),
  CONGENITAL_AB_STR       VARCHAR2(255 BYTE),
  CNS_STR                 VARCHAR2(255 BYTE),
  TESTICULAR_STR          VARCHAR2(255 BYTE),
  EVENT_STR               VARCHAR2(255 BYTE),
  DEATH_STR               VARCHAR2(255 BYTE)
)

 */

class TargetClinicalStg {
	   String ptId                  
	   String targetId              
	   String gender    
	   String genderStr
	   String naaccrRace 
	   String raceStr
	   String naaccrEthnicity
	   String ethnicityStr
	   String congenitalAbnormality 
	   String congenitalAbStr
	   String age 
	   String pbWbc
	   String cns 
	   String cnsStr
	   String testicular 
	   String testicularStr
	   String karyotype 
	   String mrdDay8
	   String mrdDay29 
	   String bmaBlastsDay8 
	   String bmaBlastsDay29 
	   String event
	   String eventStr
	   String timeToEvent
	   String death 
	   String deathStr
	   String timeToDeath 
	   String siteOfRelapse 
	   String telStatus 
	   String telStatusStr
	   String trisomies_4_10
	   String trisomies_4_10Str
	   String mllStatus 
	   String mllStatusStr
	   String e2aStatus 
	   String e2aStatusStr
	   String bcrStatus 
	   String bcrStatusStr
	   String dnaIndex
}
