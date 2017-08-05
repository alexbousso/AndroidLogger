package com.bousso.logger;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class LogSynchronizer {
    private static boolean isInitialized = false;
    private Context context;
    private LinkedBlockingQueue<LogMessage> inputQueue = new LinkedBlockingQueue<>();
    private Thread writer;

    @SuppressLint("StaticFieldLeak")
    private static LogSynchronizer instance = null;

    protected LogSynchronizer() {}

    public static LogSynchronizer getInstance() {
        if (instance == null) {
            instance = new LogSynchronizer();
        }
        return instance;
    }

    public void initialize(Context context) {
        if (isInitialized) {
            return;
        }

        this.context = context;
        writer = new Thread(new Runnable() {
            @Override
            public void run() {
                //noinspection InfiniteLoopStatement
                while (true) {
                    try {
                        LogMessage message = inputQueue.poll(1, TimeUnit.DAYS);
                    } catch (InterruptedException e) { }
                }
            }
        });
        writer.start();
        isInitialized = true;
    }

    public void write(LogMessage message) {
        if (!isInitialized) {
            throw new RuntimeException("LogSynchronizer must be initialized. Use initialize()");
        }
        inputQueue.add(message);
    }
}
