package utils;

import gmaths.*;

/**
 * AssignmentUtil
 */
public class AssignmentUtil {
    public static final float BACKDROP_HEIGHT = 6.66f;
    public static final float BACKDROP_WIDTH = 12f;

    public static double getSeconds() {
        return System.currentTimeMillis() / 1000.0;
    }

    public static Material getOrangeMaterial() {
        Material material = new Material();
        material.setAmbient(1.0f, 0.5f, 0.0f);
        material.setDiffuse(1.0f, 0.3f, 0.0f);
        material.setSpecular(0.0f, 0.0f, 0.0f);
        return material;
    }

    public static Vec3 getLightPosition(double startTime) {
        double elapsedTime = getSeconds() - startTime;
        float x = 3.0f * (float) (Math.sin(Math.toRadians(elapsedTime * 50)));
        float y = 2.4f;
        float z = 3.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 50)));
        return new Vec3(0f, 9f, 27f);
    }

    public static void dimLight(Light light) {
        float dimFactor = 0.1f;
        Material material = light.getMaterial();
        material.setAmbient(material.getAmbient().x - dimFactor, material.getAmbient().y - dimFactor,
                material.getAmbient().z - dimFactor);
        material.setDiffuse(material.getDiffuse().x - dimFactor, material.getDiffuse().y - dimFactor,
                material.getDiffuse().z - dimFactor);
        material.setSpecular(material.getSpecular().x - dimFactor, material.getSpecular().y - dimFactor,
                material.getSpecular().z - dimFactor);
        light.setMaterial(material);
    }

    public static void turnOnLight(Light light) {
        Material material = new Material();
        material.setAmbient(0.3f, 0.3f, 0.3f);
        material.setDiffuse(0.5f, 0.5f, 0.5f);
        material.setSpecular(1.0f, 1.0f, 1.0f);
        light.setMaterial(material);
    }

    public static void brightenLight(Light light) {
        float dimFactor = 0.1f;
        Material material = light.getMaterial();
        material.setAmbient(material.getAmbient().x + dimFactor, material.getAmbient().y + dimFactor,
                material.getAmbient().z + dimFactor);
        material.setDiffuse(material.getDiffuse().x + dimFactor, material.getDiffuse().y + dimFactor,
                material.getDiffuse().z + dimFactor);
        material.setSpecular(material.getSpecular().x + dimFactor, material.getSpecular().y + dimFactor,
                material.getSpecular().z + dimFactor);
        light.setMaterial(material);
    }

}