package io.github.cnquiz;


import io.github.cnquiz.network.UDPSocketObject;
import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.protocol.Protocol;
import io.github.cnquiz.protocol.handler.EcpUdpMessageHandler;
import io.github.cnquiz.protocol.handler.TERArgs;
import io.github.cnquiz.protocol.handler.UDPPacketArgs;
import io.github.cnquiz.server.UDPServer;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Ecp implements OnNetworkMessageListener {

    private final String ROOT_PATH = "./";
    private final String TOPICS_PATH = "res/";
    private final String TOPICS_FILENAME = "available_topics.txt";
    private final String TES_LIST_FILENAME = "topics.txt";
    private final String TOPICS_RELATIVE_PATH = ROOT_PATH + TOPICS_PATH + TOPICS_FILENAME;
    private final String TES_LIST_RELATIVE_PATH = ROOT_PATH + TOPICS_PATH + TES_LIST_FILENAME;


    private final int portNumber;
    private final Protocol.Ecp protocolHelper = new Protocol.Ecp();

    public Ecp(ECPOptions ecpOpt) {
        portNumber = ecpOpt.getPortNumber();
    }

    public Ecp(int portNumber) {
        this.portNumber = portNumber;
    }

    public Thread[] start() {
        Thread[] threads = new Thread[1];
        UDPServer udpServer = new UDPServer(portNumber, new EcpUdpMessageHandler(this));
        threads[0] = udpServer.start();

        return threads;
    }

    @Override
    public void onUserListRequest(Object sender, EventArgs args) {
        DatagramPacket senderPack = ((UDPPacketArgs)args).getPacket();
        try {
            protocolHelper.setUserClient(new UDPSocketObject(senderPack));
            protocolHelper.sendQuestTopicListFromFile(TOPICS_RELATIVE_PATH);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUserTopicRequest(Object sender, EventArgs args) {
        DatagramPacket senderPack = ((TERArgs)args).getPacket();
        int topicNum = Integer.parseInt(((TERArgs) args).getTopicNumber());
        try {
            protocolHelper.setUserClient(new UDPSocketObject(senderPack));
            protocolHelper.sendTESInfoFromFile(topicNum, TES_LIST_RELATIVE_PATH);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Object sender, EventArgs e) {
        DatagramPacket senderPack = ((UDPPacketArgs)e).getPacket();
        try {
            protocolHelper.setUserClient(new UDPSocketObject(senderPack));
            protocolHelper.sendError();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
    }

}
