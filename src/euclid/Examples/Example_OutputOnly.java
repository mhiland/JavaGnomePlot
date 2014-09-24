package euclid.Examples;

import euclid.Plotter.GraphLabels;
import euclid.Plotter.Plot;


//Example of a Java-Gnome Plot output to file
public final class Example_OutputOnly {
	Plot plt;
	double[] x;
	double[] y;
	double[] z;
	
	private Example_OutputOnly() {
		
		getDataToPlot();
		
		this.plt = new Plot(800, 600);
	    
		plt.linePlot(x,y, "data 1", 1,1);
		plt.linePlot(x,x, "data 2", 2,1);
		plt.linePlot(y,x, "data 3", 3,1);
		plt.linePlot(y,y, "data 4", 4,1);
		plt.linePlot(x,z, "data 5", 5,1);
		plt.linePlot(z,x, "data 6", 6,1);
		
		plt.setAutoScale();

		plt.setLegend(101, 90);
		GraphLabels labels = plt.labels();
		labels.setXlabel("X-Axis");
		labels.setYlabel("Y-Axis");
		labels.setTitle("Title");
		labels.setSubTitle("SubTitle");

		plt.printOutPDF("output");
		plt.printOutPNG("thispng");
		
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
	

	
	public static void main(String[] args) throws Exception {
		
		new Example_OutputOnly();
	}
}
