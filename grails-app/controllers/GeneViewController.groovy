  import grails.converters.*
  
  import org.springframework.web.context.support.WebApplicationContextUtils;
  import org.springframework.context.ApplicationContext 
  
  import org.codehaus.groovy.grails.web.context.ServletContextHolder;
  import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;

  import gov.nih.nci.caintegrator.application.analysis.AnalysisHelper
  import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup
  import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup
  import gov.nih.nci.caintegrator.service.findings.ExpressionLookupFinding
  import gov.nih.nci.caintegrator.analysis.messaging.DataPointVector
  import gov.nih.nci.caintegrator.util.CaIntegratorConstants

  import gov.nih.nci.cma.web.GeneSearch;
  import gov.nih.nci.cma.web.graphing.GEPlot
  import gov.nih.nci.cma.web.graphing.LegendCreator


class GeneViewController {
    
    // Define the GeneView domain class/bean  
    GeneView geneView

	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
    def index = {     	
    	// Fetch the patient lists to populate the form
    	def patLists = defaultListLoaderService.getPatientLists(session.id, false);
    	String pwLink = System.getProperty("gov.nih.nci.cma.links.pathway_url");
    	String gwbLink = System.getProperty("gov.nih.nci.cma.links.genomeworkbench_url");
    	
    	render(view:'main', model:[patLists:patLists, pwLink:pwLink, gwbLink:gwbLink])
    }
    
