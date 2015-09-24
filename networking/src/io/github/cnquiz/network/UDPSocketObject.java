package io.github.cnquiz.network;
import java.net.InetSocketAddress;

/**
 * Defines an interface for networking using UDP.
 */
public class UDPSocketObject extends SocketObject {

    public UDPSocketObject(InetSocketAddress address, byte[] data) {
        super(address, data);
    }

    public UDPSocketObject(InetSocketAddress address) {
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
