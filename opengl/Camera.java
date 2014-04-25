package opengl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.util.glu.GLU;

public class Camera 
{
	private float xpos;
	private float ypos;
	private float zpos;
	
	private float cRadius = 10.0f;
	private float xrot;
	private float yrot;
	private float zrot;
	private float zoomFactor;
	private float angle;
	
	private float CX;
	private float CY;
	private float CZ;
	public float getX() {
		return xpos;
	}

	
	public float getcRadius() {
		return cRadius;
	}

	public void setX(float x) {
		this.xpos = x;
	}

	public float getY() {
		return ypos;
	}

	public void setY(float y) {
		this.ypos = y;
	}

	public float getZ() {
		return zpos;
	}

	public void setZ(float z) {
		this.zpos = z;
	}

	public float getRotationX() {
		return xrot;
	}

	public void setRotationX(float rotationX) {
		this.xrot = rotationX;
	}

	public float getRotationY() {
		return yrot;
	}

	public void setRotationY(float rotationY) {
		this.yrot = rotationY;
	}

	public float getRotationZ() {
		return zrot;
	}

	public void setRotationZ(float rotationZ) {
		this.zrot = rotationZ;
	}
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void setZoom(float zoom)
	{
		zoomFactor = zoom;
	}
	private float fieldOfView;
	private float aspectRatio;
	private float nearClip;
	private float farClip;
	
	
	public Camera(float fieldOfView, float aspectRatio, float nearClip, float farClip, float CX, float CY, float CZ)
	{
		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.nearClip = nearClip;
		this.farClip = farClip;
		this.CX = CX;
		this.CY = CY;
		this.CZ = CZ;
		xpos =  	 0.20331843f;//0.25f;
		ypos = 		0.24773124f;//0.3f;
		zpos = 	2.12998031f;//-0.5f; 
		angle = 0;
		zoomFactor = 1;
		
		initialiseProjection();
	}
	
	private void initialiseProjection()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fieldOfView, aspectRatio, nearClip, farClip);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glLoadIdentity();
		
		
		
	}
	
	public void useView(float x, float y, float z)
	{
		
		/*	
			glTranslatef(4.5f, 4.5f, 0);
			glRotatef(yrot, 0.f, 1.f, 0.f);
			glTranslatef(-4.5f, -4.5f, 0);
			
			
			glTranslatef(4.5f, 4.5f, 0);
			glRotatef(xrot, 1.f, 0.f, 0.f);
			glTranslatef(-4.5f, -4.5f, 0);
			
		*/
		//glRotatef(xrot, 1,0,0);
	    //glRotatef(yrot, 0,1,0);
		//glRotatef(zrot, 0,0,1);
		glTranslatef(-xpos,-ypos,-zpos);
		
			//glTranslatef(x, y, z);
			glRotatef(yrot, 0.f, 1.f, 0.f);
			//glTranslatef(-x, -y, -z);
			
			//glTranslatef(x, y, z);
			glRotatef(xrot, 1.f, 0.f, 0.f);
			//glTranslatef(-x, -y, -z);
		
		glScalef(zoomFactor,zoomFactor,zoomFactor);	
		
	}
	
	public void useCrossView()
	{
		glTranslatef(-xpos,-ypos,-zpos);
	}
	
	public void move(float amt, float dir)
	{
		
		zpos += amt * Math.sin(Math.toRadians(yrot + 90 * dir));
		xpos += amt * Math.cos(Math.toRadians(yrot + 90 * dir));
		//z += amt * dir;
		//x += amt * dir;
	}
	
	public void shift(float amt, float dir)
	{
		ypos+= amt*dir; 
	}
	
	
	public void rotateY(float amt)
	{
		yrot+=amt;
	}
	
	public void rotateX(float amt)
	{
		xrot+=amt;
	}
	
	public void rotateXAxis(int dir)
	{
		if(dir ==1)
		{
			 xrot += 1;
			 if (xrot >360) 
				 xrot -= 360;
		}
		else
		{
			xrot -= 1;
		    if (xrot < -360) 
		    	xrot += 360;
		}
	
	}
	
	public void zoom(int dir) 	
	{
		if(dir ==1)
		{
			/*
		 	float xrotrad, yrotrad;
		    yrotrad = (float) (yrot / 180 * Math.PI);
		    xrotrad = (float) (xrot / 180 * Math.PI); 
		    xpos += (float) (Math.sin(yrotrad)) ;
		    zpos -= (float) (Math.cos(yrotrad)) ;
		    ypos -= (float) (Math.sin(xrotrad)) ;
		    */
			zoomFactor +=0.1f;
		}
		else
		{
			/*
			float xrotrad, yrotrad;
		    yrotrad = (float) (yrot / 180 * Math.PI);
		    xrotrad = (float) (xrot / 180 * Math.PI); 
		    xpos -= (float) (Math.sin(yrotrad))/2;
		    zpos += (float) (Math.cos(yrotrad))/2;
		    ypos += (float) (Math.sin(xrotrad))/2;
		    */
			zoomFactor -=0.1f;
		}
	}
	
	public void rotateYAxis(int dir)
	{
		if(dir ==1)
		{
			 yrot += 1;
			 if (yrot >360) 
				 yrot -= 360;
		}
		else
		{
			yrot -= 1;
		    if (yrot < -360) 
		    	yrot += 360;
		}
	}
	
	public void wat()
	{
		glRotatef(xrot,1,0,0);
		glRotatef(yrot,0,1,0);
	}
	
	
	
}
