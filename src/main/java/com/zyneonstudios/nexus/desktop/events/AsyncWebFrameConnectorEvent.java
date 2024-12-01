package com.zyneonstudios.nexus.desktop.events;

import com.zyneonstudios.nexus.desktop.frame.web.NexusWebFrame;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class AsyncWebFrameConnectorEvent implements Event{

    private final UUID uuid = UUID.randomUUID();
    private final NexusWebFrame frame;

    public AsyncWebFrameConnectorEvent(NexusWebFrame frame) {
        this.frame = frame;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public NexusWebFrame getFrame() {
        return frame;
    }

    public final void resolveAsync(String message) {
        CompletableFuture.runAsync(() -> {resolveMessage(message);});
    }

    public void resolveMessage(String message) {
        CompletableFuture.runAsync(() -> {});
    };
}
