package euclid.Plotter;

import java.util.ArrayList;

import org.freedesktop.cairo.Context;

import euclid.PlotTypes.Line;
import euclid.PlotTypes.LinePlot;
import euclid.PlotTypes.ScatterPlot;

/**
 * Calls plotter method for any plot type
 * @author mdh
 *
 */
public class Plotter extends PlotArea {

	/**
	 * renders all plots
	 * @param plot
	 * @throws Exception 
	 */
	public void setPlot(ArrayList<Plot2D> plot)  {
		Context cr = Initialise.context;
		for (Plot2D item : plot){
			whichToPlot(cr, item);
		}
		if (plot.size() ==0)
			System.err.println("Plotter: nothing to plot");
	}

	/**
	 * Sorter - Renders Correct Plot based on Type
	 * @param plot
	 * @throws RuntimeException
	 */
	private void whichToPlot(Context cr, Plot2D plot) {
		String plotType = plot.getType();
		
		switch (plotType) {
		case "LinePlot":  plotter(cr, (LinePlot) plot);
							return;
		case "ScatterPlot": plotter(cr, (ScatterPlot) plot);
							return;
		case "Line": plotter(cr, (Line) plot);
							return;
		default: System.err.println("Plot type not defined");  
		}
	}

	/**
	 * Invokes the Line Plot
	 * @param plot
	 */
	private void plotter(Context cr, LinePlot plot){	
		plot.plotter(cr, plot);
	}

	/**
	 * Invokes the Scatter Plot
	 * @param plot
	 */
	private void plotter(Context cr, ScatterPlot plot){
		plot.plotter(cr, plot);
	}

	/**
	 * Invokes the Line
	 * @param plot
	 */
	private void plotter(Context cr, Line plot){
		plot.plotter(cr, plot);
	}


}