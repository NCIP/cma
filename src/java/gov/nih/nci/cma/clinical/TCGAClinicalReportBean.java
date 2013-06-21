/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.clinical;

public class TCGAClinicalReportBean {
	
	private String patientId;
	private String tumorTissueSite;
	private String vitalStatus;
	private String dob;
	private String dod;
	private String lastFollowUp;
	private String firstProcedure;
	private String firstExam;
	private Integer karnofskyScore;
	private String firstRadiation;
	private Integer dodMinusDop;
	private Integer dodfuMinusDop;
	private Integer cfId;
	private String gender;
	private String informedConsentAcquired;
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
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getTumorTissueSite() {
		return tumorTissueSite;
	}
	public void setTumorTissueSite(String tumorTissueSite) {
		this.tumorTissueSite = tumorTissueSite;
	}
	public String getVitalStatus() {
		return vitalStatus;
	}
	public void setVitalStatus(String vitalStatus) {
		this.vitalStatus = vitalStatus;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDod() {
		return dod;
	}
	public void setDod(String dod) {
		this.dod = dod;
	}
	public String getLastFollowUp() {
		return lastFollowUp;
	}
	public void setLastFollowUp(String lastFollowUp) {
		this.lastFollowUp = lastFollowUp;
	}
	public String getFirstProcedure() {
		return firstProcedure;
	}
	public void setFirstProcedure(String firstProcedure) {
		this.firstProcedure = firstProcedure;
	}
	public String getFirstExam() {
		return firstExam;
	}
	public void setFirstExam(String firstExam) {
		this.firstExam = firstExam;
	}
	public Integer getKarnofskyScore() {
		return karnofskyScore;
	}
	public void setKarnofskyScore(Integer karnofskyScore) {
		this.karnofskyScore = karnofskyScore;
	}
	public String getFirstRadiation() {
		return firstRadiation;
	}
	public void setFirstRadiation(String firstRadiation) {
		this.firstRadiation = firstRadiation;
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
	public Integer getCfId() {
		return cfId;
	}
	public void setCfId(Integer cfId) {
		this.cfId = cfId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInformedConsentAcquired() {
		return informedConsentAcquired;
	}
	public void setInformedConsentAcquired(String informedConsentAcquired) {
		this.informedConsentAcquired = informedConsentAcquired;
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	
	public String toString() {
	  StringBuffer sb = new StringBuffer();
	  sb.append(patientId).append(",");
	  sb.append(tumorTissueSite).append(",");
	  sb.append(vitalStatus).append(",");
	  sb.append(dob).append(",");
	  sb.append(dod).append(",");
	  sb.append(lastFollowUp).append(",");
	  sb.append(firstProcedure).append(",");
	  sb.append(firstExam).append(",");
	  sb.append(karnofskyScore).append(",");
	  sb.append(firstRadiation).append(",");
	  sb.append(dodMinusDop).append(",");
	  sb.append(dodfuMinusDop).append(",");
	  sb.append(cfId).append(",");
	  sb.append(gender).append(",");
	  sb.append(informedConsentAcquired).append(",");
	  sb.append(ptid);
	  return sb.toString();		
	}
	

}
