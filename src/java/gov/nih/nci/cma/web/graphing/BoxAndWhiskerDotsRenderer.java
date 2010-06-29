package gov.nih.nci.cma.web.graphing;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.Outlier;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;

/**
 * A box-and-whisker renderer.
 */
public class BoxAndWhiskerDotsRenderer extends BoxAndWhiskerRenderer{

    /** For serialization. */
    private static final long serialVersionUID = 632027470694481177L;
    
    /** The color used to paint the median line and average marker. */
    private transient Paint artifactPaint;

    /** A flag that controls whether or not the box is filled. */
    private boolean fillBox;
    
    /** The margin between items (boxes) within a category. */
    private double itemMargin;
    
    //caIntegrator: to limit the width of the bar. Used in
    //method initialise()
    //A constant to limit the width of a bar.
    private final double maxBarWidth = 40.00;
    private HashMap<String, List> caintegOutliers;
    
    /**
     * Default constructor.
     */
    public BoxAndWhiskerDotsRenderer(HashMap<String, List> yOutliers) {
    	this.caintegOutliers = yOutliers;
        this.artifactPaint = Color.black;
        this.fillBox = true;
        this.itemMargin = 0.20;
    }
    
    /* caintegrator constructor */
    public BoxAndWhiskerDotsRenderer() {
        this.artifactPaint = Color.black;
        this.fillBox = true;
        this.itemMargin = 0.20;
    }

    /**
     * Returns the paint used to color the median and average markers.
     * 
     * @return A paint.
     */
    public Paint getArtifactPaint() {
        return this.artifactPaint;
    }

    /**
     * Sets the paint used to color the median and average markers.
     * 
     * @param paint  the paint.
     */
    public void setArtifactPaint(Paint paint) {
        this.artifactPaint = paint;
    }

    /**
     * Returns the flag that controls whether or not the box is filled.
     * 
     * @return A boolean.
     */
    public boolean getFillBox() {
        return this.fillBox;   
    }
    
    /**
     * Sets the flag that controls whether or not the box is filled and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param flag  the flag.
     */
    public void setFillBox(boolean flag) {
        this.fillBox = flag;
        notifyListeners(new RendererChangeEvent(this));
    }

    /**
     * Returns the item margin.  This is a percentage of the available space 
     * that is allocated to the space between items in the chart.
     * 
     * @return The margin.
     */
    public double getItemMargin() {
        return this.itemMargin;
    }

    /**
     * Sets the item margin.
     * 
     * @param margin  the margin.
     */
    public void setItemMargin(double margin) {
        this.itemMargin = margin;
    }

    /**
     * Returns a legend item for a series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return The legend item.
     */
    public LegendItem getLegendItem(int datasetIndex, int series) {

        CategoryPlot cp = getPlot();
        if (cp == null) {
            return null;
        }

        CategoryDataset dataset;
        dataset = cp.getDataset(datasetIndex);
        String label = getLegendItemLabelGenerator().generateLabel(
            dataset, series
        );
        String description = label;
        String toolTipText = null; 
        if (getLegendItemToolTipGenerator() != null) {
            toolTipText = getLegendItemToolTipGenerator().generateLabel(
                dataset, series
            );   
        }
        String urlText = null;
        if (getLegendItemURLGenerator() != null) {
            urlText = getLegendItemURLGenerator().generateLabel(
                dataset, series
            );   
        }
        Shape shape = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
        Paint paint = getSeriesPaint(series);
        Paint outlinePaint = getSeriesOutlinePaint(series);
        Stroke outlineStroke = getSeriesOutlineStroke(series);

        return new LegendItem(label, description, toolTipText, urlText, 
            shape, paint, outlineStroke, outlinePaint);

    }

