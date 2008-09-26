import gov.nih.nci.cma.web.GeneSearch;
import gov.nih.nci.cma.web.graphing.GEPlot
import gov.nih.nci.cma.web.graphing.LegendCreator
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.springframework.context.ApplicationContext 
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;

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
    
    def geneBasedView = {
    	def geneExpPlot = params["plot"]
    	//call the injected service passing validated params
    	//render appropriate page or error message
    	//render(view:'genePlot', model:[geneExpPlot:geneExpPlot])	
    	
    	//instantiate the pojo and call the quickSearch method passing req
    	
    	//get the bean from spring, injection wont work here
    	ApplicationContext ctx =
			ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
			def annotationManager = ctx.getBean('annotationManager') 
			def idMappingManager = ctx.getBean('idMappingManager');
    	def gs = new GeneSearch(annotationManager, idMappingManager)
    	gs.quickSearch(request)
        //render(view:'genePlot');

    	//should have put the GPlot in the session, ready to display
    	//if params['plot'] == genePlot (not KM)
    	def geneSymbol = params['geneSymbol']
        redirect(controller:"geneView",action:"genePlot")
    }
    
    def genePlot = {
    		
    		//String geneSymbol = (String) request.getAttribute("geneSymbol");
    		
    		String bwFilename = new String("");
    		String log2filename = new String("");
    		String gmfilename = new String("");
    		String legendHtml = new String("");
    		String gmgraphURL = new String("");
    		String log2graphURL = new String("");
    		String bwgraphURL = new String("");
    		String defaultURL = new String("");
    		String defaultFilename = new String("");
    		
    		StringWriter sw = new StringWriter()
    		def geneSymbol = ""

    			GEPlot gePlot = (GEPlot)request.getSession().getAttribute("gePlot");
    			if(gePlot != null)	{
    				geneSymbol = gePlot.getGeneSymbol();
    				
    				bwFilename = gePlot.generateBWLog2IntensityChart("Groups", 
    							"Log2 Expression Intensity", request.getSession(), new PrintWriter(sw), false);
    				log2filename = gePlot.generateLog2Chart("Disease Type", 
    							"Log2 Expression Intensity", request.getSession(), new PrintWriter(sw));
    				
    				gmfilename = gePlot.generateGeometricMeanIntensityChart("Group", 
    							"Mean Expression Intensity", request.getSession(), new PrintWriter(sw));
    					
    				defaultFilename = gmfilename; //log2filename; //bwFilename;
    				legendHtml = LegendCreator.buildLegend(gePlot.getLegendItemCollection(), "Probesets");
    					
    				//String size = (String) charts.get("size");
    					
    				gmgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + gmfilename;
    				log2graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + log2filename;
    				bwgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + bwFilename;
    				defaultURL = gmgraphURL;  //log2graphURL; //bwgraphURL;
    			}	
    		
    		
    	render(view:'genePlot', model:[sw:sw, geneSymbol:geneSymbol, legendHtml:legendHtml,
    	        gmfilename:gmfilename, log2filename:log2filename, bwFilename:bwFilename,
    			gmgraphURL:gmgraphURL, log2graphURL:log2graphURL, bwgraphURL:bwgraphURL, 
    			defaultURL:defaultURL, defaultFilename:defaultFilename])
    }
    //pass gene symbol on model params['geneSymbol']
    def test = { render(view:'geGraph_tile') }
}
