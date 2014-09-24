package euclid.Plotter;

import java.util.ArrayList;
import java.util.List;

import org.freedesktop.cairo.Context;

/**
 * Creates the Axis' Tick Marks
 * @author mdh
 *
 */
public class Ticks {
	public static int xtics  = 4;
	public static int ytics  = 4;
	public static int x2tics = 4;
	public static int y2tics = 4;
	public static int mxtics = 1;
	public static int mytics = 1;
	public static int mx2tics= 1;
	public static int my2tics= 1;
	private static List<Double> majorXPosition;
	private static List<Double> majorX2Position;
	private static List<Double> majorYPosition;
	private static double leftMargin;
	private static double topMargin;
	private static double rightMargin;
	private static double bottomMargin;
	
	
	/**
	 * Create Major and Minor Axis Ticks
	 */
	public void setTicks() {
		PlotArea plot = PlotArea.myPlotArea;
		leftMargin = plot.getLeftMargin();
		topMargin = plot.getTopMargin();
		rightMargin = plot.getRightMargin();
		bottomMargin = plot.getBottomMargin();
		Context cr = Initialise.context;
		double largeInterval;
		double largePosition;
		double smallInterval;
		double smallPosition;
		majorXPosition = new ArrayList<Double>();
		majorX2Position = new ArrayList<Double>();
		majorYPosition = new ArrayList<Double>();

		//Bottom
		if(xtics >0){
			cr.stroke();
			largeInterval =  ((rightMargin-leftMargin)/(xtics+1));
			largePosition = leftMargin;
			majorXPosition.add(largePosition);
			while (largePosition < rightMargin- 1.0 /*avoid rounding error*/){
				if ((int)largePosition != (int)leftMargin){
					if((int)largePosition != (int)rightMargin){
						cr.setLineWidth(2);
						cr.setSource(0.0, 0.0, 0.0, 1.0);
						cr.moveTo(largePosition+0., bottomMargin);
						cr.lineTo(largePosition, bottomMargin-10);
						cr.stroke();
					}
				}
				largePosition += largeInterval;
				majorXPosition.add(largePosition);
			}

			if(mxtics >0){
				smallInterval =  (largeInterval/(mxtics+1));
				smallPosition = leftMargin;
				while (smallPosition < rightMargin){
					cr.setLineWidth(1.0);
					cr.setSource(0.0, 0.0, 0.0, 1.0);
					cr.moveTo(smallPosition, bottomMargin);
					cr.lineTo(smallPosition, bottomMargin-5);
					cr.stroke();
					smallPosition += smallInterval;
				}
			}
		}

		//Top
		if(x2tics>0){
			largeInterval =  ((rightMargin-leftMargin)/(x2tics+1));
			largePosition = leftMargin;
			majorX2Position.add(largePosition);
			while (largePosition < rightMargin){
				if ((int)largePosition != (int)leftMargin){
					if((int)largePosition != (int)rightMargin){
						cr.setLineWidth(2);
						cr.setSource(0.0, 0.0, 0.0, 1.0);
						cr.moveTo(largePosition, topMargin);
						cr.lineTo(largePosition, topMargin+10);
						cr.stroke();
					}
				}
				largePosition += largeInterval;
				majorX2Position.add(largePosition);
			}
			if (mx2tics >0){
				smallInterval =  (largeInterval/(mx2tics+1));
				smallPosition = leftMargin;
				while (smallPosition < rightMargin){
					cr.setLineWidth(1.0);
					cr.setSource(0.0, 0.0, 0.0, 1.0);
					cr.moveTo(smallPosition, topMargin);
					cr.lineTo(smallPosition, topMargin+5);
					cr.stroke();
					smallPosition += smallInterval;
				}
			}
		}

		//Left Side
		if(ytics >0){
			largeInterval =  ((bottomMargin-topMargin)/(ytics+1));
			largePosition = topMargin;
			majorYPosition.add(largePosition);
			while ((largePosition < (bottomMargin - 1.0 /*avoid rounding error*/))){
				if ((int)largePosition != (int)bottomMargin){
					if((int)largePosition != (int)topMargin){
						cr.setLineWidth(2.0);
						cr.setSource(0.0, 0.0, 0.0, 1.0);
						cr.moveTo(leftMargin, largePosition);
						cr.lineTo(leftMargin+10, largePosition);
						cr.stroke();	
					}
				}
				largePosition += largeInterval;
				majorYPosition.add(largePosition);
			}
			if(mytics >0){
				smallInterval =  (largeInterval/(mytics+1));
				smallPosition = topMargin;
				while (smallPosition < bottomMargin){
					cr.setLineWidth(1.0);
					cr.setSource(0.0, 0.0, 0.0, 1.0);
					cr.moveTo(leftMargin, smallPosition);
					cr.lineTo(leftMargin+5, smallPosition);
					cr.stroke();
					smallPosition += smallInterval;
				}
			}
		}

		// Right Side
		if (y2tics >0){
			largeInterval =  ((bottomMargin-topMargin)/(y2tics+1));
			largePosition = topMargin;
			while (largePosition < bottomMargin){
				if ((int)largePosition != (int)bottomMargin){
					if((int)largePosition != (int)topMargin){
						cr.setLineWidth(2.0);
						cr.setSource(0.0, 0.0, 0.0, 1.0);
						cr.moveTo(rightMargin, largePosition);
						cr.lineTo(rightMargin-10, largePosition);
						cr.stroke();
					}
				}
				largePosition += largeInterval;
			}
			if (my2tics >0){
				smallInterval =  (largeInterval/(my2tics+1));
				smallPosition = topMargin;
				while (smallPosition < bottomMargin){
					cr.setLineWidth(1.0);
					cr.setSource(0.0, 0.0, 0.0, 1.0);
					cr.moveTo(rightMargin, smallPosition);
					cr.lineTo(rightMargin-5, smallPosition);
					cr.stroke();
					smallPosition += smallInterval;
				}
			}
		}
		
	}
	
	public List<Double> getMajorXPosition() {
		return majorXPosition;
	}
	public List<Double> getMajorX2Position() {
		return majorX2Position;
	}
	public List<Double> getMajorYPosition() {
		return majorYPosition;
	}

	public void setXTics(int integer) {
		xtics = integer;
	}

	public void setMXTics(int integer) {
		mxtics = integer;
	}

	public void setYTics(int integer) {
		ytics = integer;
	}

	public void setMYTics(int integer) {
		mytics = integer;
	}

	public void setX2Tics(int integer) {
		x2tics = integer;
	}

	public void setMX2Tics(int integer) {
		mx2tics = integer;
	}

	public void setY2Tics(int integer) {
	y2tics = integer;
	}

	public void setMY2Tics(int integer) {
		my2tics = integer;
	}
	

}
