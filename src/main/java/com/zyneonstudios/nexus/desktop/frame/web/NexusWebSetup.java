package com.zyneonstudios.nexus.desktop.frame.web;

import com.zyneonstudios.nexus.desktop.NexusDesktop;
import me.friwi.jcefmaven.CefAppBuilder;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefBeforeDownloadCallback;
import org.cef.callback.CefDownloadItem;
import org.cef.handler.CefDownloadHandlerAdapter;
import org.cef.handler.CefLifeSpanHandlerAdapter;

import java.awt.*;
import java.io.File;
import java.net.URI;

public class NexusWebSetup {

    private boolean setupFinished = false;

    private final CefAppBuilder builder = new CefAppBuilder();
    private final File libraryDirectory;

    private CefApp webApp = null;
    private CefClient webClient = null;

    public NexusWebSetup(String libraryPath) {
        if(!libraryPath.endsWith("/")&&!libraryPath.endsWith("\\")) {
            libraryPath += "/";
        }
        libraryDirectory = new File(libraryPath);
        if(!libraryDirectory.exists()) {
            if(!libraryDirectory.mkdirs()) {
                throw new NullPointerException("Failed to create install directory!");
            }
        }
        builder.setInstallDir(libraryDirectory);
        builder.getCefSettings().log_severity = CefSettings.LogSeverity.LOGSEVERITY_DISABLE;
        builder.getCefSettings().windowless_rendering_enabled = false;
    }

    public File getLibraryDirectory() {
        return libraryDirectory;
    }

    public CefAppBuilder getBuilder() {
        return builder;
    }

    public CefApp getWebApp() {
        return webApp;
    }

    public CefClient getWebClient() {
        return webClient;
    }

    public void enableCache(boolean enable) {
        if(enable) {
            builder.getCefSettings().cache_path = libraryDirectory.getAbsolutePath() + "/cache/";
            return;
        }
        builder.getCefSettings().cache_path = null;
    }

    public void enableCookies(boolean enable) {
        builder.getCefSettings().persist_session_cookies = enable;
    }

    public boolean setup() {
        if(!setupFinished) {
            setupFinished = true;
            try {
                webApp = builder.build();
                webClient = webApp.createClient();
                webClient.addDownloadHandler(new CefDownloadHandlerAdapter() {
                    @Override
                    public boolean onBeforeDownload(CefBrowser browser, CefDownloadItem downloadItem, String suggestedName, CefBeforeDownloadCallback callback) {
                        try {
                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().browse(new URI(downloadItem.getURL()));
                                } catch (Exception ignore) {
                                }
                            }
                        } catch (Exception ignore) {
                        }
                        return true;
                    }
                });
                webClient.addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
                    @Override
                    public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
                        try {
                            if (Desktop.isDesktopSupported()) {
                                Desktop.getDesktop().browse(new URI(target_url));
                            } else {
                                browser.loadURL(target_url);
                            }
                        } catch (Exception e) {
                            browser.loadURL(target_url);
                        }
                        return true;
                    }
                });
                return true;
            } catch (Exception e) {
                NexusDesktop.getLogger().err("Failed to finish the web setup: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean isSetupFinished() {
        return setupFinished;
    }
}