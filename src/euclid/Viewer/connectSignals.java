package euclid.Viewer;

import java.io.IOException;

import org.gnome.gtk.Builder;
import org.gnome.gtk.Button;
import org.gnome.gtk.Dialog;
import org.gnome.gtk.FileChooserDialog;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.MenuItem.Activate;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.ImageMenuItem;
import org.gnome.gtk.MenuItem;

import euclid.Plotter.Initialise;
import euclid.Plotter.PlotArea;


/**
 * This class connects the GUI functionality from glade
 * @author mdh
 *
 */
public class connectSignals extends PlotArea {
	private Builder builder;
	private static Window aboutWindow;
	private static Window fileChooser;
	private static int zoomFactor = 10;
	private static int panFactor = 10;
	private String file;
	
	public connectSignals(Builder bldr) {
		this.builder = bldr;
		aboutWindow = (Window) builder.getObject("aboutWindow");
		fileChooser = (Window) builder.getObject("fileChooser");

		final Button b = (Button) builder.getObject("quit_button");
		b.connect(handler());

		//Menu Bar Items
		final ImageMenuItem importFile = (ImageMenuItem) builder.getObject("importFile");
		importFile.connect( showFileChooser(builder));
		
		final ImageMenuItem savePNG = (ImageMenuItem) builder.getObject("savePNG");
		savePNG.connect( savePNG(builder));
		
		final ImageMenuItem savePDF = (ImageMenuItem) builder.getObject("savePDF");
		savePDF.connect( savePDF(builder));
		
		final ImageMenuItem menuitemquit = (ImageMenuItem) builder.getObject("menuitemquit");
		menuitemquit.connect( quit(builder));
		
		final ImageMenuItem about = (ImageMenuItem) builder.getObject("about");
		about.connect( showAbout(builder));
		
		//Side Bar Items
		final Button zoomIn = (Button) builder.getObject("zoomIn");
		zoomIn.connect(zoomIn());
		
		final Button zoomOut = (Button) builder.getObject("zoomOut");
		zoomOut.connect(zoomOut());
		
		final Button moveLeft = (Button) builder.getObject("moveLeft");
		moveLeft.connect(moveLeft());
		
		final Button moveRight = (Button) builder.getObject("moveRight");
		moveRight.connect(moveRight());
		
		final Button moveUp = (Button) builder.getObject("moveUp");
		moveUp.connect(moveUp());
		
		final Button moveDown = (Button) builder.getObject("moveDown");
		moveDown.connect(moveDown());

		// File Chooser Window
		final Button fc = (Button) builder.getObject("closeFileExplorer");
		fc.connect(fileChooserQuit(builder));
		
		final Button fcSelect = (Button) builder.getObject("openFile");
		fcSelect.connect(openFile(builder));
		
	}
	

	private static Clicked zoomIn() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				xmax -=zoomFactor;
				xmin +=zoomFactor;
				ymax -=zoomFactor;
				ymin +=zoomFactor;
				Viewer.area.queueDraw();
			}};
	}

	private static Clicked zoomOut() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				xmax +=zoomFactor;
				xmin -=zoomFactor;
				ymax +=zoomFactor;
				ymin -=zoomFactor;
				Viewer.area.queueDraw();
			}};
	}

	private Clicked moveDown() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				ymax -=panFactor;
				ymin -=panFactor;
				Viewer.area.queueDraw();
			}};
	}

	private Clicked moveUp() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				ymax +=panFactor;
				ymin +=panFactor;
				Viewer.area.queueDraw();
			}};
	}

	private Clicked moveRight() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				xmax +=panFactor;
				xmin +=panFactor;
				Viewer.area.queueDraw();
			}};
	}

	private Clicked moveLeft() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Initialise.setAutoScale(false);
				xmax -=panFactor;
				xmin -=panFactor;
				Viewer.area.queueDraw();
			}};
	}

	
	//save plot to png from file menu
	private static Activate savePNG(final Builder builder) {
		return new MenuItem.Activate() {
			@Override
			public void onActivate(MenuItem arg0) {
				Initialise plt = Initialise.getPlot();
				plt.printOutPNG("output2");
			}
		};    
	}

	//save plot to pdf from file menu
	private static Activate savePDF(final Builder builder) {
		return new MenuItem.Activate() {
			@Override
			public void onActivate(MenuItem arg0) {
				Initialise plt = Initialise.getPlot();
				plt.printOutPDF("output1");
			}
		};    
	}
	
	// quit button from file menu
	private static Activate quit(final Builder builder) {
		return new MenuItem.Activate() {
			@Override
			public void onActivate(MenuItem arg0) {
				Gtk.mainQuit();
			}
		};    
	}


	// open about window
	private static Activate showAbout(final Builder builder) {
		return new MenuItem.Activate() {
			@Override
			public void onActivate(MenuItem arg0) {
				aboutWindow.setTitle("  About ");
				((Dialog) aboutWindow).run();
				aboutWindow.hide();
			}
		};    
	}
	
	// open file chooser window
		private static Activate showFileChooser(final Builder builder) {
			return new MenuItem.Activate() {
				@Override
				public void onActivate(MenuItem arg0) {
					fileChooser.setTitle("  Choose File ");
					((Dialog) fileChooser).run();
					fileChooser.hide();
				}
			};    
		}
		private Clicked openFile(Builder builder2) {
			return new Button.Clicked() {
				public void onClicked(Button source) {
					FileChooserDialog str = ((FileChooserDialog) fileChooser);
					file = str.getFilename();
					try {
						ReadInFromFile r = new ReadInFromFile(file);
						Initialise.getPlot().linePlot(r.getX(), r.getY());
						zoomFactor = (int)( Math.abs(ymax-ymin));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					fileChooser.hide();
				}};
		}

		private Clicked fileChooserQuit(Builder builder2) {
			return new Button.Clicked() {
				public void onClicked(Button source) {
					fileChooser.hide();
				}};
		}
	// quit button in main window
	private static Clicked handler() {
		return new Button.Clicked() {
			public void onClicked(Button source) {
				Gtk.mainQuit();
			}};
	}
	

}
