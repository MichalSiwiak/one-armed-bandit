package net.coffeecoding;

import com.google.gson.Gson;
import net.coffeecoding.config.GameConfig;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("C:\\Users\\msiwiak\\IdeaProjects\\projects\\one-armed-bandit\\src\\main\\resources\\config.json")) {

            // Convert JSON to Java Object
            GameConfig gameConfig = gson.fromJson(reader, GameConfig.class);
            //System.out.println(gameConfig);

            List<Double> winnings = gameConfig.getWinnings();
            System.out.println(winnings);

            Collections.rotate(winnings, 1);
            System.out.println(winnings);

/*            // Convert JSON to JsonElement, and later to String
JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);*/



            Random random = new Random(500);

            System.out.println(random.nextInt());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
