package com.mohitmac.userService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // CORS is handled by the Gateway Server
    // No CORS configuration needed here
}
