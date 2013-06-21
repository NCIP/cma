/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.service.findings.handlers;

import gov.nih.nci.caintegrator.domain.finding.bean.Finding;
import gov.nih.nci.caintegrator.domain.finding.clinical.brainCancer.GBMClinicalFinding;
import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.caintegrator.studyQueryService.QueryHandler;
import gov.nih.nci.cma.query.dto.ProjectQueryDTO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


/**
 * ClinicalQueryHandler provides the functionality to take in a 
 * ClinicalQueryDTO and then create a criteria object that will allow
 * the execution of that query to bring back the correct finding objects.
 * 
 *
 * @author caIntegrator Team
 */
public class ClinicalQueryHandler implements QueryHandler {
    
    private static Logger logger = Logger.getLogger(ClinicalQueryHandler.class);
    private static final String TIMECOURSE = "timeCourse.name";
    private SessionFactory sessionFactory;
    
    /**
     * @return Returns the sessionFactory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    /**
     * @param sessionFactory The sessionFactory to set.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * Method that looks at the QueryDTO and creates a criteria to
     * be executed.
     *
     * @param query
     * @return
     */
    protected Criteria createCriteria(QueryDTO query) {
        Session s = sessionFactory.getCurrentSession();
        Criteria criteria = s.createCriteria(GBMClinicalFinding.class);
        ProjectQueryDTO clinicalQuery = (ProjectQueryDTO) query;
        
        
        for(String key : clinicalQuery.getQueryMap().keySet()){
            List unknownList = clinicalQuery.getQueryMap().get(key);
            if(unknownList.get(0) instanceof String){
                logger.info("Query params for " + key + " are Strings");
                criteria.add(Restrictions.in(key+".value", clinicalQuery.getQueryMap().get(key)));
            }
            else if(unknownList.get(0) instanceof Double){
                logger.info("Query params for " + key + " are Doubles");
                criteria.add(Restrictions.between(key+".absoluteValue", clinicalQuery.getQueryMap().get(key).get(0),
                        clinicalQuery.getQueryMap().get(key).get(1)));                
            }
        }
        
        return criteria;
    }
    public List<Finding> getResults(QueryDTO dto, Integer page) {
        // TODO Auto-generated method stub
        return null;
    }
    public Integer getResultCount(QueryDTO query) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 
     */
    public boolean canHandle(QueryDTO query){
        if(query instanceof ProjectQueryDTO){
            return true;
        }
        else return false;
    }
    public List<Finding> getResults(QueryDTO query) {
        Criteria criteria = createCriteria(query);
        if(criteria != null)        
        return criteria.list();
        else
            return null;
    }
    
    
}
