package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GameController {

    private Map<String, Game> gameMap;

    @PostConstruct
    private void init() {
        gameMap = new HashMap<>();
    }

    @GetMapping("/demo")
    public String showMainPage1() {
        return "one-armed-bandit-form";
    }


    @RequestMapping(value = "/startGame", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> startGame(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        Game game;
        if (gameMap.get(session.getId()) == null) {
            game = new Game();
            gameMap.put(session.getId(), game);
        }else{
            game = gameMap.get(session.getId());
        }

        System.out.println(session.getId());
        System.out.println(game.toString());

        return new ResponseEntity<>(game, HttpStatus.OK);
    }


    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}