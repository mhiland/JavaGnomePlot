package euclid.client;


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
public final class Client {
	Plot plt;
	Builder builder ;
    Window win;
	double[] x;
	double[] y;
	double[] z;
	
	private Client(String[] args) {
		//Example of a Users Program 
		// using Glade and a GTKDrawingArea
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
		
		//Connect your plot to the Drawing Area defined in glade
		DrawingArea area = (DrawingArea) builder.getObject("drawingArea");
		area.setTooltipText("test");
		myPlot(area);
	
		Gtk.main();;
	}
	
	
	

	// Plotting Example
	private void myPlot (DrawingArea area){
		getDataToPlot();
		
		this.plt = new Plot(area);
	    
	    
//		plt.line(-10, -0.9, 0, 0.9);
//	    plt.line( 0, 0,17,1);
//	    plt.line( 0, 0,17,2,"myline",3,1);
		plt.linePlot(x,y, "data 1", 1,1);
//		plt.linePlot(x,x, "data 2", 2,1);
//		plt.linePlot(y,x, "data 3", 3,1);
//		plt.linePlot(y,y, "data 4", 4,1);
//		plt.linePlot(x,z, "data 5", 5,1);
//		plt.linePlot(z,x, "data 6", 6,1);
//		plt.scatterPlot(x, y, "scatter 1", 2,1);
		
		plt.setAutoScale();
//		plt.setXmin(4);
//		plt.setXmax(16);
//		plt.setYmin(0);
//		plt.setYmax(2);
		
		plt.setLegend(101, 90);
		
		GraphLabels labels = plt.labels();
		labels.setXlabel("X-Axis");
		labels.setYlabel("Y-Axis");
		labels.setTitle("Title");
		labels.setSubTitle("SubTitle");
		plt.xlabel("test");
		
		
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
		int n = 0;
		while (n<=size){
//			x[i]=i;
//			y[i]=i*i;
			//if(0==i%1){
			x[n]=(i)/1.0;
			y[n]=Math.sin(i*3.1415926/180.0)*30;
			z[n]=Math.sin(i*3.1415926/180.0)*30+i;
			n++;
			//}
			i++;
		}	
	}

	// Quit Button
	private  Clicked handler() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				plt = null;
				builder = null;
				win = null;
				x = null;
				y = null;
				System.out.println("destroyed successfully ");
				Gtk.mainQuit();
			}};
	}
	
	public static void main(String[] args) {
		new Client(args);
	}
}
