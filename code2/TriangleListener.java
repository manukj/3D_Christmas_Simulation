package code2;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

public class TriangleListener implements GLEventListener {
    public TriangleListener() {
    }

    public void init(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);
        initialise(gl);
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
            // its a 2 D traingle
            -0.5f, -0.5f, 0.0f, // Bottom Left
            0.5f, -0.5f, 0.0f, // Bottom Right
            0.0f, 0.5f, 0.0f // Top middle
    };

    // The Buffers
    private int[] vertexBufferId = new int[1];
    private int[] vertexArrayId = new int[1];

    private void fillBuffers(GL3 gl){
        gl.glGenVertexArrays(1, vertexArrayId, 0);
        gl.glBindVertexArray(vertexArrayId[0]);

        gl.glGenBuffers(1, vertexBufferId, 0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);

        FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);

        gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length,fb, GL.GL_STATIC_DRAW);
        gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 3*Float.BYTES, 0);
        gl.glEnableVertexAttribArray(0);
        
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
        gl.glBindVertexArray(0);
    }

    // ***************************************************
    /*
     * THE SHADER
     */

    private String vertexShaderSource = "#version 330 core\n" +
            "\n" +
            "layout (location = 0) in vec3 position;\n" +
            "\n" +
            "void main() {\n" +
            "  gl_Position = vec4(position.x, position.y, position.z, 1.0f);\n" +
            "}";

    private String fragmentShaderSource = "#version 330 core\n" +
            "\n" +
            "out vec4 fragColor;\n" +
            "\n" +
            "void main() {\n" +
            "  fragColor = vec4(0.1f, 0.7f, 0.9f, 1.0f);\n" +
            "}";

    private int shaderProgram;

    private void initialiseShader(GL3 gl) {
        System.out.println(vertexShaderSource);
        System.out.println(fragmentShaderSource);
    }

    private int compileAndLink(GL3 gl) {
        String[][] sources = new String[1][1];
        sources[0] = new String[] { vertexShaderSource };
        ShaderCode vertexShaderCode = new ShaderCode(GL3.GL_VERTEX_SHADER,
                sources.length, sources);
        boolean compiled = vertexShaderCode.compile(gl, System.err);
        if (!compiled)
            System.err.println("[error] Unable to compile vertex shader: " + sources);

        sources[0] = new String[] { fragmentShaderSource };
        ShaderCode fragmentShaderCode = new ShaderCode(GL3.GL_FRAGMENT_SHADER,
                sources.length, sources);
        compiled = fragmentShaderCode.compile(gl, System.err);
        if (!compiled)
            System.err.println("[error] Unable to compile fragment shader: " + sources);

        ShaderProgram program = new ShaderProgram();
        program.init(gl);
        program.add(vertexShaderCode);
        program.add(fragmentShaderCode);
        program.link(gl, System.out);
        if (!program.validateProgram(gl, System.out))
            System.err.println("[error] Unable to link program");

        return program.program();
    }

    public void initialise(GL3 gl) {
        initialiseShader(gl);
        shaderProgram = compileAndLink(gl);
        fillBuffers(gl);
    }

    public void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glUseProgram(shaderProgram);
        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3); // drawing one triangle
        gl.glBindVertexArray(0);
    }

}
