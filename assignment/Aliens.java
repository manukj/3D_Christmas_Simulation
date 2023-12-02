
/**
 * Aliens
 */
import utils.*;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Aliens extends JFrame {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static final String TITLE = "Aliens";
    private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private GLCanvas canvas;
    private GLEventListener glEventListener;
    private final FPSAnimator animator;

    public static void main(String[] args) {
        Aliens window = new Aliens();
        // window.getContentPane().setPreferredSize(dimension);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.pack();
        window.setVisible(true);
        window.canvas.requestFocusInWindow();
    }

    public Aliens() {
        super(TITLE);
        GLCapabilities glCapabilities = new GLCapabilities(GLProfile.get(GLProfile.GL3));
        canvas = new GLCanvas(glCapabilities);
        Camera camera = new Camera(Camera.DEFAULT_POSITION, Camera.DEFAULT_TARGET, Camera.DEFAULT_UP);

        glEventListener = new AliensGLEventListener(camera);
        canvas.addGLEventListener(glEventListener);

        canvas.addMouseMotionListener(new MouseInput(camera));
        canvas.addKeyListener(new KeyboardInput(camera));

        addControls();

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

    private void addControls() {
        Controls controls = new Controls((ClickCallback) glEventListener);
        this.add(controls.panel, BorderLayout.EAST);
    }
}