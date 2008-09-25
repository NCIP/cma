import gov.nih.nci.caintegrator.application.analysis.AnalysisHelper
import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup
import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup
import gov.nih.nci.caintegrator.service.findings.ExpressionLookupFinding
import gov.nih.nci.caintegrator.analysis.messaging.DataPointVector

class GeneViewController {

    def index = { 
    	
    	
    	//SAMPLE Call to the analysis server
    	ReporterGroup rg = new ReporterGroup()
    	rg.add("207848_at")
    	    	    	
    	
    	SampleGroup sg = new SampleGroup()
    	sg.add("TCGA-02-0071-01A-01R-0202-01")
    	sg.add("TCGA-12-0618-01A-01R-0306-01")
    	
    	String rbinaryFileName  = "TCGA.affyhg-u133a_4_3_08.Rda"
    	    	    
    	//try sending a request
    	ExpressionLookupFinding finding = AnalysisHelper.getExpressionValuesForReporters(rg, rbinaryFileName, sg)
    	    	
    	List<DataPointVector> dataVectors = finding.getDataVectors()
    	
    	if (dataVectors != null) {
    	  System.out.println("Call to getExprValues returned numResults=${dataVectors.size()}")
    	}
    	else {
    	  System.out.println("No data vectors returned")	
    	}
    	//END SAMPLE CALL to the analysis server.
    	
    	render(view:'main')
    }
}
