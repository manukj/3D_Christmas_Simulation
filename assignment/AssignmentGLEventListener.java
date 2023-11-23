
/**
 * AssignmentGLEventListener
 */
import utils.*;
import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

import constants.Constants;
import constants.Constants.*;

public class AssignmentGLEventListener implements GLEventListener {
    private Camera camera;
    private double startTime;
    private Model background, floor;
    private Light light;
    private Mat4[] roomTransforms;
    private TextureLibrary textures;

    public AssignmentGLEventListener(Camera camera) {
        this.camera = camera;
        this.camera.setPosition(Constants.CAMERA_POISTION);
    }

    public void init(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);
        gl.glFrontFace(GL.GL_CCW); // default is 'CCW'
        gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
        gl.glCullFace(GL.GL_BACK); // default is 'back', assuming CCW
        System.out.println("OpenGL: " + gl.glGetString(GL.GL_VERSION));
        initialise(gl);
        startTime = AssignmentUtil.getSeconds();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glViewport(x, y, width, height);
        float aspect = (float) width / (float) height;
        camera.setPerspectiveMatrix(Mat4Transform.perspective(45, aspect));
    }

    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }

    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        // cube.dispose(gl);
        background.dispose(gl);
        floor.dispose(gl);
        light.dispose(gl);
    }

    public void initialise(GL3 gl) {
        Vec3 basecolor = new Vec3(0.5f, 0.5f, 0.5f);

        textures = new TextureLibrary();
        textures.add(gl, Constants.TEXTURE_NAME_BACKGROUND, Constants.TEXTURE_PATH_BACKGROUND);
        textures.add(gl, Constants.TEXTURE_NAME_FLOOR, Constants.TEXTURE_PATH_FLOOR);

        light = new Light(gl);
        light.setCamera(camera);
        light.setPosition(Constants.LIGHT_POISTION);

        // Model 1 - a floor
        String name = "floor";
        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        Material material = new Material(basecolor, basecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        background = new Model(name, mesh, new Mat4(1), shader, material, light, camera,
                textures.get(Constants.TEXTURE_NAME_BACKGROUND));

        // Model 2 - a background
        name = "background";
        mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        material = new Material(basecolor, basecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        floor = new Model(name, mesh, new Mat4(1), shader, material, light, camera,
                textures.get(Constants.TEXTURE_NAME_FLOOR));

        // quicker way using constructor
        // tt1 = new Model(name, mesh, new Mat4(1), shader, material, light);

        // Model 2 - A cube
        // reuse method local variables
        // same model matrix, light and camera
        // name = "cube";
        // mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
        // shader = new Shader(gl, "vs_standard.txt", "fs_standard_0t.txt");
        // material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f,
        // 0.31f), new Vec3(0.5f, 0.5f, 0.5f),
        // 32.0f);
        // // set up the model using a constructor
        // cube = new Model(name, mesh, new Mat4(1), shader, material, light, camera);

        roomTransforms = AssignmentUtil.getBackDropTransformation();
    }

    public void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        light.render(gl);



        // drawing the background
     
        floor.setModelMatrix(roomTransforms[0]); // change transform
        floor.render(gl);
        background.setModelMatrix(roomTransforms[1]); // change transform
        background.render(gl);
    
    }
}