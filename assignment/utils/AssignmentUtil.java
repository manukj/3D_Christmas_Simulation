package utils;

import gmaths.*;

/**
 * AssignmentUtil
 */
public class AssignmentUtil {
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
        float size = 16f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(size, 1f, size), modelMatrix);
        return modelMatrix;
    }

    private static Mat4 getMforBackDrop2() {
        float size = 16f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(size, 1f, size), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundX(90), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0, size * 0.5f, -size * 0.5f), modelMatrix);
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
}