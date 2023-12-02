package utils;

import gmaths.*;

/**
 * I declare that this code is my own work
 * Author: Manu Kenchappa Junjanna
 */
public class AssignmentUtil {
    public static final Vec4 LIGHT_ON_COLOR = new Vec4(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vec4 LIGHT_OFF_COLOR = new Vec4(0.2f, 0.2f, 0.0f, 1.0f);
    public static final Vec4 SPOT_LIGHT_ON_COLOR = new Vec4(1.0f, 0.6f, 0.0f, 0f);

    public static double getSeconds() {
        return System.currentTimeMillis() / 1000.0;
    }

}