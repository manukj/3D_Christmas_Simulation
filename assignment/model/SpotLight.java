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
 * SpotLight
 */
public class SpotLight {
    private Camera camera;
    private Light lightIn;
    private Model sphere, cameraSphere;
    private SGNode root;
    private TransformNode cameraRotationY;

    public SpotLight(GL3 gl, Camera camera, Light lightIn, Texture steelTexture, Texture cameraTexture) {
        this.camera = camera;
        this.lightIn = lightIn;
        sphere = makeSphere(gl, steelTexture);
        cameraSphere = makeSphere(gl, cameraTexture);

        float spotLightHeight = 5f;
        float spotLightWidth = 0.5f;
        float spotLightDepth = 0.5f;
        float spotCameraHeight = 0.5f;
        float spotCameraWidth = 1f;
        float spotCameraDepth = 0.5f;

        root = new NameNode("root");

        NameNode spotLight = new NameNode("spot_light");
        Mat4 m = Mat4Transform.translate(-5f, 0, 0);
        m = Mat4.multiply(m, Mat4Transform.scale(spotLightWidth, spotLightHeight, spotLightDepth));
        m = Mat4.multiply(m, Mat4Transform.translate(0, 0.5f, 0));
        TransformNode spotLightTransform = new TransformNode("body transform", m);
        ModelNode spotLightShape = new ModelNode("Sphere(body)", sphere);

        NameNode spotCamera = new NameNode("camera");
        TransformNode cameraTranslate = new TransformNode("leftarm translate",
                Mat4Transform.translate(-5f, spotLightHeight, 0));
        TransformNode cameraRotationZ = new TransformNode("leftarm rotate", Mat4Transform.rotateAroundZ(-30));
        cameraRotationY = new TransformNode("leftarm rotate", Mat4Transform.rotateAroundY(30));
        m = Mat4Transform.scale(spotCameraWidth, spotCameraHeight, spotCameraDepth);
        // m = Mat4.multiply(m, Mat4Transform.translate(-5f, 0, 0));
        // m = Mat4.multiply(m, Mat4Transform.translate(0, spotLightHeight * 0.5f, 0));
        TransformNode spotCameraTransform = new TransformNode("body transform", m);
        ModelNode spotCameraShape = new ModelNode("Sphere(body)", cameraSphere);

        root.addChild(spotLight);
        spotLight.addChild(spotLightTransform);
        spotLightTransform.addChild(spotLightShape);

        spotLight.addChild(spotCamera);
        spotCamera.addChild(cameraTranslate);
        cameraTranslate.addChild(cameraRotationZ);
        cameraRotationZ.addChild(cameraRotationY);
        cameraRotationY.addChild(spotCameraTransform);
        spotCameraTransform.addChild(spotCameraShape);

        root.update();
    }

    public void render(GL3 gl) {
        root.draw(gl);
    }

    private Model makeSphere(GL3 gl, Texture t1) {
        String name = "sphere";
        Mesh mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f),
                new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        Mat4 modelMatrix = Mat4.multiply(Mat4Transform.scale(4, 4, 4), Mat4Transform.translate(0, 0.5f, 0));
        Model sphere = new Model(name, mesh, modelMatrix, shader, material, lightIn, camera, t1);
        return sphere;
    }

}