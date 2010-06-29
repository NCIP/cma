package gov.nih.nci.cma.domain

class IdMapping {
	
	 String ptid
	 String pdid
	 String sampleId
	 String tissue
	 String filename
	 String platform
	 String analysisFileId
	 String aliquotbarcode
	 Integer mappingId
	  
	 static constraints = {
	  	ptid(blank:false)
	  	pdid(blank:false)
		sampleId(blank:false)
		tissue(nullable:true)
		filename(blank:false)
		platform(blank:false)
		analysisFileId(blank:false)
		aliquotbarcode(nullable:true)
		mappingId(blank:false)
	 }
	 
	 String toString() {"${this.pdid}, ${this.analysisFileId}"}
}
