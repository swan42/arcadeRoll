package com.swan.game.gameobject.component;

import com.pong.game.Ball;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;

public class InertiaComponent extends Component {
	
	private float _speedY;
	private float _distY;
	private Ball _ball;
	
	public InertiaComponent(GameObject gameObject, float speedX,  float speedY) {
		super(gameObject);
		
		_ball = (Ball)gameObject;
		_speedY = speedY;
	}
	
	public String getName() {
		return "InertiaComponent";
	}
	
	public boolean process() {		
		if (_ball.isFalling == true) {
			_distY = _speedY * _GO.deltaTime;
			_ball.setPosY(_ball.getPosY() - _distY);
		}
		
		return true;
	}
}
