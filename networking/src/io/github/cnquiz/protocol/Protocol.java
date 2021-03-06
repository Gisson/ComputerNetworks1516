package io.github.cnquiz.protocol;

import io.github.cnquiz.network.SocketObject;
import io.github.cnquiz.network.UDPSocketObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    public static final class User {

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

        public void list() { throw  new UnsupportedOperationException();}

        public void submit(int sid, int qid, String answerSeq) { throw new UnsupportedOperationException(); }

        private void requestTopicECP(int topicNum) {
            throw new UnsupportedOperationException();
        }

        private void requestTopicTES(int sid, int topicNum) { throw  new UnsupportedOperationException(); }

        public void requestQuest(int sid) { throw  new UnsupportedOperationException();}
    }

    public static final class Tes {
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
    public static final class Ecp {
        public static final String USER_TQR = "TQR";
        public static final String USER_TER = "TER";
        public static final String TES_IQR = "IQR";

        private final String AWT_MSG = "AWT";
        private final String ERR_MSG = "ERR";
        private final String EOF_MSG = "EOF";
        private final String AWTES_MSG = "AWTES";
        private final String AWI_MSG = "AWI";
        private final String SPACE = " ";
        private final String NEWLINE= "\n";


        private final SocketObject client;

        public Ecp(SocketObject client) {
            this.client = client;
        }

        public void sendError() {
            if (client != null) {
                client.setData(getErrorMsg().getBytes());
                try {
                    client.send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendEOF() {
            if (client != null) {
                client.setData(getEOFMsg().getBytes());
                try {
                    client.send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendQuestTopicList() {
            if (client != null) {
                client.setData(AWT_MSG + SPACE + new String(client.getData()) + NEWLINE);
                try {
                    client.send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendStatsReceivedACK(String qid) {
            if (client != null) {
                client.setData(AWI_MSG + SPACE + qid + NEWLINE);
                try {
                    client.send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendQuestTopicListFromFile(String filePath) {
            if (client == null) {
                return;
            }
            try {
                client.setData(buildAWTStringFromFile(filePath));
                client.send();
            } catch (FileNotFoundException e) {
                sendEOF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String buildAWTStringFromFile(String filePath) throws  IOException {
            StringBuilder protocolStr = new StringBuilder();
            StringBuilder topicCount = new StringBuilder();
            StringBuilder topicNames = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int numLines = 0;
            String line;
            while( (line = br.readLine()) != null) {
                if (line.length() != 0) {
                    topicNames.append(getTopicNameFromLineStr(line) + SPACE);
                    numLines++;
                }
            }
            br.close();
            // if the file is empty send EOF
            if(numLines == 0) {
                return getEOFMsg();
            }
            protocolStr.append(AWT_MSG + SPACE);
            topicCount.append(numLines);
            topicCount.append(SPACE);

            return protocolStr.toString() + topicCount.toString() + topicNames.substring(0, topicNames.length() - 1) + NEWLINE;
        }

        private String getTopicNameFromLineStr(String line) {
            return line.split(SPACE)[0];
        }

        private String getErrorMsg() {
            return (ERR_MSG + NEWLINE);
        }

        private String getEOFMsg() {
            return (EOF_MSG + NEWLINE);
        }


        public void sendTESInfoFromFile(int topicNum, String filePath) {
            if (client == null) {
                return;
            }
            try {
                client.setData(buildAWTESStringFromFile(filePath, topicNum));
                client.send();
            } catch (FileNotFoundException e) {
                sendEOF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendTESInfo(String tesAddress) {
            if (client == null) {
                return;
            }
            try {
                client.setData(AWTES_MSG + SPACE + tesAddress + NEWLINE);
                client.send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String buildAWTESStringFromFile(String filePath, int topicNum) throws IOException {
            if(topicNum < 1) { return getErrorMsg(); }

            StringBuilder protocolStr = new StringBuilder();
            StringBuilder tesAddressStr = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            int lineCtr = 1;
            while((line = br.readLine()) != null) {
                if (lineCtr == topicNum) {
                    br.close();
                    if (line.length() == 0) {
                        return getEOFMsg();
                    }
                    protocolStr.append(AWTES_MSG + SPACE);
                    tesAddressStr.append(getTesIpPortFromStr(line));
                    return protocolStr.toString() + tesAddressStr.toString() + NEWLINE;
                }

                lineCtr++;
            }

            br.close();
            return getEOFMsg();
        }

        private String getTesIpPortFromStr(String line) {
            String[] lineArr = line.split(SPACE);
            return lineArr[1] + SPACE + lineArr[2];
        }

        private boolean lineHasTopicNum(String[]lineArr, String topicNum ) {
            // last two elements are TES IP and port
            for (int i = 0; i < lineArr.length - 2; i++) {
                if (lineArr[i].equals(topicNum)) {
                    return true;
                }
            }
            return false;
        }


        private String[] stringToArray(String lineArr, String separator) {
            return lineArr.split(separator);
        }

        // Section 3.3
        public void confirmResultReceival(int qid) { throw  new UnsupportedOperationException(); }

        public SocketObject getClient() {
            return client;
        }
    }


}
