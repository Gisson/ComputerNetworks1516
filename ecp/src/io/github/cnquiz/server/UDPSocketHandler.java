package io.github.cnquiz.server;

import io.github.cnquiz.protocol.MessageHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Handles UDP messages.
 */
public final class UDPSocketHandler extends SocketHandler {

    private final DatagramPacket receivePacket;
    private final MessageHandler messageHandler;

    public UDPSocketHandler(DatagramPacket receivePacket, MessageHandler messageHandler) {
        this.receivePacket = receivePacket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        String dataRead = readString(receivePacket);
        messageHandler.handle(dataRead, receivePacket);
    }
}
