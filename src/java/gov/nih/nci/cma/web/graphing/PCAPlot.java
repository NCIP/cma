/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

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

