package euclid.Plotter;


import org.gnome.gtk.DrawingArea;
import euclid.PlotTypes.Line;
import euclid.PlotTypes.LinePlot;
import euclid.PlotTypes.ScatterPlot;


/**
 * This class serves as a interface so that the user 
 * only needs to instantiate one class to have access
 * to all relevant parts of the plotting package,
 * with function names named appropriately for 
 * intended use.
 * 
 * Also, in keeping the bulk of the code separate from
 * this interface, it will be easier to add features by
 * implementing internally and adding clean ui commands 
 * here.
 * 
 * @author mdh
 *
 */
public class Plot {
	GraphLabels graphLabels;
	Ticks ticks;
	Legend legend;
	Initialise thisPlot = null;
	
	/**
	 * Explicit Constructor 
	 */
	protected Plot() {}

	/**
	 * Construct a new plot with the internal viewer
	 * @param title Application Title
	 */
	public Plot(String title) {
		thisPlot = new  Initialise(title);
		connectPlotElements();
	}

	/**
	 * Construct a new plot for a GTKDrawingArea
	 * @param area GTKDrawingArea
	 */
	public Plot(DrawingArea area) {
		thisPlot = new  Initialise(area);
		connectPlotElements();
	}

	/**
	 * Construct a new plot to print out to file
	 * @param width In Pixels
	 * @param height In Pixels 
	 */
	public Plot(int width, int height){
		thisPlot = new Initialise(width, height);
		connectPlotElements();
	}
	
	/**
	 * Commit data to plot and display
	 */
	public void show(){
		thisPlot.show();
	}
	
	/**
	 * Defines Parameters for this class 
	 */
	private void connectPlotElements(){
		graphLabels = thisPlot.graphLabels;
		ticks  = thisPlot.ticks;
		legend = thisPlot.legend; 
	}
	
	
	/**
	 * Output Plot to PDF
	 */
	public void printOutPDF(String fileName){
		thisPlot.printOutPDF(fileName);
	}
	
	/**
	 * output Plot to PNG
	 */
	public void printOutPNG(String fileName){
		thisPlot.printOutPNG(fileName);
	}

	/**
	 * Plot a 2D LinePlot, 
	 * X and Y must be same size
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public void linePlot(double[] x, double[] y) throws Exception {
		Initialise.plot.add(new LinePlot(x, y));
	}

	/**
	 * Plot a 2D LinePlot, 
	 * X and Y must be same size
	 * @param x
	 * @param y
	 * @param name
	 * @param lc Line Colour
	 * @param lw Line Width
	 * @throws Exception 
	 */
	public void linePlot(double[] x, double[] y, String name, int lc,
			double lw) {
		Initialise.plot.add(new LinePlot(x, y, name, lc, lw));
			}

	/**
	 * Plot a 2D ScatterPlot, 
	 * X and Y must be same size
	 * @param x
	 * @param y
	 */
	public void scatterPlot(double[] x, double[] y) {
		Initialise.plot.add(new ScatterPlot(x, y));
	}

	/**
	 * Plot a 2D ScatterPlot, 
	 * X and Y must be same size
	 * @param x
	 * @param y
	 * @param name
	 * @param Line Colour
	 * @param Linw Width
	 */
	public void scatterPlot(double[] x, double[] y, String name, int lc, double lw) {
		Initialise.plot.add(new ScatterPlot(x, y, name, lc, lw));
	}

	/**
	 * Plot a Straight Line 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void line(double x1, double y1, double x2, double y2) {	
		Initialise.plot.add(new Line(x1,y1, x2,y2));
	}

	/**
	 * Plot a Straight Line 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param name
	 * @param Line Colour
	 * @param Linw Width
	 */
	public void line(double x1, double y1, double x2, double y2,
			String name, int lc, double lw) {	
		Initialise.plot.add(new Line(x1,y1, x2,y2,name,lc,lw));
			}

	public GraphLabels labels() {
		return graphLabels.myLabels;
	}

	/**
	 * Set Plot Title
	 * @param title
	 */
	public void title(String title) {
		graphLabels.setTitle(title);
	}

	/**
	 * Sets the X-Axis Label
	 * @param xlabel
	 */
	public void xlabel(String xlabel) {
		graphLabels.setXlabel(xlabel);
	}

