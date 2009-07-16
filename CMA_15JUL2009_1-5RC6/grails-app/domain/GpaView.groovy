class GpaView {

	String analysisModuleName
	String geneReporterName 
	String[] selectedGroups
	String platformName
	String analysisResultName
		
    static transients = ["analysisModuleName","geneReporterName","selectedGroups","platformName","analysisResultName"]

	static constraints = {
		//analysisModuleName(blank:false)
		//geneReporterName(blank:false)
		selectedGroups(validator: {
			return(it != null)
		})
		platformName(blank:false)
		analysisResultName(blank:false)
	}
	
	String toString() {"${this.analysisModuleName}, ${this.geneReporterName}, ${this.selectedGroups}, ${this.platformName}, ${this.analysisResultName} " }

}