    /**
     * Initialises the renderer.  This method gets called once at the start of 
     * the process of drawing a chart.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be plotted.
     * @param plot  the plot.
     * @param rendererIndex  the renderer index.
     * @param info  collects chart rendering information for return to caller.
     *
     * @return The renderer state.
     */
    public CategoryItemRendererState initialise(Graphics2D g2,
                                                Rectangle2D dataArea,
                                                CategoryPlot plot,
                                                int rendererIndex,
                                                PlotRenderingInfo info) {

        CategoryItemRendererState state = super.initialise(
            g2, dataArea, plot, rendererIndex, info
        );

        // calculate the box width
        CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
        CategoryDataset dataset = plot.getDataset(rendererIndex);
        if (dataset != null) {
            int columns = dataset.getColumnCount();
            int rows = dataset.getRowCount();
            double space = 0.0;
            PlotOrientation orientation = plot.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                space = dataArea.getHeight();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                space = dataArea.getWidth();
            }
            double categoryMargin = 0.0;
            double currentItemMargin = 0.0;
            if (columns > 1) {
                categoryMargin = domainAxis.getCategoryMargin();
            }
            if (rows > 1) {
                currentItemMargin = getItemMargin();
            }
            double used = space * (1 - domainAxis.getLowerMargin() 
                                     - domainAxis.getUpperMargin()
                                     - categoryMargin - currentItemMargin);
            if ((rows * columns) > 0) {
                state.setBarWidth(
                    used / (dataset.getColumnCount() * dataset.getRowCount())
                );
            }
            else {
                state.setBarWidth(used);
            }
        }
        
