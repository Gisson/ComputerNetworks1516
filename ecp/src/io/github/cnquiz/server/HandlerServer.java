package io.github.cnquiz.server;

import io.github.cnquiz.protocol.MessageHandler;

/**
 * Server with a {@link io.github.cnquiz.protocol.MessageHandler MessageHandler} to handle the incoming
 * packets/datagrams.
 */
public abstract class HandlerServer extends Server {

    protected MessageHandler messageHandler;

    public HandlerServer(int threadPoolSize, int serverPort, MessageHandler messageHandler) {
        super(threadPoolSize, serverPort);
        this.messageHandler = messageHandler;
    }

    public HandlerServer(int serverPort, MessageHandler messageHandler) {
        super(serverPort);
        this.messageHandler = messageHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

}
