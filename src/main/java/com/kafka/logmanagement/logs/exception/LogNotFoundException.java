package com.kafka.logmanagement.logs.exception;

/**
 * Exception thrown when a log entry is not found.
 */
public class LogNotFoundException extends Throwable {

    /**
     * Constructs a new LogNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public LogNotFoundException(String message) {
        super(message);
    }
}
