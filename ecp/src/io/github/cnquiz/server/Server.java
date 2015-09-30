package io.github.cnquiz.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Interface for a server running on a new thread.
 */
public abstract class Server {

    private static final int DEFAULT_POOL_SIZE = 10;

    protected final int serverPort;
    protected final int threadPoolSize;
    protected final ExecutorService clientProcessingPool;


    public Server(int threadPoolSize, int serverPort) {
        this.threadPoolSize = threadPoolSize;
        this.serverPort = serverPort;
        this.clientProcessingPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    public Server(int serverPort) {
        this(DEFAULT_POOL_SIZE, serverPort);
    }

    public abstract Thread start();
}
