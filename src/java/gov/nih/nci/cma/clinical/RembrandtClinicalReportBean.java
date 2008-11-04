package gov.nih.nci.cma.clinical;

public class RembrandtClinicalReportBean {
	
	private String rptStr = null;
	
	private String sampleId; 
	private String ageGroup;
	private String gender;
	private String survivalLengthRange;
	private String disease;	 
	private String grade;	 
	private String race;	 
	private String institution;	 
	private String karnofsky;	 
	private String neurologicalExamOutcome;	 
	private String mriDesc;	 
	private String followupMonth;	 
	private String steroidDoseStatus;	 
	private String antiConvulsantStatus;	 
	private String priorTherapyRadiationSite;	 
	private String priorTherapyRadiationFractionDose;	 
	private String priorTherapyRadiationFractionNumber;	 
	private String priorTherapyRadiationType;	 
	private String priorTherapyChemoAgentName;	 
	private String priorTherapyChemoCourseCount;	 
	private String priorTherapySurgeryProcedureTitle;	 
	private String priorTherapySurgeryTumorHistology;	 
	private String priorTherapySurgeryOutcome;	 
	private String onStudyTherapyRadiationSite;	 
	private String onStudyTherapyRadiationNeurosisStatus;	 
	private String onStudyTherapyRadiationFractionDose;
	private String onStudyTherapyRadiationFractionNumber;	 
	private String onStudyTherapyRadiationType;	 
	private String onStudyTherapyChemoAgentName;	 
	private String onStudyTherapyChemoCourseCount;	 
	private String onStudyTherapySurgeryProcedureTitle;	 
	private String onStudyTherapySurgeryIndication;	 
	private String onStudyTherapySurgeryHistoDiagnosis;	 
	private String onStudyTherapySurgeryOutcome;
	
	
	private String getAppendString(String origStr, String strToAppend) {
	  if ((origStr == null) || (origStr.length() == 0)) {
	    return strToAppend;
	  }
	  return origStr + ", " + strToAppend;
	}
	
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getKarnofsky() {
		return karnofsky;
	}
//	public void setKarnofsky(String karnofsky) {
//		this.karnofsky = karnofsky;
//	}
	public void appendKarnofsky(String karnofsky) {
	  this.karnofsky = getAppendString(this.karnofsky, karnofsky);
	}
	
	public String getNeurologicalExamOutcome() {
		return neurologicalExamOutcome;
	}
//	public void setNeurologicalExamOutcome(String neurologicalExamOutcome) {
//		this.neurologicalExamOutcome = neurologicalExamOutcome;
//	}
	
	public void appendNeurologicalExamOutcome(String neurologicalExamOutcome) {
		this.neurologicalExamOutcome = getAppendString(this.neurologicalExamOutcome, neurologicalExamOutcome);
	}
	
	
	public String getMriDesc() {
		return mriDesc;
	}
//	public void setMriDesc(String mriDesc) {
//		this.mriDesc = mriDesc;
//	}
	
	public void appendMriDesc(String mriDesc) {
	  this.mriDesc = getAppendString(this.mriDesc, mriDesc);
	}
	
	public String getFollowupMonth() {
		return followupMonth;
	}
	public void setFollowupMonth(String followupMonth) {
		this.followupMonth = followupMonth;
	}
	public String getSteroidDoseStatus() {
		return steroidDoseStatus;
	}
//	public void setSteroidDoseStatus(String steroidDoseStatus) {
//		this.steroidDoseStatus = steroidDoseStatus;
//	}
	public void appendSteroidDoseStatus(String steroidDoseStatus) {
		this.steroidDoseStatus = getAppendString(this.steroidDoseStatus, steroidDoseStatus);
	}
	public String getAntiConvulsantStatus() {
		return antiConvulsantStatus;
	}
//	public void setAntiConvulsantStatus(String antiConvulsantStatus) {
//		this.antiConvulsantStatus = antiConvulsantStatus;
//	}
	public void appendAntiConvulsantStatus(String antiConvulsantStatus) {
		this.antiConvulsantStatus = getAppendString(this.antiConvulsantStatus, antiConvulsantStatus);
	}
	
	public String getPriorTherapyRadiationSite() {
		return priorTherapyRadiationSite;
	}
//	public void setPriorTherapyRadiationSite(String priorTherapyRadiationSite) {
//		this.priorTherapyRadiationSite = priorTherapyRadiationSite;
//	}
	public void appendPriorTherapyRadiationSite(String priorTherapyRadiationSite) {
		this.priorTherapyRadiationSite = getAppendString(this.priorTherapyRadiationSite,priorTherapyRadiationSite);
	}
	public String getPriorTherapyRadiationFractionDose() {
		return priorTherapyRadiationFractionDose;
	}
//	public void setPriorTherapyRadiationFractionDose(
//			String priorTherapyRadiationFractionDose) {
//		this.priorTherapyRadiationFractionDose = priorTherapyRadiationFractionDose;
//	}
	public void appendPriorTherapyRadiationFractionDose(
	String priorTherapyRadiationFractionDose) {
		this.priorTherapyRadiationFractionDose = getAppendString(this.priorTherapyRadiationFractionDose, priorTherapyRadiationFractionDose);
	}	
	
