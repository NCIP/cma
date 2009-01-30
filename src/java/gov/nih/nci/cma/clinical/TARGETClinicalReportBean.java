package gov.nih.nci.cma.clinical;

public class TARGETClinicalReportBean {
  
  private String ptId;                  
  private String targetId;              
  private String gender;    
  private String genderStr;
  private String genderDisp;
  private String naaccrRace; 
  private String raceStr;
  private String raceDisp;
  private String naaccrEthnicity;
  private String ethnicityStr;
  private String ethnicityDisp;
  private String congenitalAbnormality; 
  private String congenitalAbStr;
  private String congenitalAbDisp;
  private String age; 
  private String pbWbc;
  private String cns; 
  private String cnsStr;
  private String cnsDisp;
  private String testicular; 
  private String testicularStr;
  private String testicularDisp;
  private String karyotype; 
  private String mrdDay8;
  private String mrdDay29; 
  private String bmaBlastsDay8; 
  private String bmaBlastsDay29; 
  private String event;
  private String eventStr;
  private String eventDisp;
  private String timeToEvent;
  private String death; 
  private String deathStr;
  private String deathDisp;
  private String timeToDeath; 
  private String siteOfRelapse; 
  private String telStatus; 
  private String telStatusStr;
  private String telStatusDisp;
  private String trisomies_4_10;
  private String trisomies_4_10Str;
  private String trisomies_4_10Disp;
  private String mllStatus; 
  private String mllStatusStr;
  private String mllStatusDisp;
  private String e2aStatus; 
  private String e2aStatusStr;
  private String e2aStatusDisp;
  private String bcrStatus; 
  private String bcrStatusStr;
  private String bcrStatusDisp;
  private String dnaIndex;
  
	public String getPtId() {
		return ptId;
	}
	
