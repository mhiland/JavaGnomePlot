package euclid.Plotter;


import java.util.ArrayList;

import org.freedesktop.cairo.Context;

import euclid.Plotter.Plot2D;

/**
 * The Legend
 * @author mdh
 *
 */
public class Legend extends PlotArea {
	private  static double xLocation ;
	private  static double yLocation ;
	
	
	/**
	 * Create a Legend from a list of plot-objects
	 * @param ListOfPlots
	 */
	public void setLegend( ArrayList<Plot2D> listOfPlot){
		Context cr = Initialise.context;
		//scale x and y as percentage-value to plot dimensions
		double x = (xLocation/100.0)*plotWidth +leftMargin;
		double y = bottomMargin - (yLocation/100.0)*plotHeight;
		int i = 0;
		for (Plot2D plot : listOfPlot){
			//Data Line
			setColour(plot.getLC());
			cr.setLineWidth(2);
			cr.moveTo(x,y+i);
			cr.lineTo(x+10,y+i);
			cr.stroke();
			
			//Data Label
			setColour(1);
			GraphLabels.addLabel(plot.getName(), x+16, y+i-10);
			cr.stroke();
			
			i+=14;
		}
	}

	
	/**
	 * Set X position of Legend
	 * @param x % of width
	 */
	public void setXPosition(double x){
		Legend.xLocation = x;
	}
	
	/**
	 * Set Y position of Legend
	 * @param y % of height plot
	 */
	public void setYPosition(double y){
		Legend.yLocation = y;
	}
}
