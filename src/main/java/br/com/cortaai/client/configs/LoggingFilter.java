package br.com.cortaai.client.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class LoggingFilter implements Filter {

    private static final AppLogger log = AppLogger.getLogger(LoggingFilter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final int CACHE_LIMIT = 1024 * 1024;

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";   // Color code for highlighting HTTP request logs in console output
    private static final String CYAN = "\u001B[36m";    // Color code for highlighting HTTP response logs in console output
    private static final String RED = "\u001B[31m";     // Color code for highlighting error response logs in console output

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

        log.info(GREEN + "════════════════════════════════════════════════ REQUEST ════════════════════════════════════════════════" + RESET);

        StringBuilder requestLog = new StringBuilder();
        requestLog.append(GREEN).append("➡️ REQUEST").append(RESET).append(" - ");
        requestLog.append("Method: ").append(req.getMethod()).append(" | ");
        requestLog.append("URI: ").append(req.getRequestURI()).append(" | ");
        requestLog.append("IP: ").append(getClientIp(req));

        String contentType = req.getContentType();
        if (contentType != null && !contentType.isBlank()) {
            requestLog.append(" | Content-Type: ").append(contentType);
        }

        if (requestBody != null && !requestBody.isBlank()) {
            requestLog.append(" | Body: ").append(requestBody);
        }

        log.info(requestLog.toString());

        // Log das chamadas Feign que ocorreram durante a requisição
        List<String> feignLogs = FeignLogContext.getLogs();
        for (String feignLog : feignLogs) {
            log.info(feignLog);
        }
        FeignLogContext.clear();

        StringBuilder responseLog = new StringBuilder();
        if (res.getStatus() >= 400) {
            responseLog.append(RED).append("⬅️ RESPONSE").append(RESET).append(" - ");
        } else {
            responseLog.append(CYAN).append("⬅️ RESPONSE").append(RESET).append(" - ");
        }

        responseLog.append("Status: ").append(res.getStatus()).append(" | ");
        responseLog.append("Time: ").append(duration).append("ms");

        String resContentType = res.getContentType();
        if (resContentType != null && !resContentType.isBlank()) {
            responseLog.append(" | Content-Type: ").append(resContentType);
        }

        if (responseBody != null && !responseBody.isBlank()) {
            responseLog.append(" | Body: ").append(responseBody);
        }

        if (res.getStatus() >= 400) {
            log.error(responseLog.toString());
        } else {
            log.info(responseLog.toString());
        }

        log.info(CYAN + "════════════════════════════════════════════════ RESPONSE ════════════════════════════════════════════════" + RESET);

        res.copyBodyToResponse();
    }

    private String getBody(byte[] content) {
        if (content == null || content.length == 0) return "";

        String body = new String(content, StandardCharsets.UTF_8);

        try {
            Object obj = objectMapper.readValue(body, Object.class);
            body = objectMapper.writeValueAsString(obj);
        } catch (Exception ignored) {}

        return body;
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }

        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }

        return request.getRemoteAddr();
    }
}

