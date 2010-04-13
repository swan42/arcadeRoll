package com.pong.game;

import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.game.gameobject.component.LifeDisplayRenderComponent;
import com.swan.game.gameobject.component.RenderComponent;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;

public class UISeparator extends GameObject{
	public final static float WIDTH = GameObjectManager.MAP_WIDTH;
	public final static float HEIGHT = 0.01f;


	public UISeparator(GameObjectManager gameObjectManager, float posX, float posY) {
		super(gameObjectManager);

		init(posX, posY);

		_componentList.add(new RenderComponent(this));
	}

	public String getName() {
		return "UISeparator";
	}

	public float getWidth() {
		return WIDTH;
	}
	public float getHeight() {
		return HEIGHT;
	}

	public void init(float posX, float posY) {
		RenderObject renderObj;
		renderObj = new RenderObject();
		renderObj.setSize(WIDTH, HEIGHT);
		renderObj.addTexture(RenderObject.UI_SEPARATOR_TEXTURE);
		renderObj.setPosX(posX);
		renderObj.setPosY(posY);
		renderObj.setPosZ(RenderObject.GAME_DEPTH);
		_renderObjectList.add(renderObj);
		
		setPosX(posX);
		setPosY(posY);
		setPosZ(RenderObject.GAME_DEPTH);


		RenderManager.getInstance().addRenderObjectList(_renderObjectList);
	}
}
