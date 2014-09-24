package euclid.Plotter;

import org.freedesktop.cairo.Context;

/**
 * The Background and frame
 * @author mdh
 *
 */
public class Background extends PlotArea{

	/**
	 * Initialize the background and frame
	 */
	public boolean setBackground() {
		Context cr = Initialise.context;
		//Background
		setColour(0);
		cr.rectangle(0, 0, width, height);
		cr.fill();

		//Plot boundary
		setColour(1);
		cr.moveTo(leftMargin, topMargin);
		cr.lineTo(rightMargin, topMargin);
		cr.lineTo(rightMargin, bottomMargin) ;
		cr.lineTo(leftMargin,  bottomMargin);
		cr.lineTo(leftMargin, topMargin);
		
		return true;
	}
}