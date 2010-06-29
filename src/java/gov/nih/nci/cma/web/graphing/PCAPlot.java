package gov.nih.nci.cma.web.graphing;



import gov.nih.nci.caintegrator.service.findings.PrincipalComponentAnalysisFinding;

import gov.nih.nci.caintegrator.analysis.messaging.PCAresultEntry;
import gov.nih.nci.caintegrator.ui.graphing.data.principalComponentAnalysis.PrincipalComponentAnalysisDataPoint.PCAcomponent;
import gov.nih.nci.caintegrator.ui.graphing.util.ImageMapUtil;
import gov.nih.nci.cma.query.dto.ProjectPCAQueryDTO;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;




/**
* caIntegrator License
* 
* Copyright 2001-2005 Science Applications International Corporation ("SAIC"). 
* The software subject to this notice and license includes both human readable source code form and machine readable, 
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with 
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC. 
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105. 
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an 
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control" 
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) 
* beneficial ownership of such entity. 
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive, 
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights 
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly 
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed 
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof; 
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such 
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You. 
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce 
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any. 
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This 
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user 
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments 
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and 
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any 
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License. 
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and 
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary 
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to 
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including 
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties 
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail 
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to 
*    the extent prohibited by law, resulting from Your failure to obtain such permissions. 
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and 
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses 
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, 
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, 
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. 
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, 
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
*/

public class PCAPlot {
	private static Logger logger = Logger.getLogger(PCAPlot.class);

	
    private List<PCAresultEntry> pcaResults = null;
    private Collection<CMAPCADataPoint> pcaData = new ArrayList<CMAPCADataPoint>();
    private JFreeChart chart = null;
    private Map<String, String> sampleGroupNames = new HashMap<String, String>();
    
	public PCAPlot(){}
	
	public void preparePCAPlotDataSet(ProjectPCAQueryDTO dto, PrincipalComponentAnalysisFinding finding){ 
        if(finding != null){
            pcaResults = finding.getResultEntries();
            Set<String> set  = new HashSet<String>();
            Map<String, String> sampleGroupMap = dto.getSampleGroupMap();
            for (PCAresultEntry pcaEntry : pcaResults) {

            	CMAPCADataPoint pcaPoint 
                	= new CMAPCADataPoint(pcaEntry.getSampleId(), 
                			pcaEntry.getPc1(),
                			pcaEntry.getPc2(),
                			pcaEntry.getPc3());
                //set group name for color lableling
                set.add(sampleGroupMap.get(pcaEntry.getSampleId()));
                pcaPoint.setSampleGroupName(sampleGroupMap.get(pcaEntry.getSampleId()));
                pcaData.add(pcaPoint);
            }
            int count = 1;
            for (String name : set){
            	sampleGroupNames.put(name, Integer.toString(count++));
            }
        }
	}
	public String generatePCAPlotChart(String components, 
			HttpServletRequest request, PrintWriter pw) {
		String finalURLpath = null; //imageHandler.getFinalURLPath();
	try{
        //check the components to see which graph to get
		CMAPrincipalComponentAnalysisPlot plot = null;
		if(components.equalsIgnoreCase("PC1vsPC2")){                 
			plot = new CMAPrincipalComponentAnalysisPlot(pcaData, PCAcomponent.PC2, PCAcomponent.PC1, sampleGroupNames);            
        }
        if(components.equalsIgnoreCase("PC1vsPC3")){            	
        	 plot = new CMAPrincipalComponentAnalysisPlot(pcaData, PCAcomponent.PC3, PCAcomponent.PC1, sampleGroupNames);              
        }
        if(components.equalsIgnoreCase("PC2vsPC3")){
        	plot = new CMAPrincipalComponentAnalysisPlot(pcaData, PCAcomponent.PC3, PCAcomponent.PC2, sampleGroupNames);
        }
        chart = plot.getChart();
        
        ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		// BW
		if(chart != null){
			//int bwwidth = new BigDecimal(1.5).multiply(new BigDecimal(imgW)).intValue();
			finalURLpath = ServletUtilities.saveChartAsPNG(chart, 500, 400, info, request.getSession());
			CustomOverlibToolTipTagFragmentGenerator ttip = new CustomOverlibToolTipTagFragmentGenerator();
			ttip.setExtra(" href='javascript:void(0);' "); //must have href for area tags to have cursor:pointer
			//ChartUtilities.writeImageMap(pw, finalURLpath, info,
			//		ttip,
			//		new StandardURLTagFragmentGenerator());
			//ChartUtilities.writeImageMap(pw, finalURLpath, info, true);
			pw.write(ImageMapUtil.getBoundingRectImageMapTag(finalURLpath, false, info));
			info.clear(); // lose the first one
			info = new ChartRenderingInfo(new StandardEntityCollection());
		}

		pw.flush();
	}catch (IOException e) {
		logger.error(e);
	}catch(Exception e) {
		logger.error(e);
	}catch(Throwable t) {
		logger.error(t);
	}
		return finalURLpath;
	}
}

