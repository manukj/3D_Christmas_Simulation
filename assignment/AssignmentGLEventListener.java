
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
    private Model backDrop;
    private Light light;
    private Mat4[] roomTransforms;

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
        backDrop.dispose(gl);
        light.dispose(gl);
    }

    public void initialise(GL3 gl) {
        light = new Light(gl);
        light.setCamera(camera);
        light.setPosition(Constants.LIGHT_POISTION);

        // Model 1 - a floor plane
        String name = "floor plane";
        // make mesh
        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        // make shader
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        // make material
        Material material = new Material(new Vec3(0.1f, 0.5f, 0.91f), new Vec3(0.1f, 0.5f, 0.91f),
                new Vec3(0.3f, 0.3f, 0.3f), 4.0f);

        // set up the model using methods
        backDrop = new Model();
        backDrop.setName(name);
        backDrop.setMesh(mesh);
        backDrop.setModelMatrix(new Mat4(1));
        backDrop.setShader(shader);
        backDrop.setMaterial(material);
        backDrop.setLight(light);
        backDrop.setCamera(camera);
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

        // updateLightColour();

        light.render(gl);

        // this transform could have been set up when the cube model was created,
        // since it is the same every time.
        // However, it illustrates that the transform could be changed, e.g. for
        // animation.
        // cube.setModelMatrix(getMforCube()); // change transform
        // cube.render(gl);

        // drawing the backdrop
        backDrop.setModelMatrix(roomTransforms[0]); // change transform
        backDrop.render(gl);
        backDrop.setModelMatrix(roomTransforms[1]); // change transform
        backDrop.render(gl);
        // tt1.setModelMatrix(roomTransforms[2]); // change transform
        // tt1.render(gl);
    }
}