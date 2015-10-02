package io.github.cnquiz;

import io.github.cnquiz.parser.ArgumentParser;
import io.github.cnquiz.protocol.Protocol;

public class Main {

    public static void main(String[] args) {
        Thread[] threads;
        Ecp ecp = new Ecp(ArgumentParser.parse(args));
        threads = ecp.start();

        try {
            joinThreads(threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void joinThreads(Thread[] threads) throws InterruptedException {
        for(Thread t : threads) {
            t.join();
        }
    }

}
