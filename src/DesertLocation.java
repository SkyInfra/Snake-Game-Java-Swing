public class DesertLocation extends Location {
    public DesertLocation(int width, int height) {
        super(width, height);
    }
    @Override
    public void loadObstacles() {
        obstacles.add(new Obstacle(new Position(3, 5)));
        obstacles.add(new Obstacle(new Position(4, 5)));
        obstacles.add(new Obstacle(new Position(9, 9)));
        obstacles.add(new Obstacle(new Position(4, 9)));
        obstacles.add(new Obstacle(new Position(15, 5)));
        obstacles.add(new Obstacle(new Position(21,9)));
        obstacles.add(new Obstacle(new Position(10, 9)));
        obstacles.add(new Obstacle(new Position(6, 19)));
        obstacles.add(new Obstacle(new Position(14, 11)));
        obstacles.add(new Obstacle(new Position(20, 22)));
        obstacles.add(new Obstacle(new Position(2, 1)));
        obstacles.add(new Obstacle(new Position(17, 3)));
        obstacles.add(new Obstacle(new Position(21, 17)));
    }
}