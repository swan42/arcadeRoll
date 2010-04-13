package com.swan.gameflow;

import java.util.ArrayList;

import com.pong.game.Ball;
import com.pong.game.Block;
import com.swan.ArcadeRollActivity;
import com.swan.ArcadeRollMenuActivity;
import com.swan.game.capacity.IComponent;
import com.swan.game.capacity.IGameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.BlockCollisionComponent;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


public class GameFlowManager {
	private static GameFlowManager instance = null;

	public static final float START_SCROLL_SPEED = 0.03f;
	public static final float MAX_SCROLL_SPEED = 0.12f;
	public static final float SCROLL_SPEED_INCREASE = 0.01f;
	public static final int START_PLAYER_LIFE = 3;
	public static final int SCROLL_SPEED_INCREASE_TIME_MS = 1500;
	public static final int DEATH_WAIT_TIME_MS = 3000;

	public float scrollSpeed;
	public int playerLife;
	public int playerScore;

	private boolean _playing;

	private long _startTime;
	private long _deltaTime;
	private long _currentTime;
	private long _deathTime;
	private Activity _currentActivity;

	private GameFlowManager() {
		scrollSpeed = START_SCROLL_SPEED;
		playerLife = START_PLAYER_LIFE;
		playerScore = 0;
		_startTime = System.currentTimeMillis();
		_playing = true;
	}

	public final synchronized static GameFlowManager getInstance() {
		if (instance == null) 
			instance = new GameFlowManager();
		return instance;
	}

	public void setActivity(Activity act) {
		_currentActivity = act;
	}

	public void process(long currentTime) {
		_currentTime = currentTime;
		manageDifficulty();
		manageVictory();

	}

	public void manageVictory() {
		if (_playing == false) {
			if (_deathTime - _currentTime >= DEATH_WAIT_TIME_MS)
			{
				_playing = true;
				playerLife = START_PLAYER_LIFE;
				scrollSpeed = START_SCROLL_SPEED;
			}
		}
	}

	public void manageDifficulty() {
		_deltaTime = _currentTime - _startTime;
		if (_deltaTime >= SCROLL_SPEED_INCREASE_TIME_MS) {
			_startTime = _currentTime;

			if (scrollSpeed <= MAX_SCROLL_SPEED)
				scrollSpeed += SCROLL_SPEED_INCREASE;

		}
	}

	public void addLife() {
		playerLife++;
	}

	private void resetLevel(GameObjectManager gameObjectManager) {
		ArrayList<IGameObject> gameObjectList = gameObjectManager.getGameObjectList();
		ArrayList<IComponent> componentList;
		IGameObject currentGameObject;

		for (int i = 0; i < gameObjectList.size(); i++) {
			currentGameObject = gameObjectList.get(i);

			if (currentGameObject.getName() == "Ball") {
				Ball ball = (Ball)currentGameObject;
				ball.setPosX(0.0f);
				ball.setPosY(0.0f);
				ball.isFalling = true;
			}

			else if (currentGameObject.getName() == "Block") {
				componentList = currentGameObject.getComponentList();
				BlockCollisionComponent blockCollisionComp;
				for (int j = 0; j < componentList.size(); j++) {
					if (componentList.get(j).getName() == "BlockCollisionComponent") {
						blockCollisionComp = (BlockCollisionComponent)componentList.get(j);
						blockCollisionComp.hasBall = false;
						blockCollisionComp.ballFalling = true;
						Log.v("", "Reseting block ..." );
						break;
					}
				}
			}
		}
	}

	public void removeLife(GameObjectManager gameObjectManager) {
		// Game still continue
		if (playerLife > 0) {
			playerLife--;
			Log.v("", "LIFE LOST ! " + String.valueOf(playerLife) + " LEFT." );
		}

		// GAME IS OVER !
		else {
			Log.v("", "GAME IS OVER. Wait until restart" );
			_playing = false;
			_deathTime = _currentTime;
			scrollSpeed = 0.0f;
			playerLife = START_PLAYER_LIFE;

			if (_currentActivity != null) {
				Intent intent = new Intent(_currentActivity, ArcadeRollMenuActivity.class);
				_currentActivity.startActivity(intent);
			}
		}

		resetLevel(gameObjectManager);
	}
}
