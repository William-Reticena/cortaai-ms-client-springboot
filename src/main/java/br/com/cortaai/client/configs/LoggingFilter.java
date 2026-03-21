package br.com.cortaai.client.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoggingFilter implements Filter {

    private static final AppLogger log = AppLogger.getLogger(LoggingFilter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final int CACHE_LIMIT = 1024 * 1024;

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";   // request
    private static final String CYAN = "\u001B[36m";    // response
    private static final String RED = "\u001B[31m";     // erro

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper req =
                new ContentCachingRequestWrapper((HttpServletRequest) request, CACHE_LIMIT);

        ContentCachingResponseWrapper res =
                new ContentCachingResponseWrapper((HttpServletResponse) response);

        Long start = System.currentTimeMillis();

        chain.doFilter(req, res);

        Long duration = System.currentTimeMillis() - start;

        String requestBody = getBody(req.getContentAsByteArray());
        String responseBody = getBody(res.getContentAsByteArray());

        log.info(
                GREEN + "➡️ method={} uri={} body={}" + RESET,
                req.getMethod(),
                req.getRequestURI(),
                requestBody
        );

        if (res.getStatus() >= 400) {
            log.error(
                    RED + "⬅️ status={} time={}ms body={}" + RESET,
                    res.getStatus(),
                    duration,
                    responseBody
            );
        } else {
            log.info(
                    CYAN + "⬅️ status={} time={}ms body={}" + RESET,
                    res.getStatus(),
                    duration,
                    responseBody
            );
        }

        res.copyBodyToResponse();
    }

    private String getBody(byte[] content) {
        if (content.length == 0) return "";

        String body = new String(content, StandardCharsets.UTF_8);

        try {
            Object obj = objectMapper.readValue(body, Object.class);
            body = objectMapper.writeValueAsString(obj);
        } catch (Exception ignored) {}

        return body;
    }
}
