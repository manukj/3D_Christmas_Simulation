
/**
 * AssignmentGLEventListener
 */
import utils.*;
import gmaths.*;
import model.SpotLight;
import model.Alien;
import model.Room;
import utils.AssignmentUtil.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;

import constants.Constants;
import constants.Constants.*;

public class AssignmentGLEventListener implements GLEventListener, ClickCallback {
    private Camera camera;
    private double startTime;
    private Room room;
    private Light[] lights = new Light[2];
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
        startTime = AssignmentUtil.getSeconds();
        initialise(gl);
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
        spotLight.dispose(gl);
        alien1.dispose(gl);
        alien2.dispose(gl);
        room.dispose(gl);
    }

    public void initialise(GL3 gl) {
        Vec3 basecolor = new Vec3(0.5f, 0.5f, 0.5f);

        textures = new TextureLibrary();
        textures.add(gl, Constants.TEXTURE_NAME_BACKGROUND, Constants.TEXTURE_PATH_BACKGROUND);
        textures.add(gl, Constants.TEXTURE_NAME_FLOOR, Constants.TEXTURE_PATH_FLOOR);
        textures.add(gl, Constants.TEXTURE_NAME_SNOWFALL, Constants.TEXTURE_PATH_SNOWFALL);
        textures.add(gl, Constants.TEXTURE_NAME_CAMERA, Constants.TEXTURE_PATH_CAMERA);
        textures.add(gl, Constants.TEXTURE_NAME_STEEL, Constants.TEXTURE_PATH_STEEL);

        lights[0] = new Light(gl);
        lights[0].setCamera(camera);
        lights[1] = new Light(gl);
        lights[1].setCamera(camera);
        lights[1].turnOnSpotLight();
        // orange
        lights[1].setColor(AssignmentUtil.SPOT_LIGHT_ON_COLOR);
        lights[0].setPosition(Constants.LIGHT_POISTION);

        room = new Room(gl, camera, lights, textures.get(Constants.TEXTURE_NAME_BACKGROUND),
                textures.get(Constants.TEXTURE_NAME_FLOOR), textures.get(Constants.TEXTURE_NAME_SNOWFALL), startTime);

        // spot Light
        spotLight = new SpotLight(gl, camera, lights, textures.get(Constants.TEXTURE_NAME_STEEL),
                textures.get(Constants.TEXTURE_NAME_CAMERA));

        // alien
        alien1 = new Alien(gl, camera, lights, -2f);
        alien2 = new Alien(gl, camera, lights, 2f);
    }

    public void render(GL3 gl) {
        double elapsedTime = AssignmentUtil.getSeconds() - startTime;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        lights[0].render(gl);
        lights[1].setPosition(getLightSpotPosition(elapsedTime));
        lights[1].render(gl, getSpotLightModelMatrix(elapsedTime));

        room.render(gl);

        spotLight.updateCameraAnimation(elapsedTime);

        spotLight.render(gl);

        alien1.startRockAnimation(elapsedTime, 1f);
        alien1.startHeadRollFrontNBackAnimation(elapsedTime, 2f);

        alien2.startHeadRollSideToSideAnimation(elapsedTime, 1f);
        alien2.startRockAnimation(elapsedTime, 2f);

        alien1.render(gl);
        alien2.render(gl);
    }

    public Mat4 getSpotLightModelMatrix(double elapsedTime) {
        double rotationFraction = elapsedTime % (2 * Math.PI);
        float rotateAngle = (float) Math.toDegrees(rotationFraction);

        Mat4 rotationMatrix = Mat4Transform.rotateAroundY(rotateAngle);
        rotationMatrix = Mat4.multiply(rotationMatrix, Mat4Transform.rotateAroundZ(-30));

        Vec3 lightPosition = new Vec3(-5f, 5f, 0f);
        Mat4 modelMatrix = Mat4Transform.translate(lightPosition);
        modelMatrix = Mat4.multiply(modelMatrix, rotationMatrix);
        modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.scale(0.3f, 0.3f, 0.3f));
        modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.translate(2.8f, 0, 0));
        return modelMatrix;
    }

    private Vec3 getLightSpotPosition(double elapsedTime) {
        double rotationFraction = elapsedTime % (2 * Math.PI);
        float rotateAngle = (float) Math.toDegrees(rotationFraction);
        float x = -5f + (float) (Math.cos(Math.toRadians(rotateAngle)));
        float y = 0.5f;
        float z = 1.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 80)));
        return new Vec3(-5f, 5f, 0f);
    }

    @Override
    public void toggleSpotLight() {
        if (lights[1].isOn) {
            lights[1].turnOf();
        } else {
            lights[1].turnOnSpotLight();
        }

    }

    @Override
    public void toggleMainLight() {
        if (lights[0].isOn) {
            lights[0].turnOf();
        } else {
            lights[0].turnOn();
        }
    }

    @Override
    public void dimMainLight() {
        lights[0].dim();
    }

    @Override
    public void brightenMainLight() {
        lights[0].brighten();
    }

    @Override
    public void dimSpotLight() {
        lights[1].dim();
    }

    @Override
    public void brightenSpotLight() {
        lights[1].brighten();
    }
}