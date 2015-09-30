package io.github.cnquiz.server;

import java.net.DatagramPacket;

/**
 * Handles UDP messages.
 */
public final class UDPSocketHandler implements Runnable {

    private final DatagramPacket receivePacket;

    public UDPSocketHandler(DatagramPacket receivePacket) {
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {

    }
}
