package com.annhid.hessan.mygame;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by leejh on 2016-05-15.
 */
public class Stage extends GLSurfaceView {
    private FloatBuffer vertexBuffer;
    private float w, h;

    private int screenWidth, screenHeight;
    public Stage(Context context, AttributeSet attrs){
        super (context, attrs);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        setRenderer(new MyRenderer());
        float vertices[] = {
                -0.5f, -0.5f,  0.0f,  // 0. left-bottom
                0.5f, -0.5f,  0.0f,  // 1. right-bottom
                -0.5f,  0.5f,  0.0f,  // 2. left-top
                0.5f,  0.5f,  0.0f   // 3. right-top
        };
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }
    private class MyRenderer implements GLSurfaceView.Renderer{
        public final void onDrawFrame(GL10 gl){
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT);
        }
        public final void onSurfaceChanged(GL10 gl, int width, int height){
            screenWidth = width;
            screenHeight = height;
            gl.glClearColor(0, 0, 0, 1);

            float w, h;

            if(width > height){
                h = 600;
                w = width * h / height;
            } else {
                w = 600;
                h = height * w / width;
            }

            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glOrthof(0, w, h, 0, -1, 1);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();

            gl.glViewport(0, 0, screenWidth, screenHeight);
        }
        public final void onSurfaceCreated(GL10 gl, EGLConfig config){
            gl.glEnable(GL10.GL_ALPHA_TEST);
            gl.glEnable(GL10.GL_BLEND);

            gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
    }
}
