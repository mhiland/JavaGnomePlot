package euclid.Plotter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Labels for X and Y axis' tick marks
 * @author mdh
 *
 */
public class TickLabels extends Ticks{
	static double label;
	static GraphLabels labels = new GraphLabels();
	
	/**
	 * Creates Labels for the Major Tick Marks
	 */
	public void setLabels(){
		PlotArea plot = PlotArea.myPlotArea;
		plot.setColour(1);
		String labelString;
		NumberFormat largeFormat = new DecimalFormat();
		NumberFormat mediumFormat = new DecimalFormat();
		NumberFormat smallFormat = new DecimalFormat();
		largeFormat = new DecimalFormat("0.#E0");
		mediumFormat= new DecimalFormat("#####");
		smallFormat = new DecimalFormat("#.###");
		
		//X-Axis Major Labels
		label = plot.getxmin();
		for (double posX : getMajorXPosition()){
			// Format the label depending on its value
			if(label >=0.0 && label <=10.0){
				labelString =smallFormat.format(label);
			}
			else if (label >= 1000){
				labelString =largeFormat.format(label);
			}else{
				labelString =mediumFormat.format(label);
			}
			GraphLabels.addLabel(labelString, posX-labelString.length()*5, plot.getBottomMargin()+5);
			label += plot.getxInterval();
		}
		
		// Y-Axis Major Labels
		// right justified
		label =  plot.getymin();
		int adjustment;
		for (Double posY : getMajorYPosition()){
			// Format the label depending on its value
			if((int)label >=0 && Math.abs(plot.getymax()) <=2){
				labelString = String.format("%7s", smallFormat.format(label));
				adjustment = 56;
			}else if (label >= 1000){
				labelString = String.format("%5s", largeFormat.format(label));
				adjustment = 56;
			}else{
				labelString = String.format("%6s", mediumFormat.format(label));
				adjustment = 51;
			}
			GraphLabels.addLabel(labelString, plot.getLeftMargin()-adjustment, plot.getHeight()-posY-7.0);
			label += plot.getyInterval();
			System.out.println();
		}
	}
}
