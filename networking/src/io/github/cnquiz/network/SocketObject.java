package io.github.cnquiz.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Defines an interface for networking.
 */
public abstract class SocketObject {

    protected final InetSocketAddress address;
    protected byte[] data;

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     *
     * @param address address with which the instance will be associated with
     * @param data data to send
     */
    public SocketObject(InetSocketAddress address, byte[] data) {
        this.address = address;
        this.data = data;
    }

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     *
     * @param address address with which the instance will be associated with
     * @param data data to send
     */
    public SocketObject(InetSocketAddress address, String data) {
        this(address, data.getBytes());
    }

    /**
     * Creates a SocketObject associated with the specified {@link java.net.InetSocketAddress InetSocketAddress}.
     * The data to be sent is set to null.
     *
     * @param address address with which the instance will be associated with
     */
    public SocketObject(InetSocketAddress address) {
        this.address = address;
        data = null;
    }

    public SocketObject(InetAddress addr, int port) {
        this.address = new InetSocketAddress(addr, port);
        data = null;
    }

    public SocketObject(InetAddress address, int port, String data) {
        this.address = new InetSocketAddress(address, port);
        this.data = data.getBytes();
    }

    public SocketObject(InetAddress addr, int port, byte[] data) {
        this.address = new InetSocketAddress(addr, port);
        this.data = data;
    }

    /**
     * Sends the entire byte array to the specified InetSocketAddress.
     */
    public abstract void send() throws IOException;

    /**
     * Sends the byte with the specified offset and legth array to the specified InetSocketAddress.
     *
     * @param offset data offset
     * @param length data length (beginning from offset)
     */
    public abstract void send(int offset, int length) throws IOException;

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setData(String data) { this.data = data.getBytes(); }

    public byte[] getData() {
        return data;
    }
    
    


}
