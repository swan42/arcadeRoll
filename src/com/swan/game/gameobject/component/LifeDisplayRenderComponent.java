package com.swan.game.gameobject.component;

import java.util.ArrayList;

import com.pong.game.LifeDisplay;
import com.swan.game.capacity.IRenderObject;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;
import com.swan.game.render.RenderManager;
import com.swan.game.render.renderobject.RenderObject;
import com.swan.gameflow.GameFlowManager;

public class LifeDisplayRenderComponent extends Component {

	protected boolean _init;
	protected ArrayList<IRenderObject> _renderObjectList;
	protected IRenderObject _renderObject;
	protected GameFlowManager _gameFlowManager;
	protected RenderManager _renderManager;
	protected ArrayList<IRenderObject> _reusableRenderObjectStack;
	
	private int _lifeNumber;
	private int _currentLifeDisplayed;

	public LifeDisplayRenderComponent(GameObject gameObject) {
		super(gameObject);

		_init = true;
		_gameFlowManager = GameFlowManager.getInstance();
		_renderManager = RenderManager.getInstance();
		_currentLifeDisplayed = GameFlowManager.START_PLAYER_LIFE;
		_reusableRenderObjectStack = new ArrayList<IRenderObject>();
	}

	protected void getRenderObjectList() {
		_renderObjectList = _GO.getRenderObjectList();
	}

	public String getName() {
		return "LifeDisplayRenderComponent";
	}

	private void addLife() {
		int size = _reusableRenderObjectStack.size();
		_renderObjectList.add(_reusableRenderObjectStack.get(size - 1));
		_reusableRenderObjectStack.remove(size - 1);
		
		_currentLifeDisplayed++;
	}
	
	private void removeLife() {
		if (_currentLifeDisplayed > 0) {
			_reusableRenderObjectStack.add(_renderObjectList.get(_currentLifeDisplayed - 1));
			_renderObjectList.remove(_currentLifeDisplayed - 1);
			_currentLifeDisplayed--;
		}
	}
	
	public boolean process() {
		if (_init == true)
			getRenderObjectList();

		_lifeNumber = _gameFlowManager.playerLife;
		
		if (_lifeNumber != _currentLifeDisplayed) {
			// On doit rajouter des vies
			if (_lifeNumber > _currentLifeDisplayed)
				addLife();
			
			// Ou en enlever
			else
				removeLife();
		}
		
		return true;
	}

}
