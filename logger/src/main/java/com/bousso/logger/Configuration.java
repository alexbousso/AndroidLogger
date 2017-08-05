package com.bousso.logger;

public class Configuration implements IConfiguration {
    private String pattern;
    private LogLevel minimumLogLevel;

    public Configuration(String pattern, LogLevel minimumLogLevel) {
        this.pattern = pattern;
        this.minimumLogLevel = minimumLogLevel;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public LogLevel getMinimumLogLevel() {
        return minimumLogLevel;
    }
}
