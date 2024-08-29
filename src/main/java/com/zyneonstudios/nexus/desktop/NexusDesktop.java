package com.zyneonstudios.nexus.desktop;

import com.formdev.flatlaf.FlatDarkLaf;
import com.zyneonstudios.nexus.desktop.frame.NexusFrame;
import com.zyneonstudios.nexus.desktop.frame.web.NexusWebFrame;
import com.zyneonstudios.nexus.desktop.frame.web.NexusWebSetup;
import com.zyneonstudios.nexus.desktop.logger.NexusLogger;

import javax.swing.*;
import java.awt.*;

public class NexusDesktop {

    private final static NexusLogger logger = new NexusLogger("BASE");

    public static void main(String[] args) {
        NexusDesktop.init();
        NexusWebSetup setup = new NexusWebSetup("jcef/");
        setup.enableCookies(true);
        setup.enableCache(true);
        setup.setup();

        NexusWebFrame frame1 = new NexusWebFrame(setup.getWebClient(),"https://www.google.com");
        frame1.setTitleColors(Color.black, Color.green);
        NexusWebFrame frame2 = new NexusWebFrame(setup.getWebClient(),"https://drive.zyneonstudios.com");

        frame1.setVisible(true);
        frame2.setVisible(true);

        NexusFrame frame = new NexusFrame();
        frame.setVisible(true);
    }

    public static boolean init() {
        logger.dbg("Initializing...");
        try {
            FlatDarkLaf.setup();
            UIManager.setLookAndFeel(new FlatDarkLaf());
            logger.dbg("Initialed!");
            return true;
        } catch (Exception e) {
            logger.err("Failed to initialize!");
            logger.err(e.getMessage());
            return false;
        }
    }
}