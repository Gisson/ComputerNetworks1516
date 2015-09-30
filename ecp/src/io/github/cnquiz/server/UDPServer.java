package io.github.cnquiz.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP server using a thread pool.
 */
public class UDPServer extends Server {

    private final int BUFF_SIZE = 1024; // buffer size for datagram receive packet

    public UDPServer(int threadPoolSize, int serverPort) {
        super(threadPoolSize, serverPort);
    }

    @Override
    public void start() {
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                byte[] recieveBuff = new byte[BUFF_SIZE];
                try {
                    DatagramSocket serverSocket = new DatagramSocket(serverPort);
                    DatagramPacket packet = new DatagramPacket(recieveBuff, recieveBuff.length);
                    while (true) {
                        serverSocket.receive(packet);
                        clientProcessingPool.submit(new UDPSocketHandler(packet));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }
}

