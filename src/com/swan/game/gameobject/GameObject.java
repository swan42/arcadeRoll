package com.swan.game.gameobject;

import java.util.ArrayList;

import com.swan.game.capacity.IComponent;
import com.swan.game.capacity.IGameObject;
import com.swan.game.capacity.IRenderObject;

public class GameObject implements IGameObject {
	protected ArrayList<IComponent> _componentList;
	protected GameObjectManager _GOManager;
	protected ArrayList<IRenderObject> _renderObjectList;
	private float _posX;
	private float _posY;
	private float _posZ;
	
	public long deltaTime;
	
	public GameObject(GameObjectManager gameObjectManager) {
		_GOManager = gameObjectManager;
		_componentList = new ArrayList<IComponent>();
		_renderObjectList = new ArrayList<IRenderObject>();
		_posX = 0.0f;
		_posY = 0.0f;
	}
	
	public String getName() {
		return "BasicGameObject";
	}
	
	public float getWidth() {
		return 0.0f;
	}
	public float getHeight() {
		return 0.0f;
	}
	
	public ArrayList<IRenderObject> getRenderObjectList() {
		return _renderObjectList;
	}
	
	public ArrayList<IComponent> getComponentList() {
		return _componentList;
	}
	
	public GameObjectManager getGameObjectManager() {
		return _GOManager;
	}
	
	public void init() {
		
	}
	
	public boolean processList(long deltaTime) {
		if (_componentList == null)
			return false;
		
		this.deltaTime = deltaTime;
		
		for (int i = 0; i < _componentList.size(); i++) {
			_componentList.get(i).process();
		}
		
		return true;
	}
	
	public float getPosX() {
		return _posX;
	}
	
	public void setPosX(float value) {
		_posX = value;
	}
	
	public void setPosY(float value) {
		_posY = value;
	}
	
	public float getPosY() {
		return _posY;
	}
	
	public float getPosZ() {
		return _posZ;
	}

	public void setPosZ(float posZ) {
		_posZ = posZ;
	}
}
