package euclid.Plotter;

import java.awt.geom.Point2D;

import org.freedesktop.cairo.Context;


/**
 * Super Type of all 2D Plots, <br>
 * Includes required methods, <br>
 * and common intricacies. 
 * @author mdh
 *
 */
// Data Values are Scaled to grid units, 
// which are based on the pixel-size of the 
// plot area. 
//
public abstract class Plot2D{

	public abstract double[] getY();

	public abstract double[] getX();

	public abstract String getType() ;

	public abstract String getName();

	public abstract int getLC();

	public abstract String getLT();

	public abstract double getLW();

	protected PlotArea plot;

	
	public void getPlotArea() {
		plot = PlotArea.myPlotArea;
	}


	/**
	 * Checks if a data point is beyond the margins of the Plot Area
	 * 
	 * @param x in original data form
	 * @param y in original data form
	 * @return true if data point is out of bounds, false otherwise
	 * 
	 */
	public boolean isOutOfBounds(double x, double y){
		if( (xScaled(x) <  plot.getLeftMargin()) ||
				(xScaled(x) >  plot.getRightMargin())|| 
				(yScaled(y) <  plot.getTopMargin())  || 
				(yScaled(y) >  plot.getBottomMargin())){
			return true;
		}else{
			return false;
		}
	}
	public boolean isOutOfBounds2(double x, double y){
		if( (x < plot.getLeftMargin()) ||
				(x > plot.getRightMargin())|| 
				(y < plot.getTopMargin())  || 
				(y > plot.getBottomMargin())){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * Takes a Line segment and finds 
	 * its intersection point with the plot border.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return Point2D The intersection point with the plot border.
	 */
	public Point2D boundaryPoint(double xpt1, double ypt1, double xpt2, double ypt2){
		double x1 = xScaled(xpt1); 
		double x2 = xScaled(xpt2);
		double y1 = yScaled(ypt1);
		double y2 = yScaled(ypt2); 

		Point2D rb = findIntersectionPoint(x1, y1, x2, y2, plot.getRightMargin(), plot.getBottomMargin(), plot.getRightMargin(), plot.getTopMargin());
		Point2D lb = findIntersectionPoint(x1, y1, x2, y2, plot.getLeftMargin(), plot.getBottomMargin(), plot.getLeftMargin(), plot.getTopMargin());
		Point2D tb = findIntersectionPoint(x1, y1, x2, y2, plot.getLeftMargin(), plot.getTopMargin(), plot.getRightMargin(), plot.getTopMargin());
		Point2D bb = findIntersectionPoint(x1, y1, x2, y2, plot.getLeftMargin(), plot.getBottomMargin(), plot.getRightMargin(), plot.getBottomMargin());

		if      (rb!=null) return rb;
		else if (lb!=null) return lb;
		else if (tb!=null) return tb;
		else if (bb!=null) return bb;
		
		//return an inbound value if no alternative was found
		if (isOutOfBounds(x1, y1)){
			return new Point2D.Double(x2,y2); 
		}else 
			return new Point2D.Double(x1,y1); 
	}

	/**
	 * Finds Intersection point of two line segments. <br>
	 * Line1 [(x1,y1) to (x2,y2)] <br>
	 * Line2 [(x3,y3) to (x4,y4)]
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @param x4
	 * @param y4
	 * @return Point2D or null
	 */
	public Point2D findIntersectionPoint(
			double x1, double y1, double x2, double y2, 
			double x3, double y3, double x4, double y4) 
	{
		double value1;
		double value2;
		double denominator;
		
		denominator = (y4-y3)*(x2-x1)-(x4-x3)*(y2-y1);
		if (denominator == 0.0) 
			return null;
		
		value1 = ((x4-x3)*(y1-y3)-(y4-y3)*(x1-x3))/denominator;
		value2 = ((x2-x1)*(y1-y3)-(y2-y1)*(x1-x3))/denominator;
		
		if (((value1 >= 0.0) && (value1 <= 1.0)) && ((value2 >= 0.0) && (value2 <= 1.0))){
			return new Point2D.Double( (x1 + value1*(x2 - x1)), (y1 + value1*(y2 - y1)));
		}else
			return null;
	}

	/**
	 * Plots a Line Segment to the bounded plot area.
	 * (x1,y1) --> (x2,y2)
	 * @param x1 
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void plotLineSegement(double x1, double y1 ,double x2, double y2){
		Context cr = Initialise.context;
		
		//check if the points are not infinite or NaN
		if (!isGoodValue(x1) || !isGoodValue(y1)
				|| !isGoodValue(x2) || !isGoodValue(y2))
			return;
			
			
		// Point1 and Point2 are both in-bounds
		if (!isOutOfBounds(x1,y1)&& !isOutOfBounds(x2,y2) ){
			cr.moveTo(xScaled(x1),yScaled(y1));
			cr.lineTo(xScaled(x2),yScaled(y2));
			return;
		}

		// P1 is out of bounds
		else if (isOutOfBounds(x1,y1)&& !isOutOfBounds(x2,y2)){
			cr.moveTo(xScaled(x2),yScaled(y2));
			Point2D point = boundaryPoint(x1,y1,x2,y2);
			cr.lineTo(point.getX(),point.getY());
			return;
		}

		// P2 is out of bounds
		else if (!isOutOfBounds(x1,y1)&& isOutOfBounds(x2,y2)){
			cr.moveTo(xScaled(x1),yScaled(y1));
			Point2D point = boundaryPoint(x1,y1,x2,y2);
			cr.lineTo(point.getX(),point.getY());
			return;
		}

		//P1 and P2 are out of bounds
		else if (isOutOfBounds(x1,y1)&& isOutOfBounds(x2,y2)){
				Point2D point = findAPointWithinPlotArea(x1,y1,x2,y2);
				if (point != null){
					plotLineSegement(x1,y1,point.getX(),point.getY());
					plotLineSegement(x2,y2,point.getX(),point.getY());
				}
			return;
		}
	}

	/**
	 * Given 2 points, will find a third point inbetween that is 
	 * in bounds of the plot area.<br>
	 * @param y2 
	 * @param x2 
	 * @param y1 
	 * @param x1 
	 * @return
	 */
	private Point2D findAPointWithinPlotArea(double x1, double y1, double x2, double y2) {
		double accuracy = 100;
		double interval = Math.abs(x2-x1)/accuracy;
		double slope = (y2-y1)/(x2-x1);
		double yIntercept = y1 - slope*(x1);
		double ptX;
		double ptY;
		
		//Set the leftmost point as starting point
		if (x1<x2){
			ptX = x1;
			ptY = y1;
		}else{
			ptX = x2;
			ptY = y2;
		}
		
		//linear search
		for(double i=ptX;isOutOfBounds(ptX, ptY); i++){
			ptX += interval;
			ptY = slope*ptX+yIntercept;
			if (i>accuracy){
				return null;
			}
		}
		 return new Point2D.Double(ptX,ptY);
	}

	/**
	 * Scales data-value appropriately to X-Axis of the plot
	 * @param x as original data 
	 * @return X in grid-units
	 */
	public double xScaled(double x){
		return (plot.getPlotWidth()*x/plot.getxScale() +plot.getxOffSet());
	}

	/**
	 * Scales data-value appropriately to Y-Axis of the plot
	 * @param y as original data 
	 * @return Y in grid-units
	 */
	public double yScaled(double y){
		return (plot.getyOffSet()-(plot.getPlotHeight()/plot.getyScale())*y);
	}
	
	/**
	 * A Test on the validity of data points. <br>
	 * Will not plot infinity or NaN.
	 * @param val
	 * @return True if a valid number
	 */
	public boolean isGoodValue(double val){
		if((Double.isNaN(val)) || (Double.isInfinite(val))){
			return false;
		}
		return true;
	}

}
