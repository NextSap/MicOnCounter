package com.nextsap.counter.graphics.frames;

import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.graphics.FrameManager;
import com.nextsap.counter.utils.PlayerFCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class ConfigFrame extends FrameManager {

    private final JTextArea area = new JTextArea();
    private final CustomGame customGame;

    public ConfigFrame(CustomGame customGame) {
        this.customGame = customGame;

        this.setTitle("Mic!ON - Game Admin - Config");
        this.setWidth(280);
        this.setHeight(350);
        this.initialize();

        this.getPanel().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        JPanel defaultPanel = new JPanel();
        defaultPanel.setPreferredSize(new Dimension(250, 290));

        this.area.setPreferredSize(new Dimension(200, 240));
        this.area.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        defaultPanel.add(this.area);

        JButton validateButton = new JButton("Valider");
        validateButton.setBorderPainted(false);
        validateButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        validateButton.setBackground(new Color(255, 196, 0));
        validateButton.addActionListener(this::getPlayers);
        defaultPanel.add(validateButton);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.getPanel().add(defaultPanel, constraints);
    }

    private boolean checkPlayers(List<String> players) {
        PlayerFCUtils playerFCUtils = new PlayerFCUtils(this, players);
        return playerFCUtils.isError();
    }

    private void getPlayers(ActionEvent event) {
        List<String> lines = Arrays.asList(this.area.getText().toLowerCase().split("\n"));

        for (String s : lines)
            System.out.println(s);

        if (checkPlayers(lines)) {
            customGame.setPlayers(lines);
            hide();
        }
    }
}
