/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.service.findings.handlers;

import gov.nih.nci.caintegrator.service.findings.PrincipalComponentAnalysisFinding;
import gov.nih.nci.caintegrator.service.findings.Finding;
import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.caintegrator.studyQueryService.QueryHandler;

import java.util.List;
import java.util.ArrayList;
import gov.nih.nci.caintegrator.application.analysis.AnalysisServerClientManager;
import gov.nih.nci.caintegrator.application.cache.BusinessTierCache;
import gov.nih.nci.caintegrator.application.cache.CacheFactory;
import gov.nih.nci.caintegrator.analysis.messaging.PrincipalComponentAnalysisRequest;
import gov.nih.nci.caintegrator.enumeration.FindingStatus;
import gov.nih.nci.cma.query.dto.ProjectPCAQueryDTO;

import org.apache.log4j.Logger;


import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * PCAHandler provides the functionality to take in a 
 * ProjectPCAQueryDTO and then create a PrincipalComponentAnalysisRequest object 
 * that sends a Request to analysis serve to do computation for Principal Component Analysis.
 * The returned finding objects were returne as a list.
 * 
 *
 * @author caIntegrator Team
 */
public class PCAHandler implements QueryHandler {
    
    private static Logger logger = Logger.getLogger(ClinicalQueryHandler.class);
	private PrincipalComponentAnalysisRequest pcaRequest;
	//private PrincipalComponentAnalysisFinding pcaFinding;
	private BusinessTierCache businessCacheManager;
    
    /**
     * 
     */
    public boolean canHandle(QueryDTO query){
        if(query instanceof ProjectPCAQueryDTO){
            return true;
        }
        else return false;
    }
    public Integer getResultCount(QueryDTO query) {
        // TODO Auto-generated method stub
        return null;
    }
    public List<Finding> getResults(QueryDTO dto, Integer page) {
        // TODO Auto-generated method stub
        return null;
    }
    public List<Finding> getResults(QueryDTO query) {
    	ProjectPCAQueryDTO myQueryDTO = (ProjectPCAQueryDTO)query;
		pcaRequest = new PrincipalComponentAnalysisRequest(myQueryDTO.getSessionId(), myQueryDTO.getQueryName());
		pcaRequest.setVarianceFilterValue(myQueryDTO.getVariancePercentile());
		
		pcaRequest.setDataFileName(myQueryDTO.getArrayDataFileName());
		
		pcaRequest.setReporterGroup(myQueryDTO.getReporterGroup());
		
		if (myQueryDTO.getSampleGroup() != null && !myQueryDTO.getSampleGroup().isEmpty()){
			pcaRequest.setSampleGroup(myQueryDTO.getSampleGroup());
		}
		else{
			//throw new FindingsAnalysisException("PC Analysis requires a set of samples");
			logger.error("PC Analysis requires a set of samples");
		}
        try {

        	AnalysisServerClientManager analysisServerClientManager = AnalysisServerClientManager.getInstance();
        	BusinessTierCache btcache = CacheFactory.getBusinessTierCache();
            
            PrincipalComponentAnalysisFinding pcaFinding = 
            	new PrincipalComponentAnalysisFinding(myQueryDTO.getSessionId(), myQueryDTO.getQueryName(), FindingStatus.Running, null);

            btcache.addToSessionCache(myQueryDTO.getSessionId(), myQueryDTO.getQueryName(), pcaFinding);
        	analysisServerClientManager.setCache(btcache);
			analysisServerClientManager.sendRequest(pcaRequest);
		} catch (NamingException e) {               
            logger.error(new IllegalStateException("NamingException Error getting an instance of  AnalysisServerClientManager" ));
            logger.error(e.getMessage());
            logger.error(e);
        } catch (JMSException e) {
			logger.error("JMSException: " + e.getMessage());
  			//throw new FindingsAnalysisException(e.getMessage());
		} catch(Exception e){
			logger.error("Exception: "  + e.getMessage());
			//throw new FindingsAnalysisException("Error in setting PrincipalComponentAnalysisRequest object");
		}
        Finding finding = businessCacheManager.getSessionFinding(myQueryDTO.getSessionId(), myQueryDTO.getQueryName());

        while (finding.getStatus() == FindingStatus.Running){
        	try {
        		Thread.sleep(200);
        	}catch (Exception e){
        		logger.error(e.getMessage());
        	}
        	finding = businessCacheManager.getSessionFinding(myQueryDTO.getSessionId(), myQueryDTO.getQueryName());
        }
        List<Finding> list = new ArrayList<Finding>();
        list.add(finding);

        return list;
    }
	public BusinessTierCache getBusinessCacheManager() {
		return businessCacheManager;
	}
	public void setBusinessCacheManager(BusinessTierCache businessCacheManager) {
		this.businessCacheManager = businessCacheManager;
	}
    
    
}
