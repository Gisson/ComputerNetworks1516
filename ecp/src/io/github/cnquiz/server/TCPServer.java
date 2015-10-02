package io.github.cnquiz.server;

import io.github.cnquiz.protocol.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * TCP server using a thread pool.
 */
public class TCPServer extends HandlerServer {

    private ServerSocket serverSocket;
    private Thread serverThread;

    public TCPServer(int threadPoolSize, int serverPort, MessageHandler handler) {
        super(threadPoolSize, serverPort, handler);
    }

    public TCPServer(int serverPort, MessageHandler messageHandler) {
        super(serverPort, messageHandler);
    }

    public Thread start() {
        isRunning = true;
        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(serverPort);
                    while (isRunning) {
                        Socket clientSocket = serverSocket.accept();
                        clientProcessingPool.submit(new TCPSocketHandler(clientSocket, messageHandler));
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
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
