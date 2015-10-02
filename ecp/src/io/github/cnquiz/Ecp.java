package io.github.cnquiz;


import io.github.cnquiz.network.UDPSocketObject;
import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.protocol.Protocol;
import io.github.cnquiz.protocol.handler.EcpUdpMessageHandler;
import io.github.cnquiz.protocol.handler.UDPPacketArgs;
import io.github.cnquiz.server.UDPServer;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Ecp implements OnNetworkMessageListener {

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
    public void onUserListRequest(Object sender, EventArgs e) {

    }

    @Override
    public void onUserTopicRequest(Object sender, EventArgs e) {

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
