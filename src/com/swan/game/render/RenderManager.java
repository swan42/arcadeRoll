package com.swan.game.render;

import java.util.ArrayList;

import android.util.Log;

import com.swan.game.capacity.IRenderObject;

public class RenderManager {

	private static RenderManager instance = null;
	private ArrayList<ArrayList<IRenderObject>> _renderObjectListArray;
	
	
	private RenderManager() {
		_renderObjectListArray = new ArrayList<ArrayList<IRenderObject>>();
	}

	public final synchronized static RenderManager getInstance() {
		if (instance == null) 
			instance = new RenderManager();
		return instance;
	}

	public boolean processList() {
//		for (int i = 0; i < renderObjectNumber; i++) {
//			_renderObjectList[i].draw(gl);
//		}

		return true;
	}
	
	public void addRenderObjectList(ArrayList<IRenderObject> renderObjectList) {
		_renderObjectListArray.add(renderObjectList);
	}
	
	public void removeRenderObject(ArrayList<IRenderObject> renderObjectList) {
		int index = _renderObjectListArray.lastIndexOf(renderObjectList);
		if (index > -1)
			_renderObjectListArray.remove(index);
		else
			Log.v("RenderManager", "Trying to remove an inexistant object in renderObjectListArray");
	}
	
	public ArrayList<ArrayList<IRenderObject>> getRenderObjectListArray() {
		return _renderObjectListArray;
	}
}
