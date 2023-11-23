package constants;

import gmaths.*;

/**
 * Constants
 */
public class Constants {
    public static final boolean DISPLAY_SHADERS = false;
    public static final String VERTEX_SHADER_STANDARD_PATH = "shader/vs_standard.txt";
    public static final String FRAGMENT_SHADER_STANDARD_PATH = "shader/fs_standard.txt";
    public static final String VERTEX_SHADER_LIGHT_PATH = "shader/vs_light.txt";
    public static final String FRAGMENT_SHADER_LIGHT_PATH = "shader/fs_light.txt";
    public static final Vec3 CAMERA_POISTION = new Vec3(0f, 10f, 27f);
    public static final Vec3 LIGHT_POISTION = new Vec3(-5f, 15f, 10f);

    public static final String TEXTURE_NAME_BACKGROUND = "background";
    public static final String TEXTURE_PATH_BACKGROUND = "textures/background.png";
    public static final String TEXTURE_NAME_FLOOR = "floor";
    public static final String TEXTURE_PATH_FLOOR = "textures/floor.png";
}
