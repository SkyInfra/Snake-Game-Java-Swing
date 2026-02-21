public class ForestLocation extends Location {
    public ForestLocation(int width, int height) {
        super(width, height);
    }
    @Override
    public void loadObstacles() {
        obstacles.add(new Obstacle(new Position(5, 5)));
        obstacles.add(new Obstacle(new Position(15, 5)));
        obstacles.add(new Obstacle(new Position(21,8)));
        obstacles.add(new Obstacle(new Position(14, 9)));
        obstacles.add(new Obstacle(new Position(21, 19)));
        obstacles.add(new Obstacle(new Position(23, 23)));
        obstacles.add(new Obstacle(new Position(14, 10)));
        obstacles.add(new Obstacle(new Position(23, 21)));
        obstacles.add(new Obstacle(new Position(14, 11)));
        obstacles.add(new Obstacle(new Position(21, 20)));
        obstacles.add(new Obstacle(new Position(23, 22)));
        obstacles.add(new Obstacle(new Position(2, 1)));
        obstacles.add(new Obstacle(new Position(17, 3)));
        obstacles.add(new Obstacle(new Position(20, 17)));
        obstacles.add(new Obstacle(new Position(23, 2)));
    }
}