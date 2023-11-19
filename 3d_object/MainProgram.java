import camera.*;
/**
 * MainProgram
 */
public class MainProgram {

    public static void main(String[] args) {
        Camera camera = new Camera(Camera.DEFAULT_POSITION, Camera.DEFAULT_TARGET, Camera.DEFAULT_UP);
        CreateWindow window = new CreateWindow("3D Object", new V01_GLEventListener(camera), camera);
        window.pack();
        window.setVisible(true);
    }
}