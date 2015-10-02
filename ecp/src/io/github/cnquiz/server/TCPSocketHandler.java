package io.github.cnquiz.server;

import io.github.cnquiz.protocol.MessageHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

/**
 * Created by ILUXONCHIK on 30/09/2015.
 */
public final class TCPSocketHandler extends SocketHandler {

    private final Socket clientSocket;
    private final MessageHandler messageHandler;

    public TCPSocketHandler(Socket clientSocket, MessageHandler messageHandler) {
        this.clientSocket = clientSocket;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try {
            String dataRead = readLine(clientSocket.getInputStream());
            //messageHandler.handle(dataRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
