package io.github.cnquiz;


import io.github.cnquiz.network.UDPSocketObject;
import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.protocol.Protocol;
import io.github.cnquiz.protocol.handler.EcpUdpMessageHandler;
import io.github.cnquiz.protocol.handler.IQRArgs;
import io.github.cnquiz.protocol.handler.TERArgs;
import io.github.cnquiz.protocol.handler.UDPPacketArgs;
import io.github.cnquiz.server.UDPServer;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ecp implements OnNetworkMessageListener {

    private final String ROOT_PATH = "./";
    private final String TOPICS_PATH = "res/";
    private final String TOPICS_FILENAME = "available_topics.txt";
    private final String TES_LIST_FILENAME = "topics.txt";
    private final String STATS_FILENAME = "stats.txt";
    private final String TOPICS_RELATIVE_PATH = ROOT_PATH + TOPICS_PATH + TOPICS_FILENAME;
    private final String TES_LIST_RELATIVE_PATH = ROOT_PATH + TOPICS_PATH + TES_LIST_FILENAME;
    private final String STATS_RELATIVE_PATH = ROOT_PATH + TOPICS_PATH + STATS_FILENAME;

    private final String NEWLINE = "\n";
    private final String SPACE = " ";

    private final int portNumber;

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
            Protocol.Ecp protocolHelper = new Protocol.Ecp(new UDPSocketObject(senderPack));
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
            Protocol.Ecp protocolHelper = new Protocol.Ecp(new UDPSocketObject(senderPack));
            protocolHelper.sendTESInfoFromFile(topicNum, TES_LIST_RELATIVE_PATH);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUserStatMessage(Object sender, EventArgs eventArgs) {
        IQRArgs args = (IQRArgs)eventArgs;
        String fileStr = args.getSid() + SPACE + args.getQid()
                + SPACE + args.getTopicName() + SPACE + args.getScore() + NEWLINE;
        File f = new File(STATS_RELATIVE_PATH);

        if (!f.exists() && !f.isDirectory()) {
            createFile(STATS_RELATIVE_PATH);
        }

        try {
            Files.write(Paths.get(STATS_RELATIVE_PATH), fileStr.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Protocol.Ecp protocolHelper = new Protocol.Ecp(new UDPSocketObject(args.getPacket()));
            protocolHelper.sendStatsReceivedACK(args.getQid());
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    private void createFile(String filepath) {
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Object sender, EventArgs e) {
        DatagramPacket senderPack = ((UDPPacketArgs)e).getPacket();
        try {
            Protocol.Ecp protocolHelper = new Protocol.Ecp(new UDPSocketObject(senderPack));
            protocolHelper.sendError();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
    }

}
