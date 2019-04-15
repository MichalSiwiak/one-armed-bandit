package net.coffeecoding.controller;

import net.coffeecoding.model.Game;
import net.coffeecoding.model.GameListRow;
import net.coffeecoding.model.MessageResponseBody;
import net.coffeecoding.model.SpinRequestBody;
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

    private Map<String, Game> gameMap;
    private List<GameListRow> gameListRowList;

    @PostConstruct
    private void init() {
        gameMap = new HashMap<>();
        gameListRowList = new ArrayList<>();
    }

    @GetMapping("/demo")
    public String showMainPage() {
        return "one-armed-bandit-form";
    }


    @RequestMapping(value = "/startGame", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> startGame(HttpServletRequest httpServletRequest, Model model) {

        HttpSession session = httpServletRequest.getSession();

        Game game;

        if (gameMap.get(session.getId()) == null) {

            if (gameMap.size() > 5) {
                //zrobić w pliku konfiguracyjnym

                MessageResponseBody messageResponseBody = new MessageResponseBody();
                messageResponseBody.setGameId(session.getId());
                messageResponseBody.setStatus("ERROR");
                messageResponseBody.setMessage("The number of games has been exceeded.");

                return new ResponseEntity<>(messageResponseBody, HttpStatus.IM_USED);

            } else {
                game = new Game();
                game.setGameId(session.getId());
                game.setStatus("OK");
                game.setMessage("Game Created.");
                gameMap.put(session.getId(), game);

                model.addAttribute("reels", game.getSymbols());

                MessageResponseBody messageResponseBody = new MessageResponseBody();
                messageResponseBody.setGameId(session.getId());
                messageResponseBody.setStatus("OK");
                messageResponseBody.setMessage("Game Created.");
                messageResponseBody.setRno(game.getRno());

                GameListRow gameListRow = new GameListRow();
                gameListRow.setGameId(game.getGameId());
                gameListRow.setCreated(new Date());
                gameListRowList.add(gameListRow);

                return new ResponseEntity<>(game, HttpStatus.CREATED);
            }

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

            GameListRow gameListRow = getGameListRowById(spinRequestBody.getGameId());
            gameListRow.setLastSpin(new Date());

            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {

            MessageResponseBody messageResponseBody = new MessageResponseBody();
            messageResponseBody.setGameId(spinRequestBody.getGameId());
            messageResponseBody.setStatus("ERROR");
            messageResponseBody.setMessage("Game was deleted or session has expired.");

            return new ResponseEntity<>(messageResponseBody, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/endGame", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<MessageResponseBody> endGame(@RequestBody String gameId) {

        Game game = gameMap.get(gameId);

        if (game != null) {

            MessageResponseBody messageResponseBody = new MessageResponseBody();
            gameMap.remove(gameId);

            messageResponseBody.setGameId(gameId);
            messageResponseBody.setStatus("OK");
            messageResponseBody.setRno(game.getRno());
            messageResponseBody.setMessage("Game was closed.");

            return new ResponseEntity<>(messageResponseBody, HttpStatus.OK);

        } else {

            MessageResponseBody messageResponseBody = new MessageResponseBody();
            messageResponseBody.setGameId(gameId);
            messageResponseBody.setStatus("ERROR");
            messageResponseBody.setMessage("Game was deleted or session has expired.");

            return new ResponseEntity<>(messageResponseBody, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> sessions() {
        return new ResponseEntity<>(gameListRowList, HttpStatus.OK);
    }

    private GameListRow getGameListRowById(String id) {
        for (GameListRow gameListRow : gameListRowList) {
            if (gameListRow.getGameId() == id) {
                return gameListRow;
            }
        }
        return null;
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";

    }
}