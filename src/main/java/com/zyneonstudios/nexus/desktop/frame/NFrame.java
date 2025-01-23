package com.zyneonstudios.nexus.desktop.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NFrame extends JFrame {

    public NFrame() {
        initialize();
        setTitle("New NEXUS Frame");
    }

    public NFrame(String title) {
        initialize();
        setTitle(title);
    }

    public NFrame(boolean isVisible) {
        initialize();
        setTitle("New NEXUS Frame");
        setVisible(isVisible);
    }

    public NFrame(String title, boolean isVisible) {
        initialize();
        setTitle(title);
        setVisible(isVisible);
    }

    private void initialize() {
        setMinimumSize(new Dimension(640,360));
        setLocationRelativeTo(null);
        try {
            ArrayList<Image> icons = new ArrayList<>();
            icons.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icons/nexus/16.png"))));
            icons.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icons/nexus/32.png"))));
            icons.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icons/nexus/64.png"))));
            icons.add(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icons/nexus/128.png"))));
            setTitlebar(getTitle(), Color.black, Color.white, icons);
        } catch (Exception e) {
            setTitlebar(getTitle(), Color.black, Color.white, (Image)null);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void setTitleColors(Color background, Color foreground) {
        getRootPane().putClientProperty("JRootPane.titleBarBackground", background);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", foreground);
    }

    public void setTitlebar(String title, Color background, Color foreground, Image icon) {
        setTitleColors(background,foreground);
        setTitle(title);
        setIconImage(icon);
    }

    public void setTitlebar(String title, Color background, Color foreground, List<Image> icons) {
        setTitleColors(background,foreground);
        setTitle(title);
        setIconImages(icons);
    }

    public JFrame getAsJFrame() {
        return this;
    }
}