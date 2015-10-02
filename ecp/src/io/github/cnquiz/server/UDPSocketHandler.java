package io.github.cnquiz.server;

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

    public UDPSocketHandler(DatagramPacket receivePacket) {
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        String dataRead = readString(receivePacket.getData());
        System.out.println(dataRead);
    }
}
