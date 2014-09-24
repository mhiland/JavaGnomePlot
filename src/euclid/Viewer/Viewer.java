package euclid.Viewer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Builder;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Window;



/**
 * Internal Viewer <br>
 * to view plots interactively.
 * @author mdh
 *
 */
public class Viewer{
	private Builder builder ;
	private Window win;
	protected static DrawingArea area;

	/**
	 * Construct the Viewer from external glade file
	 * @param args
	 */
	public Viewer(String windowTitle) {
		Gtk.setProgramName(windowTitle);
		String[] args = null;
		Gtk.init(args);
		
		this.builder = new Builder();
		getGladeSetup();
		getIconSetup();
		area = (DrawingArea) builder.getObject("drawingArea");
		area.setTooltipText("Java Gnome Viewer");
	}

	
		/**
		 * Get external Glade file and set it up.
		 */
		private void getGladeSetup() {
			//Create Temp glade file
			try {
				File file = null;
				String resource = "viewer.glade";
				URL res = getClass().getResource(resource);
				if (res.toString().startsWith("jar:")) {
					try {
						InputStream input = getClass().getResourceAsStream(resource);
						file = File.createTempFile("tempfile", ".tmp");
						@SuppressWarnings("resource")
						OutputStream out = new FileOutputStream(file);
						int read;
						byte[] bytes = new byte[1024];
						while ((read = input.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
						file.deleteOnExit();
					} catch (IOException ex) {
					}
				} else {
					file = new File(res.getFile());
					System.out.println("here 2"+file);
				}
				if (file != null && !file.exists()) {
					try {
						InputStream input = getClass().getResourceAsStream(resource);
						file = File.createTempFile("tempfile", ".tmp");
						@SuppressWarnings("resource")
						OutputStream out = new FileOutputStream(file);
						int read;
						byte[] bytes = new byte[1024];
						while ((read = input.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
						file.deleteOnExit();
					} catch (IOException ex) {
					}
				}
				builder.addFromFile(file.getPath());
				new connectSignals(builder);
				this.win = (Window) builder.getObject("window1");
				//win.setIcon(icon);
			} catch (Exception e) {
				System.err.println("Error 1: "+ e);
			} 
	}
		
		/**
		 * Get external Icon File and set
		 */
		private void getIconSetup() {
			try {
				File icon = null;
				String iconResource = "icon.xpm";
				URL ires = getClass().getResource(iconResource);
				System.out.println("success a");
				if (ires.toString().startsWith("jar:")) {
					try {
						InputStream input = getClass().getResourceAsStream(iconResource);
						icon = File.createTempFile("tempicon", ".xmp");
						@SuppressWarnings("resource")
						OutputStream out = new FileOutputStream(icon);
						int read;
						byte[] bytes = new byte[1024];

						while ((read = input.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
						Pixbuf tempIcon = new Pixbuf(icon.getPath());
						System.out.println(icon.getPath());
						win.setIcon(tempIcon);
						System.out.println("Success b");
						icon.deleteOnExit();
					} catch (IOException e) {
						System.err.println("a " + e);
					}
				} else {
					icon = new File(ires.getFile());
					Pixbuf tempIcon = new Pixbuf(icon.getPath());
					win.setIcon(tempIcon);
					System.out.println("Success c");
				}
			}catch (Exception e){
				System.err.println("b " + e);
			}
	}

		/**
		 * Return the drawing area.
		 * @return area
		 */
		public DrawingArea getArea(){
			return area;
		}
	}
