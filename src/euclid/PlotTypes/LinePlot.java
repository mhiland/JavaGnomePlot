package euclid.PlotTypes;

import org.freedesktop.cairo.Context;

import euclid.Plotter.Plot2D;

/**
 * Standard Line Plot
 * @author mdh
 *
 */
public class LinePlot extends Plot2D{
	private double[]x;
	private double[]y;
	private String name ="LinePlot Data";
	private String lt = "-";
	private int lc = 1;
	private double lw = 2.0;

	/**
	 * Construct a LinePlot Object
	 * @param x Data[]
	 * @param y Data[]
	 * @throws Exception 
	 */
	public LinePlot(double[] x, double[] y) throws Exception {
		this.x = x;
		this.y = y;
		if (x.length == y.length){
			return;
		}
		throw new Exception("array size do not match");
	}
	
	/**
	 * Construct a LinePlot Object with name
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public LinePlot(double[] x, double[] y, String name, int lc, double lw) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.lc = lc;
		this.lw = lw;
		if (x.length == y.length){
			return;
		}
		System.err.println("array size do not match");
	}
	
	/**
	 * Render a given LinePlot Object to the Drawing Area
	 * @param plot
	 */
	public void plotter(Context cr, LinePlot plot){	
		super.getPlotArea();
		y = plot.getY();
		x = plot.getX();
		
		int i =0;
		super.plot.setColour(lc);
		super.plot.setLineWidth(lw);
		while (i < (x.length-1)){
			plotLineSegement(x[i],y[i],x[i+1],y[i+1]);
			i++;
		}
		cr.stroke();
		
	}
	
	/**
	 * Get X-Data array
	 */
	@Override
	public double[] getX(){
		return x;
	}
	
	/**
	 * Get Y-Data array
	 */
	@Override
	public double[] getY(){
		return y;
	}
	
	/**
	 * Get Plot Type
	 */
	@Override
	public String getType(){
		return "LinePlot";
	}
	
	/**
	 * Get Data Name
	 */
	@Override
	public String getName(){
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
