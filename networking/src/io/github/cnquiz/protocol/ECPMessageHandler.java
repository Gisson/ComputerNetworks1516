package io.github.cnquiz.protocol;

import io.github.cnquiz.OnNetworkMessageListener;

/**
 * Handles messages sent to the ECP server.
 */
public final class ECPMessageHandler implements MessageHandler {

    final OnNetworkMessageListener listener;

    public ECPMessageHandler(OnNetworkMessageListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(String message) {
        System.out.println(message);
    }
}
