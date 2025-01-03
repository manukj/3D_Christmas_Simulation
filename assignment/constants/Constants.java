package constants;

import gmaths.*;
import utils.*;

/**
 * I declare that this code is my own work
 * Author: Manu Kenchappa Junjanna
 */
public class Constants {
        public static final boolean DISPLAY_SHADERS = false;

        public static final float MAIN_LIGHT_MAX_AMBIENT = 0.15f;
        public static final float MAIN_LIGHT_MAX_DIFFUSE = 0.35f;
        public static final float MAIN_LIGHT_MAX_SPECULAR = 0.5f;

        // LIGHT CONSTANTS
        public static final Material MAIN_LIGHT_MATERIAL = new Material(
                        new Vec3(MAIN_LIGHT_MAX_AMBIENT, MAIN_LIGHT_MAX_AMBIENT, MAIN_LIGHT_MAX_AMBIENT),
                        new Vec3(MAIN_LIGHT_MAX_DIFFUSE, MAIN_LIGHT_MAX_DIFFUSE, MAIN_LIGHT_MAX_DIFFUSE),
                        new Vec3(MAIN_LIGHT_MAX_SPECULAR, MAIN_LIGHT_MAX_SPECULAR, MAIN_LIGHT_MAX_SPECULAR),
                        Material.DEFAULT_SHININESS);
        public static final Material SPOT_LIGHT_MATERIAL = new Material(new Vec3(0, 0, 0),
                        new Vec3(0.5f, 0.25f, 0.0f),
                        new Vec3(0.7f, 0.7f, 0.7f), Material.DEFAULT_SHININESS);
        public static final Material LIGHT_OFF_MATERIAL = new Material(new Vec3(0, 0, 0),
                        new Vec3(0, 0, 0),
                        new Vec3(0, 0, 0), Material.DEFAULT_SHININESS);
        public static final Vec4 LIGHT_ON_COLOR = new Vec4(1.0f, 1.0f, 1.0f, 1.0f);
        public static final Vec4 LIGHT_OFF_COLOR = new Vec4(0.2f, 0.2f, 0.0f, 1.0f);
        public static final Vec4 SPOT_LIGHT_ON_COLOR = new Vec4(1.0f, 0.6f, 0.0f, 0f);

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
        public static final String VERTEX_SHADER_BUMP_PATH = "shader/vs_bump_mt_1t.txt";
        public static final String FRAGMENT_SHADER_BUMP_PATH = "shader/fs_bump_mt_1t.txt";

        public static final Vec3 CAMERA_POISTION = new Vec3(0, 6, 14);

        public static final Vec3 LIGHT_POISTION = new Vec3(-10f, 10f, 5f);
        public static final Vec3 LIGHT_POISTION2 = new Vec3(10f, 10f, 5f);

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
        public static final String TEXTURE_PATH_STEEL = "textures/steel.png";
        public static final String TEXTURE_NAME_FLOOR_GREY_SCALE = "floor_grey_scale";
        public static final String TEXTURE_PATH_FLOOR_GREY_SCALE = "textures/floor_grey_scale.png";
        public static final String TEXTURE_NAME_STEEL_BUMP = "steel_bump";
        public static final String TEXTURE_PATH_STEEL_BUMP = "textures/steel_bump.png";

        // eye
        public static final String TEXTURE_NAME_ALIEN_EYE = "alien_eye";
        public static final String TEXTURE_PATH_ALIEN_EYE = "textures/eye.png";
        public static final String TEXTURE_NAME_ALIEN2_EYE = "alien_eye_2";
        public static final String TEXTURE_PATH_ALIEN2_EYE = "textures/eye_2.png";
        // ear
        public static final String TEXTURE_NAME_ALIEN_EAR = "alien_ear";
        public static final String TEXTURE_PATH_ALIEN_EAR = "textures/ear.png";
        // anteena
        public static final String TEXTURE_NAME_ALIEN1_ANTEENA = "alien_anteena1";
        public static final String TEXTURE_PATH_ALIEN1_ANTEENA = "textures/anteena1.png";
        public static final String TEXTURE_NAME_ALIEN2_ANTEENA = "alien_anteena2";
        public static final String TEXTURE_PATH_ALIEN2_ANTEENA = "textures/anteena2.png";
        // body
        public static final String TEXTURE_NAME_ALIEN_BODY = "alien_body";
        public static final String TEXTURE_PATH_ALIEN_BODY = "textures/body.png";
        public static final String TEXTURE_NAME_ALIEN2_BODY = "alien_body_2";
        public static final String TEXTURE_PATH_ALIEN2_BODY = "textures/body2.png";
        // head
        public static final String TEXTURE_NAME_ALIEN_HEAD = "alien_head";
        public static final String TEXTURE_PATH_ALIEN_HEAD = "textures/head.png";
        public static final String TEXTURE_NAME_ALIEN2_HEAD = "alien_head_2";
        public static final String TEXTURE_PATH_ALIEN2_HEAD = "textures/head_2.png";
        // arm
        public static final String TEXTURE_NAME_ALIEN_ARM = "alien_arm";
        public static final String TEXTURE_PATH_ALIEN_ARM = "textures/arm.png";
        // dust
        public static final String TEXTURE_NAME_DUST = "dust";
        public static final String TEXTURE_PATH_DUST = "textures/dust.png";
        public static final String TEXTURE_NAME_DUST_BUMP = "dust_bump";
        public static final String TEXTURE_PATH_DUST_BUMP = "textures/dust_grey_scale.png";

}
