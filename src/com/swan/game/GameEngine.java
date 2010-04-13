package com.swan.game;

import com.swan.game.gameobject.GameObjectManager;
import com.swan.gameflow.GameFlowManager;

public class GameEngine {
	private Boolean isRunning;
	private GameFlowManager _gameFlowManager;
	private GameObjectManager gameObjectManager;
	private long _lastIterationTime = 0;
	private long _currentTime;
	
	public GameEngine() {
		
	}
	
	public void init() {
		isRunning = false;
		gameObjectManager = new GameObjectManager();
		_gameFlowManager = GameFlowManager.getInstance();
	}
	
	public void start(long currentTime) {
		_lastIterationTime = currentTime / 10;
		init();
		isRunning = true;
	}
	
	public void stop() {
		isRunning = false;
		
		// TODO : clean everything
	}
	
	public void processList(long currentTime) {
		if (isRunning == true) {
			// Converting milisecs to secs
			_currentTime = currentTime / 10;
			
			gameObjectManager.processList(_currentTime - _lastIterationTime);
			_gameFlowManager.process(currentTime);
			
			_lastIterationTime = _currentTime;
		}
	}
	
	
}
