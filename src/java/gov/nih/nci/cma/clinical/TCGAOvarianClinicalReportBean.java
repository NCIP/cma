package gov.nih.nci.cma.clinical;

	import java.util.Date;

public class TCGAOvarianClinicalReportBean {
	
	private String patientId;
	private String tumorTissueSite;
	//private String priorGlioma;
	private String vitalStatus;
	private Date dob;
	private Date dod;
	private Date lastFollowUp;
	private String race;
	//private String smokingHistory;
	//private String alcoholConsumption;
	//private String experimentalExposure;
	private String gender;
	private String informedConsentAcquired;
	//private String histologicalType;
	//private String bcrSiteId;
	//private String revision;
	//private String pretreatmentTherapy;
	//private String radiationTherapy;
	private Date initPathologicDxDate;
	private String initPathologicDxMethod;
	private String chemotherapy;
	private String immunotherapy;
	//private String hormonalTherapy;
	private String targetedMolecularTherapy;
	private Date tumorPrgr;
	private Date tumorRecur;
	//private String ethnicity;
	private String additionalRadiationTherapy;
	private String additionalChemotherapy;
	private String additionalImmunotherapy;
	private String additionalHormoneTherapy;
	private String additionalDrugTherapy;
	private String anatomicOrganSubdivision;
	private String personNeoplasmStatus;
	private String siteOfTumorFirstRecurrence;
	private String tumorStage;
	private String tumorGrade;
	private String tumorResidualDisease;
	private String primaryTherapyOutcomeSuccess;
	private String jewishOrigin;
	private Date surProcPrfm;
	private String uncOsEvent;
	private String uncOsDuration;
	private Integer dodfuMinusDx;
	private Integer dodMinusDx;
	private Integer dodMinusDop;
	private Integer dodfuMinusDop;
	private String ptid;
	
	
	/**
	 * getId returns the id to use to identify items to be placed in the patient lists
	 * @return
	 */
	public String getId() {
	  return ptid;
	}
	
	public String getPatientId() {
		return patientId;
	}	
	public void setPatientId(String data) {
		this.patientId = data;
	}
	
	public String getTumorTissueSite() {
		return tumorTissueSite;
	}
	public void setTumorTissueSite(String data) {
		this.tumorTissueSite = data;
	}
	
	/*
	public String getPriorGlioma() {
		return priorGlioma;
	}
	public void setPriorGlioma(String data) {
		this.priorGlioma = data;
	}
	*/
	
	public String getVitalStatus() {
		return vitalStatus;
	}
	public void setVitalStatus(String data) {
		this.vitalStatus = data;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date data) {
		this.dob = data;
	}
	
	public Date getDod() {
		return dod;
	}
	public void setDod(Date data) {
		this.dod = data;
	}
	
	public Date getLastFollowUp() {
		return lastFollowUp;
	}
	public void setLastFollowUp(Date data) {
		this.lastFollowUp = data;
	}
	
	public String getRace() {
		return race;
	}
	public void setRace(String data) {
		this.race = data;
	}
	
	/*
	public String getSmokingHistory() {
		return smokingHistory;
	}
	public void setSmokingHistory(String data) {
		this.smokingHistory = data;
	}

	public String getAlcoholConsumption() {
		return alcoholConsumption;
	}
	public void setAlcoholConsumption(String data) {
		this.alcoholConsumption = data;
	}

	public String getExperimentalExposure() {
		return experimentalExposure;
	}
	public void setExperimentalExposure(String data) {
		this.experimentalExposure = data;
	}
	*/
	
	public String getGender() {
		return gender;
	}
	public void setGender(String data) {
		this.gender = data;
	}
	
	public String getInformedConsentAcquired() {
		return informedConsentAcquired;
	}
	public void setInformedConsentAcquired(String data) {
		this.informedConsentAcquired = data;
	}
	
