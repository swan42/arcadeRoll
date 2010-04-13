package com.pong.game;

import com.pong.render.PongRectangle;
import com.swan.game.capacity.IComponent;
import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.RenderComponent;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;

public class Background extends GameObject {
	public final static float WIDTH = GameObjectManager.MAP_WIDTH;
	public final static float HEIGHT = GameObjectManager.MAP_HEIGHT;
	
	public Background(GameObjectManager gameObjectManager, float posX, float posY) {
		super(gameObjectManager);
		
		RenderObject renderObj = new RenderObject();
		renderObj.setSize(GameObjectManager.MAP_WIDTH, GameObjectManager.MAP_HEIGHT);
		renderObj.setTexture(RenderObject.BACKGROUND_TEXTURE);
		_renderObjectList.add(renderObj);
		
		setPosX(posX);
		setPosY(posY);
		setPosZ(RenderObject.BACKGROUND_DEPTH);
		
		RenderManager.getInstance().addRenderObjectList(_renderObjectList);
		
		_componentList.add(new RenderComponent(this));
	}
	
	public String getName() {
		return "Background";
	}
	
	public float getWidth() {
		return WIDTH;
	}
	public float getHeight() {
		return HEIGHT;
	}
}
