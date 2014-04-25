package heart_panel;

/*
 * will contain information regarding the 3d heart model
 */

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import opengl.GLContext;


public class HeartInterface {

	public Canvas canvas;
	public static Thread t;
	public static JPanel panel = new JPanel();

	public HeartInterface() {
		startthread();
		
	}
	
	public void startthread() {
		new Task().execute();
	}

	public void startDisplay() {

		try {
			canvas = new Canvas() {

				public final void addNotify() {
					super.addNotify();
					new GLContext();
				}

				public final void removeNotify() {
					super.removeNotify();
				}
			};

			canvas.setSize(800,600);
			panel.setLayout(new BorderLayout());
			panel.add(canvas);
			// adding the controls to the layout
			canvas.setFocusable(true);
			canvas.requestFocus();
			canvas.setIgnoreRepaint(false);
			panel.setVisible(true);
			
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException("unable to create display");
		}
	}
	
	public class Task extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            /* Executing method on background thread.
             * The loading bar should keep moving because, although method() is time consuming, we are on a background thread.
            */ 
        	startDisplay();
            return null;
        }

        @Override
        protected void done() {
            /* Executing method on Event Dispatch Thread.
             * The loading bar should stop because method() is time consuming and everything on the Event Dispatch Thread
             * (like the motion of the progress bar) is waiting for it to finish.
            */

            // 
//        	startDisplay();
        }
    }
}