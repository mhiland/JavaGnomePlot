package euclid.Plotter;

import java.io.IOException;
import java.util.ArrayList;

import org.freedesktop.cairo.Context;
import org.freedesktop.cairo.Format;
import org.freedesktop.cairo.ImageSurface;
import org.freedesktop.cairo.PdfSurface;
import org.freedesktop.cairo.Surface;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;

import euclid.Viewer.Viewer;

/**
 * Create 2d Plots <br>
 * Used with:<br> 
 *  - embedded GTKDrawingArea <br>
 * 	- Internal Viewer <br>
 * 	- output to PDF, PNG
 * @author mdh
 *
 */
public class Initialise extends Plot{
	private static DrawingArea canvas;
	protected static Context context;
	static ArrayList<Plot2D> plot;
	static boolean autoScale = false;
	private Background background;
	GraphLabels graphLabels;
	private PlotArea plotArea;
	private Plotter plotter;
	Ticks ticks;
	private TickLabels tickLabels;
	Legend legend;
	boolean displayLegend = false;
	protected boolean internalViewer = false;
	protected static boolean outputOnly = false;
	private static Initialise myplot;
	


	/**
	 * Explicit Constructor 
	 */
	protected Initialise() {}

	/**
	 * Construct a new plot with the internal viewer
	 * @param title Application Title
	 */
	public Initialise(String title) {
		internalViewer = true;
		myplot = this;
		plot = new ArrayList<Plot2D>();
		Viewer viewer = new Viewer(title); 
		setCanvas(viewer.getArea());
		
	}

	/**
	 * Construct a new plot for a GTKDrawingArea
	 * @param area GTKDrawingArea
	 */
	public Initialise(DrawingArea area) {
		myplot = this;
		plot = new ArrayList<Plot2D>();
		setCanvas(area);
	}

	/**
	 * Construct a new plot to print out to file
	 * @param width In Pixels
	 * @param height In Pixels 
	 */
	public Initialise(int width, int height){
		myplot = this;
		plot = new ArrayList<Plot2D>();
		setBlankCanvas(width, height);
	}

	/**
	 * Create a Canvas for the GTKDrawingArea
	 */
	private void setCanvas(DrawingArea area) {
		Initialise.canvas = area;
		plotArea = new PlotArea();
		plotArea.setWidth(canvas.getAllocatedWidth());
		plotArea.setHeight(canvas.getAllocatedHeight());
		finalizeCanvas();
	}

	/**
	 * Create Blank Canvas <br>
	 * For use with PNG/PDF
	 * 
	 * @param width in Pixels
	 * @param height in Pixels
	 */
	private void setBlankCanvas(int width, int height) {
		outputOnly = true;
		String[] args = null;
		Gtk.init(args);
		Initialise.canvas = new DrawingArea();
		plotArea = new PlotArea();
		plotArea.setWidth(width);
		plotArea.setHeight(height);
		finalizeCanvas();
	}

	/**
	 * each canvas needs to setup the environment
	 */
	private void finalizeCanvas() {
		background = new Background();
		ticks = new Ticks();
		plotter = new Plotter();
		tickLabels = new TickLabels();
		graphLabels = new GraphLabels();
		legend = new Legend();
	}

	/**
	 * Commits the Plot to the GTK DrawingArea
	 */
	public void show(){
		canvas.connect(new Widget.Draw() {
			public boolean onDraw(Widget source, Context cr) {
				try {
					drawPlot(cr);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}	
		}
				);
		if (internalViewer)
			Gtk.main();
	}


	/**
	 * Output Plot to PDF
	 */
	public void printOutPDF(String fileName){
		try {	
			PdfSurface surface = new PdfSurface(fileName+".pdf", PlotArea.width, PlotArea.height);
			Context cr = new Context(surface);
			drawPlot(cr);
			surface.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * output Plot to PNG
	 */
	public void printOutPNG(String fileName){
		try {
			Surface surface = new ImageSurface(Format.ARGB32, PlotArea.width, PlotArea.height);
			Context cr = new Context(surface);
			drawPlot(cr);
			surface.writeToPNG(fileName+".png");
			surface.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws Plot to a given Context
	 * @param cr
	 */
	private void drawPlot(Context cr){
		Initialise.context = cr;
		plotArea.setPlotArea(canvas, plot);
		background.setBackground();
		if (autoScale){
			plotArea.autoScale();
		}
		ticks.setTicks();
		plotArea.setAxisDim();
		plotter.setPlot(plot);
		tickLabels.setLabels();
		if (displayLegend){
			legend.setLegend(plot);
		}
		graphLabels.setLabels();
		cr.stroke();
	}
	
	public void reDrawPlot(){
		drawPlot(context);
	}

	
	/**
	 * For use with Internal Viewer
	 * @return instance of plot
	 */
	protected static Initialise getPlot(){
		return myplot;
	}
	
	/**
	 * Display Legend 
	 * @param x % of Plot Width [0,100]
	 * @param y % of Plot Height [0,100]
	 */
	public void setLegend(int x, int y) {
		displayLegend = true;
		legend.setXPosition((double)x);
		legend.setYPosition((double)y);
	}
	
	/**
	 * Set Auto Scale to true for X and Y axis
	 */
	public static void setAutoScale(boolean value) {
		autoScale = value;
	}
}
