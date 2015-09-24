package io.github.cnquiz.network;

import java.net.InetSocketAddress;

/**
 * Defines an interface for networking using TCP.
 */
public class TCPSocketObject extends SocketObject {

    public TCPSocketObject(InetSocketAddress address, byte[] data) {
        super(address, data);
    }

    public TCPSocketObject(InetSocketAddress address) {
        super(address);
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
