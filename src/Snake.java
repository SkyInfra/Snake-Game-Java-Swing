import java.util.*;

public class Snake {
    LinkedList<Position> body = new LinkedList<>();
    Direction direction;
    double speed;
    public Snake(Position start, Direction dir, double speed) {
        body.add(start);
        this.direction = dir;
        this.speed = speed;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {
        Position head = getHeadPosition();
        Position newHead = new Position(head.x + direction.dx, head.y + direction.dy);
        body.addFirst(newHead);
        body.removeLast();
    }
    public void grow() {
        Position tail = body.getLast();
        body.addLast(new Position(tail.x, tail.y));
    }
    public void changeDirection(Direction d) {
        this.direction = d;
    }
    public void increaseSpeed(double percent) {
        this.speed *= (1 + percent / 100);
    }
    public Position getHeadPosition() {
        return body.getFirst();
    }
    public java.util.List<Position> getBody() {
        return body;
    }
}