	/*
	public String getHistologicalType() {
		return histologicalType;
	}
	public void setHistologicalType(String data) {
		this.histologicalType = data;
	}
	
	public String getBcrSiteId() {
		return bcrSiteId;
	}
	public void setBcrSiteId(String data) {
		this.bcrSiteId = data;
	}
	
	public String getRevision() {
		return revision;
	}
	public void setRevision(String data) {
		this.revision = data;
	}
	
	public String getPretreatmentTherapy() {
		return pretreatmentTherapy;
	}
	public void setPretreatmentTherapy(String data) {
		this.pretreatmentTherapy = data;
	}
	
	public String getRadiationTherapy() {
		return radiationTherapy;
	}
	public void setRadiationTherapy(String data) {
		this.radiationTherapy = data;
	}
	*/
	
	public String getChemotherapy() {
		return chemotherapy;
	}
	public void setChemotherapy(String data) {
		this.chemotherapy = data;
	}
	
	public String getImmunotherapy() {
		return immunotherapy;
	}
	public void setImmunotherapy(String data) {
		this.immunotherapy = data;
	}
	
	/*
	public String getHormonalTherapy() {
		return hormonalTherapy;
	}
	public void setHormonalTherapy(String data) {
		this.hormonalTherapy = data;
	}
	*/
	
	public String getTargetedMolecularTherapy() {
		return targetedMolecularTherapy;
	}
	public void setTargetedMolecularTherapy(String data) {
		this.targetedMolecularTherapy = data;
	}
	
	public Date getTumorPrgr() {
		return tumorPrgr;
	}
	public void setTumorPrgr(Date data) {
		this.tumorPrgr = data;
	}
	
	public Date getTumorRecur() {
		return tumorRecur;
	}
	public void setTumorRecur(Date data) {
		this.tumorRecur = data;
	}
	
	/*
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String data) {
		this.ethnicity = data;
	}
	*/
	
	public String getAdditionalRadiationTherapy() {
		return additionalRadiationTherapy;
	}
	public void setAdditionalRadiationTherapy(String data) {
		this.additionalRadiationTherapy = data;
	}
	
	public String getAdditionalChemotherapy() {
		return additionalChemotherapy;
	}
	public void setAdditionalChemotherapy(String data) {
		this.additionalChemotherapy = data;
	}
	
	public String getAdditionalImmunotherapy() {
		return additionalImmunotherapy;
	}
	public void setAdditionalImmunotherapy(String data) {
		this.additionalImmunotherapy = data;
	}
	
	public String getAdditionalHormoneTherapy() {
		return additionalHormoneTherapy;
	}
	public void setAdditionalHormoneTherapy(String data) {
		this.additionalHormoneTherapy = data;
	}
	
	public String getAdditionalDrugTherapy() {
		return additionalDrugTherapy;
	}
	public void setAdditionalDrugTherapy(String data) {
		this.additionalDrugTherapy = data;
	}
	
	public String getAnatomicOrganSubdivision() {
		return anatomicOrganSubdivision;
	}
	public void setAnatomicOrganSubdivision(String data) {
		this.anatomicOrganSubdivision = data;
	}
	
	public String getInitPathologicDxMethod() {
		return initPathologicDxMethod;
	}
	public void setInitPathologicDxMethod(String data) {
		this.initPathologicDxMethod = data;
	}
	
	public Date getInitPathologicDxDate() {
		return initPathologicDxDate;
	}
	public void setInitPathologicDxDate(Date data) {
		this.initPathologicDxDate = data;
	}
	
	public String getPersonNeoplasmStatus() {
		return personNeoplasmStatus;
	}
	public void setPersonNeoplasmStatus(String data) {
		this.personNeoplasmStatus = data;
	}
	
	public String getSiteOfTumorFirstRecurrence() {
		return siteOfTumorFirstRecurrence;
	}
	public void setSiteOfTumorFirstRecurrence(String data) {
		this.siteOfTumorFirstRecurrence = data;
	}
	
	public String getTumorStage() {
		return tumorStage;
	}
	public void setTumorStage(String data) {
		this.tumorStage = data;
	}
	
	public String getTumorGrade() {
		return tumorGrade;
	}
	public void setTumorGrade(String data) {
		this.tumorGrade = data;
	}
	
	public String getTumorResidualDisease() {
		return tumorResidualDisease;
	}
	public void setTumorResidualDisease(String data) {
		this.tumorResidualDisease = data;
	}
	
