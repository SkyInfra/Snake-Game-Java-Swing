import java.util.*;

public class Food {
    Position position;
    boolean isBooster;
    public void generate(Location map, Snake snake) {
        Random rand = new Random();
        do {
            int x = rand.nextInt(map.width);
            int y = rand.nextInt(map.height);
            
            position = new Position(x, y);

        } while (map.isBlocked(position) || snake.getBody().contains(position));
    }
    public boolean isBoosterFood(int foodCount) {
        return foodCount > 0 && foodCount % 6 == 0;
    }
}