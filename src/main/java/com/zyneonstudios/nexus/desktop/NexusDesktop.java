package com.zyneonstudios.nexus.desktop;

import com.formdev.flatlaf.FlatDarkLaf;
import com.zyneonstudios.nexus.utilities.NexusUtilities;
import com.zyneonstudios.nexus.utilities.logger.NexusLogger;

import javax.swing.*;

public class NexusDesktop {

    private static final NexusLogger logger = NexusUtilities.getLogger();

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

    public static NexusLogger getLogger() {
        return logger;
    }
}