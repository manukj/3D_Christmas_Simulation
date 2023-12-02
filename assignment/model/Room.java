package model;

import utils.*;
import constants.Constants;
import gmaths.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;
import com.jogamp.opengl.util.texture.spi.JPEGImage;

/**
 *  I declare that this code is my own work,but taken  refernce from 3DCG code
 *  Author: Manu Kenchappa Junjanna 
 */
public class Room {

    private ModelMultipleLights[] wall;
    private Camera camera;
    private Light[] lights;
    private Texture backgroundTexture, floorTexture, snowFallTexture;
    private float roomWidth = 12f, roomHeight = 6.66f;
    private Vec3 basecolor = new Vec3(0.5f, 0.5f, 0.5f);
    private double startTime;

    public Room(GL3 gl, Camera camera, Light[] lights, Texture background, Texture floor, Texture snowFall,
            double startTime) {
        this.camera = camera;
        this.lights = lights;
        this.backgroundTexture = background;
        this.snowFallTexture = snowFall;
        this.floorTexture = floor;
        this.startTime = startTime;
        wall = new ModelMultipleLights[2];
        wall[0] = makeFloor(gl);
        wall[1] = makeBackground(gl);
    }

    private ModelMultipleLights makeBackground(GL3 gl) {
        // Model 1 - a background
        String name = "background";
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(roomWidth, 1f, roomHeight), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundX(90), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f, roomHeight * 0.5f, -3.33f), modelMatrix);
        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_MOVING_PATH,
                Constants.FRAGMENT_SHADER_MOVING_TEXTURE_MULTIPLE_LIGHTS);
        Material material = new Material(basecolor, basecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        return new ModelMultipleLights(name, mesh, modelMatrix, shader, material, lights, camera, backgroundTexture,
                snowFallTexture);
    }

    private ModelMultipleLights makeFloor(GL3 gl) {
        // base blue color
        Vec3 baseBluecolor = new Vec3(0.0f, 0.0f, 1.0f);
        String name = "floor";
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(roomWidth, 1f, roomHeight), modelMatrix);
        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        Shader shader = new Shader(gl, Constants.VERTEX_SHADER_STANDARD_PATH,
                Constants.FRAGMENT_SHADER_MULTIPLE_LIGHTS_1_TEXTURE);
        Material material = new Material(baseBluecolor, baseBluecolor, new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        return new ModelMultipleLights(name, mesh, modelMatrix, shader, material, lights, camera, floorTexture);
    }

    public void dispose(GL3 gl) {
        for (int i = 0; i < wall.length; i++) {
            wall[i].dispose(gl);
        }
    }

    public void render(GL3 gl) {
        for (int i = 0; i < wall.length; i++) {
            if (i == 1) {
                wall[i].render(gl, startTime);
            }
            wall[i].render(gl);
        }
    }
}