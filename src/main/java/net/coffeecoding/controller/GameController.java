package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @GetMapping("/demo")
    public String showMainPage1() {
        return "one-armed-bandit-form";
    }


    @RequestMapping(value = "/startGame", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> startGame()  {
        Game game = new Game();
        System.out.println(game.toString());
        return new ResponseEntity<>(game, HttpStatus.OK);
    }


    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}