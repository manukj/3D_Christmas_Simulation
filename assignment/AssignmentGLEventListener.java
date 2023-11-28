
/**
 * AssignmentGLEventListener
 */
import utils.*;
import gmaths.*;
import model.SpotLight;
import model.Alien;
import utils.AssignmentUtil.*;

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
    private SpotLight spotLight;
    private Alien alien1, alien2;

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
        background.dispose(gl);
        floor.dispose(gl);
        light.dispose(gl);
        spotLight.dispose(gl);
        alien1.dispose(gl);
        alien2.dispose(gl);
    }

    public void initialise(GL3 gl) {
        Vec3 basecolor = new Vec3(0.5f, 0.5f, 0.5f);

        textures = new TextureLibrary();
        textures.add(gl, Constants.TEXTURE_NAME_BACKGROUND, Constants.TEXTURE_PATH_BACKGROUND);
        textures.add(gl, Constants.TEXTURE_NAME_FLOOR, Constants.TEXTURE_PATH_FLOOR);
        textures.add(gl, Constants.TEXTURE_NAME_SNOWFALL, Constants.TEXTURE_PATH_SNOWFALL);
        textures.add(gl, Constants.TEXTURE_NAME_CAMERA, Constants.TEXTURE_PATH_CAMERA);
        textures.add(gl, Constants.TEXTURE_NAME_STEEL, Constants.TEXTURE_PATH_STEEL);

        light = new Light(gl);
        light.setCamera(camera);
        light.setPosition(Constants.LIGHT_POISTION);

        // Model 1 - a background
        String name = "background";
        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_MOVING_PATH, Constants.FRAGMENT_SHADER_MOVING_PATH);
        Material material = new Material(basecolor, basecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        background = new Model(name, mesh, new Mat4(1), shader, material, light, camera,
                textures.get(Constants.TEXTURE_NAME_BACKGROUND), textures.get(Constants.TEXTURE_NAME_SNOWFALL));

        // Model 2 - a floor
        name = "floor";
        mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        material = new Material(basecolor, basecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        floor = new Model(name, mesh, new Mat4(1), shader, material, light, camera,
                textures.get(Constants.TEXTURE_NAME_FLOOR));
        roomTransforms = AssignmentUtil.getBackDropTransformation();

        // spot Light
        spotLight = new SpotLight(gl, camera, light, textures.get(Constants.TEXTURE_NAME_STEEL),
                textures.get(Constants.TEXTURE_NAME_CAMERA));

        // alien
        alien1 = new Alien(gl, camera, light, -2f);
        alien2 = new Alien(gl, camera, light, 2f);
    }

    public void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        light.render(gl);

        floor.setModelMatrix(roomTransforms[0]); // change transform
        floor.render(gl, startTime);
        background.setModelMatrix(roomTransforms[1]); // change transform
        background.render(gl, startTime);
        double elapsedTime = AssignmentUtil.getSeconds() - startTime;
        spotLight.updateCameraAnimation(elapsedTime);

        spotLight.render(gl);

        alien1.startRockAnimation(elapsedTime, 1f);
        alien1.startHeadRollFrontNBackAnimation(elapsedTime, 2f);

        alien2.startHeadRollSideToSideAnimation(elapsedTime, 1f);
        alien2.startRockAnimation(elapsedTime, 2f);

        alien1.render(gl);
        alien2.render(gl);
    }
}