package com.zyneonstudios.nexus.desktop.frame.web;

import com.zyneonstudios.nexus.desktop.NexusDesktop;
import com.zyneonstudios.nexus.desktop.events.AsyncWebFrameConnectorEvent;
import com.zyneonstudios.nexus.desktop.events.WebFrameConnectorEvent;
import com.zyneonstudios.nexus.desktop.frame.NexusFrame;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefFocusHandlerAdapter;

import java.awt.*;

public class NexusWebFrame extends NexusFrame {

    private final CefBrowser browser;
    private boolean browserFocus;
    private AsyncWebFrameConnectorEvent asyncWebFrameConnectorEvent;
    private WebFrameConnectorEvent webFrameConnectorEvent;

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
        webClient.addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public boolean onConsoleMessage(CefBrowser browser, CefSettings.LogSeverity level, String message, String source, int line) {
                if (message.startsWith("[CONNECTOR] async.")) {
                    String request = message.replace("[CONNECTOR] async.", "");
                    if(asyncWebFrameConnectorEvent!=null) {
                        asyncWebFrameConnectorEvent.resolveMessage(request);
                    } else if(webFrameConnectorEvent!=null) {
                        webFrameConnectorEvent.resolveMessage(request);
                    }
                } else if (message.startsWith("[CONNECTOR] ")) {
                    String request = message.replace("[CONNECTOR] ", "");
                    if(webFrameConnectorEvent!=null) {
                        webFrameConnectorEvent.resolveMessage(request);
                    } else if(asyncWebFrameConnectorEvent!=null) {
                        asyncWebFrameConnectorEvent.resolveMessage(request);
                    }
                } else if (message.startsWith("[LOG] ")) {
                    NexusDesktop.getLogger().log(message.replace("[LOG] ","[FRAME] "));
                } else if (message.startsWith("[ERR] ")) {
                    NexusDesktop.getLogger().err(message.replace("[ERR] ","[FRAME] "));
                } else if (message.startsWith("[DEB] ")) {
                    NexusDesktop.getLogger().dbg(message.replace("[DEB] ","[FRAME] "));
                } else {
                    NexusDesktop.getLogger().dbg("[FRAME] (Console) "+message);
                }
                return super.onConsoleMessage(browser, level, message, source, line);
            }
        });
        getContentPane().add(browser.getUIComponent(),BorderLayout.CENTER);
        if(!titlebar) {
            setUndecorated(true);
        }
        pack();
    }

    public AsyncWebFrameConnectorEvent getAsyncWebFrameConnectorEvent() {
        return asyncWebFrameConnectorEvent;
    }

    public WebFrameConnectorEvent getWebFrameConnectorEvent() {
        return webFrameConnectorEvent;
    }

    public void setAsyncWebFrameConnectorEvent(AsyncWebFrameConnectorEvent asyncWebFrameConnectorEvent) {
        this.asyncWebFrameConnectorEvent = asyncWebFrameConnectorEvent;
    }

    public void setWebFrameConnectorEvent(WebFrameConnectorEvent webFrameConnectorEvent) {
        this.webFrameConnectorEvent = webFrameConnectorEvent;
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