package com.nextsap.counter.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A implemented {@link ActionListener} class
 */
public class LbBlink implements ActionListener {

    // Define attributes
    private final JLabel label;
    private final Color cor1 = new Color(54, 166, 76);
    private final Color cor2 = Color.RED;
    private int count;

    /**
     * {@link LbBlink} Constructor
     *
     * @param label is the {@link JLabel}
     */
    public LbBlink(JLabel label) {
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (count % 2 == 0)
            label.setForeground(cor1);
        else
            label.setForeground(cor2);
        count++;
    }
}
