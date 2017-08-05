package com.bousso.logger;

class LogMessage {
    private String message;
    private String component;
    private Error error;

    public LogMessage(String message, String component) {
        this(message, component, null);
    }

    public LogMessage(String message, String component, Error error) {
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
}
