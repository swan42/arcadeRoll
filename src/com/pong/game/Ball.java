package com.pong.game;

import com.pong.render.PongCircle;

import com.swan.game.capacity.IComponent;
import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.InertiaComponent;
import com.swan.game.gameobject.component.MovementComponent;
import com.swan.game.gameobject.component.RenderComponent;
import com.swan.game.gameobject.component.VictoryComponent;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;

public class Ball extends GameObject {
	
	public final static float WIDTH = 0.2f;
	public final static float HEIGHT = 0.2f;
	public final static float SPEED_X = 0.02f;
	public final static float SPEED_Y = 0.03f;
	
	public boolean isFalling = true;
	
	public Ball(GameObjectManager gameObjectManager, float posX, float posY) {
		super(gameObjectManager);
		
		init(posX, posY);
		
		_componentList.add(new MovementComponent(this, SPEED_X, SPEED_Y));
		_componentList.add(new InertiaComponent(this, SPEED_X, SPEED_Y));
		_componentList.add(new VictoryComponent(this));
		_componentList.add(new RenderComponent(this));
	}
	
	public String getName() {
		return "Ball";
	}
	
	public float getWidth() {
		return WIDTH;
	}
	public float getHeight() {
		return HEIGHT;
	}
	
	public void init(float posX, float posY) {
		RenderObject renderObj = new RenderObject();
		renderObj.setSize(WIDTH, HEIGHT);
		renderObj.addTexture(RenderObject.BALL_TEXTURE);
		_renderObjectList.add(renderObj);
		setPosX(posX);
		setPosY(posY);
		setPosZ(RenderObject.GAME_DEPTH);
		
		RenderManager.getInstance().addRenderObjectList(_renderObjectList);
	}
}
