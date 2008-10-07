import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.caintegrator.studyQueryService.FindingsManager;
import gov.nih.nci.caintegrator.service.task.Task;
import gov.nih.nci.caintegrator.service.task.TaskResult;
import gov.nih.nci.caintegrator.application.configuration.SpringContext;

import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneExprReporter;
import gov.nih.nci.caintegrator.domain.annotation.service.AnnotationManager;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.util.idmapping.IdMapper;
import gov.nih.nci.caintegrator.util.idmapping.IdMappingCriteria;
import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup;
import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup;
import gov.nih.nci.cma.util.ProjectConstants;
import gov.nih.nci.cma.web.graphing.PCAPlot;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.caintegrator.service.findings.PrincipalComponentAnalysisFinding;

import gov.nih.nci.cma.query.dto.ProjectPCAQueryDTO

class PCAService {

	static scope = "request"
	
    boolean transactional = false

    def createDTO = { sessionId, pcaForm ->
    	//validate
    	
    	//create a list for samples, if only 1 list is selected, grails makes it a string, not string[]
    	String[] sgroups = pcaForm.getParameterValues("selectedGroups");
    	
    	IdMapper idMappingManager = SpringContext.getBean("idMappingManager");
    	
//    	QueryDTO queryDTO = dtoBuilder.buildQueryDTO(form, request.getSession().getId());
    	
    	ProjectPCAQueryDTO dto = new ProjectPCAQueryDTO();
    	dto.setQueryName(pcaForm.getParameter("analysisResultName"));
        dto.setArrayDataFileName(pcaForm.getParameter("arrayPlatform"));
        dto.setSessionId(sessionId);
        String percentile = pcaForm.getParameter("variancePercentile");
        String filterType = pcaForm.getParameter("filterType");
        boolean advFilter = pcaForm.getParameter("adv");
        
        if(advFilter == true)	{
        	try	{
        		double percentage = Double.valueOf(percentile);
        		dto.setVariancePercentile(percentage/100);
        	}
        	catch(Exception e)	{
        		//bad validation
        	}
        }
        System.out.println("SID: "+ 	sessionId)
        System.out.println("groups: " + pcaForm.getParameter("sgroups"));
        
        //set patients
        UserListBeanHelper helper = new UserListBeanHelper(sessionId);
        SampleGroup sampleGroup = new SampleGroup("PCASampleGroup");
        //Retrieve sample Id in format of "TCGA.02.0001.01C.01R.00177.01"	
        //and then maintain them in their original groups to be used for
        //PCA graph coloring.
        Set<String> patientIdSet = new HashSet<String>();
        IdMappingCriteria criteria = new IdMappingCriteria();
        criteria.setFileName(pcaForm.getParameter("arrayPlatform"));
        Map<String, String> sampleGroupMap = new HashMap<String, String>();
        for(String listName : sgroups){
        	
        	Collection<String> items = helper.getItemsFromList(listName);
        	patientIdSet.addAll(items);
        	criteria.setPatientIds(patientIdSet);
			//in the format of "TCGA.02.0001.01C.01R.00177.01"	
        	Set<String> idSet = idMappingManager.getRbinaryIdsForPatientDIDs(criteria);
        	sampleGroup.addAll(idSet);
        	for (String id : idSet){
        		sampleGroupMap.put(id, listName);
        	}
        	patientIdSet.clear();
        }
        dto.setSampleGroup(sampleGroup);
        dto.setSampleGroupMap(sampleGroupMap);
        
        //gene report

		String geneReporterName = pcaForm.getParameter("geneReporterName");
		ReporterGroup reporterGroup = new ReporterGroup();
		if (pcaForm.getParameter("adv") && geneReporterName != null && !geneReporterName.equalsIgnoreCase("none")){
			int i = geneReporterName.lastIndexOf(ListType.Reporter.toString());
			//it's a reporter filter
			if (i > 0){
				geneReporterName = geneReporterName.substring(0, i - 3);
		    	List<ListItem> listItemts = helper.getUserList(geneReporterName).getListItems();
		    	
		    	List<String> reporterIdSet = new ArrayList<String>();
				for (Iterator it = listItemts.iterator(); it.hasNext(); ) {
					ListItem item = (ListItem)it.next();
					reporterIdSet.add(item.getName());
				}
				
				reporterGroup.addAll(reporterIdSet);
			}
			//It's a gene
			else{
				i = geneReporterName.lastIndexOf(ListType.Gene.toString());
				geneReporterName = geneReporterName.substring(0, i - 3);
		    	List<ListItem> listItemts = helper.getUserList(geneReporterName).getListItems();
		    	
		    	List<String> geneList = new ArrayList<String>();
				for (Iterator it = listItemts.iterator(); it.hasNext(); ) {
					ListItem item = (ListItem)it.next();
					geneList.add(item.getName().toUpperCase());
				}
				AnnotationCriteria ac = new AnnotationCriteria();
				ac.setGeneSymbols(geneList);
				Map<GeneBiomarker, Collection<GeneExprReporter>> tempMap = null;

				//This is for gene expression, not copy number
				ac.setArrayPlatformName(pcaForm.getParameter("platformName"));
				try{
					tempMap = annotationManager.getReportersForGenes(ac);
					logger.info("Have found " + tempMap.size() + " reporters.");
				} catch (Exception e){
					logger.error("getReportersForGenes returns error: " + e.getMessage());
				}
			
				List<String> rGroup = new ArrayList<String>();
				Set<GeneBiomarker> markerSet = tempMap.keySet();
				for (GeneBiomarker marker : markerSet){
					Collection<GeneExprReporter> c = tempMap.get(marker);
					for (GeneExprReporter reporter : c){
						rGroup.add(reporter.getName());
					}
				}
				reporterGroup.addAll(rGroup);
			}
		}
		if (!reporterGroup.isEmpty())
			dto.setReporterGroup(reporterGroup);

        
        
        
        ///////////
        QueryDTO queryDTO = dto;
    	return queryDTO;
    	//submitQuery(sessionId, queryDTO)
    }
    
