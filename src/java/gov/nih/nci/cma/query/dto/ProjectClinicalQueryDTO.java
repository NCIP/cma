/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

/**
 * 
 */
package gov.nih.nci.cma.query.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author rossok
 *
 */
public class ProjectClinicalQueryDTO implements ProjectQueryDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ProjectClinicalQueryDTO.class);

    Map<String, List> queryMap = new HashMap<String, List>();
    String queryName;
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cma.query.dto.ProjectQueryDTO#getQueryMap()
     */
    public Map<String, List> getQueryMap() {
        return this.queryMap;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cma.query.dto.ProjectQueryDTO#setQueryMap(java.util.Map)
     */
    public void setQueryMap(Map<String, List> queryMap) {
        this.queryMap = queryMap;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.caintegrator.dto.query.QueryDTO#getQueryName()
     */
    public String getQueryName() {
        return this.queryName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.caintegrator.dto.query.QueryDTO#setQueryName(java.lang.String)
     */
    public void setQueryName(String name) {
       this.queryName = name;
    }

}
