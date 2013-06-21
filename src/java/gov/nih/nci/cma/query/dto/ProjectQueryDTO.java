/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.query.dto;

import gov.nih.nci.caintegrator.dto.query.QueryDTO;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * ProjectQueryDTO stores all data recieved from the UI.
 * 
 * @author rossok
 *
 */
public interface ProjectQueryDTO extends QueryDTO {

    
    /**
     * @return the queryMap
     */
    public Map<String, List> getQueryMap();
    /**
     * @param queryMap the queryMap to set
     */
    public void setQueryMap(Map<String, List> queryMap);
   

}
