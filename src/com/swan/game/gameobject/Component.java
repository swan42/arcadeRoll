package com.swan.game.gameobject;

import com.swan.game.capacity.IComponent;

public class Component implements IComponent {
	protected GameObject _GO;
	
	public String getName() {
		return "BasicComponent";
	}
	
	public Component(GameObject gameObject) {
		_GO = gameObject;
	}
	
	public boolean process() {

		return true;
	}
}
