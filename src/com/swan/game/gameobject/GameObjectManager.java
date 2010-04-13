package com.swan.game.gameobject;

import java.util.ArrayList;
import java.util.Random;

import com.pong.game.Background;
import com.pong.game.Ball;
import com.pong.game.Block;
import com.pong.game.LifeDisplay;
import com.pong.game.UISeparator;

import com.swan.game.capacity.IGameObject;

public class GameObjectManager {
	private ArrayList<IGameObject> gameObjectList;
	private boolean _victory;
	
	public final static int BLOCK_COUNT = 5;
	public final static float BLOCK_OFFSET = 0.9f;
		
	public final static float MAP_TOP_GAMEFIELD = 1.8f;
	public final static float MAP_WIDTH = 4.2f;
	public final static float HALF_MAP_WIDTH = 1.8f;
	public final static float MAP_HEIGHT = 4.7f;
	public final static float HALF_MAP_HEIGHT = 2.5f;
	
	public GameObjectManager() {
		gameObjectList = new ArrayList<IGameObject>();
		//gameObjectList = new GameObject[1];
		initGameObjects();
		_victory = false;
	}
	
	private void buildLevel() {
		Random generator = new Random();
		int blockCount = 0;
		float blockX;
		float blockY = 1.9f;
		
		float ballX = 0.0f;
		float ballY = 2.25f;
		
		while (blockCount <= BLOCK_COUNT) {
			// Renvoi un float random entre 0 et 1, puis applique facteur + translation
			// pour tapper dans le bon range de nombres
			blockX = (generator.nextFloat() * (MAP_WIDTH - Block.WIDTH) - HALF_MAP_WIDTH);
			gameObjectList.add(new Block(this, blockX, blockY));
			
			//System.out.println("BLOCK X : " + blockX + " , Y : " + blockY);
			
			if (blockCount == 0)
				ballX = blockX + (Block.WIDTH / 2);
			
			blockCount++;
			blockY -= BLOCK_OFFSET;
		}
		
		gameObjectList.add(new Ball(this, ballX, ballY));
		gameObjectList.add(new LifeDisplay(this, 1.0f, 2.15f));
		gameObjectList.add(new UISeparator(this, - HALF_MAP_WIDTH, 2.12f));
		return ;
	}
	
	private void initGameObjects() {	
		buildLevel();
	}
	
	public boolean processList(long deltaTime) {
		for (int i = 0; i < gameObjectList.size(); i++) {
			gameObjectList.get(i).processList(deltaTime);
		}
		
		return true;
	}
	
	public boolean getVictory() {
		return _victory;
	}
	
	public void setVictory(boolean value) {
		_victory = value;
		
		if (value == true) {
			System.out.println("YOU LOSE"); 
		}
	}
	
	public ArrayList<IGameObject> getGameObjectList() {
		return gameObjectList;
	}
}
