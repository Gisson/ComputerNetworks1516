package io.github.cnquiz;


import io.github.cnquiz.parser.ECPOptions;
import io.github.cnquiz.server.TCPServer;
import io.github.cnquiz.server.UDPServer;

public class Ecp {

    private final int portNumber;

    public Ecp(ECPOptions ecpOpt) {
        portNumber = ecpOpt.getPortNumber();
    }

    public Ecp(int portNumber) {
        this.portNumber = portNumber;
    }

    public Thread[] start() {
        Thread[] threads = new Thread[2];

        TCPServer tcpServer = new TCPServer(portNumber);
        threads[0] = tcpServer.start();

        UDPServer udpServer = new UDPServer(portNumber);
        threads[1] = udpServer.start();

        return threads;
    }

}
