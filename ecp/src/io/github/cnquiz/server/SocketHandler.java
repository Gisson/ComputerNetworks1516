package io.github.cnquiz.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;

/**
 * Defines an interface for incoming connection handlers.
 */
public abstract class SocketHandler implements Runnable {

    /**
     * Reads a line from the provided InputStream.
     * @param inputStream InputStream containing the data
     * @return first line of the provided InputStream
     * @throws IOException
     */
    protected String readLine(InputStream inputStream) throws IOException {
        BufferedReader inputBuffReader = new BufferedReader(new InputStreamReader(inputStream));
        return inputBuffReader.readLine();
    }

    /**
     * Returns String represented by the byte array.
     * @param packet DatagramPacket containing the data
     * @return String represented by the byte array
     * @throws IOException
     */
    protected String readString(DatagramPacket packet) {
       return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }
}
