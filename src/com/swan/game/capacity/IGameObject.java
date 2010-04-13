package com.swan.game.capacity;

import java.util.ArrayList;

public interface IGameObject {
	public String getName();
	public float getWidth();
	public float getHeight();
	public void init();
	public boolean processList(long deltaTime);
	public ArrayList<IComponent> getComponentList();
}
