package euclid.Examples;

import euclid.Plotter.GraphLabels;
import euclid.Plotter.Plot;

//Example of a Java-Gnome Plot using a glade interface
public final class Example_InternalViewer {
	Plot plt;
	double[] x;
	double[] y;
	double[] z;
	
	/**
	 *  Use the built-in viewer to view plots quickly
	 * @param args
	 * @throws Exception 
	 */
	private Example_InternalViewer() throws Exception {
		generateData();
			
		plt = new Plot("Java Gnome Plot");
		
		plt.setAutoScale();

		plt.setLegend(101, 90);
		GraphLabels labels = plt.labels();
		labels.setXlabel("X-Axis");
		labels.setYlabel("Y-Axis");
		labels.setTitle("Title");
		labels.setSubTitle("SubTitle");

		plt.show();
	}
	
	
	
	// generate a data set
	private void generateData(){
		int scale = 10;
		int size = 40*scale;
		x = new double[size+1];
		y = new double[size+1];
		z = new double[size+1];
		int i = 0;
		while (i<=size){
			x[i]=i;
			y[i]=Math.sin(i*3.1415926/90.0)*30*Math.exp(i/(size/2.));
			z[i]=Math.sin(i*3.1415926/(180.0*i))*30*Math.sin(i*i*3.1415926/180.0)*Math.sin(i*3.1415926/90.0)*Math.cos(-i*3.1415926/45.0)*Math.exp(i/(size/2.));
			i++;
		}	
	}

	public static void main(String[] args) throws Exception {
		new Example_InternalViewer();
	}
}
