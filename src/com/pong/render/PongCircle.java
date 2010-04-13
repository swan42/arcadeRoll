package com.pong.render;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.swan.game.render.renderobject.RenderObject;

public class PongCircle extends RenderObject {

	public PongCircle() {
		super();
		
		vertices = new float[12];
		vertices[0] = 0.0f;
		vertices[1] = 0.0f;
		vertices[2] = 0.0f;
		
		vertices[3] = 0.2f;
		vertices[4] = 0.0f;
		vertices[5] = 0.0f;
		
		vertices[6] = 0.0f;
		vertices[7] = 0.2f;
		vertices[8] = 0.0f;
		
		vertices[9] = 0.2f;
		vertices[10] = 0.2f;
		vertices[11] = 0.0f;
		
		//
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
}
