package org.mik.first.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record Message(int statusCode, String message, String details, LocalDateTime timestamp) {
}
