import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.cma.web.graphing.PCAPlot;
import gov.nih.nci.caintegrator.application.analysis.gp.GenePatternIntegrationHelper;

class AnalysisToolsController {

	String gpHomeURL = "";
	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
		gpHomeURL = GenePatternIntegrationHelper.gpHomeURL(request);
	}
	
	def pCAService;
	def gPService;
	
    def index = { redirect(action:menu)  }
    
    def menu =	{
    	render(view:'menu', model:[gpHomeURL:gpHomeURL])
    }
    
    def pcaSetup = { 
    	def patLists = defaultListLoaderService.getPatientLists(session.id, false);
    	def geneLists = defaultListLoaderService.getGeneLists(session.id, false);
    	
    	render(view:'pcaSetup', model:[patLists:patLists, geneLists:geneLists])
    }
    
    def pcaSubmit = {
    		//TODO:  if only 1 is selected, a nullptr is thrown in the service
//    		String[] sgroups = request.getParameterValues("selectedGroups");
//    		params.sgroups = sgroups;
    	//had to pass entire request, not just params, b/c grails cant properly make a mult
    	//select a string[] if only 1 thing is selected.
		
    	if(params.selectedGroups == null)	{
    		//flash and redirect
    		flash.message = "Please select at least one Sample Group"
            redirect(controller:"analysisTools", action:"pcaSetup")
            return
    	}
    	if(params.arrayPlatform == null || params.arrayPlatform == "")	{
    		//flash and redirect
    		flash.message = "Please select a Platform"
            redirect(controller:"analysisTools", action:"pcaSetup")
            return
    	}
    	if(params.analysisResultName == null || params.analysisResultName == "")	{
    		//flash and redirect
    		flash.message = "Please select a Name for your Analysis"
            redirect(controller:"analysisTools", action:"pcaSetup")
            return
    	}
    	
    	QueryDTO queryDTO = pCAService.createDTO(session.getId(), request)
		
		pCAService.submitQuery(session.getId(), queryDTO);
		
		//task should be put in cache and checked via inbox
		//TODO:  WONT forward properly using this notation.
		redirect(controller: 'inbox', action:'index');
		return;
    }
    
    def pcaPlot = {
    		
    	PCAPlot	pcaPlot = pCAService.prepareReport(session.getId(), params.taskId);
//    	request.getSession().setAttribute("pcaPlot", plot)
    	
//    	PCAPlot pcaPlot = (PCAPlot)request.getSession().getAttribute("pcaPlot"); 
		StringWriter sw = new StringWriter()

    	String PC1vsPC2 = pcaPlot.generatePCAPlotChart("PC1vsPC2", request, new PrintWriter(sw)); 
    	String PC1vsPC3 = pcaPlot.generatePCAPlotChart("PC1vsPC3", request, new PrintWriter(sw)); 
    	String PC2vsPC3 = pcaPlot.generatePCAPlotChart("PC2vsPC3", request, new PrintWriter(sw)); 
    	String PC1vsPC2URL = request.getContextPath() + "/servlet/DisplayChart?filename=" + PC1vsPC2; 
    	String PC1vsPC3URL = request.getContextPath() + "/servlet/DisplayChart?filename=" + PC1vsPC3; 
    	String PC2vsPC3URL = request.getContextPath() + "/servlet/DisplayChart?filename=" + PC2vsPC3; 
    	String defaultFilename = PC1vsPC2; 
    	String defaultURL = PC1vsPC2URL; 
    	
    	render(view: 'pcaPlot', model:[sw:sw, PC1vsPC2:PC1vsPC2, PC1vsPC3:PC1vsPC3,
    	      PC2vsPC3:PC2vsPC3, PC1vsPC2URL:PC1vsPC2URL, PC1vsPC3URL:PC1vsPC3URL, PC2vsPC3URL:PC2vsPC3URL,
    	      defaultFilename:defaultFilename, defaultURL:defaultURL]);
    }
    
    def genePatternSetup = {
    		
        	def patLists = defaultListLoaderService.getPatientLists(session.id, false);
        	
        	// Get the data context 
    		def dataContext = grailsApplication.config.cma.dataContext
    		// Displays or hides certain GP setup page elements
    		String displayEl = ""
    		
    		if (dataContext.equalsIgnoreCase("TCGA")) {
    			// Below not used for Rembrandt data
    			def geneLists = defaultListLoaderService.getGeneLists(session.id, false);
        	
        		// 'Copy Number' not used for Rembrandt data. That leaves 
        		// a selection of one, so don't bother with list for Rembrandt.
        		def moduleList = ["Gene Expression"] //, "Copy Number"
        		
        		// Display some elements not visible for dataContext="Rembrandt"
        		//displayEl = "display:inline"
        	
        		render(view:'genePatternSetup', model:[patLists:patLists, geneLists:geneLists, moduleList:moduleList])
    		}
    		else if (dataContext.equalsIgnoreCase("Rembrandt")) {
    			//the two lines below are bad code, why would you do this?
    			// Hide some elements not visible for dataContext="Rembrandt"
    			//displayEl = "display:none"
    			def moduleList = ["Gene Expression"]
    			render(view:'genePatternSetup', model:[patLists:patLists, moduleList:moduleList])
    		}
    }
    
    def genePatternSubmit = {
    		/*
        	println(params['analysisModuleName'])
        	println(params['selectedGroups'])
        	println(params['geneReporterName'])
        	println(params['platformName'])
        	println(params['chromosomeName'])
        	println(params['analysisResultName'])
        	*/
        	
        	gPService.generateGPSubmission(request)

        	render(view:'genePatternJobView')        	     	
    }
}
