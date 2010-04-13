package com.swan.game.gameobject.component;


import java.util.ArrayList;

import com.swan.game.capacity.IRenderObject;
import com.swan.game.gameobject.Component;
import com.swan.game.gameobject.GameObject;

public class RenderComponent extends Component {
	protected boolean _init;
	protected ArrayList<IRenderObject> _renderObjectList;
	protected IRenderObject _renderObject;

	public RenderComponent(GameObject gameObject) {
		super(gameObject);

		_init = true;
	}

	protected void getRenderObjectList() {
		_renderObjectList = _GO.getRenderObjectList();
	}

	public String getName() {
		return "RenderComponent";
	}

	public boolean process() {
		if (_init == true)
			getRenderObjectList();

		for (int i = 0; i < _renderObjectList.size(); i++) {
			_renderObject = _renderObjectList.get(i);
			_renderObject.setPosX(_GO.getPosX() + i);
			_renderObject.setPosY(_GO.getPosY());
			_renderObject.setPosZ(_GO.getPosZ());
		}
		return true;
	}
}
