package br.com.cortaai.client.configs;

import br.com.cortaai.client.dtos.feign.response.ValidateFeignResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.com.cortaai.client.facades.AuthFacade;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthFacade authFacade;
    private static final AppLogger log = AppLogger.getLogger(AuthTokenFilter.class);

    private static final String[] PUBLIC_PATHS = {
        "/api/v1/auth/login",
        "/api/v1/auth/refresh-token",
        "/api/v1/users"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getRequestURI();
        for (String path : PUBLIC_PATHS) {
            if (requestPath.equals(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isBlank()) {
            log.warn("Missing Authorization header");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Missing Authorization header\"}");
            return;
        }

        if (!authHeader.startsWith("Bearer ")) {
            log.warn("Invalid Authorization header format");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid Authorization header format. Expected: Bearer <token>\"}");
            return;
        }

        try {
            String token = authHeader.substring(7).trim();
            
            if (token.isBlank()) {
                log.warn("Empty token in Authorization header");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token is empty\"}");
                return;
            }

            ValidateFeignResponse feignResponse = authFacade.validateToken(token);
            Boolean isTokenValid = feignResponse.inValid();

            if (isTokenValid == null) {
                log.error("Token validation returned null response");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token validation service error\"}");
                return;
            }

            if (!isTokenValid) {
                log.warn("Invalid or expired token");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                return;
            }

            request.setAttribute("user", feignResponse.user());
        } catch (Exception ex) {
            log.error("Error validating token: " + ex.getMessage(), ex);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token validation failed\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
