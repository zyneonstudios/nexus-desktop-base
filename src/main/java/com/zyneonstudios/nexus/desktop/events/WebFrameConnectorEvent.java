package com.zyneonstudios.nexus.desktop.events;

import com.zyneonstudios.nexus.desktop.frame.web.NexusWebFrame;

import java.util.UUID;

public abstract class WebFrameConnectorEvent implements Event{

    private final UUID uuid = UUID.randomUUID();
    private final NexusWebFrame frame;

    private WebFrameConnectorEvent(NexusWebFrame frame) {
        this.frame = frame;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public NexusWebFrame getFrame() {
        return frame;
    }

    public abstract boolean resolveMessage(String message);
}
