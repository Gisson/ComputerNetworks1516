package io.github.cnquiz.server;

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

    public TCPSocketHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            String dataRead = readLine(clientSocket.getInputStream());
            System.out.println(dataRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
