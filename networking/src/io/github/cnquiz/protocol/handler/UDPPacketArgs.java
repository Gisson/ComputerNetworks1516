package io.github.cnquiz.protocol.handler;

import io.github.cnquiz.EventArgs;

import java.net.DatagramPacket;

/**
 *
 */
public class UDPPacketArgs extends EventArgs {

    private final DatagramPacket packet;

    public UDPPacketArgs(DatagramPacket packet) { this.packet = packet; }

    public DatagramPacket getPacket() {
        return packet;
    }
}
