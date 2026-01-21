# 🚀 WorkingWithAI - GeminiService 🤖

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)](https://spring.io/projects/spring-boot) 
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/) 
[![API](https://img.shields.io/badge/API-Gemini-blueviolet)](https://developers.google.com/)

---

**GeminiService** is a **Spring Boot service** that integrates with **Google’s Gemini AI** to provide **context-aware conversational AI**. 🧠💬  

It keeps an **in-memory conversation history**, sends the **full conversation** to the AI for **coherent responses**, and allows **resetting the memory**. 🔄✨  

Use it via a **REST endpoint** to chat with the AI or clear the session. 🛠️

---

### 🌟 Features

- 🧠 **Context-aware chat with memory**  
- 🔄 **Reset conversation memory**  
- ⚡ **Simple integration with Spring Boot**  

---

### 📌 Quick Start

1. Configure `application.properties` with your API key and model.  
2. Send POST requests to `/api/chat` to interact.  
3. POST to `/api/chat/reset` to clear memory.  

---

> 💡 **Tip:** Memory is session-based — restarting the app clears all history.
