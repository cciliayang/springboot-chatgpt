package com.learningchatgpt.springbootgpt.controller;

import com.learningchatgpt.springbootgpt.dto.ChatBotRequest;
import com.learningchatgpt.springbootgpt.dto.ChatBotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatBotController {
    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;


    @Autowired
    private RestTemplate template;

//    @GetMapping("/chat")
//    public String chat(@RequestParam("prompt") String prompt, Model model2) {
//        ChatBotRequest request = new ChatBotRequest(model, prompt);
//        ChatBotResponse chatGptResponse = template.postForObject(apiURL, request, ChatBotResponse.class);
//
//        return chatGptResponse.getChoices().get(0).getMessage().getContent();
//    }

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt, Model modelUI) {
        ChatBotRequest request = new ChatBotRequest(model, prompt);
        ChatBotResponse chatGptResponse = template.postForObject(apiURL, request, ChatBotResponse.class);

        // Add model attributes for Thymeleaf template
        modelUI.addAttribute("prompt", prompt);
        modelUI.addAttribute("chatBotResponse", chatGptResponse.getChoices().get(0).getMessage().getContent());
        return modelUI.toString();

    }

}





