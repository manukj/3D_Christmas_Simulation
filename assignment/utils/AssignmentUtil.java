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

    public static Mat4[] getBackDropTransformation() {
        Mat4[] t = new Mat4[4];
        t[0] = getMforBackDrop1();
        t[1] = getMforBackDrop2();
        return t;
    }

    private static Mat4 getMforBackDrop1() {
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(BACKDROP_WIDTH, 1f, BACKDROP_HEIGHT), modelMatrix);
        return modelMatrix;
    }

    private static Mat4 getMforBackDrop2() {
        float height = 6.66f;
        float width = 12f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(BACKDROP_WIDTH, 1f, BACKDROP_HEIGHT), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundX(90), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f, BACKDROP_HEIGHT * 0.5f, -3.33f), modelMatrix);
        // modelMatrix);
        return modelMatrix;
    }

    // private Mat4 getMforTT3() {
    // float size = 16f;
    // Mat4 modelMatrix = new Mat4(1);
    // modelMatrix = Mat4.multiply(Mat4Transform.scale(size, 1f, size),
    // modelMatrix);
    // modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundY(90), modelMatrix);
    // modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(-90), modelMatrix);
    // modelMatrix = Mat4.multiply(Mat4Transform.translate(-size * 0.5f, size *
    // 0.5f, 0), modelMatrix);
    // return modelMatrix;
    // }

    public static Vec3 getLightPosition(double startTime) {
        double elapsedTime = getSeconds() - startTime;
        float x = 3.0f * (float) (Math.sin(Math.toRadians(elapsedTime * 50)));
        float y = 2.4f;
        float z = 3.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 50)));
        return new Vec3(0f, 9f, 27f);
    }

    
}