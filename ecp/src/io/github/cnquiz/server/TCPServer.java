package io.github.cnquiz.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP server using a thread pool.
 */
public class TCPServer extends Server{

    public TCPServer(int threadPoolSize, int serverPort) {
        super(threadPoolSize, serverPort);
    }

    public TCPServer(int serverPort) {
        super(serverPort);
    }

    public Thread start() {
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(serverPort);
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        clientProcessingPool.submit(new TCPSocketHandler(clientSocket));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread serverThread = new Thread(serverTask);
        serverThread.start();

        return serverThread;
    }
}
