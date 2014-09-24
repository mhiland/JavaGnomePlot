package euclid.PlotTypes;


import org.freedesktop.cairo.Context;

import euclid.Plotter.Plot2D;

/**
 * Line Object and Plotter
 * @author mdh
 *
 */
public class Line extends Plot2D {
	private double[] xLine;
	private double[] yLine;
	private double[] x ;
	private double[] y ;
	private String name ="Line";
	private int lc = 1;
	private String lt = "-";
	private double lw = 2.0;
	
	
	/**
	 *  Constructor of a Line Segment - Basic
	 * @param x1 Point 1
	 * @param y1 Point 1
	 * @param x2 Point 2
	 * @param y2 Point 2
	 */
	public Line(double x1, double y1,double x2, double y2){
		xLine = new double[2];
		yLine = new double[2];
		xLine[0] =x1;
		xLine[1] =x2;
		yLine[0] =y1;
		yLine[1] =y2;
	}
	
	/**
	 *  Constructor of a Line Segment - Complete
	 * @param x1 Point 1
	 * @param y1 Point 1
	 * @param x2 Point 2
	 * @param y2 Point 2
	 * @param name Data Label
	 * @param lc Line Colour
	 * @param lw Line Width
	 */
	public Line(double x1, double y1,double x2, double y2, String name, int lc, double lw){
		xLine = new double[2];
		yLine = new double[2];
		xLine[0] =x1;
		xLine[1] =x2;
		yLine[0] =y1;
		yLine[1] =y2;
		this.name = name;
		this.lc = lc;
		this.lw = lw;
	}
	
	/**
	 * Plot a Line Segment
	 * @param cr
	 * @param plot
	 */
	public void plotter(Context cr, Line plot){	
		super.getPlotArea();
		y = plot.getY();
		x = plot.getX();
		int i =0;
		super.plot.setColour(lc);
		super.plot.setLineWidth(lw);
		while (i < (x.length-1.0)){
			plotLineSegement(x[i],y[i],x[i+1],y[i+1]);
			i++;
		}
		cr.stroke();
	}

	/**
	 * Y-axis Values 
	 * [start,end]
	 */
	@Override
	public double[] getY() {
		return yLine;
	}

	/**
	 * X-axis Values
	 * [start, end]
	 */
	@Override
	public double[] getX() {
		return xLine;
	}
	
	/**
	 * Object Type
	 */
	@Override
	public String getType() {
		return "Line";
	}

	@Override
	public String getName() {
		return name;
	}
	
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
