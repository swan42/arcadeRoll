package com.swan.game.capacity;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

public interface IRenderObject {
	public void draw(GL10 gl);
	public void loadGLTexture(GL10 gl, Context context);
	
	public float getPosX();
	public float getPosY();
	public float getPosZ();
	
	public void setPosX(float value);
	public void setPosY(float value);	
	public void setPosZ(float value);

	public void setSize(float width, float height);
	public void setTexture(int textureID);	
}
