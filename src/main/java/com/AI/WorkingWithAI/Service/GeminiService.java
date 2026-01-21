package com.AI.WorkingWithAI.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GeminiService {

    @Value("${google.ai.api.key}")
    private String apiKey;

    @Value("${google.ai.api.base-url}")
    private String apiBaseUrl;

    @Value("${google.ai.model.name}")
    private String modelName;

    private final RestTemplate restTemplate = new RestTemplate();

    // In-memory conversation history
    private final List<String> conversationHistory = new ArrayList<>();

    // Add new message, send entire conversation to Gemini
    public String chatWithMemory(String userMessage) {
        // Add user message to history
        conversationHistory.add("User: " + userMessage);

        // Combine full conversation into one string
        String fullConversation = String.join("\n", conversationHistory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", fullConversation)
                        ))
                )
        );

        String url = String.format("%s/models/%s:generateContent?key=%s", apiBaseUrl, modelName, apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            Map<String, Object> body = response.getBody();
            if (body != null && body.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    String aiResponse = (String) parts.get(0).get("text");

                    // Save AI response to conversation history
                    conversationHistory.add("Gemini: " + aiResponse);
                    return aiResponse;
                }
            }
            return "{\"error\":\"No response from Gemini API\"}";
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }

    // Reset conversation memory
    public void resetMemory() {
        conversationHistory.clear();
    }
}
