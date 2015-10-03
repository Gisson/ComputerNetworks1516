package io.github.cnquiz.protocol.handler;

import java.net.DatagramPacket;

/**
 *
 */
public class IQRArgs extends UDPPacketArgs {

    private final String sid;
    private final String qid;
    private final String topic_name;
    private final String score;


    public IQRArgs(DatagramPacket packet, String sid, String qid, String topic_name, String score) {
        super(packet);
        this.sid = sid;
        this.qid = qid;
        this.topic_name = topic_name;
        this.score = score;
    }

    public String getSid() {
        return sid;
    }

    public String getQid() {
        return qid;
    }

    public String getTopicName() {
        return topic_name;
    }

    public String getScore() {
        return score;
    }
}
