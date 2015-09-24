package io.github.cnquiz.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Defines an interface for networking using TCP.
 */
public final class TCPSocketObject extends SocketObject {

    private Socket clientSocket;

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     *
     * @param address address with which the instance will be associated with
     * @param data data to send
     * @throws IOException
     */
    public TCPSocketObject(InetSocketAddress address, byte[] data) throws IOException {
        super(address, data);
        clientSocket = new Socket(address.getAddress(), address.getPort());
    }

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     * The data to be sent is set to null.
     *
     * @param address address with which the instance will be associated with
     * @throws IOException
     */
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
