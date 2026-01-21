package com.AI.WorkingWithAI.Controller;

import org.springframework.web.bind.annotation.*;
import com.AI.WorkingWithAI.Service.GeminiService;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping
    public String chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");

        if (message == null || message.trim().isEmpty()) {
            return "{\"error\":\"Message cannot be empty\"}";
        }

        // Send the message and get AI response (with memory)
        return geminiService.chatWithMemory(message);
    }

    @PostMapping("/reset")
    public String resetMemory() {
        geminiService.resetMemory();
        return "{\"status\":\"Conversation memory cleared\"}";
    }
}
