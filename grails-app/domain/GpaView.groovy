class GpaView {

	String analysisModuleName
	String geneReporterName 
	String[] selectedGroups
	//String platformName
	String arrayPlatform
	String analysisResultName
		
    static transients = ["analysisModuleName","geneReporterName","selectedGroups","arrayPlatform","analysisResultName"]

	static constraints = {
		//analysisModuleName(blank:false)
		//geneReporterName(blank:false)
		selectedGroups(validator: {
			return(it != null)
		})
		arrayPlatform(blank:false)
		analysisResultName(blank:false)
	}
	
	String toString() {"${this.analysisModuleName}, ${this.geneReporterName}, ${this.selectedGroups}, ${this.arrayPlatform}, ${this.analysisResultName} " }

}
