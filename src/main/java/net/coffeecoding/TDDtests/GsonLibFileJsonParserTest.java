package net.coffeecoding.TDDtests;

import com.google.gson.Gson;
import net.coffeecoding.config.GameConfig;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GsonLibFileJsonParserTest {
    public static void main(String[] args) throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:config.json");

        Gson gson = new Gson();
        try (Reader reader = new FileReader(file)) {

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
