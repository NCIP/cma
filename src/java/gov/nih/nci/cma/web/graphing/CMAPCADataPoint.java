/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.web.graphing;


import gov.nih.nci.caintegrator.ui.graphing.data.principalComponentAnalysis.PrincipalComponentAnalysisDataPoint;


public class CMAPCADataPoint extends PrincipalComponentAnalysisDataPoint {

	private String sampleGroupName;

	public CMAPCADataPoint(String sampleId) {
		super(sampleId);
	}

	public CMAPCADataPoint(String sampleId, double pc1value, double pc2value, double pc3value) {
	  super(sampleId, pc1value, pc2value, pc3value);
	}

	public String getSampleGroupName() {
		return sampleGroupName;
	}

	public void setSampleGroupName(String sampleGroupName) {
		this.sampleGroupName = sampleGroupName;
	}
}
