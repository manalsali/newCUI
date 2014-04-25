package opengl;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import heart_panel.HeartInterface;
import main_cardiopointer.Main;
import menu.MenuMethods;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import DicomParse.dicomParser;

/**
 * 
 * Generic LWJGL Application SuperCLass. contains the boiler plate code for the
 * window. currently, window size is fixed and cannot be resized, but for the
 * purposes of demonstrating the leap motion functionality, it is satisfactory.
 * 
 */
public class Application {

	protected ArrayList<float[]> points = new ArrayList<float[]>();
	protected ArrayList<Float> colors = new ArrayList<Float>();
	protected Camera camera;
	protected float x = 0, y = 0, z = 0;
	protected int imageThreshold = 170;
	protected boolean requestExit = false;

	public Application() {
		new Task().execute();
	}

	private void begin() {

		Initialise();
		while (!requestExit) {
			update();
			render();

			Display.update();
			Display.sync(60); // 60 frames per second ( matching monitor refresh
								// rate)
		}
		destroy();
	}

	public void Initialise() {

	}

	public void update() {

	}

	public void render() {

	}

	public void destroy() {
		Display.destroy();
		Main.removeHeartPanel();
		// System.exit(0);
		MenuMethods.beginVolumeRender = false;

	}
	
	
	public class Task extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
        	startDisplay();
            return null;
        }

        @Override
        protected void done() {
        	
        }
    }
	
	public void startDisplay() {

		while (!MenuMethods.beginVolumeRender) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("im in");
		ArrayList<int[][]> dcmList;
		dicomParser dcmParser = new dicomParser();
		dcmList = new ArrayList<int[][]>();

		for (int i = 0; i < MenuMethods.dcmFiles.length; ++i) {
			dcmList.add(dcmParser.getRGBComponents(dcmParser
					.getDCMBufferedImage(MenuMethods.dcmFiles[i])));
		}

		MenuMethods.dcmFiles = null;

		for (int k = 0; k < dcmList.size(); k++) {
			int[][] dcmPixelComponents = dcmList.get(k);
			for (int i = 0; i < 512; i++) {
				for (int j = 0; j < 512; j++) {
					if (dcmPixelComponents[i][j] >= imageThreshold) {
						float[] p = new float[3];
						x += p[0] = (float) i;
						y += p[1] = (float) j;
						z += p[2] = (float) k;

						// System.out.println(c[0]*255 + " " + c[1]*255
						// + " " + c[2]*255);
						colors.add(dcmPixelComponents[i][j] / 255f);
						points.add(p);
					}
				}
			}
		}

		// to find average point
		x = x / (float) (points.size());
		y = y / (float) (points.size());
		z = z / (float) (points.size());

		System.out.println("Average is: " + x + " " + y + " " + z);

		for (int i = 0; i < points.size(); ++i) {
			float[] p = new float[3];
			p = points.get(i);

			p[0] = p[0] + x;
			p[1] = p[1] + y;
			p[2] = p[2] + z;

			points.set(i, p);
		}

		x = x / (float) (points.size() / 1000);
		y = y / (float) (points.size() / 1000);
		z = z / (float) (points.size() / 1000);

		try {
			//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			//Display.setDisplayMode(new DisplayMode(dim.width/2,dim.height/2));
			Display.setParent(Main.heart.canvas);
			Display.setVSyncEnabled(true);
			Display.create();

			begin();

		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1); // this application is hardware
								// accelerated.
		}
	}
	
}