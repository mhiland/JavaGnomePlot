package euclid.Examples;


import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Builder;
import org.gnome.gtk.Button;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;

import euclid.Plotter.GraphLabels;
import euclid.Plotter.Plot;

//Example of a Java-Gnome Plot using a glade interface
public final class Example_EmbeddedPlot {
	Plot plt;
	Builder builder ;
	Window win;
	double[] x;
	double[] y;
	double[] z;


	// Example of a Users Program 
	// using Glade and a GTKDrawingArea
	// Throws exception of x,y data sizes don't match
	private Example_EmbeddedPlot(String[] args) throws Exception {
		Gtk.setProgramName("gtkPlot");
		Gtk.init(args);
		this.builder = new Builder();
		try {
			Pixbuf icon = new Pixbuf("includes/icon.xpm");
			builder.addFromFile("includes/drawingArea.glade");
			this.win = (Window) builder.getObject("window1");
			win.setIcon(icon);
			final Button b = (Button) builder.getObject("quit_button");
			b.connect(handler());
		} catch (Exception e) {
			System.err.println("Error: "+ e);
		} 

		//Connect your plot to the Drawing Area defined in your glade project
		DrawingArea area = (DrawingArea) builder.getObject("drawingArea");
		area.setTooltipText("test");
		myPlot(area);

		Gtk.main();;
	}




	// Plotting Example
	private void myPlot (DrawingArea area) throws Exception{
		this.plt = new Plot(area);
		getDataToPlot();
		
		//Throws exception of x,y data sizes don't match
		plt.linePlot(x,y, "data 1", 1,1);
		plt.linePlot(x,z, "data 2", 4,1);
		plt.scatterPlot(x, y);

//		plt.setAutoScale();
		plt.setXmin(20);
		plt.setXmax(50);
		plt.setYmin(20);
		plt.setYmax(80);

		plt.setLegend(101, 90);
		GraphLabels labels = plt.labels();
		labels.setXlabel("X-Axis");
		labels.setYlabel("Y-Axis");
		labels.setTitle("Title");
		labels.setSubTitle("SubTitle");
//		plt.printOutPDF("output"); //save a copy
//		plt.printOutPNG("thispng");
		plt.show();
	
	}

	// generate a data set
	private void getDataToPlot(){
		int scale = 10;
		int size = 40*scale;
		x = new double[size+1];
		y = new double[size+1];
		z = new double[size+1];
		int i =0;
		while (i<=size){
			x[i]=(i)/1.0;
			y[i]=Math.sin(i*3.1415926/180.0)*30;
			z[i]=Math.sin((i+20)*3.1415926/180.0)*30;
			i++;
		}	
	}

	// Quit Button
	private  Clicked handler() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				System.out.println("destroyed successfully ");
				Gtk.mainQuit();
			}};
	}

	public static void main(String[] args) throws Exception {
		new Example_EmbeddedPlot(args);
	}
}
