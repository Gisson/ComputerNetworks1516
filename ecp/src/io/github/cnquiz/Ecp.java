package io.github.cnquiz;


import io.github.cnquiz.parser.ECPOptions;

public class Ecp {
    private final int portNumber;

    public Ecp(ECPOptions ecpOpt) {
        portNumber = ecpOpt.getPortNumber();
    }

    public Ecp(int portNumber) {
        this.portNumber = portNumber;
    }

}
