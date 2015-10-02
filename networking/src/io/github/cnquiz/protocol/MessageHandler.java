package io.github.cnquiz.protocol;

import java.net.DatagramPacket;

/**
 * Defines an interface for protocol message handlers.
 */
public interface MessageHandler {

    void handle(String message, DatagramPacket packet);
}
