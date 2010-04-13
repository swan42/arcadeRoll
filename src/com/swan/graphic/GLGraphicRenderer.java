package com.swan.graphic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.swan.game.capacity.IRenderObject;
import com.swan.game.render.RenderManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

public class GLGraphicRenderer {
	
	private static final String TAG = "GLGraphicRenderer"; 
	
	private Context _context;
	private ArrayList<ArrayList<IRenderObject>> _renderObjectlistArray;
	private ArrayList<IRenderObject> _currentRenderObjectList;
	private IRenderObject _currentRenderObject;
	
	public GLGraphicRenderer(Context context) {
		_renderObjectlistArray = RenderManager.getInstance().getRenderObjectListArray();
		_context = context;
	}
	
	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		Log.v(TAG, "OPENGL SURFACE CREATED");
		
	    gl.glEnable(GL10.GL_BLEND); // Enable blending using premultiplied alpha.
	    gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
	    
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}
	
	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {
		//Clear Screen And Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();					//Reset The Current Modelview Matrix
		
		for (int i = 0; i < _renderObjectlistArray.size(); i++) {
			_currentRenderObjectList = _renderObjectlistArray.get(i);
			
			for (int j = 0; j < _currentRenderObjectList.size(); j++) { 
				_currentRenderObject = _currentRenderObjectList.get(j);
				gl.glLoadIdentity();
				gl.glTranslatef(_currentRenderObject.getPosX(), _currentRenderObject.getPosY(), _currentRenderObject.getPosZ());
				_currentRenderObject.draw(gl);
			}
			
		}
	}
	
	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		for (int i = 0; i < _renderObjectlistArray.size(); i++) {
			_currentRenderObjectList = _renderObjectlistArray.get(i);
			
			for (int j = 0; j < _currentRenderObjectList.size(); j++) { 
				_currentRenderObject = _currentRenderObjectList.get(j);
				_currentRenderObject.loadGLTexture(gl, _context);
			}
			
		}
		
		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}
	
	/**
	 * Load the textures
	 * 
	 * @param gl - The GL Context
	 * @param context - The Activity context
	 */
	
}
