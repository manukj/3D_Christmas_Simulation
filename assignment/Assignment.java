
/**
 * Assignment
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

public class Assignment extends JFrame {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static final String TITLE = "Assignment";
    private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private GLCanvas canvas;
    private GLEventListener glEventListener;
    private final FPSAnimator animator;

    public static void main(String[] args) {
        Assignment window = new Assignment();
        // window.getContentPane().setPreferredSize(dimension);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.pack();
        window.setVisible(true);
        window.canvas.requestFocusInWindow();
    }

    public Assignment() {
        super(TITLE);
        GLCapabilities glCapabilities = new GLCapabilities(GLProfile.get(GLProfile.GL3));
        canvas = new GLCanvas(glCapabilities);
        Camera camera = new Camera(Camera.DEFAULT_POSITION, Camera.DEFAULT_TARGET, Camera.DEFAULT_UP);

        glEventListener = new AssignmentGLEventListener(camera);
        canvas.addGLEventListener(glEventListener);

        canvas.addMouseMotionListener(new MouseInput(camera));
        canvas.addKeyListener(new KeyboardInput(camera));

        buildButtons();

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

    private void buildButtons() {
        ClickCallback callback = (ClickCallback) glEventListener;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel mainLightLabel = new JLabel("Main Light");
        JButton toggleMainLight = new JButton("Toggle Main light");
        toggleMainLight.addActionListener(e -> {
            callback.toggleMainLight();
        });
        JButton dimMainLight = new JButton("Dim Main light");
        dimMainLight.addActionListener(e -> {
            callback.dimMainLight();
        });
        JButton brightenMainLight = new JButton("Brighten Main light");
        brightenMainLight.addActionListener(e -> {
            callback.brightenMainLight();
        });
        panel.add(mainLightLabel);
        panel.add(toggleMainLight);
        panel.add(dimMainLight);
        panel.add(brightenMainLight);

        JLabel spotLightLabel = new JLabel("SpotLight");
        JButton toggleSpotLight = new JButton("Toggle Spot light");
        toggleSpotLight.addActionListener(e -> {
            callback.toggleSpotLight();
        });
        JButton dimSpotLight = new JButton("Dim Spot light");
        dimSpotLight.addActionListener(e -> {
            callback.dimSpotLight();
        });
        JButton brightenSpotLight = new JButton("Brighten Spot light");
        brightenSpotLight.addActionListener(e -> {
            callback.brightenSpotLight();
        });
        panel.add(spotLightLabel);
        panel.add(toggleSpotLight);
        panel.add(dimSpotLight);
        panel.add(brightenSpotLight);

        this.add(panel, BorderLayout.EAST);
    }
}