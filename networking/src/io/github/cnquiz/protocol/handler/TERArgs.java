package io.github.cnquiz.protocol.handler;

import java.net.DatagramPacket;

/**
 * Contains arguments related to the TER request.
 */
public class TERArgs extends UDPPacketArgs {

    private final String topicNumber;

    public TERArgs(DatagramPacket packet, String topicNumber) {
        super(packet);
        this.topicNumber = topicNumber;
    }

    public String getTopicNumber() {
        return topicNumber;
    }
}
