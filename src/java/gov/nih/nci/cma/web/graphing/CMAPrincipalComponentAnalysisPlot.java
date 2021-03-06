/*L
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cma/LICENSE.txt for details.
 */

package gov.nih.nci.cma.web.graphing;



import gov.nih.nci.caintegrator.ui.graphing.data.DataRange;
import gov.nih.nci.caintegrator.ui.graphing.data.principalComponentAnalysis.PrincipalComponentAnalysisDataPoint.PCAcomponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.util.StringUtils;
import org.jfree.data.Range;


/**
 * This class generates a plot for the PrincipalComponentAnalysis graph
 * that fits CMA requirement.
 *
 *
 */

public class CMAPrincipalComponentAnalysisPlot {

	//private ColorByType colorBy;
	private PCAcomponent component1;
	private PCAcomponent component2;
	private Map<String, Color> colorMap = new HashMap<String, Color>();
	private Collection<CMAPCADataPoint> dataPoints;
	private JFreeChart pcaChart = null;
	private NumberFormat nf = NumberFormat.getNumberInstance();
	private Map<String, String> sampleGroupNames;

	public CMAPrincipalComponentAnalysisPlot(Collection<CMAPCADataPoint> dataPoints,
			PCAcomponent component1, PCAcomponent component2, Map<String, String> sampleGroupNames){ //, ColorByType colorBy) {
	  //this.colorBy = colorBy;
	  this.component1 = component1;
	  this.component2 = component2;
	  this.dataPoints = dataPoints;
	  this.nf.setMaximumFractionDigits(1);
	  this.sampleGroupNames = sampleGroupNames;
	  colorMap.put("1", Color.GREEN);
	  colorMap.put("2", Color.BLUE);
	  colorMap.put("3", Color.YELLOW);
	  colorMap.put("4", Color.CYAN);
	  colorMap.put("5", Color.MAGENTA);
	  colorMap.put("6", Color.ORANGE);
	  colorMap.put("7", Color.PINK);
	  colorMap.put("8", Color.RED);
	  colorMap.put("9", Color.GRAY);

	  createChart();

	}

	private void createChart() {

		String xLabel = component1.toString();
		String yLabel = component2.toString();



		pcaChart = ChartFactory.createScatterPlot("Principal Component Analysis",xLabel, yLabel, null,  PlotOrientation.VERTICAL,
	            true,
	            true,
	            false );

		XYPlot plot = (XYPlot) pcaChart.getPlot();

		buildLegend();

	    plot.setNoDataMessage(null);
	    XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
	    renderer.setToolTipGenerator(new StandardXYToolTipGenerator());
	    renderer.setUseOutlinePaint(true);
        plot.setRangeCrosshairVisible(false);
	    plot.setDomainCrosshairVisible(false);

//	     XYShapeAnnotation annotation = new XYShapeAnnotation(new Rectangle2D.Double(25.0, 25.0, 5, 5));
//
//	     plot.addAnnotation(annotation);


        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis rangeAxis  =	(NumberAxis) plot.getRangeAxis();


        //should determine axis range using datapoints.
        DataRange component1Range = getDataRange(dataPoints, PCAcomponent.PC1);
        DataRange component2Range = getDataRange(dataPoints, PCAcomponent.PC2);
        DataRange component3Range = getDataRange(dataPoints, PCAcomponent.PC3);

        Double pc1AbsMax = Math.max(Math.abs(component1Range.getMaxRange()), Math.abs(component1Range.getMinRange()));
        Double pc2AbsMax = Math.max(Math.abs(component2Range.getMaxRange()), Math.abs(component2Range.getMinRange()));
        Double pc3AbsMax = Math.max(Math.abs(component3Range.getMaxRange()), Math.abs(component3Range.getMinRange()));

        Double maxAbsVal = Math.max(pc1AbsMax, pc2AbsMax);

        maxAbsVal = Math.max(maxAbsVal, pc3AbsMax);

        //maxAbsVal = Math.max(100.0, maxAbsVal);

        domainAxis.setAutoRangeIncludesZero(false);


        double tickUnit = 25.0;

        if (maxAbsVal <= 50.0 && maxAbsVal >= 25.0) {
          tickUnit =10.0; //5.0;
        }
        else if (maxAbsVal <= 25.0) {
          tickUnit = 5.0;
        }

        domainAxis.setTickUnit(new NumberTickUnit(tickUnit));
        rangeAxis.setTickUnit(new NumberTickUnit(tickUnit));

        double glyphScaleFactor = (maxAbsVal*2.0)/600.0;   //assuming 600 pixels for the graph

        double adjAbsVal = Math.ceil(maxAbsVal + (glyphScaleFactor*8.0));

        //domainAxis.setRange(-maxAbsVal, maxAbsVal);
        domainAxis.setRange(-adjAbsVal, adjAbsVal);

        //rangeAxis.setRange(-maxAbsVal, maxAbsVal);
        rangeAxis.setRange(-adjAbsVal, adjAbsVal);

        createGlyphsAndAddToPlot(plot); //, glyphScaleFactor);

	   // Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
	    //try and match the UI e9e9e9
	    Paint p = new Color(233, 233, 233);

	    pcaChart.setBackgroundPaint(p);
	}

