package com.zyneonstudios.nexus.desktop.frame.nexus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class NexusFrame extends JFrame {

    private JLabel title = new JLabel();
    private JButton close = new JButton("X");
    private JButton maximize = new JButton("O");
    private JButton minimize = new JButton("-");

    private int x = 0;
    private int y = 0;

    JPanel titlebar = new JPanel();
    JPanel mainPane = new JPanel();

    public NexusFrame() {
        setUndecorated(true);
        Dimension defaultSize = new Dimension(1280, 720);
        setSize(defaultSize);
        setLocationRelativeTo(null);

        new NexusResizer(this);
        setupTitlebar();

        getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));
    }

    private void setupTitlebar() {
        titlebar.setLayout(new BorderLayout());
        titlebar.setBackground(Color.black);
        titlebar.setSize(getWidth(), 1);
        title.setFont(new Font("Arial", Font.PLAIN, 14));
        title.setForeground(Color.white);
        title.setBorder(BorderFactory.createEmptyBorder(1,1,0,0));
        titlebar.add(title, BorderLayout.WEST);

        close.setBackground(Color.black);
        close.setFont(new Font("Arial", Font.PLAIN, 16));
        close.setForeground(Color.white);
        close.setBorderPainted(false);
        close.addActionListener(e -> dispose());

        maximize.setBackground(Color.black);
        maximize.setFont(new Font("Arial", Font.PLAIN, 16));
        maximize.setBorderPainted(false);
        maximize.setForeground(Color.white);
        maximize.addActionListener(e -> setSize(Toolkit.getDefaultToolkit().getScreenSize()));

        minimize.setBackground(Color.black);
        minimize.setFont(new Font("Monospaced", Font.PLAIN, 16));
        minimize.setBorderPainted(false);
        minimize.setForeground(Color.white);
        minimize.addActionListener(e -> setState(ICONIFIED));

        JPanel windowControls = new JPanel(new BorderLayout());
        windowControls.setBackground(Color.BLACK);
        windowControls.add(minimize, BorderLayout.WEST);
        //windowControls.add(maximize, BorderLayout.EAST);

        JPanel buttons = new JPanel(new BorderLayout());
        buttons.setBackground(Color.black);
        buttons.add(windowControls, BorderLayout.WEST);
        buttons.add(close, BorderLayout.EAST);

        titlebar.add(buttons, BorderLayout.EAST);
        add(titlebar, BorderLayout.NORTH);

        titlebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        titlebar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
            }
        });
    }

    public JPanel getTitlebar() {
        return titlebar;
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(" "+title);
        super.setTitle(title);
    }

    public JFrame getAsJFrame() {
        return this;
    }

    public JButton getCloseButton() {
        return close;
    }

    public JButton getMaximizeButton() {
        return maximize;
    }

    public JButton getMinimizeButton() {
        return minimize;
    }

    public JLabel getLabel() {
        return title;
    }
}