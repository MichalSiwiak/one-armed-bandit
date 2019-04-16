package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import net.coffeecoding.model.SessionGame;
import net.coffeecoding.model.Spin;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class GameController {

    private Map<String, Game> activeGames;
    private List<SessionGame> sessionGames;

    @PostConstruct
    private void init() {
        activeGames = new HashMap<>();
        sessionGames = new ArrayList<>();
    }

    @GetMapping("/demo")
    public String showMainPage() {
        return "one-armed-bandit-form";
    }

    @GetMapping("/sessions")
    public String showActiveGames(Model model) {
        return "sessions-form";
    }


    @RequestMapping(value = "/startGame", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Game> startGame(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();

        if (activeGames.get(session.getId()) == null) {

            if (activeGames.size() >= 5) {//zrobić w pliku konfiguracyjnym

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
                game.setWin(0);
                game.setMessage("Game Created");
                activeGames.put(session.getId(), game);

                SessionGame sessionGame = new SessionGame();
                sessionGame.setGameId(game.getGameId());
                sessionGame.setCreated(new Date());
                sessionGame.setStatus("Active");
                sessionGame.setNumberOfGame(sessionGames.size()+1);
                sessionGames.add(sessionGame);

                return new ResponseEntity<>(game, HttpStatus.OK);
            }

        } else {
            Game game = activeGames.get(session.getId());
            game.setMessage("Game Already Exist");
            return new ResponseEntity<>(game, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/spin", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> spin(@RequestBody Spin spin) {

        Game game = activeGames.get(spin.getGameId());

        if (game != null) {
            game.spin();
            game.setRno(game.getRno() + 1);
            game.setMessage("Spin Created");
            SessionGame sessionGame = getActiveSessionGameById(spin.getGameId());
            sessionGame.setLastSpin(new Date());
            sessionGame.setWin(game.getWin());

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

    @RequestMapping(value = "/endGame", consumes = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Game> endGame(@RequestBody String gameId) {

        Game game = activeGames.get(gameId);

        if (game != null) {
            activeGames.remove(gameId);
            SessionGame sessionGame = getActiveSessionGameById(gameId);
            sessionGame.setStatus("Closed"); //can null
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

    @RequestMapping(value = "/activeGames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> sessions() {
        return new ResponseEntity<>(sessionGames, HttpStatus.OK);
    }

    /**
     * HTTP DELETE - Delete sales
     */
    @RequestMapping(value = "/activeGames/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSales(@PathVariable("id") String id) {
        SessionGame sessionGame = getActiveSessionGameById(id);
        if (sessionGame != null) {
            sessionGame.setStatus("Closed");
            activeGames.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private SessionGame getActiveSessionGameById(String id) {
        for (SessionGame sessionGame : sessionGames) {
            if (sessionGame.getGameId().equals(id) && sessionGame.getStatus().equals("Active")) {
                return sessionGame;
            }
        }
        return null; // new GameListROw ?? zamiast null
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}