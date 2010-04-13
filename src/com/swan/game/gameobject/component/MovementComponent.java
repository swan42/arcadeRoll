package com.swan.game.gameobject.component;

import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;
import com.swan.game.input.InputManager;

public class MovementComponent extends Component  {

	private float _speedX;
	private float _distX;
	
	public MovementComponent(GameObject gameObject, float speedX, float speedY) {
		super(gameObject);
		_speedX = speedX;
	}
	
	public String getName() {
		return "MovementComponent";
	}
	
	public boolean process() {
		
		String direction = InputManager.getInstance().keyPressedList[0];
		_distX = _speedX * _GO.deltaTime;

		if (direction == "LEFT") {
			_GO.setPosX(_GO.getPosX() - _distX); 
		}

		else if (direction == "RIGHT") {
			_GO.setPosX(_GO.getPosX() + _distX); 
		}

		return true;
	}
	
}
