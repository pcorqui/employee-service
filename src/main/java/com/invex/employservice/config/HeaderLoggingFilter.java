package com.invex.employservice.config;

import jakarta.servlet.FilterChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class HeaderLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            // Evitar loguear credenciales sensibles por principios DevSecOps
            if (!headerName.equalsIgnoreCase("authorization")) {
                log.info("Header [{}]: {}", headerName, request.getHeader(headerName));
            }
        });
        filterChain.doFilter(request, response);
    }
}
