public abstract class Location {
    int width, height;
    java.util.List<Obstacle> obstacles = new java.util.ArrayList<>();
    public Location(int width, int height) {
        this.width = width;
        this.height = height;
        loadObstacles();
    }
    public abstract void loadObstacles();
    public boolean isBlocked(Position pos) {
        return obstacles.stream().anyMatch(o -> o.position.equals(pos));
    }
}