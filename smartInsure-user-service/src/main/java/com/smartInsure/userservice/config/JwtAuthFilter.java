package com.smartInsure.userservice.config;

import com.smartInsure.userservice.client.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    public JwtAuthFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("JwtAuthFilter CALLED for: " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth header = " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            Map<String, Object> result = authClient.validateAndGetUser(authHeader);
            System.out.println("Auth result = " + result);

            if (Boolean.TRUE.equals(result.get("valid"))) {

                String username = (String) result.get("username");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of()   // no roles for now
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("username", username);
            }
        }

        filterChain.doFilter(request, response);
    }
}