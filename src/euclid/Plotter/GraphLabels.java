package euclid.Plotter;

import org.freedesktop.cairo.Context;
import org.gnome.pango.Alignment;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;

/**
 * Plots User Defined Axis and Title Labels 
 * @author mdh
 *
 */
public class GraphLabels {
	private String title    = "";
	private String subTitle = "";
	private String xlabel   = "";
	private String ylabel   = "" ;
	protected GraphLabels myLabels;
	private static PlotArea plot;
	
	protected GraphLabels(){
		myLabels = this;
		plot = PlotArea.myPlotArea;
	}
	
	/**
	 * Create Title and axis labels
	 */
	public void setLabels() {
		Context cr = Initialise.context;
		
		
		final Layout labels;
		final FontDescription desc;
		final FontDescription subTitleDesc;

		labels = new Layout(cr);
		labels.setWidth(plot.getWidth());
		labels.setAlignment(Alignment.CENTER);

		// Title
		desc = new FontDescription("DejaVu Serif, Book 12");
		labels.setFontDescription(desc);
		labels.setMarkup(title);
		cr.moveTo(0, plot.getTopMargin()*plot.getMargin());
		cr.showLayout(labels);

		// Subtitle
		subTitleDesc = new FontDescription("DejaVu Serif, Book 10");
		labels.setFontDescription(subTitleDesc);
		labels.setMarkup(subTitle);
		cr.moveTo(0, plot.getTopMargin()/2);
		cr.showLayout(labels);

		// X-Axis Label
		labels.setMarkup(xlabel);
		cr.moveTo(0, plot.getBottomMargin() + (plot.getHeight()- plot.getBottomMargin())/2);
		cr.showLayout(labels);

		// Y-Axis Label
		labels.setMarkup(ylabel);
		labels.setAlignment(Alignment.LEFT);
		cr.moveTo(plot.getLeftMargin()*plot.getMargin(),  plot.getHeight()/2);
		cr.rotate(-90*3.14159265359/180);

		cr.showLayout(labels);
	}

	/**
	 * Create Custom Labels
	 * 
	 * @param cr
	 * @param label
	 * @param posX
	 * @param posY
	 */
	public static void addLabel( String label, double posX, double posY){
		final Layout layout;
		final FontDescription desc;
		Context cr = Initialise.context;
		
		layout = new Layout(cr);
		desc = new FontDescription("DejaVu Serif, Book 12");
		layout.setWidth(plot.getWidth());
		layout.setFontDescription(desc);
		layout.setMarkup(label);
		cr.moveTo(posX, posY);
		cr.showLayout(layout);
	}

	/**
	 * Set Plot Title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set Plot SubTitle
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * Set Main X-Axis Label
	 * @param xlabel
	 */
	public void setXlabel(String xlabel) {
		this.xlabel = xlabel;
	}

	/**
	 * set Main Y-Axis Label
	 * @param ylabel
	 */
	public void setYlabel(String ylabel) {
		this.ylabel = ylabel;
	}

}
