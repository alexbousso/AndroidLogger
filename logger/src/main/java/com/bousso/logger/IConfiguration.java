package com.bousso.logger;

/**
 * Created by eytan on 05/08/17.
 */

public interface IConfiguration {
    String getPattern();
    LogLevel getMinimumLogLevel();
}