        if (state.getBarWidth() > maxBarWidth)
        	state.setBarWidth(maxBarWidth);
        return state;

    }

    /**
     * Draw a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area in which the data is drawn.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2,
                         CategoryItemRendererState state,
                         Rectangle2D dataArea,
                         CategoryPlot plot,
                         CategoryAxis domainAxis,
                         ValueAxis rangeAxis,
                         CategoryDataset dataset,
                         int row,
                         int column,
                         int pass) {
                             
        if (!(dataset instanceof BoxAndWhiskerCategoryDataset)) {
            throw new IllegalArgumentException(
                "BoxAndWhiskerRenderer.drawItem() : the data should be of type "
                + "BoxAndWhiskerCategoryDataset only."
            );
        }

        PlotOrientation orientation = plot.getOrientation();

        if (orientation == PlotOrientation.HORIZONTAL) {
            drawHorizontalItem(
                g2, state, dataArea, plot, domainAxis, rangeAxis, 
                dataset, row, column
            );
        } 
        else if (orientation == PlotOrientation.VERTICAL) {
            drawVerticalItem(
                g2, state, dataArea, plot, domainAxis, rangeAxis, 
                dataset, row, column
            );
        }
        
    }

    /**
     * Draws the visual representation of a single data item when the plot has 
     * a horizontal orientation.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    public void drawHorizontalItem(Graphics2D g2,
                                   CategoryItemRendererState state,
                                   Rectangle2D dataArea,
                                   CategoryPlot plot,
                                   CategoryAxis domainAxis,
                                   ValueAxis rangeAxis,
                                   CategoryDataset dataset,
                                   int row,
                                   int column) {

        BoxAndWhiskerCategoryDataset bawDataset 
            = (BoxAndWhiskerCategoryDataset) dataset;

        double categoryEnd = domainAxis.getCategoryEnd(
            column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
        );
        double categoryStart = domainAxis.getCategoryStart(
            column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
        );
        double categoryWidth = Math.abs(categoryEnd - categoryStart);

        double yy = categoryStart;
        int seriesCount = getRowCount();
        int categoryCount = getColumnCount();

        if (seriesCount > 1) {
            double seriesGap = dataArea.getWidth() * getItemMargin()
                               / (categoryCount * (seriesCount - 1));
            double usedWidth = (state.getBarWidth() * seriesCount) 
                               + (seriesGap * (seriesCount - 1));
            // offset the start of the boxes if the total width used is smaller
            // than the category width
            double offset = (categoryWidth - usedWidth) / 2;
            yy = yy + offset + (row * (state.getBarWidth() + seriesGap));
        } 
        else {
            // offset the start of the box if the box width is smaller than 
            // the category width
            double offset = (categoryWidth - state.getBarWidth()) / 2;
            yy = yy + offset;
        }

        Paint p = getItemPaint(row, column);
        if (p != null) {
            g2.setPaint(p);
        }
        Stroke s = getItemStroke(row, column);
        g2.setStroke(s);

        RectangleEdge location = plot.getRangeAxisEdge();

        Number xQ1 = bawDataset.getQ1Value(row, column);
        Number xQ3 = bawDataset.getQ3Value(row, column);
        Number xMax = bawDataset.getMaxRegularValue(row, column);
        Number xMin = bawDataset.getMinRegularValue(row, column);

        Shape box = null;
        if (xQ1 != null && xQ3 != null && xMax != null && xMin != null) {

            double xxQ1 = rangeAxis.valueToJava2D(
                xQ1.doubleValue(), dataArea, location
            );
            double xxQ3 = rangeAxis.valueToJava2D(
                xQ3.doubleValue(), dataArea, location
            );
            double xxMax = rangeAxis.valueToJava2D(
                xMax.doubleValue(), dataArea, location
            );
            double xxMin = rangeAxis.valueToJava2D(
                xMin.doubleValue(), dataArea, location
            );
            double yymid = yy + state.getBarWidth() / 2.0;
            
            // draw the upper shadow...
            g2.draw(new Line2D.Double(xxMax, yymid, xxQ3, yymid));
            g2.draw(
                new Line2D.Double(xxMax, yy, xxMax, yy + state.getBarWidth())
            );

            // draw the lower shadow...
            g2.draw(new Line2D.Double(xxMin, yymid, xxQ1, yymid));
            g2.draw(
                new Line2D.Double(xxMin, yy, xxMin, yy + state.getBarWidth())
            );

            // draw the box...
            box = new Rectangle2D.Double(
                Math.min(xxQ1, xxQ3), yy, Math.abs(xxQ1 - xxQ3), 
                state.getBarWidth()
            );
            if (this.fillBox) {
                g2.fill(box);
            } 
            g2.draw(box);

        }

        g2.setPaint(this.artifactPaint);
        double aRadius = 0;                 // average radius

        // draw mean - SPECIAL AIMS REQUIREMENT...
        Number xMean = bawDataset.getMeanValue(row, column);
        if (xMean != null) {
            double xxMean = rangeAxis.valueToJava2D(
                xMean.doubleValue(), dataArea, location
            );
            aRadius = state.getBarWidth() / 4;
            Ellipse2D.Double avgEllipse = new Ellipse2D.Double(
                xxMean - aRadius, yy + aRadius, aRadius * 2, aRadius * 2
            );
            g2.fill(avgEllipse);
            g2.draw(avgEllipse);
        }

        // draw median...
        Number xMedian = bawDataset.getMedianValue(row, column);
        if (xMedian != null) {
            double xxMedian = rangeAxis.valueToJava2D(
                xMedian.doubleValue(), dataArea, location
            );
            g2.draw(
                new Line2D.Double(
                    xxMedian, yy, xxMedian, yy + state.getBarWidth()
                )
            );
        }
        
        // collect entity and tool tip information...
        if (state.getInfo() != null) {
            EntityCollection entities = state.getEntityCollection();
            if (entities != null) {
                String tip = null;
                CategoryToolTipGenerator tipster 
                    = getToolTipGenerator(row, column);
                if (tipster != null) {
                    tip = tipster.generateToolTip(dataset, row, column);
                }
                String url = null;
                if (getItemURLGenerator(row, column) != null) {
                    url = getItemURLGenerator(row, column).generateURL(
                        dataset, row, column
                    );
                }
                CategoryItemEntity entity = new CategoryItemEntity(
                    box, tip, url, dataset, row, dataset.getColumnKey(column), 
                    column
                );
                entities.add(entity);
            }
        }

    } 
        
    /**
     * Draws the visual representation of a single data item when the plot has 
     * a vertical orientation.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param plot  the plot (can be used to obtain standard color information 
     *              etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     */
    public void drawVerticalItem(Graphics2D g2, 
                                 CategoryItemRendererState state,
                                 Rectangle2D dataArea,
                                 CategoryPlot plot, 
                                 CategoryAxis domainAxis, 
                                 ValueAxis rangeAxis,
                                 CategoryDataset dataset, 
                                 int row, 
                                 int column) {

        BoxAndWhiskerCategoryDataset bawDataset 
            = (BoxAndWhiskerCategoryDataset) dataset;
        
        double categoryEnd = domainAxis.getCategoryEnd(
            column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
        );
        double categoryStart = domainAxis.getCategoryStart(
            column, getColumnCount(), dataArea, plot.getDomainAxisEdge()
        );

        double categoryWidth = (categoryEnd - categoryStart);

        double xx = categoryStart;
        int seriesCount = getRowCount();
        int categoryCount = getColumnCount();
        
        if (seriesCount > 1) {
            double seriesGap = dataArea.getWidth() * getItemMargin() 
                               / (categoryCount * (seriesCount - 1));
            double usedWidth = (state.getBarWidth() * seriesCount) 
                               + (seriesGap * (seriesCount - 1));
            // offset the start of the boxes if the total width used is smaller
            // than the category width
            double offset = (categoryWidth - usedWidth) / 2;
            xx = xx + offset + (row * (state.getBarWidth() + seriesGap));
        } 
        else {
            // offset the start of the box if the box width is smaller than the 
            // category width
            double offset = (categoryWidth - state.getBarWidth()) / 2;
            xx = xx + offset;
        } 
        
        double yyAverage = 0.0;
        double yyOutlier;

		//bar colors are determined by the Paint p obtained here in a rotational
		//manner (from a Color array).  By switching the column and raw values,
		//you can get a different color pattern for the bar:  In the method 
		//getItemPaint(), only the first argument counts for the color. The original
		//code Paint p = getItemPaint(row, column); is commented out for a difference.
        //Paint p = getItemPaint(row, column);
        Paint p = getItemPaint(column, row); // <-- this is wrong, dont know who put this here
       // Paint p = PaintUtilities.stringToColor("red"); // coin plot should all be one color
        if (p != null) {
            g2.setPaint(p);
        }
        Stroke s = getItemStroke(row, column);
        g2.setStroke(s);

        double aRadius = 0;                 // average radius

        RectangleEdge location = plot.getRangeAxisEdge();

        Number yQ1 = bawDataset.getQ1Value(row, column);
        Number yQ3 = bawDataset.getQ3Value(row, column);
        Number yMax = bawDataset.getMaxRegularValue(row, column);
        Number yMin = bawDataset.getMinRegularValue(row, column);
        Shape box = null;
        if (yQ1 != null && yQ3 != null && yMax != null && yMin != null) {

            double yyQ1 = rangeAxis.valueToJava2D(
                yQ1.doubleValue(), dataArea, location
            );
            double yyQ3 = rangeAxis.valueToJava2D(
                yQ3.doubleValue(), dataArea, location
            );
            double yyMax = rangeAxis.valueToJava2D(
                yMax.doubleValue(), dataArea, location
            );
            double yyMin = rangeAxis.valueToJava2D(
                yMin.doubleValue(), dataArea, location
            );
            double xxmid = xx + state.getBarWidth() / 2.0;
            
            // draw the upper shadow...
            g2.draw(new Line2D.Double(xxmid, yyMax, xxmid, yyQ3));
            g2.draw(
                new Line2D.Double(xx, yyMax, xx + state.getBarWidth(), yyMax)
            );

            // draw the lower shadow...
            g2.draw(new Line2D.Double(xxmid, yyMin, xxmid, yyQ1));
            g2.draw(
                new Line2D.Double(xx, yyMin, xx + state.getBarWidth(), yyMin)
            );

            // draw the body...
            box = new Rectangle2D.Double(
                xx, Math.min(yyQ1, yyQ3), state.getBarWidth(), 
                Math.abs(yyQ1 - yyQ3)
            );
            if (this.fillBox) {
                g2.fill(box);
            }
            //The following draw the boxes where mean and media are located.
            g2.draw(box);
  
        }
        
        g2.setPaint(this.artifactPaint);

        // draw median...
        Number yMedian = bawDataset.getMedianValue(row, column);
        if (yMedian != null) {
            double yyMedian = rangeAxis.valueToJava2D(
                yMedian.doubleValue(), dataArea, location
            );
            g2.draw(
                new Line2D.Double(
                    xx, yyMedian, xx + state.getBarWidth(), yyMedian
                )
            );
        }
        
        //caIntegrator:  The outLiner is no longer needed to be here.
        // draw the rawData around the box...
        /////////////////////////////////caIntegrator: Begin of drawing dots around the box
        double maxAxisValue = rangeAxis.valueToJava2D(
            rangeAxis.getUpperBound(), dataArea, location
        ) + aRadius;
        double minAxisValue = rangeAxis.valueToJava2D(
            rangeAxis.getLowerBound(), dataArea, location
        ) - aRadius;

        g2.setPaint(p);

		//caIntegrator: oRadius is the radius of the outlier circles. It was used to be 3.
        double oRadius = state.getBarWidth() / 10;    // outlier radius

        List outliers = new ArrayList();

		//Get the raw item data for a given row/column pair for plotting.
		//getRawItemData() method is added to bawDataset object for 
		//this purpose.
        
       	//List yOutliers = bawDataset.getRawItemData(row, column);
       	List yOutliers = this.caintegOutliers.get(String.valueOf(row)+"_"+String.valueOf(column));
       	
        if (yOutliers != null) {
            for (int i = 0; i < yOutliers.size(); i++) {
                double outlier = ((Number) yOutliers.get(i)).doubleValue();
				yyOutlier = rangeAxis.valueToJava2D(
                	outlier, dataArea, location
                );
				outliers.add(
					new Outlier(
						xx + state.getBarWidth() / 2.0, yyOutlier, oRadius
					)
				);

                Collections.sort(outliers);
            }

			//Sort the raw data according to its Y axis first as groups.
			//Grouping of these raw data is based on a single standard:
			//if any number of data, when plotted on a vertical line, overlap
			//with one another, they belong to the same group. In this case, 
			//the grouping is largely determined by the diameter of the dot, that
			//represents each raw data.
			boolean firstOutlier = true;
			double topY = 0;
			double bottomY = 0;
			List groupList = new ArrayList();
			List tempList = null;
			double diameter = 2*oRadius;
			
			double difference = 0;
			for (Iterator iterator = outliers.iterator(); iterator.hasNext();) {                	
                 
                Outlier outlier = (Outlier)iterator.next();	
                
                //Set the smallest outlier as the base bottom line
                if (firstOutlier)
                {
                	firstOutlier = false;
                	bottomY = outlier.getY();
                	tempList = new ArrayList();
                	tempList.add(outlier);
                	continue;
                }
                
                topY = outlier.getY();
                
                //If this one and the one before it is overlapping,
                //Then put them in the same group, so we can spread them
                //horizontally
                difference = topY - bottomY;
                
                if (difference < diameter)
                {
                	tempList.add(outlier);
                }
                //They do not overlap, they belong to the different groups.
                else
                {
                	bottomY = topY;
                	groupList.add(tempList);
                	tempList = new ArrayList();
                	tempList.add(outlier);
                }
			}
           
			//caIntegrator - x axis cloud
           //Process each outlier's x coordinates  
           boolean isOdd = true;
           double offSet = 0;
           int listSize = 0;

           for (int m = 0; m < groupList.size(); m++)
           {
              List list = (List)groupList.get(m);
              if (list != null && list.size() > 1)
              {
					listSize = list.size();
              		isOdd = (listSize%2 == 1)?true:false;
              	
              		if (isOdd)
              		{
              			offSet = diameter*(listSize/2);
              		}
              		else
              		{
              			offSet = diameter*(listSize/2) - oRadius;
              		}
              		
              		Random r = new Random();
          			offSet = (Math.abs(r.nextInt()) % 3)*3;
              		offSet = 0;
              		
              		for (int n = 0; n < list.size(); n++)
              		{
              			Outlier outlier = (Outlier)list.get(n);
              		
              			outlier.setPoint(new Point2D.Double(outlier.getX() - offSet, outlier.getY()));
              			offSet = offSet - diameter;
              			r = new Random();
              			int randInt = Math.abs(r.nextInt()) % 3;
              			offSet = randInt*2;
              		}
              	}              
              }
                 
             //Draw these dots on the graph.   		
             for (Iterator iterator = groupList.iterator(); 
                 iterator.hasNext();) {                	

                List list = (List) iterator.next();
                for (int i = 0; i < list.size(); i++)
                {
                	Outlier outlier = (Outlier)list.get(i);
                	Point2D point = outlier.getPoint();
					drawEllipse(point, oRadius, g2);
            	}
            }			 
        }
    }

    /**
     * Draws a dot to represent an outlier. 
     * 
     * @param point  the location.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    private void drawEllipse(Point2D point, double oRadius, Graphics2D g2) {
        Ellipse2D dot = new Ellipse2D.Double(
            point.getX() + oRadius / 2, point.getY(), oRadius, oRadius
        );
        g2.draw(dot);
    }

    /**
     * Draws two dots to represent the average value of more than one outlier.
     * 
     * @param point  the location
     * @param boxWidth  the box width.
     * @param oRadius  the radius.
     * @param g2  the graphics device.
     */
    private void drawMultipleEllipse(Point2D point, double boxWidth, 
                                     double oRadius, Graphics2D g2)  {
                                         
        Ellipse2D dot1 = new Ellipse2D.Double(
            point.getX() - (boxWidth / 2) + oRadius, point.getY(), 
            oRadius, oRadius
        );
        Ellipse2D dot2 = new Ellipse2D.Double(
            point.getX() + (boxWidth / 2), point.getY(), oRadius, oRadius
        );
        g2.draw(dot1);
        g2.draw(dot2);
    }

    /**
     * Draws a triangle to indicate the presence of far-out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x coordinate.
     * @param m  the y coordinate.
     */
    private void drawHighFarOut(double aRadius, Graphics2D g2, double xx, 
                                double m) {
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
        g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
        g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
    }

    /**
     * Draws a triangle to indicate the presence of far-out values.
     * 
     * @param aRadius  the radius.
     * @param g2  the graphics device.
     * @param xx  the x coordinate.
     * @param m  the y coordinate.
     */
    private void drawLowFarOut(double aRadius, Graphics2D g2, double xx, 
                               double m) {
        double side = aRadius * 2;
        g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
        g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
        g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;   
        }
        if (!(obj instanceof BoxAndWhiskerRenderer)) {
            return false;   
        }
        if (!super.equals(obj)) {
            return false;
        }
        BoxAndWhiskerDotsRenderer that = (BoxAndWhiskerDotsRenderer) obj;
        if (!PaintUtilities.equal(this.artifactPaint, that.artifactPaint)) {
            return false;
        }
        if (!(this.fillBox == that.fillBox)) {
            return false;   
        }
        if (!(this.itemMargin == that.itemMargin)) {
            return false;   
        }
        return true;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.artifactPaint, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.artifactPaint = SerialUtilities.readPaint(stream);
    }

	//caIntegrator //debug method.
	public void printList(List list)
	{
		if (list == null || list.isEmpty())
		{
			System.out.println("\nThe list is either null or empty.\n");
		}
		else
		{
			int count = 0;
			System.out.println("\n****************************size = " + list.size());
			for (Iterator it = list.iterator(); it.hasNext();)
			{
				System.out.print(it.next() + "  ");
				count++;
				if (count%5 == 0)
				{
					System.out.print("\n");
				}
			}
			System.out.println("\n****************************");
		}
	}

	public HashMap getCaintegOutliers() {
		return caintegOutliers;
	}

	public void setCaintegOutliers(HashMap caintegOutliers) {
		this.caintegOutliers = caintegOutliers;
	}
}
