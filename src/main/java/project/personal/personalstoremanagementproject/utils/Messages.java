package project.personal.personalstoremanagementproject.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Messages {

    private static final Map<String, String> messageMap = new HashMap<>();

    // Singleton Instance
    private static final Messages INSTANCE = new Messages();

    // Private constructor to prevent direct instantiation
    private Messages() {
        loadMessages();
    }

    // Singleton accessor
    public static Messages getInstance() {
        return INSTANCE;
    }

    // Load messages from file (only once)
    private void loadMessages() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("constantcsv/messageCsv.csv")))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    messageMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading message file", e);
        }
    }

    // Retrieve message
    public String getMessages(String messageId, String... params) {
        String messageText = messageMap.get(messageId);
        if (messageText != null) {
            for (int i = 0; i < params.length; i++) {
                messageText = messageText.replace("{" + i + "}", params[i]);
            }
        }
        return messageText != null ? messageText : "Message not found for ID: " + messageId;
    }
}
