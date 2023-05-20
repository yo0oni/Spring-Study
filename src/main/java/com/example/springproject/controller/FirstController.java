package com.example.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "요정");
        return "greetings"; // templates/greetings.mustache 를 찾아서 브라우저로 바로 전송해줌
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("username", "요정");
        return "goodbye"; // templates/greetings.mustache 를 찾아서 브라우저로 바로 전송해줌
    }
}