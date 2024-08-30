package com.zyneonstudios.nexus.desktop.frame.web;

import com.zyneonstudios.nexus.desktop.frame.NexusFrame;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandlerAdapter;

import java.awt.*;

public class NexusWebFrame extends NexusFrame {

    private final CefBrowser browser;
    private boolean browserFocus;

    public NexusWebFrame(CefClient webClient, String url, boolean titlebar) {
        super();
        setTitle("New NEXUS Web Frame");
        browser = webClient.createBrowser(url, false, false);
        webClient.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFocus = false;
            }

            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFocus) return;
                browserFocus = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }
        });
        getContentPane().add(browser.getUIComponent(),BorderLayout.CENTER);
        if(!titlebar) {
            setUndecorated(true);
        }
        pack();
    }

    public CefBrowser getBrowser() {
        return browser;
    }

    public boolean isBrowserFocussed() {
        return browserFocus;
    }

    public void executeJavaScript(String... scripts) {
        for (String script : scripts) {
            browser.executeJavaScript(script, browser.getURL(), 0);
        }
    }
}