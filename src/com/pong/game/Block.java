package com.pong.game;

import com.pong.render.PongRectangle;

import com.swan.game.capacity.IComponent;
import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.BlockCollisionComponent;
import com.swan.game.gameobject.component.MovementComponent;
import com.swan.game.gameobject.component.RenderComponent;
import com.swan.game.gameobject.component.ScrollerComponent;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;

public class Block extends GameObject {
	public final static float WIDTH = 1.0f;
	public final static float HEIGHT = 0.28f;
	
	public Block(GameObjectManager gameObjectManager, float posX, float posY) {
		super(gameObjectManager);

		init(posX, posY);
		
		_componentList.add(new BlockCollisionComponent(this, WIDTH, HEIGHT));
		_componentList.add(new ScrollerComponent(this));
		_componentList.add(new RenderComponent(this));
	}
	
	public String getName() {
		return "Block";
	}
	
	public void init(float posX, float posY) {
		RenderObject renderObj = new RenderObject();
		renderObj.setSize(WIDTH, HEIGHT);
		renderObj.addTexture(RenderObject.BLOCK_TEXTURE);
		_renderObjectList.add(renderObj);
		setPosX(posX);
		setPosY(posY);
		setPosZ(RenderObject.GAME_DEPTH);
		
		RenderManager.getInstance().addRenderObjectList(_renderObjectList);
	}
}
