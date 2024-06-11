import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    public static Map<String, Map<String, String>> loadConfig(String filename) throws IOException {
        Map<String, Map<String, String>> config = new HashMap<>();
        Map<String, String> currentSection = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    String section = line.substring(1, line.length() - 1);
                    currentSection = new HashMap<>();
                    config.put(section, currentSection);
                } else if (currentSection != null && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        currentSection.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        }

        return config;
    }
}
