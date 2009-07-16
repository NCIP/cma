class RembrandtClinicalView {

	String[] sampleGroup
	String age
	String ageAtDxLower
	String ageAtDxUpper
	String gender
	String survival
	String survivalLower
	String survivalUpper
	String disease
	String race
	String queryName
		
    static transients = ["sampleGroup","age","ageAtDxLower","ageAtDxUpper","gender","survival","survivalLower","survivalUpper","disease","race","queryName"]

	static constraints = {
		sampleGroup(validator: {
			return(it != null)
		})
		//queryName(blank:false)
	}
	
	String toString() {"${this.sampleGroup}, ${this.ageAtDxLower}, ${this.ageAtDxUpper}, ${this.gender}, ${this.survivalLower}, ${this.survivalUpper}, ${this.disease}, ${this.race}, ${this.queryName}" }
	


}
