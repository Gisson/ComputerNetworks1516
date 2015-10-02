package io.github.cnquiz.protocol.handler;

import io.github.cnquiz.EventArgs;

import java.net.DatagramPacket;

/**
 *
 */
public final class UDPHandlerArgs extends EventArgs {

    private final DatagramPacket packet;

    public UDPHandlerArgs(DatagramPacket packet) { this.packet = packet; }

    public DatagramPacket getPacket() {
        return packet;
    }
}
