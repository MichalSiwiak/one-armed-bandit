package net.coffeecoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class GameController {

    @GetMapping("/demo")
    public String showMainPage1() {
        return "one-armed-bandit-form";
    }


    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}