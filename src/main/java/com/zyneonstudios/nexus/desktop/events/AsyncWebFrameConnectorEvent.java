package com.zyneonstudios.nexus.desktop.events;

import com.zyneonstudios.nexus.desktop.frame.web.NexusWebFrame;
import com.zyneonstudios.nexus.utilities.events.Event;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AsyncWebFrameConnectorEvent implements Event {

    private final UUID uuid = UUID.randomUUID();
    private final NexusWebFrame frame;
    private String message;

    public AsyncWebFrameConnectorEvent(NexusWebFrame frame, String message) {
        this.frame = frame;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean execute() {
        CompletableFuture.runAsync(() -> {
            resolveMessage(message);
        });
        return true;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public NexusWebFrame getFrame() {
        return frame;
    }

    protected abstract void resolveMessage(String message);
}