    def submitQuery =	{ sessionId, queryDTO ->
 
	   	//lookup dependencies manually (using the caintegrator helper SpringContext)
		FindingsManager findingsManager = (FindingsManager) SpringContext.getBean("CMAFindingsManager");
		PresentationCacheManager presentationCacheManager = (PresentationCacheManager) SpringContext.getBean("presentationCacheManager");

    	Task task = findingsManager.submitQuery(queryDTO);
    	presentationCacheManager.addNonPersistableToSessionCache(sessionId,task.getId(),task);           

    	
    }
    
    def prepareReport =	{ sessionId, taskId ->
    	System.out.println("prepare report for: " + sessionId + " " + taskId)
		PresentationCacheManager presentationCacheManager = (PresentationCacheManager) SpringContext.getBean("presentationCacheManager");
		FindingsManager findingsManager = (FindingsManager) SpringContext.getBean("CMAFindingsManager");

    	PCAPlot plot = new PCAPlot();
	    try {
	    	Task task = (Task) presentationCacheManager.getNonPersistableObjectFromSessionCache(sessionId, 
	    			taskId);
	    	if(task!=null){
	    		TaskResult taskResult =  findingsManager.getTaskResult(task);
	    		if (taskResult != null) {
				   
				    PrincipalComponentAnalysisFinding principalComponentAnalysisFinding = null;
				    Collection resultResult = taskResult.getTaskResults();
				    if (resultResult != null && !resultResult.isEmpty()){
				    	for (Iterator itt = resultResult.iterator(); itt.hasNext();){
				    		principalComponentAnalysisFinding = (PrincipalComponentAnalysisFinding)itt.next();
				    		break;
				    	}
				    }
				    plot.preparePCAPlotDataSet((ProjectPCAQueryDTO)task.getQueryDTO(), principalComponentAnalysisFinding); 
	
//				    request.getSession().setAttribute("pcaPlot", plot);
	    		}
	    	}
	    	else	{
	//    		return (mapping.findForward("failure"));
				System.out.println("Task Null")
	    	}
	    } catch (Exception e) {
	//    	logger.error(e);
	//    	return (mapping.findForward("failure"));
			System.out.println("Caught: " + e.getMessage());
	    } 

	    return plot;
    }
}
