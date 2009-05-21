class ClinicalView {

	String[] sampleGroups
	String patientId
	String tumorTissueSite
	String queryName
		
    static transients = ["sampleGroups","patientId","tumorTissueSite","queryName"]

	static constraints = {
		sampleGroups(validator: {
			return(it != null)
		})
		/*
		patientId(blank:false)
		tumorTissueSite(blank:false)
		queryName(blank:false)
		*/
	}
	
	String toString() {"${this.sampleGroups}, ${this.patientId}, ${this.tumorTissueSite}, ${this.queryName}" }
	


}