	public void setPtId(String ptId) {
		this.ptId = ptId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getGender() {
		
		if (genderDisp == null) {
		   genderDisp = genderStr + " - " + gender;
		}
		return genderDisp;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getNaaccrRace() {		
		if (raceDisp == null) {
		  raceDisp = raceStr + " - " + naaccrRace;
		}
		return raceDisp;
	}
	
	public void setNaaccrRace(String naaccrRace) {
		this.naaccrRace = naaccrRace;
	}
	
	public String getNaaccrEthnicity() {	
		if (ethnicityDisp == null) {
		   ethnicityDisp = ethnicityStr + " - " + naaccrEthnicity;
		}		
		return ethnicityDisp;
	}
	
	public void setNaaccrEthnicity(String naaccrEthnicity) {
		this.naaccrEthnicity = naaccrEthnicity;
	}
	
	public String getCongenitalAbnormality() {
		
		if (congenitalAbDisp == null) {
			congenitalAbDisp = congenitalAbStr + " - " + congenitalAbnormality;
		}		
		return congenitalAbDisp;
	}
	
	public void setCongenitalAbnormality(String congenitalAbnormality) {
		this.congenitalAbnormality = congenitalAbnormality;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPbWbc() {
		return pbWbc;
	}
	public void setPbWbc(String pbWbc) {
		this.pbWbc = pbWbc;
	}
	
	public String getCns() {
		
		if (cnsDisp == null) {
		  cnsDisp = cnsStr + " - " + cns;
		}
		
		return cnsDisp;
	}
	
	public void setCns(String cns) {
		this.cns = cns;
	}
	
	public String getTesticular() {
		
		if (testicularDisp == null) {
		  testicularDisp = testicularStr + " - " + testicular;
		}
		
		return testicularDisp;
	}
	
	public void setTesticular(String testicular) {
		this.testicular = testicular;
	}
	public String getKaryotype() {
		return karyotype;
	}
	public void setKaryotype(String karyotype) {
		this.karyotype = karyotype;
	}
	public String getMrdDay8() {
		return mrdDay8;
	}
	public void setMrdDay8(String mrdDay8) {
		this.mrdDay8 = mrdDay8;
	}
	public String getMrdDay29() {
		return mrdDay29;
	}
	public void setMrdDay29(String mrdDay29) {
		this.mrdDay29 = mrdDay29;
	}
	public String getBmaBlastsDay8() {
		return bmaBlastsDay8;
	}
	public void setBmaBlastsDay8(String bmaBlastsDay8) {
		this.bmaBlastsDay8 = bmaBlastsDay8;
	}
	public String getBmaBlastsDay29() {
		return bmaBlastsDay29;
	}
	public void setBmaBlastsDay29(String bmaBlastsDay29) {
		this.bmaBlastsDay29 = bmaBlastsDay29;
	}
	
	public String getEvent() {
		
		if (eventDisp == null) {
		  eventDisp = eventStr + " - " + event;
		}
		
		return eventDisp;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getTimeToEvent() {
		return timeToEvent;
	}
	
	public void setTimeToEvent(String timeToEvent) {
		this.timeToEvent = timeToEvent;
	}
	
	public String getDeath() {
		
		if (deathDisp == null) {
		  deathDisp = deathStr + " - " + death;
		}
	
		return deathDisp;
	}
	
	public void setDeath(String death) {
		this.death = death;
	}
	
	public String getTimeToDeath() {
		return timeToDeath;
	}
	
	public void setTimeToDeath(String timeToDeath) {
		this.timeToDeath = timeToDeath;
	}
	
	public String getSiteOfRelapse() {
		return siteOfRelapse;
	}
	
	public void setSiteOfRelapse(String siteOfRelapse) {
		this.siteOfRelapse = siteOfRelapse;
	}
	
	public String getTelStatus() {
		
		if (telStatusDisp == null) {
		  telStatusDisp = telStatusStr + " - " +  telStatus;
		}
		
		return telStatusDisp;
	}
	
	public void setTelStatus(String telStatus) {
		this.telStatus = telStatus;
	}
	
	public String getTrisomies_4_10() {
		if (trisomies_4_10Disp == null) {
		  trisomies_4_10Disp = trisomies_4_10Str + " - " + trisomies_4_10;
		}
		return trisomies_4_10Disp;
	}
	
	public void setTrisomies_4_10(String trisomies_4_10) {
		this.trisomies_4_10 = trisomies_4_10;
	}
	
	public String getMllStatus() {
		if (mllStatusDisp == null) {
		  mllStatusDisp = mllStatusStr + " - " + mllStatus;
		}			
		return mllStatusDisp;
	}
	
	public void setMllStatus(String mllStatus) {
		this.mllStatus = mllStatus;
	}
	
	public String getE2aStatus() {
		if (e2aStatusDisp == null) {
		  e2aStatusDisp = e2aStatusStr + " - " + e2aStatus;
		}
		
		return e2aStatusDisp;
	}
	
	public void setE2aStatus(String status) {
		e2aStatus = status;
	}
	
	public String getBcrStatus() {
		if (bcrStatusDisp == null) {
		  bcrStatusDisp = bcrStatusStr + " - " + bcrStatus;
		}
		
		return bcrStatusDisp;
	}
	
	public void setBcrStatus(String bcrStatus) {
		this.bcrStatus = bcrStatus;
	}
	
	public String getDnaIndex() {
		return dnaIndex;
	}
	
	public void setDnaIndex(String dnaIndex) {
		this.dnaIndex = dnaIndex;
	}
	
	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}
	
	public void setRaceStr(String raceStr) {
		this.raceStr = raceStr;
	}
	
	public void setEthnicityStr(String ethnicityStr) {
		this.ethnicityStr = ethnicityStr;
	}
	
	public void setCongenitalAbStr(String congenitalAbStr) {
		this.congenitalAbStr = congenitalAbStr;
	}
	
	public void setCnsStr(String cnsStr) {
		this.cnsStr = cnsStr;
	}
	
	public void setTesticularStr(String testicularStr) {
		this.testicularStr = testicularStr;
	}
	
	public void setEventStr(String eventStr) {
		this.eventStr = eventStr;
	}
	
	public void setDeathStr(String deathStr) {
		this.deathStr = deathStr;
	}

	public void setGenderDisp(String genderDisp) {
		this.genderDisp = genderDisp;
	}

	public void setRaceDisp(String raceDisp) {
		this.raceDisp = raceDisp;
	}

	public void setEthnicityDisp(String ethnicityDisp) {
		this.ethnicityDisp = ethnicityDisp;
	}

	public void setCongenitalAbDisp(String congenitalAbDisp) {
		this.congenitalAbDisp = congenitalAbDisp;
	}

	public void setCnsDisp(String cnsDisp) {
		this.cnsDisp = cnsDisp;
	}

	public void setTesticularDisp(String testicularDisp) {
		this.testicularDisp = testicularDisp;
	}

	public void setEventDisp(String eventDisp) {
		this.eventDisp = eventDisp;
	}

	public void setDeathDisp(String deathDisp) {
		this.deathDisp = deathDisp;
	}

	public void setTelStatusStr(String telStatusStr) {
		this.telStatusStr = telStatusStr;
	}

	public void setTrisomies_4_10Str(String trisomies_4_10Str) {
		this.trisomies_4_10Str = trisomies_4_10Str;
	}

	public void setMllStatusStr(String mllStatusStr) {
		this.mllStatusStr = mllStatusStr;
	}

	public void setE2aStatusStr(String statusStr) {
		e2aStatusStr = statusStr;
	}

	public void setBcrStatusStr(String bcrStatusStr) {
		this.bcrStatusStr = bcrStatusStr;
	} 
}