	public String getPrimaryTherapyOutcomeSuccess() {
		return primaryTherapyOutcomeSuccess;
	}
	public void setPrimaryTherapyOutcomeSuccess(String data) {
		this.primaryTherapyOutcomeSuccess = data;
	}
	
	public String getJewishOrigin() {
		return jewishOrigin;
	}
	public void setJewishOrigin(String data) {
		this.jewishOrigin = data;
	}
	
	public Date getSurProcPrfm() {
		return surProcPrfm;
	}
	public void setSurProcPrfm(Date data) {
		this.surProcPrfm = data;
	}
	
	public String getUncOsEvent() {
		return uncOsEvent;
	}
	public void setUncOsEvent(String data) {
		this.uncOsEvent = data;
	}
	
	public String getUncOsDuration() {
		return uncOsDuration;
	}
	public void setUncOsDuration(String data) {
		this.uncOsDuration = data;
	}
	
	public Integer getDodMinusDx() {
		return dodMinusDx;
	}
	public void setDodMinusDx(Integer data) {
		this.dodMinusDx = data;
	}
	
	public Integer getDodfuMinusDx() {
		return dodfuMinusDx;
	}	
	public void setDodfuMinusDx(Integer data) {
		this.dodfuMinusDx = data;
	}
	
	public Integer getDodMinusDop() {
		return dodMinusDop;
	}
	public void setDodMinusDop(Integer dodMinusDop) {
		this.dodMinusDop = dodMinusDop;
	}
	
	public Integer getDodfuMinusDop() {
		return dodfuMinusDop;
	}	
	public void setDodfuMinusDop(Integer dodfuMinusDop) {
		this.dodfuMinusDop = dodfuMinusDop;
	}
	
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String data) {
		this.ptid = data;
	}
	
	public String toString() {
	  StringBuffer sb = new StringBuffer();
	  sb.append(patientId).append(",");
	  sb.append(tumorTissueSite).append(",");
	  //sb.append(priorGlioma).append(",");
	  sb.append(vitalStatus).append(",");
	  sb.append(dob).append(",");
	  sb.append(dod).append(",");
	  sb.append(lastFollowUp).append(",");
	  sb.append(race).append(",");
	  //sb.append(smokingHistory).append(",");
	  //sb.append(alcoholConsumption).append(",");
	  //sb.append(experimentalExposure).append(",");
	  sb.append(gender).append(",");
	  sb.append(informedConsentAcquired).append(",");
	  //sb.append(histologicalType).append(",");
	  //sb.append(bcrSiteId).append(",");
	  //sb.append(revision).append(",");
	  //sb.append(radiationTherapy).append(",");
	  sb.append(initPathologicDxDate).append(",");
	  sb.append(initPathologicDxMethod).append(",");
	  sb.append(chemotherapy).append(",");
	  sb.append(immunotherapy).append(",");
	  //sb.append(hormonalTherapy).append(",");
	  sb.append(targetedMolecularTherapy).append(",");
	  sb.append(tumorPrgr).append(",");
	  sb.append(tumorRecur).append(",");
	  //sb.append(ethnicity).append(",");
	  sb.append(additionalRadiationTherapy).append(",");
	  sb.append(additionalChemotherapy).append(",");
	  sb.append(additionalImmunotherapy).append(",");
	  sb.append(additionalHormoneTherapy).append(",");
	  sb.append(additionalDrugTherapy).append(",");
	  sb.append(anatomicOrganSubdivision).append(",");
	  sb.append(personNeoplasmStatus).append(",");
	  sb.append(siteOfTumorFirstRecurrence).append(",");
	  sb.append(tumorStage).append(",");
	  sb.append(tumorGrade).append(",");
	  sb.append(tumorResidualDisease).append(",");
	  sb.append(primaryTherapyOutcomeSuccess).append(",");
	  sb.append(jewishOrigin).append(",");
	  sb.append(surProcPrfm).append(",");
	  sb.append(uncOsEvent).append(",");
	  sb.append(uncOsDuration).append(",");
	  sb.append(dodfuMinusDx).append(",");
	  sb.append(dodMinusDx).append(",");
	  sb.append(dodfuMinusDop).append(",");
	  sb.append(dodMinusDop).append(",");
	  sb.append(ptid);
	  
	  return sb.toString();		
	}
	

}
