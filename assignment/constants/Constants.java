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
    public static final String VERTEX_SHADER_MOVING_PATH = "shader/vs_moving_shader.txt";
    public static final String FRAGMENT_SHADER_MOVING_PATH = "shader/fs_moving_shader.txt";
    public static final String FRAGMENT_SHADER_MULTIPLE_LIGHTS_0_TEXTURE = "shader/fs_standard_m_0t.txt";
    public static final String FRAGMENT_SHADER_MULTIPLE_LIGHTS_1_TEXTURE = "shader/fs_standard_m_1t.txt";
    public static final String FRAGMENT_SHADER_MULTIPLE_LIGHTS_2_TEXTURE = "shader/fs_standard_m_2t.txt";
    public static final String FRAGMENT_SHADER_MOVING_TEXTURE_MULTIPLE_LIGHTS = "shader/fs_moving_ml_2t.txt";

    public static final Vec3 CAMERA_POISTION = new Vec3(0, 6, 14);
    // 0.037405625,5.9521856,15.164336
    // -0.31873077,7.803866,13.521669
    // new Vec3(4f,12f,18f)

    public static final Vec3 LIGHT_POISTION = new Vec3(-5f, 15f, 10f);
    public static final Vec3 LIGHT_2_POISTION = new Vec3(30f, 1f, 0f);

    public static final String TEXTURE_NAME_BACKGROUND = "background";
    public static final String TEXTURE_PATH_BACKGROUND = "textures/background.png";
    public static final String TEXTURE_NAME_FLOOR = "floor";
    public static final String TEXTURE_PATH_FLOOR = "textures/floor.png";
    public static final String TEXTURE_NAME_SNOWFALL = "snowfall";
    public static final String TEXTURE_PATH_SNOWFALL = "textures/snowfall.png";
    public static final String TEXTURE_NAME_CAMERA = "camera";
    public static final String TEXTURE_PATH_CAMERA = "textures/camera.jpeg";
    public static final String TEXTURE_NAME_STEEL = "steel";
    public static final String TEXTURE_PATH_STEEL = "textures/steel.jpeg";
}