	/**
	 * Build the legend
	 *
	 */
	private void buildLegend() {

		LegendTitle legend = pcaChart.getLegend();
		LegendItemSource[] sources = new LegendItemSource[1];
		PcaLegendItemSource legendSrc = new PcaLegendItemSource();
		LegendItem item = null;

		Ellipse2D.Double circle = new Ellipse2D.Double(0,0,8,8);

	  //Rect=survival less than 10 months
		Set<String> groupNames = sampleGroupNames.keySet();
		for (String name : groupNames){
			Color color = colorMap.get(sampleGroupNames.get(name));
			if (color == null)
				color = Color.BLACK;
			item = new LegendItem(name, null, null, null, circle, color);
			legendSrc.addLegendItem(item);
		}
		sources[0] = legendSrc;
		legend.setSources(sources);
	}

	/**
	 * This chart uses the XYAnnotation as a glyph to represent
	 * a single pca data point. Glyph shape is determined by survival time.
	 * Survival of more than 10 months is represented by a circle. 10 months or less
	 * is represented by a square. Component1 values are represented by X
	 * Component2 values are represented by Y
	 */

	private void createGlyphsAndAddToPlot(XYPlot plot){ //, double glyphScaleFactor) {
		XYShapeAnnotation glyph;
		Shape glyphShape = null;
		Color glyphColor;
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		Range r = domainAxis.getRange();
		double upperBound = r.getUpperBound();

		double dotSize = 0.7;
		if (upperBound >= 50 && upperBound <= 100)
			dotSize = 1d;
		else if (upperBound > 100 && upperBound <= 150)
			dotSize = 2d;
		else if (upperBound > 150 && upperBound <= 200)
			dotSize = 3d;
		else if (upperBound > 200 )
			dotSize = 5d;

		//PrincipalComponentAnalysisDataPoint pcaPoint;
		double x, y;
		//for (Iterator i=dataPoints.iterator(); i.hasNext(); ) {
		for (CMAPCADataPoint pcaPoint : dataPoints){
			x = pcaPoint.getComponentValue(component1);
			y = pcaPoint.getComponentValue(component2);

			String sampleGroupName = pcaPoint.getSampleGroupName();
			String order = sampleGroupNames.get(sampleGroupName);
			glyphShape = getShapeForDataPoint(order, x, y, dotSize);

			glyphColor = colorMap.get(order);
			if (glyphColor == null)
				glyphColor = Color.black;
			glyph = new XYShapeAnnotation(glyphShape, new BasicStroke(1.0f), Color.BLACK, glyphColor);
			String tooltip = "";

			tooltip = pcaPoint.getSampleId() + " " + pcaPoint.getSampleGroupName();

            glyph.setToolTipText(tooltip);
            plot.addAnnotation(glyph);
		}
	}

	private Shape getShapeForDataPoint(String order, double x, double y, double dotSize){
		Shape glyphShape;
		Ellipse2D.Double circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(x,y, x + dotSize, y + dotSize);
		glyphShape = circle;
		//Now we only use one shape for the plot.
		/*
		if(order.equalsIgnoreCase("1"))	{
			//glyphShape = ShapeUtilities.createDiamond(new Float(x));
			Rectangle2D.Double rect = new Rectangle2D.Double();
			rect.setFrameFromCenter(x,y, x+2,y+2);
			glyphShape = rect;
			Shape gShape = ShapeUtilities.rotateShape(glyphShape, new Double(0.785398163), new Float(x),new Float(y));
			glyphShape = gShape;
		}
		else if (order.equalsIgnoreCase("2")) {
			Rectangle2D.Double rect = new Rectangle2D.Double();
			rect.setFrameFromCenter(x,y, x+2,y+2);
			glyphShape = rect;
		}
		else if (order.equalsIgnoreCase("3")) {
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(x,y, x+2, y+2);
			glyphShape = circle;
		}
		else if (order.equalsIgnoreCase("4")) {
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(x,y, x+2, y+2);
			glyphShape = circle;
		}
		else if (order.equalsIgnoreCase("5")) {
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(x,y, x+2, y+2);
			glyphShape = circle;
		}
		else if (order.equalsIgnoreCase("6")) {
			Ellipse2D.Double circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(x,y, x+2, y+2);
			glyphShape = circle;
		}
		else {
			GeneralPath gp = new GeneralPath();
			float xf = (float)x;
			float yf = (float)y;
			//make a triangle
			gp.moveTo(xf,yf);
			gp.lineTo(xf+3.0f,yf-3.0f);
			gp.lineTo(xf-3.0f,yf-3.0f);
			gp.closePath();
			glyphShape = gp;
		}
		*/
		return glyphShape;
	}
	public JFreeChart getChart() { return pcaChart; }

