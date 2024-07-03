package us.cursos.backend;

import us.cursos.api.Cursos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncDispatcher {

    private static final ExecutorService async = Executors.newCachedThreadPool();

    // Warning! Async code
    private static final Thread listenerThread = new Thread(() -> {
        Cursos.getInstance().getLogger().info("Thread started!");
        Cursos.getInstance().getLogger().info("Thread name: " + Thread.currentThread().getName() + " | Thread ID: " + Thread.currentThread().threadId());
        // TODO
    }, "listenerThread");

    public static void startListenerThread() {
        listenerThread.start();
    }
}
