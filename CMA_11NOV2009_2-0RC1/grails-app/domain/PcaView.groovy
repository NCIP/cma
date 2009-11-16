class PcaView {

	String[] selectedGroups
	String adv 
	String arrayPlatform
	String platformName
	String analysisResultName
		
    static transients = ["adv","selectedGroups","arrayPlatform","platformName","analysisResultName"]

	static constraints = {
		selectedGroups(validator: {
			return(it != null)
		})
		arrayPlatform(blank:false)
		analysisResultName(blank:false)
	}
	
	String toString() {"${this.adv}, ${this.selectedGroups}, ${this.arrayPlatform}, ${this.platformName}, ${this.analysisResultName} " }
	


}
