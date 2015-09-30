package io.github.cnquiz.protocol;

import io.github.cnquiz.network.SocketObject;
import io.github.cnquiz.network.TCPSocketObject;
import io.github.cnquiz.network.UDPSocketObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Class responsible for encapsulating the protocol specified in project's description.
 */
public final class Protocol {

    /**
     *  Class responsible for encapsulating the protocol for the user application specified in project's description.
     *  An instance of this class is bound to the provided ECP address.
     */
    public final class User {

        private SocketObject tesClient;
        private final SocketObject ecpClient;

        /**
         *
         * @param ecpAddress address of the ecp server
         * @throws IOException propagated from {@link
         * io.github.cnquiz.network.TCPSocketObject#TCPSocketObject(InetSocketAddress) TCPSocketObject()}
         * @throws SocketException propagated from {@link
         * io.github.cnquiz.network.UDPSocketObject#UDPSocketObject(InetSocketAddress) UDPSocketObject()}
         */
        public User(InetSocketAddress ecpAddress) throws IOException {
            ecpClient = new UDPSocketObject(ecpAddress);
        }


        private void request(int topicNum) { throw new UnsupportedOperationException(); }

        public void list() {
            ecpClient.setData("TQR".getBytes());
            ecpClient.send();
        }

        public void submit(int sid, int qid, String answerSeq) { throw new UnsupportedOperationException(); }

        private void requestTopicECP(int topicNum) {
            throw new UnsupportedOperationException();
        }

        private void requestTopicTES(int sid, int topicNum) { throw  new UnsupportedOperationException(); }

        public void requestQuest(int sid) { throw  new UnsupportedOperationException();}
    }

    public final class Tes {
        private SocketObject userClient;
        private final SocketObject ecpClient;

        public Tes(InetSocketAddress ecpAddress) throws SocketException { ecpClient = new UDPSocketObject(ecpAddress);}

        // Section 3.2
        public void sendQuest(int qid, long timeLimit, long size, String filename) {
            /*
             *  Example usage of timeLimit is with Calendar.setTimeInMillis(timeLimit) and then extract the date
             *  from there.
             */
            throw  new UnsupportedOperationException();
        }

        public void sendResolults(int qid, double score) { throw  new UnsupportedOperationException(); }

        // Section 3.3
        public void sendUserResult(int sid, int qid, String topicName, double score) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     *  Class responsible for encapsulating the protocol for the ecp application specified in project's description.
     *  An instance of this class is bound to the provided user address.
     */
    public final class Ecp {
        private SocketObject userClient;
        private SocketObject tesClient;

        // TODO: Issue #6
        public void sendQuestTopicList(String topicList) { throw new UnsupportedOperationException();}
        public void sendQuestTopicList(String[] topicList) { throw new UnsupportedOperationException();}

        public void sendTESInfo(String tesAddress) { throw  new UnsupportedOperationException(); }

        // Section 3.3
        public void confirmResultReceival(int qid) { throw  new UnsupportedOperationException(); }
    }

}
