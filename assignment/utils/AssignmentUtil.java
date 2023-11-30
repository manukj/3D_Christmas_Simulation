package utils;

import gmaths.*;

/**
 * AssignmentUtil
 */
public class AssignmentUtil {
    public static final float BACKDROP_HEIGHT = 6.66f;
    public static final float BACKDROP_WIDTH = 12f;
    public static final Vec4 LIGHT_ON_COLOR = new Vec4(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vec4 LIGHT_OFF_COLOR = new Vec4(0.2f, 0.2f, 0.0f, 1.0f);
    public static final Vec4 SPOT_LIGHT_ON_COLOR = new Vec4(1.0f, 0.6f, 0.0f, 0f);

    public static double getSeconds() {
        return System.currentTimeMillis() / 1000.0;
    }

}