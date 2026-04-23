package br.com.cortaai.client.configs;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignCustomLogger extends Logger {

    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    @Override
    protected void log(String configKey, String format, Object... args) {
        // Não loga no nível debug para evitar duplicação
    }

    @Override
    public void logRequest(String configKey, Logger.Level logLevel, Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(GREEN).append("  ➡️ FEIGN REQUEST").append(RESET).append(" - ");
        sb.append("Method: ").append(request.httpMethod()).append(" | ");
        sb.append("URL: ").append(request.url());

        if (request.body() != null) {
            String body = new String(request.body(), StandardCharsets.UTF_8);
            sb.append(" | Body: ").append(body);
        }

        if (!request.headers().isEmpty()) {
            sb.append(" | Headers: ").append(request.headers());
        }

        FeignLogContext.addLog(sb.toString());
    }

    @Override
    public Response logAndRebufferResponse(String configKey, Logger.Level logLevel, Response response, long elapsedTime)
            throws IOException {

        StringBuilder sb = new StringBuilder();

        if (response.status() >= 400) {
            sb.append(RED).append("  ⬅️ FEIGN RESPONSE").append(RESET).append(" - ");
        } else {
            sb.append(CYAN).append("  ⬅️ FEIGN RESPONSE").append(RESET).append(" - ");
        }

        sb.append("Status: ").append(response.status()).append(" | ");
        sb.append("Time: ").append(elapsedTime).append("ms | ");
        sb.append("URL: ").append(response.request().url());

        if (response.body() != null) {
            byte[] bodyBytes = Util.toByteArray(response.body().asInputStream());
            String body = new String(bodyBytes, StandardCharsets.UTF_8);
            sb.append(" | Body: ").append(body);
            response = response.toBuilder().body(bodyBytes).build();
        }

        if (!response.headers().isEmpty()) {
            sb.append(" | Headers: ").append(response.headers());
        }

        FeignLogContext.addLog(sb.toString());

        return response;
    }
}
