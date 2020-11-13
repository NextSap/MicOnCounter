package com.nextsap.counter.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * @author Fallancy <3
 */

public class FrameManager {

    private JFrame frame;
    private JPanel panel;

    private String title;
    private Image favicon;
    private int width;
    private int height;
    private boolean resizable;
    private boolean main;

    public FrameManager() {
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.title = null;
        this.favicon = null;
        this.width = 300;
        this.height = 200;
        this.resizable = false;
        this.main = false;
    }

    public void initialize() {
        if (this.title != null) this.frame.setTitle(this.title);
        if (this.favicon != null) this.frame.setIconImage(this.favicon);

        this.frame.setSize(this.width, this.height);
        this.frame.setResizable(this.resizable);
        this.frame.setContentPane(this.panel);
    }

    public void show() {
        this.frame.setLocationRelativeTo(null);
        if (this.main) this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        else {
            this.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }

        this.frame.setVisible(true);
    }

    public void hide() {
        this.frame.setVisible(false);
    }

    public void showErrorDialog(String message) {
        showErrorDialog("Une erreur est survenue !", message);
    }

    public void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(this.getFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showInformationDialog(String title, String message) {
        JOptionPane.showMessageDialog(this.getFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public int showQuestionDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(this.getFrame(), message, title, JOptionPane.YES_NO_OPTION);
    }

    public String showInputDialog(String title, String message) {
        return JOptionPane.showInputDialog(this.getFrame(), message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getFavicon() {
        return favicon;
    }

    public void setFavicon(String path) {
        ImageIcon favicon = new ImageIcon(path);
        this.setFavicon(favicon.getImage());
    }

    public void setFavicon(Image favicon) {
        this.favicon = favicon;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
