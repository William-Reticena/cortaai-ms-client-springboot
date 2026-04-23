package br.com.cortaai.client.configs;

import java.util.ArrayList;
import java.util.List;

public class FeignLogContext {

    private static final ThreadLocal<List<String>> feignLogs = ThreadLocal.withInitial(ArrayList::new);

    public static void addLog(String log) {
        feignLogs.get().add(log);
    }

    public static List<String> getLogs() {
        return new ArrayList<>(feignLogs.get());
    }

    public static void clear() {
        feignLogs.remove();
    }
}
