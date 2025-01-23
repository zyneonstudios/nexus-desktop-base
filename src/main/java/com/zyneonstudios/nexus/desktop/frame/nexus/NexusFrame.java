package com.zyneonstudios.nexus.desktop.frame.nexus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class NexusFrame extends JFrame {

    private JLabel title = new JLabel();

    private int x = 0;
    private int y = 0;
    private int initialWidth;
    private int initialHeight;
    private int resizeDirection = 0;

    private static final int TOP = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static final int TOP_LEFT = 5;
    private static final int TOP_RIGHT = 6;
    private static final int BOTTOM_LEFT = 7;
    private static final int BOTTOM_RIGHT = 8;

    JPanel titlebar = new JPanel();
    JPanel mainPane = new JPanel();

    public NexusFrame() {
        setUndecorated(true);
        Dimension defaultSize = new Dimension(1280, 720);
        setSize(defaultSize);
        setLocationRelativeTo(null);

        new NexusResizer(this);
        setupTitlebar();

        mainPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                initialWidth = getWidth();
                initialHeight = getHeight();
                resizeDirection = getResizeDirection(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                resizeDirection = 0;
            }
        });

        mainPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizeDirection != 0) {
                    int newWidth = initialWidth + (e.getX() - x);
                    int newHeight = initialHeight + (e.getY() - y);

                    // Ensure the frame size doesn't exceed its minimum or maximum dimensions
                    newWidth = Math.max(newWidth, getMinimumSize().width);
                    newWidth = Math.min(newWidth, getMaximumSize().width);
                    newHeight = Math.max(newHeight, getMinimumSize().height);
                    newHeight = Math.min(newHeight, getMaximumSize().height);

                    setSize(newWidth, newHeight);
                }
            }
        });

        getRootPane().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1,true));
    }

    private void setupTitlebar() {
        titlebar.setLayout(new BorderLayout());
        titlebar.setBackground(Color.black);
        titlebar.setSize(getWidth(), 1);
        title.setFont(new Font("Arial", Font.PLAIN, 14));
        title.setBorder(BorderFactory.createEmptyBorder(1,1,0,0));
        titlebar.add(title, BorderLayout.WEST);

        JButton close = new JButton("X");
        close.setBackground(Color.black);
        close.setFont(new Font("Arial", Font.PLAIN, 16));
        close.setBorderPainted(false);
        close.addActionListener(e -> dispose());

        JButton maximize = new JButton("O");
        maximize.setBackground(Color.black);
        maximize.setFont(new Font("Arial", Font.PLAIN, 16));
        maximize.setBorderPainted(false);
        maximize.addActionListener(e -> setSize(Toolkit.getDefaultToolkit().getScreenSize()));

        JButton minimize = new JButton("-");
        minimize.setBackground(Color.black);
        minimize.setFont(new Font("Monospaced", Font.PLAIN, 16));
        minimize.setBorderPainted(false);
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

    private int getResizeDirection(int x, int y) {
        int width = getWidth();
        int height = getHeight();

        if (x < 10) {
            if (y < 10) {
                return TOP_LEFT;
            } else if (y > height - 10) {
                return BOTTOM_LEFT;
            } else {
                return LEFT;
            }
        } else if (x > width - 10) {
            if (y < 10) {
                return TOP_RIGHT;
            } else if (y > height - 10) {
                return BOTTOM_RIGHT;
            } else {
                return RIGHT;
            }
        } else if (y < 10) {
            return TOP;
        } else if (y > height - 10) {
            return BOTTOM;
        } else {
            return 0;
        }
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
}