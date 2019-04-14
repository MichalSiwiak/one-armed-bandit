package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import net.coffeecoding.model.MessageResponseBody;
import net.coffeecoding.model.SpinRequestBody;
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
        MessageResponseBody messageResponseBody = new MessageResponseBody();
        Game game;

        if (gameMap.get(session.getId()) == null) {
            game = new Game();
            game.setGameId(session.getId());
            game.setStatus("OK");
            gameMap.put(session.getId(), game);

            messageResponseBody.setGameId(session.getId());
            messageResponseBody.setStatus("OK");
            messageResponseBody.setMessage("Game Created.");
            messageResponseBody.setRno(game.getRno());
            return new ResponseEntity<>(messageResponseBody, HttpStatus.CREATED);
        } else {
            game = gameMap.get(session.getId());
            game.setMessage("Game Already Exist.");
            return new ResponseEntity<>(game, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/spin", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> spin(@RequestBody SpinRequestBody spinRequestBody) {

        System.out.println(spinRequestBody.getGameId());
        System.out.println(spinRequestBody.getRno());
        Game game = gameMap.get(spinRequestBody.getGameId());

        if (game != null) {
            game.spin();
            game.setRno(game.getRno() + 1);
            game.setMessage("Spin Created.");
        } else {
            MessageResponseBody messageResponseBody = new MessageResponseBody();
            messageResponseBody.setGameId(spinRequestBody.getGameId());
            messageResponseBody.setStatus("ERROR");
            messageResponseBody.setMessage("Game was deleted or session has expired.");
            return new ResponseEntity<>(messageResponseBody, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game, HttpStatus.OK);

    }


    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}