	/**
	 * Sets the Y-Axis Label
	 * @param ylabel
	 */
	public void ylabel(String ylabel) {
		graphLabels.setYlabel(ylabel);
	}

	/**
	 * Sets the SubTitle
	 * @param subTitle
	 */
	public void subTitle(String subTitle) {
		graphLabels.setSubTitle(subTitle);
	}

	/**
	 * Define the number of Major Primary X Ticks
	 * @param integer
	 */
	public void setXTics(int integer) {
		ticks.setXTics(integer);
	}

	/**
	 * Define the number of Minor Primary X Ticks
	 * @param integer
	 */
	public void setMXTics(int integer) {
		ticks.setMXTics(integer);
	}

	/**
	 * Define the number of Major Primary Y Ticks
	 * @param integer
	 */
	public void setYTics(int integer) {
		ticks.setYTics(integer);
	}

	/**
	 * Define the number of Minor Primary Y Ticks
	 * @param integer
	 */
	public void setMYTics(int integer) {
		ticks.setMYTics(integer);
	}

	/**
	 * Define the number of Major Secondary X Ticks
	 * @param integer
	 */
	public void setX2Tics(int integer) {
		ticks.setX2Tics(integer);
	}

	/**
	 * Define the number of Minor Secondary X Ticks
	 * @param integer
	 */
	public void setMX2Tics(int integer) {
		ticks.setMX2Tics(integer);
	}

	/**
	 * Define the number of Major Secondary Y Ticks
	 * @param integer
	 */
	public void setY2Tics(int integer) {
		ticks.setY2Tics(integer);
	}

	/**
	 * Define the number of Minor Secondary Y Ticks
	 * @param integer
	 */
	public void setMY2Tics(int integer) {
		ticks.setMY2Tics(integer);
	}

	/**
	 * Remove Major Primary X Ticks
	 */
	public void unsetXTics() {
		ticks.setXTics(0);
	}

	/**
	 * Remove Minor Primary X Ticks
	 */
	public void unsetMXTics() {
		ticks.setMXTics(0);
	}

	/**
	 * Remove Major Primary Y Ticks
	 */
	public void unsetYTics() {
		ticks.setYTics(0);
	}

	/**
	 * Remove Minor Primary Y Ticks
	 */
	public void unsetMYTics() {
		ticks.setMYTics(0);
	}

	/**
	 * Remove Major Secondary X Ticks
	 */
	public void unsetX2Tics() {
		ticks.setX2Tics(0);
	}

	/**
	 * Remove Minor Secondary X Ticks
	 */
	public void unsetMX2Tics() {
		ticks.setMX2Tics(0);
	}

	/**
	 * Remove Major Secondary Y Ticks
	 */
	public void unsetY2Tics() {
		ticks.setY2Tics(0);
	}

	/**
	 * Remove Minor Secondary Y Ticks
	 */
	public void unsetMY2Tics() {
		ticks.setMY2Tics(0);
	}

	/**
	 * Set Minimum X-Axes Value
	 * @param xmin
	 */
	public void setXmin(double xmin) {
		PlotArea.xmin =xmin;
	}

	/**
	 * Set Maximum X-Axes Value
	 * @param xmax
	 */
	public void setXmax(double xmax) {
		PlotArea.xmax =xmax;
	}

	/**
	 * Set Minimum Y-Axes Value
	 * @param ymin
	 */
	public void setYmin(double ymin) {
		PlotArea.ymin =ymin;
	}

	/**
	 * Set Maximum Y-Axes Value
	 * @param ymax
	 */
	public void setYmax(double ymax) {
		PlotArea.ymax =ymax;
	}

	/**
	 * Set Auto Scale to true for X and Y axis
	 */
	public void setAutoScale() {
		Initialise.autoScale = true;
	}

	/**
	 * Set Auto Scale to true for X and Y axis
	 */
	public static void setAutoScale(boolean value) {
		Initialise.setAutoScale(value);
	}

	/**
	 * Display Legend 
	 * @param x % of Plot Width [0,100]
	 * @param y % of Plot Height [0,100]
	 */
	public void setLegend(int x, int y) {
		thisPlot.setLegend(x, y);
	}

}
