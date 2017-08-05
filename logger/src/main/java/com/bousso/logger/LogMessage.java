package com.bousso.logger;

public class LogMessage {
    private LogLevel level;
    private String message;
    private String component;
    private Error error;

    public LogMessage(LogLevel level, String message, String component) {
        this(level, message, component, null);
    }

    public LogMessage(LogLevel level, String message, String component, Error error) {
        this.level = level;
        this.message = message;
        this.component = component;
        this.error = error;
    }

    String getMessage() {
        return message;
    }

    String getComponent() {
        return component;
    }

    Error getError() {
        return error;
    }

    LogLevel getLevel() {
        return level;
    }
}
