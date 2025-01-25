package com.zyneonstudios.nexus.desktop.events;

import com.zyneonstudios.nexus.desktop.frame.web.WebFrame;
import com.zyneonstudios.nexus.utilities.events.Event;

import java.util.UUID;

public abstract class WebFrameConnectorEvent implements Event {

    private final UUID uuid = UUID.randomUUID();
    private final WebFrame frame;
    private String message;

    public WebFrameConnectorEvent(WebFrame frame, String message) {
        this.frame = frame;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public final boolean execute() {
        return resolveMessage(message);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public WebFrame getFrame() {
        return frame;
    }

    protected abstract boolean resolveMessage(String message);
}
