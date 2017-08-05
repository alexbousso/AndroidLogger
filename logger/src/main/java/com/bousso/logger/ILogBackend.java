package com.bousso.logger;

/**
 * Created by eytan on 05/08/17.
 */

public interface ILogBackend {
    void write(LogLevel level, LogMessage message);
    void flush();
    void close();
}
