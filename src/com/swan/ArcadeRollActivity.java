package com.swan;

import com.swan.gameflow.GameFlowManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ArcadeRollActivity extends Activity {
	/** Our own OpenGL View overridden */
	private PongView pongView;

	/**
	 * Initiate the OpenGL View and set our own
	 * Renderer (@see Lesson02.java)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Initiate our Lesson with this Activity Context handed over
		pongView = new PongView(this);
		//Set the lesson as View to the Activity
		setContentView(pongView);
		
		GameFlowManager.getInstance().setActivity(this);
	}

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	public void onStart() {
		
	}
	
	@Override
	public void onStop() {
		
	}
	
	@Override
	public void onDestroy() {
		
	}
}