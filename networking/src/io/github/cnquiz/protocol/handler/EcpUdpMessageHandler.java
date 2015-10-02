package io.github.cnquiz.protocol.handler;

import io.github.cnquiz.OnNetworkMessageListener;
import io.github.cnquiz.protocol.MessageHandler;
import io.github.cnquiz.protocol.Protocol;

import java.net.DatagramPacket;

/**
 * Handles messages sent to the ECP server.
 */
public final class EcpUdpMessageHandler implements MessageHandler {

    private final char NEWLINE = '\n';
    private final String SPACE = " ";

    private final OnNetworkMessageListener listener;

    private String[] msgArr;

    public EcpUdpMessageHandler(OnNetworkMessageListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(String message, DatagramPacket packet) {

        if (!endsWithNewLine(message)) {
            listener.onError(this, new UDPPacketArgs(packet));
        }
        msgArr = messageToArray(message);

    }

    private void interpertMessage(String message, DatagramPacket packet) {
        switch (msgArr[0]) {
            case (Protocol.Ecp.USER_TQR) :
                listener.onUserListRequest(this, new UDPPacketArgs(packet));
                break;

            default:
                listener.onError(this, new UDPPacketArgs(packet));
        }
    }

    private boolean endsWithNewLine(String str) {
        if (str.length() == 0) {
            return false;
        }
        char lastChar = str.charAt(str.length() - 1);
        return lastChar == NEWLINE;
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() -1);
    }

    private String[] messageToArray(String str) {
        String[] arr = str.split(SPACE);
        arr[arr.length - 1] = removeLastChar(arr[arr.length - 1]);
        return arr;
    }
}
