package com.bousso.logger;

/**
 * Created by eytan on 05/08/17.
 */

public class Logger {
    private String component;
    private LogSynchronizer syncher;

    public Logger(String component) {
        this.component = component;
        syncher = LogSynchronizer.getInstance();
    }

    public Logger(Class component) {
        this(component.getName());
    }

    public void trace(String message) {
        syncher.write(new LogMessage(LogLevel.Trace, message, component));
    }

    public void debug(String message) {
        syncher.write(new LogMessage(LogLevel.Debug, message, component));
    }

    public void verbose(String message) {
        syncher.write(new LogMessage(LogLevel.Verbose, message, component));
    }

    public void warning(String message) {
        syncher.write(new LogMessage(LogLevel.Warning, message, component));
    }

    public void error(String message) {
        syncher.write(new LogMessage(LogLevel.Error, message, component));
    }

    public void critical(String message) {
        syncher.write(new LogMessage(LogLevel.Critical, message, component));
    }

    public void trace(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Trace, message, component, error));
    }

    public void debug(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Debug, message, component, error));
    }

    public void verbose(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Verbose, message, component, error));
    }

    public void warning(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Warning, message, component, error));
    }

    public void error(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Error, message, component, error));
    }

    public void critical(String message, Error error) {
        syncher.write(new LogMessage(LogLevel.Critical, message, component, error));
    }
}
