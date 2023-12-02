package model;

import utils.*;
import gmaths.*;
import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.glsl.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;
import com.jogamp.opengl.util.texture.spi.JPEGImage;

import constants.Constants;

/**
 * I declare that this code is my own work
 * Author: Manu Kenchappa Junjanna
 */
public class SpotLight {
    private Camera camera;
    private Light[] lights;
    private ModelMultipleLights sphere, cameraSphere;
    private SGNode root;
    private TransformNode cameraRotationY;

    public SpotLight(GL3 gl, Camera camera, Light[] lights, Texture steelTexture, Texture cameraTexture) {
        this.camera = camera;
        this.lights = lights;
        sphere = makeSphere(gl, steelTexture);
        cameraSphere = makeSphere(gl, cameraTexture);

        float poleHeight = 5f;
        float poleScale = 0.5f;

        float cameraHeight = 0.5f;
        float cameraWidth = 1f;
        float cameraDepth = 0.5f;

        // Scene graph nodes
        root = new NameNode("root");
        TransformNode rootTranslate = new TransformNode("robot transform", Mat4Transform.translate(-5f, 0, 0));

        // Pole to the Camera
        NameNode pole = new NameNode("spot_light");
        Mat4 m = Mat4Transform.scale(poleScale, poleHeight, poleScale);
        m = Mat4.multiply(m, Mat4Transform.translate(0, 0.5f, 0));
        TransformNode poleTransform = new TransformNode("body transform", m);
        ModelNode poleShape = new ModelNode("Sphere(body)", sphere);

        NameNode spotCamera = new NameNode("camera");
        TransformNode cameraTranslate = new TransformNode("leftarm translate",
                Mat4Transform.translate(0, poleHeight, 0));

        cameraRotationY = new TransformNode("leftarm rotate", Mat4Transform.rotateAroundY(30));
        m = Mat4Transform.scale(cameraWidth, cameraHeight, cameraDepth);
        m = Mat4.multiply(m, Mat4Transform.translate(0.5f, 0, 0));
        TransformNode spotCameraTransform = new TransformNode("body transform", m);
        ModelNode spotCameraShape = new ModelNode("Sphere(body)", cameraSphere);

        root.addChild(rootTranslate);
        rootTranslate.addChild(pole);

        pole.addChild(poleTransform);
        poleTransform.addChild(poleShape);

        pole.addChild(spotCamera);
        spotCamera.addChild(cameraTranslate);
        cameraTranslate.addChild(cameraRotationY);
        cameraRotationY.addChild(spotCameraTransform);
        spotCameraTransform.addChild(spotCameraShape);

        root.update();
    }

    public void render(GL3 gl) {
        root.draw(gl);
    }

    private ModelMultipleLights makeSphere(GL3 gl, Texture t1) {
        String name = "sphere";
        Mesh mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH,
                Constants.FRAGMENT_SHADER_MULTIPLE_LIGHTS_1_TEXTURE);
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f),
                new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        Mat4 modelMatrix = Mat4.multiply(Mat4Transform.scale(4, 4, 4), Mat4Transform.translate(0, 0.5f, 0));
        ModelMultipleLights sphere = new ModelMultipleLights(name, mesh, modelMatrix, shader, material, lights, camera,
                t1);
        return sphere;
    }

    /**
     * Updates the camera animation based on the elapsed time. to ratate the camera
     * Y axis and keeping z axis fixed to 30 degrees
     * 
     * @param elapsedTime the elapsed time in seconds
     */
    public void updateCameraAnimation(double elapsedTime) {
        double rotationFraction = elapsedTime % (2 * Math.PI);
        float rotateAngle = (float) Math.toDegrees(rotationFraction);
        Mat4 rotationMatrix = Mat4Transform.rotateAroundY(rotateAngle);
        rotationMatrix = Mat4.multiply(rotationMatrix, Mat4Transform.rotateAroundZ(-30));
        cameraRotationY.setTransform(rotationMatrix);
        cameraRotationY.update();
    }

    public void dispose(GL3 gl) {
        sphere.dispose(gl);
        cameraSphere.dispose(gl);
    }

}