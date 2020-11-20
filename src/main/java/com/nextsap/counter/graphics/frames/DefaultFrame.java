package com.nextsap.counter.graphics.frames;

import com.google.gson.Gson;
import com.nextsap.counter.Settings;
import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.graphics.FrameManager;
import com.nextsap.counter.graphics.frames.result.ResultFrame;
import com.nextsap.counter.loader.Loader;
import com.nextsap.counter.logger.FileType;
import com.nextsap.counter.logger.Log;
import com.nextsap.counter.logger.LogType;
import com.nextsap.counter.utils.LbBlink;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * An extended {@link FrameManager} class
 */
public class DefaultFrame extends FrameManager {

    // Define attributes
    private final JButton startButton;
    private final JLabel blinkLabel;
    private final JButton endButton;
    private long start;
    private Timer timer;

    /**
     * {@link DefaultFrame} Constructor
     */
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
        titlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), Settings.getVersion()));

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

        JLabel aboutLabel = new JLabel(Settings.getAbout());
        aboutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        aboutPanel.add(aboutLabel);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.getPanel().add(aboutPanel, constraints);
    }

    /**
     * When click on the start button
     *
     * @param event is the click event
     */
    public void gameStartClickEvent(ActionEvent event) {
        this.start = System.currentTimeMillis();
        this.startButton.setEnabled(false);
        this.blinkLabel.setText("Monitoring en cours...");
        this.blinkLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        this.timer = new Timer(500, new LbBlink(blinkLabel));
        this.timer.start();
        this.endButton.setEnabled(true);

        Log.create(LogType.INFORMATION, FileType.CURRENT, "Game started");
        Log.create(LogType.INFORMATION, FileType.MATCH, "Game started");
    }

    /**
     * When click on the end button
     *
     * @param event is the click event
     */
    public void gameEndClickEvent(ActionEvent event) {
        CustomGame customParty = Loader.parser(this.start, System.currentTimeMillis());
        if (Loader.partyFinished) {
            gameEnd(customParty);
        } else {
            int rep = this.showQuestionDialog("Attention !", "Voulez-vous vraiment terminer la partie ? Le programme pense que la partie n'est pas terminée.");
            if (rep == 0)
                gameEnd(customParty);
        }
    }

    /**
     * Procedure when the game is ended
     *
     * @param customGame is the current game
     */
    private void gameEnd(CustomGame customGame) {
        this.startButton.setEnabled(true);
        this.endButton.setEnabled(false);
        this.blinkLabel.setText("");
        this.timer.stop();

        Log.create(LogType.INFORMATION, FileType.CURRENT, "Game ended");
        Log.create(LogType.INFORMATION, FileType.MATCH, "Game ended");

        new ResultFrame(customGame).show();
        Log.create(LogType.INFORMATION, FileType.MATCH, new Gson().toJson(customGame));
    }
}