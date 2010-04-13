package com.swan.game.gameobject.component;

import java.util.ArrayList;

import com.pong.game.Ball;
import com.pong.game.Block;

import com.swan.game.capacity.IComponent;
import com.swan.game.capacity.IGameObject;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;

public class BlockCollisionComponent extends Component {
	public boolean hasBall;
	public boolean ballFalling;

	private float _objectWidth;
	private float _objectHeight;
	private float _posX;
	private float _posY;
	private float _ballX;
	private float _ballY;
	private boolean _init;
	private Ball _ball;
	
	public BlockCollisionComponent(GameObject gameObject, float objectWidth, float objectHeight) {
		super(gameObject);
		_init = true;
		hasBall = false;
		ballFalling = true;
		_objectWidth = objectWidth;
		_objectHeight = objectHeight;
	}
	
	public String getName() {
		return "BlockCollisionComponent";
	}
	
	private void init() {
		_posX = _GO.getPosX();
		_posY = _GO.getPosY();
		
		IGameObject currentGameObject;
		ArrayList<IGameObject> gameObjectList = _GO.getGameObjectManager().getGameObjectList();
		for (int i = 0; i < gameObjectList.size(); i++) {
			currentGameObject = gameObjectList.get(i);
			if (currentGameObject.getName() == "Ball") {
				_ball = (Ball)currentGameObject;
				break ;
			}
		}
	}
	
	public boolean process() {
		if (_init == true)
			init();
		
		ballFalling = _ball.isFalling;
		_ballX = _ball.getPosX();
		_ballY = _ball.getPosY();

		if (ballFalling == true) {
			if (_ballX  >= _posX && _ballX <= _posX + Block.WIDTH
					&& _ballY <= (_posY + Block.HEIGHT) && (_ballY + Ball.HEIGHT) >= _posY) 
			{
				_ball.setPosY(_posY + Block.HEIGHT);
				hasBall = true;
				_ball.isFalling = false;
			}
		}
		
		else if (hasBall == true) {
			if ((_ballX + Ball.WIDTH)  < _posX || _ballX > _posX + Block.WIDTH) 
			{
				hasBall = false;
				_ball.isFalling = true;
			}
		}

		return true;
	}
}
