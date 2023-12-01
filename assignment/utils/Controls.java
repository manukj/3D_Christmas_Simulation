package utils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * Controls
 */
public class Controls {

    public JPanel panel;

    public Controls(ClickCallback callback) {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // main light controls
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

        // spot light controls
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

        // Alien 1 animation controls
        JLabel alien1Label = new JLabel("Animate Alien 1");
        JButton alien1IncreaseRockSpeed = new JButton("Increase Rock Speed");
        alien1IncreaseRockSpeed.addActionListener(e -> {
            callback.increaseRockSpeed(1);
        });
        JButton alien1DecreaseRockSpeed = new JButton("Decrease Rock Speed");
        alien1DecreaseRockSpeed.addActionListener(e -> {
            callback.decreaseRockSpeed(1);
        });
        JButton alien1IncreaseRollSpeed = new JButton("Increase Roll Speed");
        alien1IncreaseRollSpeed.addActionListener(e -> {
            callback.increaseRollSpeed(1);
        });
        JButton alien1DecreaseRollSpeed = new JButton("Decrease Roll Speed");
        alien1DecreaseRollSpeed.addActionListener(e -> {
            callback.decreaseRollSpeed(1);
        });
        JButton alien1ChangeRollDirection = new JButton("Change Roll Direction");
        alien1ChangeRollDirection.addActionListener(e -> {
            callback.changeRollDirection(1);
        });

        JButton toggleRockAnimation = new JButton("Toggle Rock Animation");
        toggleRockAnimation.addActionListener(e -> {
            callback.toggleRockAnimation(1);
        });
        JButton toggleRollAnimation = new JButton("Toggle Roll Animation");
        toggleRollAnimation.addActionListener(e -> {
            callback.toggleRollAnimation(1);
        });
        panel.add(alien1Label);
        panel.add(alien1IncreaseRockSpeed);
        panel.add(alien1DecreaseRockSpeed);
        panel.add(alien1IncreaseRollSpeed);
        panel.add(alien1DecreaseRollSpeed);
        panel.add(alien1ChangeRollDirection);
        panel.add(toggleRockAnimation);
        panel.add(toggleRollAnimation);

    }
}