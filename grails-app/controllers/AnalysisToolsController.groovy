import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.cma.web.graphing.PCAPlot;

class AnalysisToolsController {

	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
	def pCAService;
	
    def index = { redirect(action:menu)  }
    
    def menu =	{
    	render(view:'menu')
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
}
