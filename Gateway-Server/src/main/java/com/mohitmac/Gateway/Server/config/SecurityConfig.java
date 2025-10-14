package com.mohitmac.Gateway.Server.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){

        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/api/notifications/ws/**").permitAll()
                .pathMatchers("/api/categories/salon-owner/**",
                              "/api/notifications/salon-owner/**",
                              "/api/service-offering/salon-owner/**")
                    .hasRole("SALON_OWNER")
                .pathMatchers("/api/salons/**",
                              "/api/categories/**",
                              "/api/notifications/**",
                              "/api/service-offering/**",
                              "/api/booking/**",
                              "/api/payment/**",
                              "/api/users/**",
                              "/api/reviews/**")
                    .hasAnyRole("CUSTOMER", "ADMIN", "SALON_OWNER")
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(grantAuthoritiesExtractor()))
            );          


                http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .cors(cors->cors.configurationSource(corsConfigurationSource()));


           
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration  configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000 , http://localhost:5170"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS","PATCH"));
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> grantAuthoritiesExtractor() {
        JwtAuthenticationConverter  jwtAuthenticationConverter =new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakConverter());

        return new  ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
    
}
