package constants;

import gmaths.*;

/**
 * I declare that this code is my own work
 * Author: Manu Kenchappa Junjanna
 */
public class Constants {
    public static final boolean DISPLAY_SHADERS = false;

    // Path to the fragment and vertex shaders
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

    public static final Vec3 LIGHT_POISTION = new Vec3(-5f, 15f, 10f);

    // Name and path of all the textures used
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
    // eye
    public static final String TEXTURE_NAME_ALIEN_EYE = "alien_eye";
    public static final String TEXTURE_PATH_ALIEN_EYE = "textures/eye.png";
    public static final String TEXTURE_NAME_ALIEN2_EYE = "alien_eye_2";
    public static final String TEXTURE_PATH_ALIEN2_EYE = "textures/eye_2.png";
    // ear
    public static final String TEXTURE_NAME_ALIEN_EAR = "alien_ear";
    public static final String TEXTURE_PATH_ALIEN_EAR = "textures/ear.png";
    // anteena
    public static final String TEXTURE_NAME_ALIEN1_ANTEENA1 = "alien_anteena1_1";
    public static final String TEXTURE_PATH_ALIEN1_ANTEENA1 = "textures/anteena1_1.png";
    public static final String TEXTURE_NAME_ALIEN1_ANTEENA2 = "alien_anteena1_1";
    public static final String TEXTURE_PATH_ALIEN1_ANTEENA2 = "textures/anteena1_2.png";
    public static final String TEXTURE_NAME_ALIEN2_ANTEENA1 = "alien_anteena2_1";
    public static final String TEXTURE_PATH_ALIEN2_ANTEENA1 = "textures/anteena2_1.png";
    public static final String TEXTURE_NAME_ALIEN2_ANTEENA2 = "alien_anteena2_2";
    public static final String TEXTURE_PATH_ALIEN2_ANTEENA2 = "textures/anteena2_2.png";
    
}
