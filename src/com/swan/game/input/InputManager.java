package com.swan.game.input;

import android.view.KeyEvent;

public class InputManager {
	
	private static InputManager instance = null;
	public String[] keyPressedList;
	
	private InputManager() {
		keyPressedList = new String[1];
	}
	
	 public final synchronized static InputManager getInstance() {
         if (instance == null) 
             instance = new InputManager();
         return instance;
     }
	
	public void onKeyUp(int keyCode, KeyEvent event) {
		//
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			keyPressedList[0] = "LEFT";
		} 
		
		else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			keyPressedList[0] = "RIGHT";
		} 
		
		else if(keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			keyPressedList[0] = "UP";
		} 
		
		else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			keyPressedList[0] = "DOWN";
		} 
	}
}