	public String getPriorTherapyRadiationFractionNumber() {
		return priorTherapyRadiationFractionNumber;
	}
//	public void setPriorTherapyRadiationFractionNumber(
//			String priorTherapyRadiationFractionNumber) {
//		this.priorTherapyRadiationFractionNumber = priorTherapyRadiationFractionNumber;
//	}
	public void appendPriorTherapyRadiationFractionNumber(
			String priorTherapyRadiationFractionNumber) {
		this.priorTherapyRadiationFractionNumber = getAppendString(this.priorTherapyRadiationFractionNumber, priorTherapyRadiationFractionNumber);
	}
	public String getPriorTherapyRadiationType() {
		return priorTherapyRadiationType;
	}
//	public void setPriorTherapyRadiationType(String priorTherapyRadiationType) {
//		this.priorTherapyRadiationType = priorTherapyRadiationType;
//	}
	public void appendPriorTherapyRadiationType(String priorTherapyRadiationType) {
		this.priorTherapyRadiationType = getAppendString(this.priorTherapyRadiationType, priorTherapyRadiationType);
	}
	public String getPriorTherapyChemoAgentName() {
		return priorTherapyChemoAgentName;
	}
//	public void setPriorTherapyChemoAgentName(String priorTherapyChemoAgentName) {
//		this.priorTherapyChemoAgentName = priorTherapyChemoAgentName;
//	}
	public void appendPriorTherapyChemoAgentName(String priorTherapyChemoAgentName) {
		this.priorTherapyChemoAgentName = getAppendString(this.priorTherapyChemoAgentName, priorTherapyChemoAgentName);
	}
	public String getPriorTherapyChemoCourseCount() {
		return priorTherapyChemoCourseCount;
	}
//	public void setPriorTherapyChemoCourseCount(String priorTherapyChemoCourseCount) {
//		this.priorTherapyChemoCourseCount = priorTherapyChemoCourseCount;
//	}
	public void appendPriorTherapyChemoCourseCount(String priorTherapyChemoCourseCount) {
		this.priorTherapyChemoCourseCount = getAppendString(this.priorTherapyChemoCourseCount,priorTherapyChemoCourseCount) ;
	}
	public String getPriorTherapySurgeryProcedureTitle() {
		return priorTherapySurgeryProcedureTitle;
	}
//	public void setPriorTherapySurgeryProcedureTitle(
//			String priorTherapySurgeryProcedureTitle) {
//		this.priorTherapySurgeryProcedureTitle = priorTherapySurgeryProcedureTitle;
//	}
	public void appendPriorTherapySurgeryProcedureTitle(
			String priorTherapySurgeryProcedureTitle) {
		this.priorTherapySurgeryProcedureTitle = getAppendString(this.priorTherapySurgeryProcedureTitle,priorTherapySurgeryProcedureTitle);
	}
	public String getPriorTherapySurgeryTumorHistology() {
		return priorTherapySurgeryTumorHistology;
	}
//	public void setPriorTherapySurgeryTumorHistology(
//			String priorTherapySurgeryTumorHistology) {
//		this.priorTherapySurgeryTumorHistology = priorTherapySurgeryTumorHistology;
//	}
	public void appendPriorTherapySurgeryTumorHistology(
			String priorTherapySurgeryTumorHistology) {
		this.priorTherapySurgeryTumorHistology = getAppendString(this.priorTherapySurgeryTumorHistology, priorTherapySurgeryTumorHistology);
	}
	public String getPriorTherapySurgeryOutcome() {
		return priorTherapySurgeryOutcome;
	}
//	public void setPriorTherapySurgeryOutcome(String priorTherapySurgeryOutcome) {
//		this.priorTherapySurgeryOutcome = priorTherapySurgeryOutcome;
//	}
	public void appendPriorTherapySurgeryOutcome(String priorTherapySurgeryOutcome) {
		this.priorTherapySurgeryOutcome = getAppendString(this.priorTherapySurgeryOutcome,priorTherapySurgeryOutcome) ;
	}
	public String getOnStudyTherapyRadiationSite() {
		return onStudyTherapyRadiationSite;
	}
//	public void setOnStudyTherapyRadiationSite(String onStudyTherapyRadiationSite) {
//		this.onStudyTherapyRadiationSite = onStudyTherapyRadiationSite;
//	}
	public void appendOnStudyTherapyRadiationSite(String onStudyTherapyRadiationSite) {
		this.onStudyTherapyRadiationSite = getAppendString(this.onStudyTherapyRadiationSite,onStudyTherapyRadiationSite) ;
	}
	public String getOnStudyTherapyRadiationNeurosisStatus() {
		return onStudyTherapyRadiationNeurosisStatus;
	}
//	public void setOnStudyTherapyRadiationNeurosisStatus(
//			String onStudyTherapyRadiationNeurosisStatus) {
//		this.onStudyTherapyRadiationNeurosisStatus = onStudyTherapyRadiationNeurosisStatus;
//	}
	public void appendOnStudyTherapyRadiationNeurosisStatus(
			String onStudyTherapyRadiationNeurosisStatus) {
		this.onStudyTherapyRadiationNeurosisStatus = getAppendString(this.onStudyTherapyRadiationNeurosisStatus,onStudyTherapyRadiationNeurosisStatus);
	}
	public String getOnStudyTherapyRadiationFractionDose() {
		return onStudyTherapyRadiationFractionDose;
	}
//	public void setOnStudyTherapyRadiationFractionDose(
//			String onStudyTherapyRadiationFractionDose) {
//		this.onStudyTherapyRadiationFractionDose = onStudyTherapyRadiationFractionDose;
//	}
	public void appendOnStudyTherapyRadiationFractionDose(
			String onStudyTherapyRadiationFractionDose) {
		this.onStudyTherapyRadiationFractionDose = getAppendString(this.onStudyTherapyRadiationFractionDose,onStudyTherapyRadiationFractionDose) ;
	}
	public String getOnStudyTherapyRadiationFractionNumber() {
		return onStudyTherapyRadiationFractionNumber;
	}
//	public void setOnStudyTherapyRadiationFractionNumber(
//			String onStudyTherapyRadiationFractionNumber) {
//		this.onStudyTherapyRadiationFractionNumber = onStudyTherapyRadiationFractionNumber;
//	}
	public void appendOnStudyTherapyRadiationFractionNumber(
			String onStudyTherapyRadiationFractionNumber) {
		this.onStudyTherapyRadiationFractionNumber = getAppendString(this.onStudyTherapyRadiationFractionNumber,onStudyTherapyRadiationFractionNumber); 
	}
	public String getOnStudyTherapyRadiationType() {
		return onStudyTherapyRadiationType;
	}
//	public void setOnStudyTherapyRadiationType(String onStudyTherapyRadiationType) {
//		this.onStudyTherapyRadiationType = onStudyTherapyRadiationType;
//	}
	public void appendOnStudyTherapyRadiationType(String onStudyTherapyRadiationType) {
		this.onStudyTherapyRadiationType = getAppendString(this.onStudyTherapyRadiationType, onStudyTherapyRadiationType);
	}
	public String getOnStudyTherapyChemoAgentName() {
		return onStudyTherapyChemoAgentName;
	}
//	public void setOnStudyTherapyChemoAgentName(String onStudyTherapyChemoAgentName) {
//		this.onStudyTherapyChemoAgentName = onStudyTherapyChemoAgentName;
//	}
	public void appendOnStudyTherapyChemoAgentName(String onStudyTherapyChemoAgentName) {
		this.onStudyTherapyChemoAgentName = getAppendString(this.onStudyTherapyChemoAgentName,onStudyTherapyChemoAgentName) ;
	}
	public String getOnStudyTherapyChemoCourseCount() {
		return onStudyTherapyChemoCourseCount;
	}
//	public void setOnStudyTherapyChemoCourseCount(
//			String onStudyTherapyChemoCourseCount) {
//		this.onStudyTherapyChemoCourseCount = onStudyTherapyChemoCourseCount;
//	}
	public void appendOnStudyTherapyChemoCourseCount(
			String onStudyTherapyChemoCourseCount) {
		this.onStudyTherapyChemoCourseCount = getAppendString(this.onStudyTherapyChemoCourseCount,onStudyTherapyChemoCourseCount);
	}
	public String getOnStudyTherapySurgeryProcedureTitle() {
		return onStudyTherapySurgeryProcedureTitle;
	}
//	public void setOnStudyTherapySurgeryProcedureTitle(
//			String onStudyTherapySurgeryProcedureTitle) {
//		this.onStudyTherapySurgeryProcedureTitle = onStudyTherapySurgeryProcedureTitle;
//	}
	public void appendOnStudyTherapySurgeryProcedureTitle(
			String onStudyTherapySurgeryProcedureTitle) {
		this.onStudyTherapySurgeryProcedureTitle = getAppendString(this.onStudyTherapySurgeryProcedureTitle, onStudyTherapySurgeryProcedureTitle) ;
	}
	public String getOnStudyTherapySurgeryIndication() {
		return onStudyTherapySurgeryIndication;
	}
//	public void setOnStudyTherapySurgeryIndication(
//			String onStudyTherapySurgeryIndication) {
//		this.onStudyTherapySurgeryIndication = onStudyTherapySurgeryIndication;
//	}
	public void appendOnStudyTherapySurgeryIndication(
			String onStudyTherapySurgeryIndication) {
		this.onStudyTherapySurgeryIndication = getAppendString(this.onStudyTherapySurgeryIndication, onStudyTherapySurgeryIndication) ;
	}
	public String getOnStudyTherapySurgeryHistoDiagnosis() {
		return onStudyTherapySurgeryHistoDiagnosis;
	}
//	public void setOnStudyTherapySurgeryHistoDiagnosis(
//			String onStudyTherapySurgeryHistoDiagnosis) {
//		this.onStudyTherapySurgeryHistoDiagnosis = onStudyTherapySurgeryHistoDiagnosis;
//	}
	public void appendOnStudyTherapySurgeryHistoDiagnosis(
			String onStudyTherapySurgeryHistoDiagnosis) {
		this.onStudyTherapySurgeryHistoDiagnosis = getAppendString(this.onStudyTherapySurgeryHistoDiagnosis, onStudyTherapySurgeryHistoDiagnosis);
	}
	public String getOnStudyTherapySurgeryOutcome() {
		return onStudyTherapySurgeryOutcome;
	}
//	public void setOnStudyTherapySurgeryOutcome(String onStudyTherapySurgeryOutcome) {
//		this.onStudyTherapySurgeryOutcome = onStudyTherapySurgeryOutcome;
//	}
	public void appendOnStudyTherapySurgeryOutcome(String onStudyTherapySurgeryOutcome) {
		this.onStudyTherapySurgeryOutcome = getAppendString(this.onStudyTherapySurgeryOutcome, onStudyTherapySurgeryOutcome);
	}
	public String getSurvivalLengthRange() {
		return survivalLengthRange;
	}
	public void setSurvivalLengthRange(String survivalLengthRange) {
		this.survivalLengthRange = survivalLengthRange;
	}	
	
	
	public String toString() {
		
	 if (rptStr == null) {
	
		 StringBuffer sb = new StringBuffer();		 
		 sb.append(sampleId).append(",");
		 sb.append(ageGroup).append(",");
		 sb.append(gender).append(",");		 
		 sb.append(survivalLengthRange).append(",");
		 sb.append(disease).append(",");
		 sb.append(grade).append(",");
		 sb.append(race).append(",");
		 sb.append(institution).append(",");
		 sb.append(karnofsky).append(",");
		 sb.append(neurologicalExamOutcome).append(",");
		 sb.append(mriDesc).append(",");
		 sb.append(followupMonth).append(",");
		 sb.append(steroidDoseStatus).append(",");
		 sb.append(antiConvulsantStatus).append(",");
		 sb.append(priorTherapyRadiationSite).append(",");
		 sb.append(priorTherapyRadiationFractionDose).append(",");
		 sb.append(priorTherapyRadiationFractionNumber).append(",");
		 sb.append(priorTherapyRadiationType).append(",");
		 sb.append(priorTherapyChemoAgentName).append(",");
		 sb.append(priorTherapyChemoCourseCount).append(",");
		 sb.append(priorTherapySurgeryProcedureTitle).append(",");
		 sb.append(priorTherapySurgeryTumorHistology).append(",");
		 sb.append(priorTherapySurgeryOutcome).append(",");
		 sb.append(onStudyTherapyRadiationSite).append(",");
		 sb.append(onStudyTherapyRadiationNeurosisStatus).append(",");
		 sb.append(onStudyTherapyRadiationFractionDose).append(",");
		 sb.append(onStudyTherapyRadiationFractionNumber).append(",");
		 sb.append(onStudyTherapyRadiationType).append(",");
		 sb.append(onStudyTherapyChemoAgentName).append(",");
		 sb.append(onStudyTherapyChemoCourseCount).append(",");
		 sb.append(onStudyTherapySurgeryProcedureTitle).append(",");
		 sb.append(onStudyTherapySurgeryIndication).append(",");
		 sb.append(onStudyTherapySurgeryHistoDiagnosis).append(",");
		 sb.append(onStudyTherapySurgeryOutcome);
		 
		 rptStr = sb.toString();
		 
	 }
		
	 return rptStr;
		
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
}
