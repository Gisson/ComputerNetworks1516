package io.github.cnquiz.server;

import io.github.cnquiz.protocol.MessageHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

/**
 * UDP server using a thread pool.
 */
public class UDPServer extends HandlerServer {

    private final int BUFF_SIZE = 1024; // buffer size for datagram receive packet

    private DatagramSocket serverSocket;
    private Thread serverThread;

    public UDPServer(int threadPoolSize, int serverPort, MessageHandler messageHandler) {
        super(threadPoolSize, serverPort, messageHandler);
    }

    public UDPServer(int serverPort, MessageHandler messageHandler) {
        super(serverPort, messageHandler);
    }

    @Override
    public Thread start() {
        isRunning = true;
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new DatagramSocket(serverPort);
                    while (isRunning) {
                        byte[] receiveBuff = new byte[BUFF_SIZE];
                        DatagramPacket packet = new DatagramPacket(receiveBuff, receiveBuff.length);
                        serverSocket.receive(packet);
                        clientProcessingPool.submit(new UDPSocketHandler(packet, messageHandler));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        serverThread = new Thread(serverTask);
        serverThread.start();

        return serverThread;
    }

    @Override
    public void stop() {
        isRunning = false;

        if(serverSocket != null) {
            serverSocket.close();
        }

        if (clientProcessingPool != null) {
            try {
                clientProcessingPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

