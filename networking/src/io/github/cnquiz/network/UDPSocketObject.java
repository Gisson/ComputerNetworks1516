package io.github.cnquiz.network;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Defines an interface for networking using UDP.
 */
public final class UDPSocketObject extends SocketObject {

    private final DatagramSocket clientSocket;

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     *
     * @param address address with which the instance will be associated with
     * @param data data to send
     * @throws SocketException
     */
    public UDPSocketObject(InetSocketAddress address, byte[] data) throws SocketException {
        super(address, data);
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw e;
        }
    }

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     * The data to be sent is set to null.
     *
     * @param address address with which the instance will be associated with
     * @throws SocketException
     */
    public UDPSocketObject(InetSocketAddress address) throws SocketException {
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
