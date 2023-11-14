package texture;

import java.io.File;

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.texture.*;

/**
 * TextureUtil
 */
public class TextureUtil {

    public static Texture loadTexture(GL3 gl3, String filename) {
        Texture texture = null;
        try {
            File f = new File(filename);
            texture = (Texture) TextureIO.newTexture(f, true);
            texture.bind(gl3);
            texture = (Texture) TextureIO.newTexture(f, true);
            texture.bind(gl3);
            texture.setTexParameteri(gl3, GL3.GL_TEXTURE_MIN_FILTER, GL3.GL_LINEAR);
            texture.setTexParameteri(gl3, GL3.GL_TEXTURE_MAG_FILTER, GL3.GL_LINEAR);
            texture.setTexParameteri(gl3, GL3.GL_TEXTURE_WRAP_S, GL3.GL_CLAMP_TO_EDGE);
            texture.setTexParameteri(gl3, GL3.GL_TEXTURE_WRAP_T, GL3.GL_CLAMP_TO_EDGE);
        } catch (Exception e) {
            System.out.println("Error loading texture " + filename);
        }
        return texture;
    }
}