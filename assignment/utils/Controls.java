package utils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * I declare that this code is my own work
 * Author: Manu Kenchappa Junjanna
 */
public class Controls {

    public JPanel panel;

    public Controls(ClickCallback callback) {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // main light controls
        mainLightControls(callback, 0);
        mainLightControls(callback, 1);

        // spot light controls
        spotLightControls(callback);

        // Alien 1 animation controls
        addAlienControls(callback, 1);
        // Alien 2 animation controls
        addAlienControls(callback, 2);
        JLabel endJLabel = new JLabel("----------------------------");
        panel.add(endJLabel);
    }

    void addAlienControls(ClickCallback callback, int alienIndex) {
        JLabel alienLabel = new JLabel("------- Animate Alien " + alienIndex + "-------");

        // rock animation controls
        JLabel rockLabel = new JLabel("**** Rock Animation  ****");
        JButton alienIncreaseRockSpeed = new JButton("+ Speed");
        alienIncreaseRockSpeed.addActionListener(e -> {
            callback.increaseRockSpeed(alienIndex);
        });
        JButton alienDecreaseRockSpeed = new JButton("- Speed");
        alienDecreaseRockSpeed.addActionListener(e -> {
            callback.decreaseRockSpeed(alienIndex);
        });
        JPanel rockControlPanel = new JPanel();
        rockControlPanel.setLayout(new BoxLayout(rockControlPanel, BoxLayout.X_AXIS));
        rockControlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rockControlPanel.add(alienIncreaseRockSpeed);
        rockControlPanel.add(alienDecreaseRockSpeed);
        JButton toggleRockAnimation = new JButton("Toggle Rock Animation");
        toggleRockAnimation.addActionListener(e -> {
            callback.toggleRockAnimation(alienIndex);
        });

        // roll animation controls
        JLabel rollLabel = new JLabel("**** Roll Animation Speed ****");
        JButton alienIncreaseRollSpeed = new JButton("+ Speed");
        alienIncreaseRollSpeed.addActionListener(e -> {
            callback.increaseRollSpeed(alienIndex);
        });
        JButton alienDecreaseRollSpeed = new JButton("- Speed");
        alienDecreaseRollSpeed.addActionListener(e -> {
            callback.decreaseRollSpeed(alienIndex);
        });
        JPanel rollControlPanel = new JPanel();
        rollControlPanel.setLayout(new BoxLayout(rollControlPanel, BoxLayout.X_AXIS));
        rollControlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        rollControlPanel.add(alienIncreaseRollSpeed);
        rollControlPanel.add(alienDecreaseRollSpeed);
        JButton toggleRollAnimation = new JButton("Toggle Roll Animation");
        toggleRollAnimation.addActionListener(e -> {
            callback.toggleRollAnimation(alienIndex);
        });
        JButton alienChangeRollDirection = new JButton("Change Roll Direction");
        alienChangeRollDirection.addActionListener(e -> {
            callback.changeRollDirection(alienIndex);
        });

        panel.add(alienLabel);

        panel.add(rockLabel);
        panel.add(toggleRockAnimation);
        panel.add(rockControlPanel);

        panel.add(rollLabel);
        panel.add(toggleRollAnimation);
        panel.add(alienChangeRollDirection);
        panel.add(rollControlPanel);

    }

    void mainLightControls(ClickCallback callback, int lightIndex) {
        JLabel mainLightLabel = new JLabel("------- Main Light " + (lightIndex + 1) + " -------");
        JButton toggleMainLight = new JButton("Toggle light");
        toggleMainLight.addActionListener(e -> {
            callback.toggleMainLight(lightIndex);
        });
        JButton dimMainLight = new JButton("Dim light");
        dimMainLight.addActionListener(e -> {
            callback.dimMainLight(lightIndex);
        });
        JButton brightenMainLight = new JButton("Brighten light");
        brightenMainLight.addActionListener(e -> {
            callback.brightenMainLight(lightIndex);
        });
        panel.add(mainLightLabel);
        panel.add(toggleMainLight);
        panel.add(dimMainLight);
        panel.add(brightenMainLight);
    }

    void spotLightControls(ClickCallback callback) {
        JLabel spotLightLabel = new JLabel("--------- SpotLight ---------");
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

    }
}