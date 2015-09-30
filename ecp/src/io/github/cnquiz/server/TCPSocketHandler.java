package io.github.cnquiz.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by ILUXONCHIK on 30/09/2015.
 */
public final class TCPSocketHandler implements  Runnable {

    private final Socket clientSocket;

    public TCPSocketHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // TODO
        System.out.println("Hello TCP!");


    }
}
