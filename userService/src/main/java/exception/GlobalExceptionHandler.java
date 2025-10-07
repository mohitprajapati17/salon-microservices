package exception;

import java.time.LocalDateTime;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import payload_response.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e,WebRequest req){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(),LocalDateTime.now(), req.getDescription(false));
        return  ResponseEntity.ok(response);
    }
}
