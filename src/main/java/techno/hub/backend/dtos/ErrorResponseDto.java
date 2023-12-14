package techno.hub.backend.dtos;

import lombok.Data;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ErrorResponseDto {
    private String error;
    private String message;
    private String stackTrace;

    private long timestamp;
    private String requestId;

    public ErrorResponseDto(String error, String message, String stackTrace) {
        this.error = error;
        this.message = message;
        this.stackTrace = stackTrace;

        ZoneId zoneId = ZoneId.of("UCT");
        this.timestamp = LocalDateTime.now(zoneId).atZone(zoneId).toEpochSecond();

        this.requestId = MDC.get("traceId");
    }
}
