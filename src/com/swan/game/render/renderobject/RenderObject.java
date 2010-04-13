package com.swan.game.render.renderobject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.swan.game.capacity.IRenderObject;

public class RenderObject implements IRenderObject {

	public final static float GAME_DEPTH = -6.0f;
	public final static float BACKGROUND_DEPTH = -7.0f;

	public final static int NO_TEXTURE = -1;
	public final static int BACKGROUND_TEXTURE = com.swan.R.drawable.clouds_bg;
	public final static int BLOCK_TEXTURE = com.swan.R.drawable.block;
	public final static int BALL_TEXTURE = com.swan.R.drawable.ball;
	public final static int LIFE_TEXTURE = com.swan.R.drawable.life;
	public final static int UI_SEPARATOR_TEXTURE = com.swan.R.drawable.uiseparator;

	protected ArrayList<Integer> _textureIDList;
	protected int _currentTextureID;
	
	/** The buffer holding the vertices */
	protected FloatBuffer vertexBuffer;
	/** The buffer holding the texture coordinates */
	private FloatBuffer textureBuffer;
	/** The buffer holding the indices */
	private ByteBuffer indexBuffer;

	/** The initial vertex definition */
	protected float vertices[];
	/** The initial texture coordinates (u, v) */	
	private float texture[];
	/** The initial indices definition */	
	private byte indices[];

	/** Our texture pointer */
	private int[] textures = new int[1];
	
	protected float _posX;
	protected float _posY;
	protected float _posZ;

	protected float _width;
	protected float _height;

	/**
	 * The Square constructor.
	 * 
	 * Initiate the buffers.
	 */
	public RenderObject() {
		_textureIDList = new ArrayList<Integer>();
		_posX = 0.0f;
		_posY = 0.0f;
		_posZ = GAME_DEPTH;
	}

	/**
	 * The object own drawing function.
	 * Called from the renderer to redraw this instance
	 * with possible changes in values.
	 * 
	 * @param gl - The GL context
	 */
	public void draw(GL10 gl) {
		
		//Bind our only previously generated texture in this case
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		//Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//Set the face rotation
		gl.glFrontFace(GL10.GL_CCW);
		
		//Enable the vertex and texture state
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		//Draw the vertices as triangles, based on the Index Buffer information
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

	public void setSize(float width, float height) {
		_width = width;
		_height = height;

		vertices = new float[12];
		vertices[0] = 0.0f;
		vertices[1] = 0.0f;
		vertices[2] = 0.0f;

		vertices[3] = _width;
		vertices[4] = 0.0f;
		vertices[5] = 0.0f;

		vertices[6] = 0.0f;
		vertices[7] = _height;
		vertices[8] = 0.0f;

		vertices[9] = _width;
		vertices[10] = _height;
		vertices[11] = 0.0f;

		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}

	public void addTexture(int textureID) {
		if (_textureIDList.size() == 0)
			_currentTextureID = textureID;
		_textureIDList.add(textureID);
		
	}
	
	public void setTexture(int textureID) {
		_currentTextureID = textureID;
	}

	public void loadGLTexture(GL10 gl, Context context) {
		//Mapping coordinates for the vertices
		texture = new float[8];
		texture[0] = 0.0f;
		texture[1] = 1.0f;
		texture[2] = 1.0f;
		texture[3] = 1.0f;
		texture[4] = 0.0f;
		texture[5] = 0.0f;
		texture[6] = 1.0f;
		texture[7] = 0.0f;

		//
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		
		//Faces definition
		indices = new byte[6];
		
		//Face front
		indices[0] = 0;
		indices[1] = 1;
		indices[2] = 3;
		indices[3] = 0;
		indices[4] = 3;
		indices[5] = 2;

		//
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		//Get the texture from the Android resource directory
		InputStream is = context.getResources().openRawResource(_textureIDList.get(0));
		Bitmap bitmap = null;
		try {
			//BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);

		} finally {
			//Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}

		//Generate one texture pointer...
		gl.glGenTextures(1, textures, 0);
		//...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		//Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		//Clean up
		bitmap.recycle();
	}
	
	public float getPosX() {
		return _posX;
	}

	public float getPosZ() {
		return _posZ;
	}

	public void setPosZ(float value) {
		_posZ = value; 
	}

	public void setPosX(float value) {
		_posX = value;
	}

	public void setPosY(float value) {
		_posY = value;
	}

	public float getPosY() {
		return _posY;
	}

}
