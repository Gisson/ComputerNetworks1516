package io.github.cnquiz.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Defines an interface for networking using TCP.
 */
public final class TCPSocketObject extends SocketObject {

    private Socket clientSocket;

    public TCPSocketObject(InetSocketAddress address, byte[] data) throws IOException {
        super(address, data);
        try {
            clientSocket = new Socket(address.getAddress(), address.getPort());
        } catch (IOException e) {
            throw  new IOException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    public TCPSocketObject(InetSocketAddress address) throws IOException {
        this(address, null);
    }

    @Override
    public void send() {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void send(int offset, int length) {
        throw  new UnsupportedOperationException();
    }
}
