package com.zyneonstudios.nexus.desktop.frame.web;

import com.zyneonstudios.nexus.desktop.events.AsyncWebFrameConnectorEvent;
import com.zyneonstudios.nexus.desktop.events.WebFrameConnectorEvent;
import org.cef.browser.CefBrowser;

import javax.swing.*;

public interface WebFrame {

    AsyncWebFrameConnectorEvent getAsyncWebFrameConnectorEvent();

    WebFrameConnectorEvent getWebFrameConnectorEvent();

    void setAsyncWebFrameConnectorEvent(AsyncWebFrameConnectorEvent asyncWebFrameConnectorEvent);

    void setWebFrameConnectorEvent(WebFrameConnectorEvent webFrameConnectorEvent);

    CefBrowser getBrowser();

    boolean isBrowserFocussed();

    void executeJavaScript(String... scripts);

    JFrame getAsJFrame();
}