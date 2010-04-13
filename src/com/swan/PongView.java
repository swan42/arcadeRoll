package com.swan;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.swan.game.GameEngine;
import com.swan.game.input.InputManager;
import com.swan.game.render.RenderManager;
import com.swan.graphic.GLGraphicRenderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class PongView extends GLSurfaceView implements Renderer {
	
	
	
	/** The Activity Context */
	private Context context;
	
	private InputManager _inputManager;
	
	/** Our object used to draw any stuff on screen */
	private GLGraphicRenderer graphicRenderer;
	
	/** Our GameEngine to process inputs, AI, movement, ... */
	private GameEngine game;
	
	public PongView(Context context) {
		super(context);
		
		
		
		//Set this as Renderer
		this.setRenderer(this);
		//Request focus, otherwise buttons won't react
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		
		//
		this.context = context;
		graphicRenderer = new GLGraphicRenderer(context);
		_inputManager = InputManager.getInstance();
		game = new GameEngine();
	}
	
	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		graphicRenderer.onSurfaceCreated(gl, config);
		
		game.start(System.currentTimeMillis());
	}
	
	
	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {
		game.processList(System.currentTimeMillis());
		graphicRenderer.onDrawFrame(gl);
	}		

	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		graphicRenderer.onSurfaceChanged(gl, width, height);
	}
	
/* ***** Listener Events  ***** */	
	/**
	 * Override the key listener to receive keyUp events.
	 * 
	 * Check for the DPad presses left, right, up, down and middle.
	 * Change the rotation speed according to the presses
	 * or change the texture filter used through the middle press.
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		_inputManager.keyPressedList[0] = "";
		
		//We handled the event
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		_inputManager.onKeyUp(keyCode, event);
		
		//We handled the event
		return true;
	}
		
	/**
	 * Override the touch screen listener.
	 * 
	 * React to moves and presses on the touchscreen.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//
//		float x = event.getX();
//        float y = event.getY();
//        
//        //If a touch is moved on the screen
//        if(event.getAction() == MotionEvent.ACTION_MOVE) {
//        	//Calculate the change
//        	float dx = x - oldX;
//	        float dy = y - oldY;
//        	//Define an upper area of 10% on the screen
//        	int upperArea = this.getHeight() / 10;
        	
//        	//Zoom in/out if the touch move has been made in the upper
//        	if(y < upperArea) {
//        		z -= dx * TOUCH_SCALE / 2;
//        	
//        	//Rotate around the axis otherwise
//        	} else {        		
//    	        xrot += dy * TOUCH_SCALE;
//    	        yrot += dx * TOUCH_SCALE;
//        	}        
        
        //A press on the screen
//        } else if(event.getAction() == MotionEvent.ACTION_UP) {
//        	//Define an upper area of 10% to define a lower area
//        	int upperArea = this.getHeight() / 10;
//        	int lowerArea = this.getHeight() - upperArea;
//        	
//        	//Change the light setting if the lower area has been pressed 
//        	if(y > lowerArea) {
//        		if(light) {
//        			light = false;
//        		} else {
//        			light = true;
//        		}
//        	}
//        }
        
        //Remember the values
//        oldX = x;
//        oldY = y;
        
        //We handled the event
		return true;
	}

}
