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

}