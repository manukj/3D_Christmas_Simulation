
import java.nio.FloatBuffer;

import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class TriangleListener implements GLEventListener {
    private double startTime;
    private Shader shader;
    private int vertexStride = 6;
    private int vertexXYZFloats = 3;
    private int vertexColourFloats = 3;

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
    }

    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }

    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glDeleteBuffers(1, vertexBufferId, 0);
    }

    // The Data
    private float[] vertices = {
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, // Bottom Left, blue (r=0, g=0, b=1)
            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, // Bottom Right, green
            0.0f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f // Top middle, red
    };

    // The Buffers
    private int[] vertexBufferId = new int[1];
    private int[] vertexArrayId = new int[1];

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

        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        gl.glBindVertexArray(0);
    }

    // ***************************************************
    /*
     * THE SHADER
     */

    public void initialise(GL3 gl) {
        shader = new Shader(gl, "./shaders/vs.txt", "./shaders/fs.txt");
        fillBuffers(gl);
    }

    public void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        shader.use(gl);
        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3); // drawing one triangle
        gl.glBindVertexArray(0);
    }

    private double getSeconds() {
        return System.currentTimeMillis() / 1000.0;
    }
}
