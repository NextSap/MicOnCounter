package com.nextsap.counter.graphics.frames;

import com.nextsap.counter.Settings;
import com.nextsap.counter.customer.CustomParty;
import com.nextsap.counter.graphics.FrameManager;
import com.nextsap.counter.graphics.frames.result.ResultFrame;
import com.nextsap.counter.loader.Loader;
import com.nextsap.counter.utils.LbBlink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DefaultFrame extends FrameManager {


    private final JButton startButton;
    private final JLabel blinkLabel;
    private final JButton endButton;
    private long start;
    private Timer timer;

    public DefaultFrame() {
        this.setTitle("Mic!ON - Game Admin");
        this.setWidth(550);
        this.setHeight(420);
        this.initialize();
        this.setMain(true);

        this.getPanel().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(320, 190));
        titlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "v1.0"));

        ImageIcon logo = new ImageIcon(Settings.getIconPath());
        JLabel titleLabel = new JLabel(logo);
        titlePanel.add(titleLabel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.getPanel().add(titlePanel, constraints);

        // Spacer
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(0, 20));

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.getPanel().add(spacerPanel, constraints);

        // Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(170, 100));

        this.startButton = new JButton("Lancement de la partie");
        this.startButton.setBorderPainted(false);
        this.startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.startButton.setBackground(new Color(64, 226, 64));
        this.startButton.addActionListener(this::gameStartClickEvent);
        buttonsPanel.add(this.startButton);

        this.blinkLabel = new JLabel("");
        buttonsPanel.add(blinkLabel);

        this.endButton = new JButton("Fin de la partie");
        this.endButton.setBorderPainted(false);
        this.endButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        this.endButton.setBackground(new Color(255, 60, 60));
        this.endButton.setEnabled(false);
        this.endButton.addActionListener(this::gameEndClickEvent);
        buttonsPanel.add(this.endButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.getPanel().add(buttonsPanel, constraints);

        // About
        JPanel aboutPanel = new JPanel();
        aboutPanel.setPreferredSize(new Dimension(130, 50));

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
        this.blinkLabel.setText("Monitoring en cours...");
        this.blinkLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        this.timer = new Timer(500, new LbBlink(blinkLabel));
        this.timer.start();
        this.endButton.setEnabled(true);
    }

    public void gameEndClickEvent(ActionEvent actionEvent) {
        long end = System.currentTimeMillis();
        CustomParty customParty = Loader.parser(this.start, end);
        if (Loader.partyFinished) {
            gameEnd(customParty);
        } else {
            int rep = this.showQuestionDialog("Attention !", "Voulez-vous vraiment terminer la partie ? Le programme pense que la partie n'est pas terminée.");
            if (rep == 0)
                gameEnd(customParty);
        }
    }

    private void gameEnd(CustomParty customParty) {
        this.startButton.setEnabled(true);
        this.endButton.setEnabled(false);
        this.blinkLabel.setText("");
        this.timer.stop();

        new ResultFrame(customParty).show();
    }
}