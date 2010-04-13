package com.swan.game.gameobject.component;

import java.util.ArrayList;
import java.util.Random;

import com.pong.game.Ball;
import com.pong.game.Block;
import com.swan.game.capacity.IComponent;
import com.swan.game.capacity.IGameObject;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.gameflow.GameFlowManager;

public class ScrollerComponent extends Component implements IComponent {
	
	public final static float MAP_WIDTH = GameObjectManager.MAP_WIDTH;
	public final static float HALF_MAP_WIDTH = GameObjectManager.HALF_MAP_WIDTH;
	public final static float HALF_MAP_HEIGHT = GameObjectManager.HALF_MAP_HEIGHT;
	public final static float MAP_TOP_GAMEFIELD = GameObjectManager.MAP_TOP_GAMEFIELD;
	
	private boolean _init = false;
	private float _posY;
	private float _newX;
	private float _newY;
	private Random _generator;
	private Ball _ball;
	private BlockCollisionComponent _blockCollisionComponent;
	private GameFlowManager _gameFlowManager;
	
	public ScrollerComponent(GameObject gameObject) {
		super(gameObject);
	}

	private void init() {
		IGameObject currentGameObject;
		_gameFlowManager = GameFlowManager.getInstance();
		_generator = new Random();
		ArrayList<IGameObject> gameObjectList = _GO.getGameObjectManager().getGameObjectList();
		for (int i = 0; i < gameObjectList.size(); i++) {
			currentGameObject = gameObjectList.get(i);
			if (currentGameObject.getName() == "Ball") {
				_ball = (Ball)currentGameObject;
				break ;
			}
		}
		
		ArrayList<IComponent> componentList = _GO.getComponentList();
		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i).getName() == "BlockCollisionComponent") {
				_blockCollisionComponent = (BlockCollisionComponent)componentList.get(i);
				break;
			}
		}
	}
	
	public String getName() {
		return "ScrollerComponent";
	}

	public boolean process() {
		if (_init == false)
			init();
		
		if (_blockCollisionComponent.hasBall == true) {
			_ball.setPosY(_ball.getPosY() + _gameFlowManager.scrollSpeed);
		}
		
		_posY = _GO.getPosY();
		_GO.setPosY(_posY + _gameFlowManager.scrollSpeed);
		
		if (_posY > MAP_TOP_GAMEFIELD) {
			_newX = (_generator.nextFloat() * (MAP_WIDTH - Block.WIDTH) - HALF_MAP_WIDTH);
			_newY = -HALF_MAP_HEIGHT;
			
			_GO.setPosX(_newX);
			_GO.setPosY(_newY);
		}
		
		return false;
	}

}
