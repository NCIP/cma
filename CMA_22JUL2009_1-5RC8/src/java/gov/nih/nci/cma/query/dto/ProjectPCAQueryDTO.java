/**
 * 
 */
package gov.nih.nci.cma.query.dto;

import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup;
import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup;
import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.HashMap;


/**
 * @author rossok
 *
 */
public class ProjectPCAQueryDTO implements QueryDTO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ProjectPCAQueryDTO.class);

    String queryName;
    
    private String arrayDataFileName = "";
    private String geneReporterName = "";
    private double variancePercentile = 0.70;

	private ReporterGroup reporterGroup = null;
	private SampleGroup sampleGroup = null;
	private String sessionId = null;
	//First string hold patientId in format "TCGA.02.0001.01C.01R.00177.01" and second string sample group name
	private Map<String, String> sampleGroupMap = new HashMap<String, String>();
	
    public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public String getArrayDataFileName() {
		return arrayDataFileName;
	}

	public void setArrayDataFileName(String arrayDataFileName) {
		this.arrayDataFileName = arrayDataFileName;
	}

	public String getGeneReporterName() {
		return geneReporterName;
	}

	public void setGeneReporterName(String geneReporterName) {
		this.geneReporterName = geneReporterName;
	}

	public double getVariancePercentile() {
		return variancePercentile;
	}

	public void setVariancePercentile(double variancePercentile) {
		this.variancePercentile = variancePercentile;
	}

	public ReporterGroup getReporterGroup() {
		return reporterGroup;
	}

	public void setReporterGroup(ReporterGroup reporterGroup) {
		this.reporterGroup = reporterGroup;
	}

	public SampleGroup getSampleGroup() {
		return sampleGroup;
	}

	public void setSampleGroup(SampleGroup sampleGroup) {
		this.sampleGroup = sampleGroup;
	}

	public Map<String, String> getSampleGroupMap() {
		return sampleGroupMap;
	}

	public void setSampleGroupMap(Map<String, String> sampleGroupMap) {
		this.sampleGroupMap = sampleGroupMap;
	}

}
