/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.web.graphing;


import gov.nih.nci.caintegrator.analysis.messaging.DataPoint;
import gov.nih.nci.caintegrator.analysis.messaging.DataPointVector;
import gov.nih.nci.caintegrator.analysis.messaging.ReporterGroup;
import gov.nih.nci.caintegrator.analysis.messaging.SampleGroup;
import gov.nih.nci.caintegrator.application.analysis.AnalysisHelper;
import gov.nih.nci.caintegrator.enumeration.FindingStatus;
import gov.nih.nci.caintegrator.service.findings.ExpressionLookupFinding;
import gov.nih.nci.caintegrator.ui.graphing.chart.plot.BoxAndWhiskerCoinPlotRenderer;
import gov.nih.nci.caintegrator.ui.graphing.chart.plot.FaroutOutlierBoxAndWhiskerCalculator;

import java.awt.Color;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
//import org.apache.commons.math.util.MathUtils;
import flanagan.math.Fmath;
import javax.servlet.http.HttpSession;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.LegendItemSource;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;


public class GEPlot {
	public enum PlotSize { SMALL, MEDIUM, LARGE };

	//bwdataset holds the data for the BoxAndWhisker Plot
	private DefaultBoxAndWhiskerCategoryDataset bwdataset = new DefaultBoxAndWhiskerCategoryDataset();

	//Hold raw data in a format of Map<reporterName, Map<groupname, List<value>>>
	private Map<String, Map<String, List<Double>>> rawDataMap = new HashMap<String, Map<String, List<Double>>>();

	//groupList is holding sample group name to be used for coin BW plot
	private List<String> groupList = new ArrayList<String>();

	//log2Dataset holds the data for the LOG2 plot, w/std deviation error bars
	private DefaultStatisticalCategoryDataset log2Dataset = new DefaultStatisticalCategoryDataset();

	//gmDataset holds the data for the Geometric Mean plot
	private DefaultCategoryDataset gmDataset = new DefaultCategoryDataset();

	private static Logger logger = Logger.getLogger(GEPlot.class);

	//stdDevMap holds the standard deviation data
	private HashMap<String, String> stdDevMap = new HashMap<String, String>();
	private String  geneSymbol = null;
	private String  reporterName = null;
	private int imgW = 650;
	LegendItemCollection legendItemCollection = null;
	final DecimalFormat decimalFormat = new DecimalFormat("0.0000");
	public GEPlot(){}

	public GEPlot(String geneSymbol){
		this.geneSymbol = geneSymbol;
	}

