package io.github.cnquiz.protocol;

import io.github.cnquiz.network.SocketObject;
import io.github.cnquiz.network.TCPSocketObject;
import io.github.cnquiz.network.UDPSocketObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Class responsible for encapsulating the protocol specified in project's description.
 */
public final class Protocol {

    /**
     *  Class responsible for encapsulating the protocol for the user application specified in project's description.
     *  An instance of this class is bound to the provided addresses.
     */
    public final class User {

        private final SocketObject tesClient;
        private final SocketObject ecpClient;

        /**
         *
         * @param tesAddress address of the tes server
         * @param ecpAddress address of the ecp server
         * @throws IOException propagated from {@link
         * io.github.cnquiz.network.TCPSocketObject#TCPSocketObject(InetSocketAddress) TCPSocketObject()}
         * @throws SocketException propagated from {@link
         * io.github.cnquiz.network.UDPSocketObject#UDPSocketObject(InetSocketAddress) UDPSocketObject()}
         */
        public User(InetSocketAddress tesAddress, InetSocketAddress ecpAddress) throws IOException {
            tesClient = new TCPSocketObject(tesAddress);
            ecpClient = new UDPSocketObject(ecpAddress);
        }

        public void list() {
            throw new UnsupportedOperationException();
        }

        public void request(int topicNum) {
            throw new UnsupportedOperationException();
        }

        public void submit(String answerSeq) {
            throw new UnsupportedOperationException();
        }
    }

    public final class TES {
        // TODO
    }

    public final class ECP {
        // TODO
    }

}
