package com.swan.game.gameobject.component;

import com.pong.game.Ball;
import com.swan.game.capacity.IGameObject;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;
import com.swan.game.gameobject.GameObjectManager;
import com.swan.gameflow.GameFlowManager;

public class VictoryComponent extends Component {
	
	private Ball _ball;
	private GameFlowManager _gameFlowManager;
	public final static float HALF_MAP_HEIGHT = GameObjectManager.HALF_MAP_HEIGHT;
	public final static float MAP_TOP_GAMEFIELD = GameObjectManager.MAP_TOP_GAMEFIELD;
	
	public VictoryComponent(GameObject gameObject) {
		super(gameObject);
		
		init();
	}

	private void init() {
		_ball = (Ball)_GO;
		_gameFlowManager = GameFlowManager.getInstance();
	}
	
	public String getName() {
		return "VictoryComponent";
	}


	public boolean process() {
		float posY = _GO.getPosY();
		
		if 	(posY < -HALF_MAP_HEIGHT
			|| posY > MAP_TOP_GAMEFIELD) 
		{
			_gameFlowManager.removeLife(_GO.getGameObjectManager());
		}
		
		return true;
	}
}
