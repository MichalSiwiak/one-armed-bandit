package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import net.coffeecoding.entity.SessionGame;
import net.coffeecoding.model.Spin;
import net.coffeecoding.service.SessionGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class GameController {

    @Autowired
    private SessionGameService sessionGameService;

    private Map<String, Game> activeGames;

    @PostConstruct
    private void init() {
        activeGames = new HashMap<>();
    }

    @GetMapping("/demo")
    public String showMainPage() {
        return "one-armed-bandit-form";
    }

    @GetMapping("/sessions")
    public String showActiveGames() {
        return "sessions-form";
    }

    /**
     * HTTP GET - create new game if not exist in session
     */
    @RequestMapping(value = "/startGame", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Game> startGame(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();

        if (activeGames.get(session.getId()) == null) {

            if (activeGames.size() >= 5) {//number of limit of games should be in config file

                Game game = new Game();
                game.setGameId(session.getId());
                game.setStatus("ERROR");
                game.setRno(0);
                game.setWin(0);
                game.setSymbols(game.getInitSymbols());
                game.setMessage("The number of games has been exceeded - please close abandoned games");

                return new ResponseEntity<>(game, HttpStatus.OK);

            } else {
                Game game = new Game();
                game.setGameId(session.getId());
                game.setStatus("OK");
                game.setMessage("Game created");
                activeGames.put(session.getId(), game);

                SessionGame sessionGame = new SessionGame();
                sessionGame.setGameId(game.getGameId());
                sessionGame.setCreated(new Date());
                sessionGame.setStatus("Active");
                sessionGameService.saveSessionGame(sessionGame);

                return new ResponseEntity<>(game, HttpStatus.OK);
            }

        } else {
            Game game = activeGames.get(session.getId());
            game.setMessage("Game already exist in this session");
            return new ResponseEntity<>(game, HttpStatus.OK);
        }
    }

    /**
     * HTTP POST - execute spin in game
     */
    @RequestMapping(value = "/spin", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> spin(@RequestBody Spin spin) {

        Game game = activeGames.get(spin.getGameId());

        if (game != null) {
            game.spin((byte) spin.getRno());
            game.setRno(game.getRno() + 1);
            game.setMessage("Spin created");
            SessionGame sessionGame = getActiveSessionGameById(spin.getGameId());
            sessionGame.setLastSpin(new Date());
            sessionGame.setWin(game.getWin());
            sessionGameService.saveSessionGame(sessionGame);

            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            Game notFoundGame = new Game();
            notFoundGame.setGameId(spin.getGameId());
            notFoundGame.setStatus("ERROR");
            notFoundGame.setRno(0);
            notFoundGame.setWin(0);
            notFoundGame.setSymbols(notFoundGame.getInitSymbols());
            notFoundGame.setMessage("Game was closed or session has expired");

            return new ResponseEntity<>(notFoundGame, HttpStatus.OK);
        }
    }

    /**
     * HTTP POST - end the game
     */
    @RequestMapping(value = "/endGame", consumes = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Game> endGame(@RequestBody String gameId) {

        Game game = activeGames.get(gameId);

        if (game != null) {
            activeGames.remove(gameId);
            SessionGame sessionGame = getActiveSessionGameById(gameId);
            sessionGame.setStatus("Closed"); //// warning!!! could Null Pointer Exception - test it
            sessionGameService.saveSessionGame(sessionGame);
            game.setSymbols(game.getInitSymbols());
            game.setMessage("Game was closed");

            return new ResponseEntity<>(game, HttpStatus.OK);

        } else {
            Game notFoundGame = new Game();
            notFoundGame.setGameId(gameId);
            notFoundGame.setStatus("ERROR");
            notFoundGame.setRno(0);
            notFoundGame.setWin(0);
            notFoundGame.setSymbols(notFoundGame.getInitSymbols());
            notFoundGame.setMessage("Game was closed or session has expired");

            return new ResponseEntity<>(notFoundGame, HttpStatus.OK);
        }
    }

    /**
     * HTTP GET - get all saved games
     */
    @RequestMapping(value = "/activeGames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> sessions() {
        return new ResponseEntity<>(sessionGameService.findAll(), HttpStatus.OK);
    }

    /**
     * HTTP DELETE - Close sessionGame
     */
    @RequestMapping(value = "/activeGames/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSessionGame(@PathVariable("id") String id) {
        SessionGame sessionGame = getActiveSessionGameById(id);
        if (sessionGame != null) {
            sessionGame.setStatus("Closed");
            activeGames.remove(id);
            sessionGameService.saveSessionGame(sessionGame);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private SessionGame getActiveSessionGameById(String id) {

        List<SessionGame> sessionGames = sessionGameService.findAll();
        for (SessionGame sessionGame : sessionGames) {
            if (sessionGame.getGameId().equals(id) && sessionGame.getStatus().equals("Active")) {
                return sessionGame;
            }
        }
        return null; // warning!!! could Null Pointer Exception - test it
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}