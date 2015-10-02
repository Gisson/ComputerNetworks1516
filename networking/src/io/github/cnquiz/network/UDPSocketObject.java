package io.github.cnquiz.network;
import java.io.IOException;
import java.net.DatagramPacket;
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
        clientSocket = new DatagramSocket();
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

    public UDPSocketObject(DatagramPacket packet) throws SocketException {
        super(packet.getAddress(), packet.getPort());
        clientSocket = new DatagramSocket();
    }

    @Override
    public void send() throws IOException {
        send(0, getData().length);
    }
    
    
    @Override
    public void send(int offset, int length) throws IOException

    {
        clientSocket.send(new DatagramPacket(getData(), offset, length, address.getAddress(), address.getPort()));
    }
}
