package com.nextsap.counter.graphics.frames;

import com.nextsap.counter.graphics.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DefaultFrame extends FrameManager {

    ImageIcon icon = new ImageIcon("C:\\Users\\NextSap\\AppData\\Roaming\\MicOnCounter\\icon.png");
    private JLabel infoLabel;
    private JButton startButton;
    private JButton endButton;
    private long start;
    private long end;

    public DefaultFrame() {
        this.setTitle("Mic!ON - Game Admin");
        this.setWidth(460);
        this.setHeight(300);
        this.initialize();
        this.setMain(true);

        this.getPanel().setLayout(new GridBagLayout());
        this.getPanel().setBackground(new Color(152, 152, 152));
        this.setFavicon(icon.getImage());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(310, 60));
        titlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "v1.0"));
        titlePanel.setBackground(new Color(152, 152, 152));

        JLabel titleLabel = new JLabel("Mic!ON - Game Admin");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titlePanel.add(titleLabel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.getPanel().add(titlePanel, constraints);

        // Spacer
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        spacerPanel.setBackground(new Color(152, 152, 152));

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.getPanel().add(spacerPanel, constraints);

        // Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(170, 100));
        buttonsPanel.setBackground(new Color(152, 152, 152));

        this.startButton = new JButton("Lancement de la partie");
        this.startButton.setBorderPainted(false);
        this.startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.startButton.setBackground(new Color(101, 233, 87));
        this.startButton.addActionListener(this::gameStartClickEvent);
        buttonsPanel.add(this.startButton);

        this.endButton = new JButton("Fin de la partie");
        this.endButton.setBorderPainted(false);
        this.endButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.endButton.setBackground(new Color(255, 70, 70));
        this.endButton.setEnabled(false);
        this.endButton.addActionListener(this::gameEndClickEvent);
        buttonsPanel.add(this.endButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.getPanel().add(buttonsPanel, constraints);

        // About
        JPanel aboutPanel = new JPanel();
        aboutPanel.setPreferredSize(new Dimension(130, 50));
        aboutPanel.setBackground(new Color(152, 152, 152));

        JLabel micLabel = new JLabel("For Mic!ON by NextSap");
        micLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        aboutPanel.add(micLabel);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.getPanel().add(aboutPanel, constraints);
    }

    public void gameStartClickEvent(ActionEvent event) {
        this.start = System.currentTimeMillis();
        this.startButton.setEnabled(false);
    }

    public void gameEndClickEvent(ActionEvent actionEvent) {
        this.end = System.currentTimeMillis();
        this.endButton.setEnabled(true);


    }

}