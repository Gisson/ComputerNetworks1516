package io.github.cnquiz.server;

import io.github.cnquiz.protocol.ECPMessageHandler;
import io.github.cnquiz.protocol.MessageHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

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
            messageHandler.handle(dataRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
