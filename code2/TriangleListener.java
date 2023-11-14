
import java.nio.*;

import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;

import texture.TextureUtil;



public class TriangleListener implements GLEventListener {
    private double startTime;
    private Shader shader;

    private int vertexStride = 8;
    private int vertexXYZFloats = 3;
    private int vertexColourFloats = 3;
    private int vertexTexFloats = 2;
    private Texture texture;

    private int[] indices = { // Note that we start from 0
            0, 1, 2
    };

    private float[] vertices = { // position, colour, tex coords
            0.0f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.5f, 1.0f,
            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f
    };

    // The Buffers
    private int[] vertexBufferId = new int[1];
    private int[] vertexArrayId = new int[1];
    private int[] elementBufferId = new int[1];

    public TriangleListener() {
    }

    public void init(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);
        initialise(gl);
        startTime = getSeconds();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glViewport(x, y, width, height);
    }

    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }

    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glDeleteBuffers(1, vertexBufferId, 0);
        gl.glDeleteVertexArrays(1, vertexArrayId, 0);
        gl.glDeleteBuffers(1, elementBufferId, 0);
    }

    private void fillBuffers(GL3 gl) {
        gl.glGenVertexArrays(1, vertexArrayId, 0);
        gl.glBindVertexArray(vertexArrayId[0]);

        gl.glGenBuffers(1, vertexBufferId, 0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
        FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);
        gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, fb, GL.GL_STATIC_DRAW);

        int stride = vertexStride;
        int numXYZFloats = vertexXYZFloats;
        int offset = 0;
        gl.glVertexAttribPointer(0, numXYZFloats, GL.GL_FLOAT, false, stride * Float.BYTES, 0);
        gl.glEnableVertexAttribArray(0);

        int numColorFloats = vertexColourFloats;
        offset = numXYZFloats * Float.BYTES;
        gl.glVertexAttribPointer(1, numColorFloats, GL.GL_FLOAT, false,
                stride * Float.BYTES, offset);
        gl.glEnableVertexAttribArray(1);

        // now do the texture coordinates in vertex attribute 2
        int numTexFloats = vertexTexFloats;
        offset = (numXYZFloats + numColorFloats) * Float.BYTES;
        gl.glVertexAttribPointer(2, numTexFloats, GL.GL_FLOAT, false, stride * Float.BYTES, offset);
        gl.glEnableVertexAttribArray(2);

        gl.glGenBuffers(1, elementBufferId, 0);
        IntBuffer ib = Buffers.newDirectIntBuffer(indices);

        gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, elementBufferId[0]);
        gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, Integer.BYTES * indices.length, ib, GL.GL_STATIC_DRAW);
        gl.glBindVertexArray(0);
    }

    // ***************************************************
    /*
     * THE SHADER
     */

    public void initialise(GL3 gl) {
        shader = new Shader(gl, "./shaders/vs.txt", "./shaders/fs.txt");
        fillBuffers(gl);
        texture = TextureUtil.loadTexture(gl, "./texture/cloud.jpg");
    }

    public void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        shader.use(gl);

        gl.glActiveTexture(GL.GL_TEXTURE0);
        texture.bind(gl);

        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3); // drawing one triangle
        gl.glBindVertexArray(0);
    }

    private double getSeconds() {
        return System.currentTimeMillis() / 1000.0;
    }
}
