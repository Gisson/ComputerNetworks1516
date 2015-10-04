package io.github.cnquiz.parser;

public final class ECPOptions {

    private static final int GROUP_NUMBER = 33;
    private static final int PORT_BASE = 8000;
    private final int portNumber;

    public ECPOptions(int portNumber) {
        this.portNumber = portNumber;
    }

    public ECPOptions(String portNumber) {
        this.portNumber = Integer.parseInt(portNumber);
    }

    public ECPOptions() {
        this(PORT_BASE + GROUP_NUMBER);
    }

    public int getPortNumber() {
        return portNumber;
    }
}
