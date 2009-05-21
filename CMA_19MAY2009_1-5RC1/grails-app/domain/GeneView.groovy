class GeneView {

	String plot
	String geneSymbol 
	String[] sampleGroups
	String geArrayPlatform
	String pathwayVisualization
		
    static transients = ["plot","geneSymbol","sampleGroups","geArrayPlatform","pathwayVisualization"]

	static constraints = {
		plot(blank:false)
		geneSymbol(blank:false)
		sampleGroups(validator: { val, obj ->
			return( (val != null) || ((obj.plot == 'genomeWorkbench') && (val == null)) )
		})		
		geArrayPlatform(blank:false)
		pathwayVisualization(blank:false)
	}
	
	String toString() {"${this.plot}, ${this.geneSymbol}, ${this.sampleGroups}, ${this.geArrayPlatform}, ${this.pathwayVisualization} " }
	

}
