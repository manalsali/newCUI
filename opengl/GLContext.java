
package opengl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;


public class GLContext extends Application
{
	//private float rotAngle =0;
	  //private float x = 0;
	 private Camera camera =null;
	 private boolean crossX = false;
	 private float crossXXcoord;
	 private float crossXYcoord;
	 private float crossXZcoord;
	public void Initialise()
	{
		
		/*
		// 3D shape has a more realistic look with Perspective mode rather than Orthographic
				glMatrixMode(GL_PROJECTION);
				gluPerspective(70f, 800f/600f, 0.2f, 800);
				
				// render to the full size of the window
				glViewport(0,0,Display.getWidth(), Display.getHeight());
				
				glMatrixMode(GL_MODELVIEW);
				glLoadIdentity(); 
				
				glEnable(GL_DEPTH_TEST);
				*/
		
		 camera = new Camera(45, (float)Display.getWidth()/ (float)Display.getHeight(), 0.001f, 800, x,y,z);
		// GLU.gluLookAt(x,y,z-0.2f, x, y, z, 0, 1, 0);
		 crossXXcoord = camera.getX();
		 crossXYcoord = camera.getY();
		 crossXZcoord = 0;
		 //camera.useView(x,y,z);
				 
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
	   glPointSize(1.0f);
	   glLoadIdentity();
	   //glTranslatef(0,0, -camera.getcRadius());
	   
	   camera.useView(x,y,z);
	   
	    //camera.useView();
	
		boolean zoomIn = Keyboard.isKeyDown(Keyboard.KEY_R);
		boolean zoomOut = Keyboard.isKeyDown(Keyboard.KEY_E); 
		boolean moveUP = Keyboard.isKeyDown(Keyboard.KEY_W);
		boolean moveDown = Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean	left = Keyboard.isKeyDown(Keyboard.KEY_A);
		boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
		boolean reset = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		boolean enabledCrossX = Keyboard.isKeyDown(Keyboard.KEY_C);
		boolean exit = Keyboard.isKeyDown(Keyboard.KEY_J);
		
			if(enabledCrossX)
			{
				if(!crossX)
					crossX = true;
				else
					crossX = false;
				crossXXcoord = camera.getX();
				 crossXYcoord = camera.getY();
				 crossXZcoord = 0;
			  //System.out.println("value of crossX is: "+crossX);
			}
			if(!crossX)
			{
				if(zoomIn)
					camera.zoom(1);
				if(zoomOut)
					camera.zoom(-1);
				if(left)
					camera.rotateYAxis(1);//cam.rotateY(-0.1f);
				
				if(right)
					camera.rotateYAxis(-1);//cam.rotateY(0.1f);
				if(moveUP)
					camera.rotateXAxis(1);
				if(moveDown)
					camera.rotateXAxis(-1);
				
				
				if(reset)
				{
					camera.setX(0.20331843f);
					camera.setY(0.24773124f);
					camera.setZ(2.12998031f);
					camera.setRotationX(0f);
					camera.setRotationY(0f);
					camera.setZoom(1f);
					
				}
			}
				
			
	/*
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			camera.rotateY(2f);
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			camera.rotateY(-2f);
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
				camera.rotateX(2f);
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				camera.rotateX(-02f);
		*/
		
	    
		
			
			
		
		
		//glTranslatef(0,0,-20.5f);
		
        
		
		//glLoadIdentity();
		
		
		
		

		
		
			if(crossX)
			{
				glPushMatrix();
				glLoadIdentity();
				camera.useCrossView();
				glLineWidth(2.5f);
				glColor3f(1,0,0);
				glBegin(GL_LINES);
				glVertex3f(crossXXcoord-2f,crossXYcoord, crossXZcoord);
				glVertex3f(crossXXcoord+2f,crossXYcoord, crossXZcoord);
				
				glVertex3f(crossXXcoord,crossXYcoord-2f, crossXZcoord);
				glVertex3f(crossXXcoord,crossXYcoord+2f, crossXZcoord);
				glEnd();
				
				
					if(zoomIn)
						crossXZcoord +=0.05f;
						
					if(zoomOut)
						crossXZcoord -=0.05f;
					if(left)
						crossXXcoord -=0.05f;
					
					if(right)
						crossXXcoord +=0.05f;
					
					if(moveUP)
						crossXYcoord +=0.05f;
					if(moveDown)
						crossXYcoord -=0.05f;
					glPopMatrix();
			
			}
		
		
		//glPushMatrix();
		//camera.useView(x,y,z);
	    
		
		glBegin(GL_POINTS);
	    for(int i = 0; i < points.size(); ++i)
	    {
	    	float c = colors.get(i);
	    	float[] p = points.get(i);
	    	glColor3f(c, c, c);
	    	glVertex3f((float)(511-p[0])/ (float) 1000, (float)(511-p[1])/ (float) 1000, ((float)(185-p[2])/ (float) 1000));
	    	//glNormal3f();
	    	//glVertex3f(511-p[0],511-p[1],511-p[2]);
	    }
	    
	    glEnd();
	    
		//glPopMatrix();
			
		
		/*
		glBegin(GL_QUADS);
    	glColor3f(0.0f,0.0f,1f);
    	
    	glVertex3f(0, 3, 0);
    	glVertex3f(0, 6, 0);
    	glVertex3f(1, 6, 0);
    	glVertex3f(1, 3, 0);
    
        glEnd();
        */
     /*
        glColor3f(0.0f,1.0f,0f);
        int RADIUS = 2;
        float DEGREE_TO_RAD = (float) (Math.PI / 180);
        glBegin( GL_LINE_LOOP );
        glVertex2f( 4.5f,4.5f );
        int M_IN_DEGREE = 370;
        int N_IN_DEGREE = 100;
        for( int nR =N_IN_DEGREE; nR < M_IN_DEGREE; nR++ )
        {
            float fX = (float) Math.sin((float)nR * DEGREE_TO_RAD ) ;
            float fY = (float) Math.cos((float)nR * DEGREE_TO_RAD );
            glVertex2f( fX, fY );
        }
        glEnd();
       */ 
	   /*
	    glLineWidth(2.5f);
	   
	    glBegin(GL_LINES);
		    glColor3f(1,0,0);
		    glVertex3f(0,-10,0);
		    glVertex3f(0,10,0);
		    
		    glVertex3f(-10,0,0);
		    glVertex3f(10,0,0);
		    
		    glVertex3f(0,0,-10);
		    glVertex3f(0,0,10);
		    
	    glEnd();
		
	    */
	    
		
		
	    if(exit)
	    	requestExit = true;
			
	    
	   

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void pollInput()
    {
    	while (Keyboard.next()) {
    		if (Keyboard.getEventKeyState()) {
    		if (Keyboard.getEventKey() == Keyboard.KEY_1) {
    			rotAngle = 0.5f;
    				}
    	
    		if (Keyboard.getEventKey() == Keyboard.KEY_3) {
			}
    			rotAngle = -0.5f;
    		}
    	}
    }
    */
		
		
	
	

}
