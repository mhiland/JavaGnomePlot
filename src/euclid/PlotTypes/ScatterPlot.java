package euclid.PlotTypes;


import org.freedesktop.cairo.Context;

import euclid.Plotter.Plot2D;


/**
 * 2D Scatter Plot
 * @author mdh
 *
 */
public class ScatterPlot extends Plot2D {
	private double[] x;
	private double[] y;
	private String name = "Scatter Data";
	private int lc=1;
	double size=2;
	private String lt = "+";
	private double lw = 2.0;

	/**
	 * Constructor- Basic
	 * @param x
	 * @param y
	 */
	public ScatterPlot(double[] x, double[] y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor- Complete
	 * @param x
	 * @param y
	 * @param DataLabel
	 * @param LineColour
	 */
	public ScatterPlot(double[] x, double[] y, String name, int lc, double lw) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.lc = lc;
		this.lw = lw;
	}

	/**
	 * Render scatter plot to canvas
	 * @param cr
	 * @param plot
	 */
	public void plotter(Context cr, ScatterPlot plot){	
		super.getPlotArea();
		y = plot.getY();
		x = plot.getX();
		super.plot.setColour(lc);
		super.plot.setLineWidth(lw);
		int i = 0;
		while (i < (x.length-1.0)){
			// will not plot the point if is out of bounds
			if(isGoodValue(x[i])&&isGoodValue(y[i])){
				if (!isOutOfBounds(x[i],y[i])){
					cr.moveTo(xScaled(x[i])-size,yScaled(y[i]));
					cr.lineTo(xScaled(x[i])+size,yScaled(y[i]));
					cr.stroke();
					cr.moveTo(xScaled(x[i]),yScaled(y[i])-size);
					cr.lineTo(xScaled(x[i]),yScaled(y[i])+size);
					cr.stroke();
				}
			}
			i++;
		}
	}

	/**
	 * Get Y-Data array
	 */
	@Override
	public double[] getY() {
		return y;
	}

	/**
	 * Get x-Data array
	 */
	@Override
	public double[] getX() {
		return x;
	}

	/** 
	 * Get Plot Type
	 */
	@Override
	public String getType(){
		return "ScatterPlot";
	}

	/**
	 * Get Data Label
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get Line Colour
	 */
	@Override
	public int getLC(){
		return lc;
	}

	/**
	 * Get Line Type
	 */
	@Override
	public String getLT(){
		return lt;
	}

	/**
	 * Get Line Width
	 * @return
	 */
	@Override
	public double getLW(){
		return lw;
	}
}