	/**
	 * Same interface as ChartUtilities.writeImageMap. This version will find the
	 * bounding rectangles for the entities in the ChartRenderingInfo object and will write those
	 * to the image map.
	 * @param writer
	 * @param name
	 * @param info
	 * @param useOverlibToolTip
	 */
	public void writeBoundingRectImageMap(PrintWriter writer, String name, ChartRenderingInfo info, boolean useOverlibToolTip) {
	  EntityCollection collection = info.getEntityCollection();
      Collection entities = collection.getEntities();

      Collection<ChartEntity> boundingEntities = getBoundingEntities(entities);
      writeBoundingRectImageMap(writer, name, boundingEntities, useOverlibToolTip);

	}

	/**
	 * Write the image map for the collection of bounding entities.
	 * @param writer
	 * @param name
	 * @param boundingEntities
	 * @param useOverlibToolTip
	 */
	private void writeBoundingRectImageMap(PrintWriter writer, String name, Collection<ChartEntity> boundingEntities, boolean useOverlibToolTip) {
	  System.out.println("Num entities=" + boundingEntities.size());
	  StringBuffer sb = new StringBuffer();
      ChartEntity chartEntity;
      String areaTag;

      StandardToolTipTagFragmentGenerator ttg = new StandardToolTipTagFragmentGenerator();
      StandardURLTagFragmentGenerator urlg = new StandardURLTagFragmentGenerator();
      sb.append("<map id=\"" + name + "\" name=\"" + name + "\">");
	  sb.append(StringUtils.getLineSeparator());
      for (Iterator i=boundingEntities.iterator(); i.hasNext(); ) {
       	 chartEntity = (ChartEntity) i.next();
       	 areaTag = chartEntity.getImageMapAreaTag(ttg, urlg).trim();
   	     if (areaTag.length() > 0) {
           sb.append(chartEntity.getImageMapAreaTag(ttg, urlg));
           sb.append(StringUtils.getLineSeparator());
   	     }
      }
      sb.append("</map>");
      writer.println(sb.toString());
	}

	/**
	 * Get a collection of entities with the area shape equal to the bounding rectangle
	 * for the shape of original entity. This is necessary because the Javascript for the sample
	 * selection lasso can only handle rect objects.
	 * @param entities
	 * @return a collection of entities containing the bounding rectangles of the original entities
	 */
	private Collection<ChartEntity> getBoundingEntities(Collection entities) {
	  ChartEntity entity;
	  ChartEntity boundingEntity;
	  Shape shape;
	  Rectangle2D boundingRect;
	  Collection<ChartEntity> boundingEntities = new ArrayList<ChartEntity>();
	  for (Iterator i=entities.iterator(); i.hasNext(); ) {
	     entity = (ChartEntity) i.next();
	     shape = entity.getArea();
	     boundingRect = shape.getBounds2D();
	     boundingEntity = new ChartEntity(boundingRect, entity.getToolTipText(), entity.getURLText());
	     boundingEntities.add(boundingEntity);
	  }
	  return boundingEntities;
	}


	/**
	 * Get the range of values for a given clinical factor
	 * @param dataPoints
	 * @param factor
	 * @return
	 */
	private DataRange getDataRange(Collection<CMAPCADataPoint> dataPoints, PCAcomponent component) {
	  double maxValue = Double.MIN_VALUE;
	  double minValue = Double.MAX_VALUE;
	  double value;

	  for (CMAPCADataPoint dataPoint:dataPoints) {
		value = dataPoint.getComponentValue(component);
		if (value < minValue) {
		  minValue = value;
		}

		if (value > maxValue) {
		  maxValue = value;
		}
	  }

	  DataRange range = new DataRange(minValue, maxValue);
	  return range;
	}

	/**
	 * A class for building the legend
	 * @author harrismic
	 *
	 */
	private class PcaLegendItemSource implements LegendItemSource {

		private LegendItemCollection items = new LegendItemCollection();

		public void addLegendItem(LegendItem item) {
		  items.add(item);
		}

		public LegendItemCollection getLegendItems() {
			return items;
		}

	}
}
