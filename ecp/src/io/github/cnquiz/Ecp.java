package io.github.cnquiz;


import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.protocol.ECPMessageHandler;
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
        Thread[] threads = new Thread[2];

        TCPServer tcpServer = new TCPServer(portNumber, new ECPMessageHandler(this));
        threads[0] = tcpServer.start();

        UDPServer udpServer = new UDPServer(portNumber, new ECPMessageHandler(this));
        threads[1] = udpServer.start();

        return threads;
    }

    @Override
    public void onUserListRequest(Object sender, EventArgs e) {

    }

    @Override
    public void onUserTopicRequest(Object sender, EventArgs e) {

    }
}
