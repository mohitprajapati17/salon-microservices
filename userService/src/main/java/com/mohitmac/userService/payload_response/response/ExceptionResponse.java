package com.mohitmac.userService.payload_response.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    public String message;
    public LocalDateTime timestamp;
    public String error;
    
}
