package com.pong.game;

import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.LifeDisplayRenderComponent;
import com.swan.game.gameobject.component.RenderComponent;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;

public class LifeDisplay extends GameObject {
	
	public final static float WIDTH = 0.2f;
	public final static float HEIGHT = 0.2f;
	
	
	public LifeDisplay(GameObjectManager gameObjectManager, float posX, float posY) {
		super(gameObjectManager);
		
		init(posX, posY);
		
		_componentList.add(new LifeDisplayRenderComponent(this));
	}
	
	public String getName() {
		return "LifeDisplay";
	}
	
	public float getWidth() {
		return WIDTH;
	}
	public float getHeight() {
		return HEIGHT;
	}
	
	public void init(float posX, float posY) {
		RenderObject renderObj;
		float padding = 0.0f;
		
		for (int i = 0; i < 3; i++) {
			renderObj = new RenderObject();
			renderObj.setSize(WIDTH, HEIGHT);
			renderObj.addTexture(RenderObject.LIFE_TEXTURE);
			renderObj.setPosX(posX + padding);
			renderObj.setPosY(posY);
			renderObj.setPosZ(RenderObject.GAME_DEPTH);
			_renderObjectList.add(renderObj);
			setPosX(posX + padding);
			setPosY(posY);
			setPosZ(RenderObject.GAME_DEPTH);
			
			padding += 0.25f;
		}
		
		RenderManager.getInstance().addRenderObjectList(_renderObjectList);
	}

}