	/**
	 *
	 * @param rbinaryFileName
	 * @param samples
	 * @param reporters
	 * @return 1 if the data set was prepared successfully
	 * 		  -1 if there was a problem preparing the data set
	 */
	public int prepareGeneGraphDataSet(String rbinaryFileName, List<SampleGroup> samples,
			ReporterGroup reporters){
		for (SampleGroup sample : samples)
			groupList.add(sample.getGroupName());

		List<ExpressionLookupFinding> findings = null;
		try{
			findings = AnalysisHelper.getExpressionValuesForReporters(reporters,
    			rbinaryFileName, samples);
		}catch (Exception e){
			logger.error("Error calling AnalysisHelper.getExpressionValuesForReporters");
			logger.error(e);
		}
		int count = 0;
		if (findings != null && !findings.isEmpty()){
			for (ExpressionLookupFinding finding : findings){
			    if (finding.getStatus() == FindingStatus.Error) return -1;
				List<DataPointVector> dataPointVectors = finding.getDataVectors();
				if (!dataPointVectors.isEmpty()){
					//Each DataPointVector contains data for a reporter.
					for (DataPointVector reporter : dataPointVectors){
						List<Double> datalist = new ArrayList<Double>();
						Map<String, List<Double>> tempMap = rawDataMap.get(reporter.getName());
						if (tempMap == null){
							tempMap = new HashMap<String, List<Double>>();
							rawDataMap.put(reporter.getName(), tempMap);
						}

						List<DataPoint> dataPoints = reporter.getDataPoints();

						for (DataPoint dataPoint : dataPoints ){
							datalist.add(dataPoint.getX());
						}

						bwdataset.add(datalist, reporter.getName(), groupList.get(count));

						tempMap.put(groupList.get(count), datalist);

						//The log2 value here is the mean log2 expression value for the probeset
						Double log2 = reporter.getMeanX();
						Double stdDouble = reporter.getStdDeviationX();
						//Generate log2 values for log2Dataset
						log2Dataset.add(log2, stdDouble, //new Double(stdd),
							reporter.getName(), groupList.get(count));

						//The geometric mean is the anti-log2 mean value for the probeset.
						double log2Value = log2.doubleValue();
						double stdd = stdDouble.doubleValue();
						double mean = Fmath.antilog2(log2Value);
						gmDataset.addValue(new Double(mean), reporter.getName(), groupList.get(count));

						//stddev for the probeset and sample group pair.
						stdDevMap.put(reporter.getName()+"::"+ groupList.get(count), decimalFormat.format(stdd));
					}
				}
				count++;
			}
			imgW = calculateImageWidth(bwdataset.getRowCount(), bwdataset.getColumnCount());
		}
		return 1;
	}
	public String generateBWLog2IntensityChart(String xAxisLabel, String yAxisLabel,
			HttpSession session, PrintWriter pw, boolean isCoinPlot) {
		String bwFilename = "";

		//PlotSize ps = PlotSize.MEDIUM;

		JFreeChart bwChart = null;
		try {
			//IMAGE Size Control

			CategoryAxis xAxis = new CategoryAxis(xAxisLabel);
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			NumberAxis yAxis = new NumberAxis(yAxisLabel);
			yAxis.setAutoRangeIncludesZero(true);
			BoxAndWhiskerCoinPlotRenderer bwRenderer = null;
			CategoryPlot bwPlot = null;

			if (isCoinPlot){
				Map<String, List<Double>> groupMap = rawDataMap.get(reporterName);
				DefaultBoxAndWhiskerCategoryDataset smallBwdataset = new DefaultBoxAndWhiskerCategoryDataset();
				int row = 0;
				int column = 0;
				HashMap<String, List> caIntegatorCoinList = new HashMap<String, List>();
				for (String group : groupList){
					smallBwdataset.add(groupMap.get(group), reporterName, group);
					caIntegatorCoinList.put(row + "_" + column++, groupMap.get(group));
				}
	        	bwRenderer = new BoxAndWhiskerCoinPlotRenderer(caIntegatorCoinList);
	        	bwRenderer.setDisplayAllOutliers(true);
	        	bwRenderer.setDisplayCoinCloud(true);
	        	bwRenderer.setDisplayMean(false);
	        	bwRenderer.setFillBox(false);
	        	bwRenderer.setPlotColor(null);
	        	bwPlot = new CategoryPlot(smallBwdataset, xAxis, yAxis, bwRenderer);

	        	if (groupList.size() < 6 )
	        		imgW = 200;

			}
			else {
				bwRenderer = new BoxAndWhiskerCoinPlotRenderer();
				bwRenderer.setDisplayAllOutliers(true);
				bwRenderer.setBaseToolTipGenerator(new CategoryToolTipGenerator() {
					public String generateToolTip(CategoryDataset dataset,int series, int item) {
						String tt="";
						NumberFormat formatter = new DecimalFormat(".####");
						String key = "";
						//String s = formatter.format(-1234.567);  // -001235
						if(dataset instanceof DefaultBoxAndWhiskerCategoryDataset){
							DefaultBoxAndWhiskerCategoryDataset ds = (DefaultBoxAndWhiskerCategoryDataset)dataset;
							try	{
								String med = formatter.format(ds.getMedianValue(series, item));
								tt += "Median: " + med + "<br/>";
								tt += "Mean: " + formatter.format(ds.getMeanValue(series, item))+"<br/>";
								tt += "Q1: " + formatter.format(ds.getQ1Value(series, item))+"<br/>";
								tt += "Q3: " + formatter.format(ds.getQ3Value(series, item))+"<br/>";
								tt += "Max: " + formatter.format(
									FaroutOutlierBoxAndWhiskerCalculator.getMaxFaroutOutlier(ds,series, item))+"<br/>";
								tt += "Min: " + formatter.format(
									FaroutOutlierBoxAndWhiskerCalculator.getMinFaroutOutlier(ds,series, item))+"<br/>";
								tt += "<br/><br/>Please click on the box and whisker to view a plot for this reporter.<br/>";
								key = ds.getRowKeys().get(series).toString();
							}
							catch(Exception e) {}
						}
						String returnString = "onclick=\"popCoin('"+geneSymbol+"','"+key+"');\" | ";

						return returnString + tt;
					}
				});
				bwRenderer.setFillBox(false);
				bwPlot = new CategoryPlot(bwdataset, xAxis, yAxis, bwRenderer);
			}

			bwChart = new JFreeChart(bwPlot);

			bwChart.setBackgroundPaint(java.awt.Color.white);
			LegendTitle title = bwChart.getLegend();
			LegendItemSource[] sources = title.getSources();

			legendItemCollection = sources[0].getLegendItems();
			bwChart.removeLegend();

			// Write the chart image to the temporary directory
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

			// BW
			if(bwChart != null){
				//int bwwidth = new BigDecimal(1.5).multiply(new BigDecimal(imgW)).intValue();
				bwFilename = ServletUtilities.saveChartAsPNG(bwChart, imgW, 400, info, session);
				CustomOverlibToolTipTagFragmentGenerator ttip = new CustomOverlibToolTipTagFragmentGenerator();
				ttip.setExtra(" href='javascript:void(0);' "); //must have href for area tags to have cursor:pointer
				ChartUtilities.writeImageMap(pw, bwFilename, info,
						ttip,
						new StandardURLTagFragmentGenerator());
				info.clear(); // lose the first one
				info = new ChartRenderingInfo(new StandardEntityCollection());
			}
			//END  BW

			pw.flush();

		} catch (Exception e) {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		// return filename;
		//charts.put("errorBars", log2Filename);
		//charts.put("noErrorBars", rawFilename);
		//charts.put("bwFilename", bwFilename);
		//charts.put("legend", legendHtml);
		//charts.put("size", ps.toString());

		return bwFilename;
	}

	public String generateLog2Chart(String xAxisLabel, String yAxisLabel,
			HttpSession session, PrintWriter pw) {
		String log2Filename = "";

		JFreeChart log2Chart = null;
		try {

			log2Chart = ChartFactory.createBarChart(
					null,
					xAxisLabel, // domain axis label
					yAxisLabel,
					log2Dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips?
					false // URLs?
					);
			log2Chart.setBackgroundPaint(java.awt.Color.white);
			// lets start some customization to retro fit w/jcharts lookand feel
			CategoryPlot plot = log2Chart.getCategoryPlot();
			CategoryAxis axis = plot.getDomainAxis();
			axis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			axis.setLowerMargin(0.02); // two percent
			axis.setCategoryMargin(0.20); // 20 percent
			axis.setUpperMargin(0.02); // two percent
			StatisticalBarRenderer renderer = new StatisticalBarRenderer();

			// BarRenderer renderer = (BarRenderer) plot.getRenderer();
			renderer.setItemMargin(0.01); // one percent
			renderer.setDrawBarOutline(true);
			renderer.setOutlinePaint(Color.BLACK);
			renderer.setBaseToolTipGenerator(new CategoryToolTipGenerator() {

				public String generateToolTip(CategoryDataset dataset,
						int series, int item) {
					String stdDev = (String) stdDevMap.get(dataset
							.getRowKey(series)
							+ "::" + dataset.getColumnKey(item));

					return "Probeset : " + dataset.getRowKey(series)
							+ "<br/>Intensity : "
							+ new DecimalFormat("0.0000").format(dataset.getValue(series, item)) + "<br/>"
							+ "<br/>Std. Dev.: " + stdDev + "<br/>";
				}

			});

			// LegendTitle lg = chart.getLegend();
			plot.setRenderer(renderer);

			// lets generate a custom legend - assumes theres only one source?
			//LegendItemCollection lic = log2Chart.getLegend().getSources()[0].getLegendItems();
			//legendHtml = LegendCreator.buildLegend(lic, "Probesets");
			log2Chart.removeLegend();
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			log2Filename = ServletUtilities.saveChartAsPNG(log2Chart, imgW, 400, info, session);
			ChartUtilities.writeImageMap(pw, log2Filename, info,
					new CustomOverlibToolTipTagFragmentGenerator(),
					new StandardURLTagFragmentGenerator());

		}catch (Exception e) {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		return log2Filename;
	}

	public String generateGeometricMeanIntensityChart(String xAxisLabel, String yAxisLabel,
			HttpSession session, PrintWriter pw) {
		String gmfilename = "";

		JFreeChart gmChart = null;
		try {

			gmChart = ChartFactory.createBarChart(
					null,
					xAxisLabel, // domain axis label
					yAxisLabel,
					gmDataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips?
					false // URLs?
					);
			gmChart.setBackgroundPaint(java.awt.Color.white);
			// lets start some customization to retro fit w/jcharts lookand feel
			CategoryPlot plot = gmChart.getCategoryPlot();
			CategoryAxis axis = plot.getDomainAxis();
			axis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			axis.setLowerMargin(0.02); // two percent
			axis.setCategoryMargin(0.20); // 20 percent
			axis.setUpperMargin(0.02); // two percent
			//StatisticalBarRenderer renderer = new StatisticalBarRenderer();
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			// BarRenderer renderer = (BarRenderer) plot.getRenderer();
			renderer.setItemMargin(0.01); // one percent
			renderer.setDrawBarOutline(true);
			renderer.setOutlinePaint(Color.BLACK);
			renderer.setBaseToolTipGenerator(new CategoryToolTipGenerator() {

				public String generateToolTip(CategoryDataset dataset,
						int series, int item) {
					String stdDev = (String) stdDevMap.get(dataset
							.getRowKey(series)
							+ "::" + dataset.getColumnKey(item));

					return "Probeset : " + dataset.getRowKey(series)
							+ "<br/>Intensity : "
							+ new DecimalFormat("0.0000").format(dataset.getValue(series, item)) + "<br/>"
							+ "<br/>Std. Dev.: " + stdDev + "<br/>";
				}

			});

			plot.setRenderer(renderer);

			gmChart.removeLegend();
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			//gmfilename = ServletUtilities.saveChartAsPNG(gmChart, imgW, 400, info, session);
			gmfilename = ServletUtilities.saveChartAsPNG(gmChart, imgW, 400, info, session);
			ChartUtilities.writeImageMap(pw, gmfilename, info,
					new CustomOverlibToolTipTagFragmentGenerator(),
					new StandardURLTagFragmentGenerator());

		}catch (Exception e) {
			System.out.println("Exception - " + e.toString());
			e.printStackTrace(System.out);
		}
		return gmfilename;
	}

	public static JFreeChart generateLog2IntensityChart(AbstractDataset dataSet,
			String xAxisLabel, String yAxisLabel) {
		return null;
	}
	public static JFreeChart generateGeometricMeanChart(AbstractDataset dataSet,
			String xAxisLabel, String yAxisLabel) {
		return null;

	}
	public LegendItemCollection getLegendItemCollection() {
		return legendItemCollection;
	}
	public void setLegendItemCollection(LegendItemCollection legendItemCollection) {
		this.legendItemCollection = legendItemCollection;
	}

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	private int calculateImageWidth(int row, int column){
		int imgW = 650;
		int totalColumn = row*column;
		if(totalColumn > 60)	{
			imgW = new BigDecimal(row).multiply(new BigDecimal(75)).intValue() > 1000 ? new BigDecimal(row).multiply(new BigDecimal(75)).intValue() : 1000;
		}
		else if (totalColumn < 60 &&  totalColumn > 30){
			imgW = 600;
		}
		else if (totalColumn < 30 &&  totalColumn > 5){
			imgW = 500;
		}
		else {
			imgW = 400;
		}
		return imgW;
	}
}

