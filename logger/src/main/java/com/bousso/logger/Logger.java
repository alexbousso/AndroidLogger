package com.bousso.logger;

/**
 * Created by eytan on 05/08/17.
 */

public class Logger {
    private String component;

    public Logger(String component) {
        this.component = component;
    }

    public Logger(Class component) {
        this.component = component.getName();
    }

    public void trace(String message) {
        LogSynchronizer.trace(new LogMessage(message, component));
    }

    public void debug(String message) {
        LogSynchronizer.debug(new LogMessage(message, component));
    }

    public void verbose(String message) {
        LogSynchronizer.verbose(new LogMessage(message, component));
    }

    public void warning(String message) {
        LogSynchronizer.warning(new LogMessage(message, component));
    }

    public void error(String message) {
        LogSynchronizer.error(new LogMessage(message, component));
    }

    public void critical(String message) {
        LogSynchronizer.critical(new LogMessage(message, component));
    }

    public void trace(String message, Error error) {
        LogSynchronizer.trace(new LogMessage(message, component, error));
    }

    public void debug(String message, Error error) {
        LogSynchronizer.debug(new LogMessage(message, component, error));
    }

    public void verbose(String message, Error error) {
        LogSynchronizer.verbose(new LogMessage(message, component, error));
    }

    public void warning(String message, Error error) {
        LogSynchronizer.warning(new LogMessage(message, component, error));
    }

    public void error(String message, Error error) {
        LogSynchronizer.error(new LogMessage(message, component, error));
    }

    public void critical(String message, Error error) {
        LogSynchronizer.critical(new LogMessage(message, component, error));
    }
}
