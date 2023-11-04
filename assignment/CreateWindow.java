import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class CreateWindow extends JFrame {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private GLCanvas canvas;
    // private GLEventListener glEventListener;
    private final FPSAnimator animator;


    public CreateWindow(String title) {
        super(title);
        GLCapabilities glCapabilities = new GLCapabilities(GLProfile.get(GLProfile.GL3));
        canvas = new GLCanvas(glCapabilities);
        getContentPane().add(canvas, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animator.stop();
                remove(canvas);
                dispose();
                System.exit(0);
            }
        });
        animator = new FPSAnimator(canvas, 60); 
        animator.start(); 
    }
}