    def geneBasedView = {        	
    	// Bind request parameters onto properties of the GeneView bean
	  	geneView = new GeneView(params) 
	  	  		  		  	  	
	  	if(geneView.validate()) {
	    	def geneExpPlot = params["plot"]
	    	
	    	// Get the beans from spring, injection wont work here
	    	ApplicationContext ctx =
				ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
			def annotationManager = ctx.getBean('annotationManager') 
			def idMappingManager = ctx.getBean('idMappingManager');
	    	//should be available via the km jar
	    	def kmReporterService = ctx.getBean('kmReporterService');
	    	
	    	String geneSymbol = params['geneSymbol']
	    	
	    	def gs = new GeneSearch(annotationManager, idMappingManager, kmReporterService)
	    	def annotationsMap = gs.lookupReportersForQuickSearch(request)
	
			if(annotationsMap == null || annotationsMap.size()==0)	{
	    		//no reporters, so dont plot anything
	    		flash.message = "No reporters for this gene and platform"
	            redirect(controller:"geneView")
	            return
	    	}
	    	
	    	if (params['plot'] == 'geneExpPlot' )	{
		    	gs.genePlot(request)
		    	
		        redirect(controller:"geneView",action:"genePlot")
		        return
		    }
	    	else if (params['plot'] == CaIntegratorConstants.GENE_EXP_KMPLOT )	{
		    	gs.geneKMPlot(request)
		    	def kmRequestMap = gs.getKmRequestMap()
		    	if(kmRequestMap == null)	{
		    		flash.message = "Error performing KM Plot.  Please select different parameters and retry."
		            redirect(action:index)
		            return
		    	}
		    	else	{
		    		params.taskId = kmRequestMap.get("taskId")
		    		params.plot = kmRequestMap.get("plotType")
		    		params.plotType = kmRequestMap.get("plotType")
		    		params.reporter = kmRequestMap.get("reporter")
		    		redirect(action:'kmPlot', params:params)
		    		return
		    	}
		    }
	    	else	{
	    		flash.message = "Please select a  valid plot type"
	            redirect(action:index)
	            return
	    	}
        } else {
	        List selectedSampleGrpList
	        if ( request.getParameterValues("sampleGroups") != null ) {
		        selectedSampleGrpList = Arrays.asList(request.getParameterValues("sampleGroups"))
		    }

	    	def patLists = defaultListLoaderService.getPatientLists(session.id, false);
	    	String pwLink = System.getProperty("gov.nih.nci.cma.links.pathway_url");
	    	String gwbLink = System.getProperty("gov.nih.nci.cma.links.genomeworkbench_url");
	        render(view:'main',model:[geneView:geneView, patLists:patLists, pwLink:pwLink, gwbLink:gwbLink, selectedSampleGrpList:selectedSampleGrpList])
        }
        
    
    	/*
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
    	//should be available via the km jar
    	def kmReporterService = ctx.getBean('kmReporterService');
    	
    	
    	String geneSymbol = params['geneSymbol']

    	//    	return below otherwise you get illegalstateexceptions: committed ???, so always return after redirect?
    	if(geneSymbol.length() == 0)	{
    		flash.message = "Please Enter a Gene Symbol"
            redirect(controller:"geneView")
            return 
    	}
    	if(params.sampleGroupNameMultiple == null)	{
    		flash.message = "Please Select a Sample Group"
            redirect(controller:"geneView")
            return 
    	}
    	if(params.geArrayPlatform == null || params.geArrayPlatform == "")	{
    		flash.message = "Please Select a Platform"
            redirect(controller:"geneView")
            return 
    	}
    	
    	
    	
    	def gs = new GeneSearch(annotationManager, idMappingManager, kmReporterService)
    	def annotationsMap = gs.lookupReportersForQuickSearch(request)

		if(annotationsMap == null || annotationsMap.size()==0)	{
    		//no reporters, so dont plot anything
    		flash.message = "No reporters for this gene and platform"
            redirect(controller:"geneView")
            return
    	}
    	
    	
    	
    	if (params['plot'] == 'geneExpPlot' )	{
	    	gs.genePlot(request)
	    	
	        redirect(controller:"geneView",action:"genePlot")
	        return
	    }
    	else if (params['plot'] == CaIntegratorConstants.GENE_EXP_KMPLOT )	{
	    	gs.geneKMPlot(request)
	    	def kmRequestMap = gs.getKmRequestMap()
	    	if(kmRequestMap == null)	{
	    		flash.message = "Error performing KM Plot.  Please select different parameters and retry."
	            redirect(action:index)
	            return
	    	}
	    	else	{
	    		//redirect(controller:"geneView",action:"kmPlot")
	    		//render view w/ plotType, reporter, taskId
	    		// "taskId":"1222897098350","geArrayPlatform":"TCGA.affyhg-u133a_4_3_08.Rda","pathwayName":"h_RELAPathway","quickSearchType":"Gene Keyword HUGO","reporter":"211551_at","geneSymbol":"EGFR","plot":"GE_KM_PLOT","method":"quickSearch","sampleGroupNameMultiple":"Med_Survival"
//	    		redirect(action:"kmPlot", params:[plot:kmRequestMap.get("plotType"), plotType:kmRequestMap.get("plotType"), 
//	    		        reporter:kmRequestMap.get("reporter"), taskId:kmRequestMap.get("taskId"), geArrayPlatform:'',
//	    		        pathwayName:'', quickSearchType:'', geneSymbol:'', sampleGroupNameMultiple:''])
	    		
	    		params.taskId = kmRequestMap.get("taskId")
	    		params.plot = kmRequestMap.get("plotType")
	    		params.plotType = kmRequestMap.get("plotType")
	    		params.reporter = kmRequestMap.get("reporter")
	    		redirect(action:'kmPlot', params:params)
	    		return
	    	}
	    }
    	else	{
    		flash.message = "Please select a  valid plot type"
            redirect(action:index)
            return
    	}
    	*/
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
				
				String geArrayPlatform = (String)request.getSession().getAttribute("geArrayPlatform");
				println "\n\nThe geArrayPlatform = " + geArrayPlatform + "\n\n";
				//legendHtml = LegendCreator.buildLegend(gePlot.getLegendItemCollection(), "Probesets");
				legendHtml = LegendCreator.buildLegend(geArrayPlatform, gePlot.getLegendItemCollection(), "Probesets");
					
				//String size = (String) charts.get("size");
					
				gmgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + gmfilename;
				log2graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + log2filename;
				bwgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + bwFilename;
				defaultURL = gmgraphURL;  //log2graphURL; //bwgraphURL;
				
				render(view:'genePlot', model:[sw:sw, geneSymbol:geneSymbol, legendHtml:legendHtml,
				                   	        gmfilename:gmfilename, log2filename:log2filename, bwFilename:bwFilename,
				                   			gmgraphURL:gmgraphURL, log2graphURL:log2graphURL, bwgraphURL:bwgraphURL, 
				                   			defaultURL:defaultURL, defaultFilename:defaultFilename])
				return
			}	
			else	{
				flash.message = "No Data found for this gene and platform combination.  Please select a different gene or platform and try again."
	            redirect(action:index)
	            return
			}
    }
    
    def popCoinGraph = {
    		String reporterName = params["reporter"]
    		String geneSymbol = params["geneSymbol"]
    		GEPlot gePlot = (GEPlot)request.getSession().getAttribute("gePlot");
    		gePlot.setGeneSymbol(geneSymbol);
    		gePlot.setReporterName(reporterName);
    		
    		StringWriter sw = new StringWriter()
    		
    		geneSymbol = gePlot.getGeneSymbol();
    		reporterName = gePlot.getReporterName();

    		String bwFilename = gePlot.generateBWLog2IntensityChart("Groups", 
    				"Log2 Expression Intensity", request.getSession(), new PrintWriter(sw), true);

    		String bwgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + bwFilename;
    			
    		render(view:"popCoinPlot", model:[sw:sw, bwgraphURL:bwgraphURL, bwFilename:bwFilename, geneSymbol:geneSymbol, reporterName:reporterName])
    }
    
    def kmPlot = {
    	//set the params we need in the page - this comes from the legacy code
    	def sreq = params as JSON
    	def path = request.getContextPath()
    	def sessionId = session.getId()

    	// removing basePath and <base> tag caused problems wtih dwrspring path
        def cc_NOT_INCLUDED = CaIntegratorConstants.NOT_INCLUDED
        def cc_GENE_EXP_KMPLOT = CaIntegratorConstants.GENE_EXP_KMPLOT
//    	render(view:"geneKmPlot", params:params)
        render(view:"geneKmPlot_1", model:[plot:params.plot,listItems:sreq, sessionId:sessionId, 
           cc_NOT_INCLUDED:cc_NOT_INCLUDED, cc_GENE_EXP_KMPLOT:cc_GENE_EXP_KMPLOT])
    }
    //pass gene symbol on model params['geneSymbol']
    def test = { render(view:'geGraph_tile') }
}
