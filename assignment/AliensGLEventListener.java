
/**
 *  I declare that this code is my own work
 *  Author: Manu Kenchappa Junjanna 
 */
import utils.*;
import gmaths.*;
import model.SpotLight;
import model.AlienModel;
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

public class AliensGLEventListener implements GLEventListener, ClickCallback {
    private Camera camera;
    private double startTime;
    private Room room;
    private Light[] lights = new Light[3];
    private TextureLibrary textures;
    private SpotLight spotLight;
    private AlienModel alien1, alien2;

    public AliensGLEventListener(Camera camera) {
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
        gl.glFrontFace(GL.GL_CCW);
        gl.glEnable(GL.GL_CULL_FACE);
        gl.glCullFace(GL.GL_BACK);
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
        textures.add(gl, Constants.TEXTURE_NAME_STEEL_BUMP, Constants.TEXTURE_PATH_STEEL_BUMP);
        textures.add(gl, Constants.TEXTURE_NAME_FLOOR_GREY_SCALE, Constants.TEXTURE_PATH_FLOOR_GREY_SCALE);

        // general light 1
        lights[0] = new Light(gl, 1);
        lights[0].setCamera(camera);
        // general light 2
        lights[1] = new Light(gl, 1);
        lights[1].setCamera(camera);
        // spot light
        lights[2] = new Light(gl, 2);
        lights[2].setCamera(camera);
        lights[2].turnOnSpotLight();

        // set the spot light to orange color
        lights[0].setPosition(Constants.LIGHT_POISTION);
        lights[1].setPosition(Constants.LIGHT_POISTION2);
        lights[2].setColor(Constants.SPOT_LIGHT_ON_COLOR);

        // room Model - floor and background
        room = new Room(gl, camera, lights, textures.get(Constants.TEXTURE_NAME_BACKGROUND),
                textures.get(Constants.TEXTURE_NAME_FLOOR), textures.get(Constants.TEXTURE_NAME_FLOOR_GREY_SCALE),
                textures.get(Constants.TEXTURE_NAME_SNOWFALL), startTime);

        // spot Light Model
        spotLight = new SpotLight(gl, camera, lights, textures.get(Constants.TEXTURE_NAME_STEEL),
                textures.get(Constants.TEXTURE_NAME_STEEL_BUMP),
                textures.get(Constants.TEXTURE_NAME_CAMERA));

        // alien Models
        alien1 = new AlienModel(gl, camera, lights, -2f, startTime, 1);
        alien2 = new AlienModel(gl, camera, lights, 2f, startTime, 2);
    }

    public void render(GL3 gl) {
        double elapsedTime = AssignmentUtil.getSeconds() - startTime;
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        lights[0].render(gl);
        lights[1].render(gl);
        lights[2].setPosition(getLightSpotPosition(elapsedTime));
        lights[2].render(gl, getLightModelMatrix(elapsedTime));

        room.render(gl);

        // animate the spot light and render it
        spotLight.updateCameraAnimation(elapsedTime);
        spotLight.render(gl);

        alien1.render(gl);
        alien2.render(gl);
    }

    /*
     * This method is used give the transformation matrix for the spot light, that
     * is in sync with the spot light rotation
     */
    public Mat4 getLightModelMatrix(double elapsedTime) {
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
        float x = -5f + -5.0f * (float) (Math.sin(Math.toRadians(-rotateAngle - 90)));
        float y = 1f;
        float z = 2.0f * (float) (Math.cos(Math.toRadians(-rotateAngle - 90)));
        return new Vec3(x, y, z);
    }

    /*
     * ClickCallback methods implementation to handle click events
     */

    @Override
    public void toggleSpotLight() {
        if (lights[2].isOn) {
            lights[2].turnOf();
        } else {
            lights[2].turnOnSpotLight();
        }

    }

    @Override
    public void toggleMainLight(int lightIndex) {
        if (lights[lightIndex].isOn) {
            lights[lightIndex].turnOf();
        } else {
            lights[lightIndex].turnOn();
        }
    }

    @Override
    public void dimMainLight(int lightIndex) {
        lights[lightIndex].dimmerMainLight();
    }

    @Override
    public void brightenMainLight(int lightIndex) {
        lights[lightIndex].brightenMainLight();
    }

    @Override
    public void dimSpotLight() {
        lights[2].dimSpotLight();
    }

    @Override
    public void brightenSpotLight() {
        lights[2].brightenSpotLight();
    }

    @Override
    public void increaseRockSpeed(int alienIndex) {
        if (alienIndex == 1) {
            alien1.increaseRockSpeed();
        } else if (alienIndex == 2) {
            alien2.increaseRockSpeed();
        }
    }

    @Override
    public void decreaseRockSpeed(int alienIndex) {
        if (alienIndex == 1) {
            alien1.decreaseRockSpeed();
        } else if (alienIndex == 2) {
            alien2.decreaseRockSpeed();
        }
    }

    @Override
    public void increaseRollSpeed(int alienIndex) {
        if (alienIndex == 1) {
            alien1.increaseRollSpeed();
        } else if (alienIndex == 2) {
            alien2.increaseRollSpeed();
        }
    }

    @Override
    public void decreaseRollSpeed(int alienIndex) {
        if (alienIndex == 1) {
            alien1.decreaseRollSpeed();
        } else if (alienIndex == 2) {
            alien2.decreaseRollSpeed();
        }
    }

    @Override
    public void changeRollDirection(int alienIndex) {
        if (alienIndex == 1) {
            alien1.changeRollDirection();
        } else if (alienIndex == 2) {
            alien2.changeRollDirection();
        }
    }

    @Override
    public void toggleRockAnimation(int alienIndex) {
        if (alienIndex == 1) {
            alien1.toggleRockAnimation();
        } else if (alienIndex == 2) {
            alien2.toggleRockAnimation();
        }
    }

    @Override
    public void toggleRollAnimation(int alienIndex) {
        if (alienIndex == 1) {
            alien1.toggleRollAnimation();
        } else if (alienIndex == 2) {
            alien2.toggleRollAnimation();
        }
    }
}