package io.github.cnquiz.parser;

public final class ECPOptions {

    private static final int GROUP_NUMBER = 42;
    private final int portNumber;

    public ECPOptions(int portNumber) {
        this.portNumber = portNumber;
    }

    public ECPOptions(String portNumber) {
        this.portNumber = Integer.parseInt(portNumber);
    }

    public ECPOptions() {
        this(GROUP_NUMBER);
    }

    public int getPortNumber() {
        return portNumber;
    }
}
