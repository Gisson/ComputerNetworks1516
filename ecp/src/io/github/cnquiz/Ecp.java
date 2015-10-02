package io.github.cnquiz;


import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.protocol.handler.EcpUdpMessageHandler;
import io.github.cnquiz.server.TCPServer;
import io.github.cnquiz.server.UDPServer;

public class Ecp implements OnNetworkMessageListener {

    private final int portNumber;

    public Ecp(ECPOptions ecpOpt) {
        portNumber = ecpOpt.getPortNumber();
    }

    public Ecp(int portNumber) {
        this.portNumber = portNumber;
    }

    public Thread[] start() {
        Thread[] threads = new Thread[1];

        //TCPServer tcpServer = new TCPServer(portNumber, new EcpUdpMessageHandler(this));
        //threads[0] = tcpServer.start();

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

    }
}
