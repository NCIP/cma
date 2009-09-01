package gov.nih.nci.cma.web;

import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup;
import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneBiomarker;
import gov.nih.nci.caintegrator.domain.annotation.gene.bean.GeneExprReporter;
import gov.nih.nci.caintegrator.domain.annotation.service.AnnotationManager;
import gov.nih.nci.caintegrator.domain.annotation.service.AnnotationManagerImpl;
import gov.nih.nci.caintegrator.plots.services.KMReporterService;
import gov.nih.nci.caintegrator.service.findings.ExpressionLookupFinding;
import gov.nih.nci.caintegrator.studyQueryService.dto.annotation.AnnotationCriteria;
import gov.nih.nci.caintegrator.util.CaIntegratorConstants;
import gov.nih.nci.caintegrator.util.PlatformMapping;
import gov.nih.nci.caintegrator.util.idmapping.IdMapper;
import gov.nih.nci.caintegrator.util.idmapping.IdMappingCriteria;
import gov.nih.nci.cma.web.graphing.GEPlot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public class GeneSearch {

	private AnnotationManager annotationManager;// = new AnnotationManagerImpl();
	private Map<GeneBiomarker, Collection<GeneExprReporter>> tempMap = null;
	private IdMapper idMappingManager;
	private KMReporterService kmReporterService;;
	
	private String chartType;
	private String geneName;
	private String binaryFileName;
	private String arrayPlatformName;
	
	private Map<String, String> kmRequestMap;
	
	public GeneSearch(AnnotationManager am, IdMapper idm, KMReporterService kms)	{
		annotationManager = am;
		idMappingManager = idm;
		kmReporterService = kms;
	}
	
	public Map lookupReportersForQuickSearch(HttpServletRequest request)	{
		System.out.println("running quickSearch.............");
		
    	chartType = request.getParameter("plot");
		geneName = request.getParameter("geneSymbol");
		binaryFileName = request.getParameter("geArrayPlatform");
		System.out.println("binaryFileName: " + binaryFileName);
		
		if (geneName != null){
			geneName = geneName.trim();
		}
		if (!chartType.equals(CaIntegratorConstants.SAMPLE_KMPLOT)){
			if ((geneName == null || geneName.length() == 0)){
			}
		}

		arrayPlatformName = request.getParameter("platformName");
        System.out.println(arrayPlatformName);

        
		//For now validate through reporter fetch
		if (geneName != null && geneName.length() > 0 && !chartType.equals(CaIntegratorConstants.SAMPLE_KMPLOT)) {
			AnnotationCriteria ac = new AnnotationCriteria();
			List<String> geneSymbols = new ArrayList<String>();
			geneSymbols.add(geneName.toUpperCase());
			ac.setGeneSymbols(geneSymbols);
			
			//This is for gene expression, not copy number
			ac.setArrayPlatformName(arrayPlatformName);
			try{
				System.out.println("annotationManager: " + annotationManager);
				System.out.println("geneName:  " + geneName + " arrayPlatformName: " + arrayPlatformName);
				tempMap = annotationManager.getReportersForGenes(ac);
			} catch (Exception e){
				//logger.error("getReportersForGenes returns error: " + e.getMessage());
				System.out.println("annotationManager bombed: " );
				e.printStackTrace();
			}
		}
		
		if ((geneName == null || geneName.length() == 0) || tempMap == null || tempMap.isEmpty()){
			//ERROR
			System.out.println("geneName: " + geneName + "  tempMap: " + tempMap);
		}

        System.out.println(chartType);
		
		return tempMap;
	}
	
	public void genePlot(HttpServletRequest request)	{
		
        	try{
				ReporterGroup reporterGroup = new ReporterGroup();
 				Set<GeneBiomarker> markerSet = tempMap.keySet();
 				for (GeneBiomarker marker : markerSet){
 					Collection<GeneExprReporter> c = tempMap.get(marker);
 					for (GeneExprReporter reporter : c){
 						reporterGroup.add(reporter.getName());
 					}
 				}
 					
//				logger.debug("user has requested geneExpPlot");
 				//TODO:  validate this and set defaults
 				//String[] patientGroups = request.getParameterValues("sampleGroupNameMultiple");
 				String[] patientGroups = request.getParameterValues("sampleGroups");
				List<SampleGroup> sampleGroups = new ArrayList<SampleGroup>();
				// ALL_PATIENTS, High_Survival, Med_Survival, Low_Survival
				
				System.out.println("in genePlot");
				
			    UserListBeanHelper helper = new UserListBeanHelper(request.getSession().getId());
			    IdMappingCriteria criteria = new IdMappingCriteria();
			    	
				for (String patientGroup : patientGroups){
					SampleGroup sampleGroup = new SampleGroup(patientGroup);
					Set<String> idSet = null;
				    List<ListItem> listItemts = helper.getUserList(patientGroup).getListItems();
				    Set<String> patientIdSet = new HashSet<String>();
					for (Iterator i = listItemts.iterator(); i.hasNext(); ) {
						ListItem item = (ListItem)i.next();
						patientIdSet.add(item.getName());
					}
					criteria.setPatientIds(patientIdSet);
					criteria.setFileName(binaryFileName);
					//in the format of "TCGA.02.0001.01C.01R.00177.01"	
				    idSet = idMappingManager.getRbinaryIdsForPatientDIDs(criteria);
				    for (String id : idSet){
				    	sampleGroup.add(id);
				    }
				    sampleGroups.add(sampleGroup);

				    GEPlot plot = new GEPlot(geneName);

				    int status = plot.prepareGeneGraphDataSet(binaryFileName, sampleGroups, reporterGroup);
					if (status == -1) {
						//ERR
						request.getSession().setAttribute("gePlot", null);
					}
					else	{
						request.getSession().setAttribute("gePlot", plot);
						System.out.println("plot in session");
					}
				}
//				return mapping.findForward("histogram");
			} catch (Exception e) {
                System.out.println("caught ex");
                e.printStackTrace();
//				logger.error("Gene Expression Plot Flopped");
//				logger.error(e);
//				return mapping.findForward("error");
			}
		}

	public void geneKMPlot(HttpServletRequest request)	{
		//legacy code:  
		//this is dependent on the KM code, which requires certain things to be in the request 
		//these items are collected in kmRequestMap
		
		//String[] sgnm = request.getParameterValues("sampleGroupNameMultiple");
		String[] sgnm = request.getParameterValues("sampleGroups");
		if(sgnm!=null && sgnm.length > 0){
			System.out.println("got sampleGroupNameMultiple");
			Map<String, String> m = new HashMap<String, String>();
			m.put("plotType", CaIntegratorConstants.GENE_EXP_KMPLOT);
			//ExpressionLookupFinding finding = kmReporterService.getExpressionFinding(geneName,gsForm.getGeArrayPlatform(), gsForm.getSampleGroupNameMultiple()[0], request.getSession().getId());

			ExpressionLookupFinding finding = kmReporterService.getExpressionFinding(geneName,binaryFileName, 
					sgnm[0], request.getSession().getId());
			System.out.println("got finding");

			if(finding!=null && finding.getDataVectors()!=null){
			    m.put("reporter",finding.getDataVectors().get(0).getName());
			    m.put("taskId",finding.getTaskId());
			    kmRequestMap = m;
			}
			else	{
				System.out.println("******************* Finding Null ********************");
			}
		}
		else	{
			System.out.println("******************* sampleGroupNameMultiple Null ********************");
		}
		//this far means error
		return;
	}
	
	public AnnotationManager getAnnotationManager() {
		return annotationManager;
	}

	public void setAnnotationManager(AnnotationManager annotationManager) {
		this.annotationManager = annotationManager;
	}

	public IdMapper getIdMappingManager() {
		return idMappingManager;
	}

	public void setIdMappingManager(IdMapper idMappingManager) {
		this.idMappingManager = idMappingManager;
	}

	public KMReporterService getKmReporterService() {
		return kmReporterService;
	}

	public void setKmReporterService(KMReporterService kmReporterService) {
		this.kmReporterService = kmReporterService;
	}

	public Map<String, String> getKmRequestMap() {
		return kmRequestMap;
	}
	
}
