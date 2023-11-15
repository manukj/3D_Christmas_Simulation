/**
 * MainProgram
 */
public class MainProgram {

    public static void main(String[] args) {
        CreateWindow window = new CreateWindow("3D Object", new V01_GLEventListener());
        window.pack();
        window.setVisible(true);
    }
}