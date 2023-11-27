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
 * Alien
 */
public class Alien {
    private Camera camera;
    private Light lightIn;
    private SGNode root;
    private Model sphere;

    public Alien(GL3 gl, Camera camera, Light light, float xPosition) {
        this.camera = camera;
        this.lightIn = light;
        sphere = makeSphere(gl);

        float bodyScale = 2f;
        float headScale = 1f;
        float eyeWidth = 0.25f;
        float eyeDepth = 0.15f;
        float eyeScale = 0.2f;

        float earLength = 0.8f;
        float earWidth = 0.1f;
        float earDepth = 0.3f;

        float antennaLength = 0.4f;
        float anteenaScale = 0.06f;
        float antennaSphereScale = 0.2f;

        float armLength = 1f;
        float armScale = 0.2f;

        root = new NameNode("root");
        TransformNode rootTranslate = new TransformNode("root translate", Mat4Transform.translate(xPosition, 0, 0));

        NameNode body = new NameNode("body");
        Mat4 m = Mat4Transform.scale(bodyScale, bodyScale, bodyScale);
        m = Mat4.multiply(m, Mat4Transform.translate(0, 0.5f, 0));
        TransformNode bodyTransform = new TransformNode("body transform", m);
        ModelNode bodyShape = new ModelNode("Sphere(body)", sphere);

        NameNode head = new NameNode("head");
        TransformNode headTranslate = new TransformNode("head translate",
                Mat4Transform.translate(0, bodyScale + headScale * 0.5f, 0));
        m = Mat4Transform.scale(headScale, headScale, headScale);
        TransformNode headTransform = new TransformNode("head transform", m);
        ModelNode headShape = new ModelNode("Sphere(head)", sphere);

        NameNode antenna = new NameNode("antenna");
        TransformNode antennaTranslate = new TransformNode("antenna translate",
                Mat4Transform.translate(0, headScale * 0.5f + antennaLength * 0.5f, 0));
        m = Mat4Transform.scale(anteenaScale, antennaLength, anteenaScale);
        TransformNode antennaTransform = new TransformNode("antenna transform", m);
        ModelNode antennaShape = new ModelNode("Sphere(antenna)", sphere);

        NameNode antennaSphere = new NameNode("antenna sphere");
        m = Mat4Transform.translate(0, antennaLength * 0.5f + antennaSphereScale * 0.5f, 0);
        m = Mat4.multiply(m, Mat4Transform.scale(antennaSphereScale, antennaSphereScale, antennaSphereScale));
        TransformNode antennaSphereTransform = new TransformNode("antenna spheretransform", m);
        ModelNode antennaSphereShape = new ModelNode("Sphere(antenna sphere)", sphere);

        NameNode leftEye = new NameNode("left eye");
        m = Mat4Transform.translate(headScale * 0.18f, headScale * 0.20f, headScale * 0.38f);
        m = Mat4.multiply(m, Mat4Transform.scale(eyeWidth, eyeScale, eyeDepth));
        TransformNode leftEyeTransform = new TransformNode("left eye transform", m);
        ModelNode leftEyeShape = new ModelNode("Sphere(left eye)", sphere);

        NameNode rightEye = new NameNode("right eye");
        m = Mat4Transform.translate(-headScale * 0.18f, headScale * 0.20f, headScale * 0.38f);
        m = Mat4.multiply(m, Mat4Transform.scale(eyeWidth, eyeScale, eyeDepth));
        TransformNode rightEyeTransform = new TransformNode("right eye transform", m);
        ModelNode rightEyeShape = new ModelNode("Sphere(right eye)", sphere);

        NameNode leftEar = new NameNode("left ear");
        m = Mat4Transform.translate(headScale * 0.5f, earLength * 0.5f, 0);
        m = Mat4.multiply(m, Mat4Transform.scale(earWidth, earLength, earDepth));
        TransformNode leftEarTransform = new TransformNode("left ear transform", m);
        ModelNode leftEarShape = new ModelNode("Sphere(left ear)", sphere);

        NameNode rightEar = new NameNode("right ear");
        m = Mat4Transform.translate(-headScale * 0.5f, earLength * 0.5f, 0);
        m = Mat4.multiply(m, Mat4Transform.scale(earWidth, earLength, earDepth));
        TransformNode rightEarTransform = new TransformNode("right ear transform", m);
        ModelNode rightEarShape = new ModelNode("Sphere(right ear)", sphere);

        NameNode leftArm = new NameNode("left arm");
        m = Mat4Transform.translate((bodyScale + armScale) * 0.5f, (bodyScale + armLength) * 0.5f, 0);
        m = Mat4.multiply(m, Mat4Transform.rotateAroundZ(-20));
        m = Mat4.multiply(m, Mat4Transform.scale(armScale, armLength, armScale));
        TransformNode leftArmTransform = new TransformNode("left arm transform", m);
        ModelNode leftArmShape = new ModelNode("Sphere(left arm)", sphere);

        NameNode rightArm = new NameNode("right arm");
        m = Mat4Transform.translate(-(bodyScale + armScale) * 0.5f, (bodyScale + armLength) * 0.5f, 0);
        m = Mat4.multiply(m, Mat4Transform.rotateAroundZ(20));
        m = Mat4.multiply(m, Mat4Transform.scale(armScale, armLength, armScale));
        TransformNode rightArmTransform = new TransformNode("right arm transform", m);
        ModelNode rightArmShape = new ModelNode("Sphere(right arm)", sphere);

        // body -> root
        root.addChild(rootTranslate);
        rootTranslate.addChild(body);
        body.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        // head -> body
        body.addChild(headTranslate);
        headTranslate.addChild(head);
        head.addChild(headTransform);
        headTransform.addChild(headShape);

        // antenna -> head
        head.addChild(antennaTranslate);
        antennaTranslate.addChild(antenna);
        antenna.addChild(antennaTransform);
        antennaTransform.addChild(antennaShape);
        // antennaSphere -> antenna
        antenna.addChild(antennaSphere);
        antennaSphere.addChild(antennaSphereTransform);
        antennaSphereTransform.addChild(antennaSphereShape);

        // leftEye -> head
        head.addChild(leftEye);
        leftEye.addChild(leftEyeTransform);
        leftEyeTransform.addChild(leftEyeShape);
        // rightEye -> head
        head.addChild(rightEye);
        rightEye.addChild(rightEyeTransform);
        rightEyeTransform.addChild(rightEyeShape);

        // leftEar -> head
        head.addChild(leftEar);
        leftEar.addChild(leftEarTransform);
        leftEarTransform.addChild(leftEarShape);

        // rightEar -> head
        head.addChild(rightEar);
        rightEar.addChild(rightEarTransform);
        rightEarTransform.addChild(rightEarShape);

        // leftArm -> body
        body.addChild(leftArm);
        leftArm.addChild(leftArmTransform);
        leftArmTransform.addChild(leftArmShape);

        // rightArm -> body
        body.addChild(rightArm);
        rightArm.addChild(rightArmTransform);
        rightArmTransform.addChild(rightArmShape);

        root.update();
    }

    public void render(GL3 gl) {
        root.draw(gl);
    }

    public void dispose(GL3 gl) {
        sphere.dispose(gl);
    }

    private Model makeSphere(GL3 gl) {
        String name = "sphere";
        Mesh mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH, Constants.FRAGMENT_SHADER_STANDARD_PATH);
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f),
                new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        Mat4 modelMatrix = Mat4.multiply(Mat4Transform.scale(4, 4, 4), Mat4Transform.translate(0, 0.5f, 0));
        Model sphere = new Model(name, mesh, modelMatrix, shader, material, lightIn, camera);
        return sphere;
    }
}