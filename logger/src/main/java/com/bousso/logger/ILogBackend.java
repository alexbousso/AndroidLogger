package com.bousso.logger;

/**
 * Created by eytan on 05/08/17.
 */

public interface ILogBackend {
    void write(LogMessage message);
    void flush();
    void close();
}
