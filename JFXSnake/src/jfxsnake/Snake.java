package jfxsnake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Piece> snakeBody;
    private Direction direction;
    private boolean grow;
    
    public Snake(int startX, int startY, Direction startD) {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Piece(startX, startY));
        direction = startD;
        grow = true;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction d) {
        direction = d;
    }
    
    public int getLength() {
        return snakeBody.size();
    }
    
    public List<Piece> getSnakeBody() {
        return snakeBody;
    }
    
    public void grow() {
        grow = true;
    }
    
    public void move() {
        switch (direction) {
            case UP:
                snakeBody.add(snakeBody.size(), 
                        new Piece(snakeBody.get(snakeBody.size() - 1).getX(),
                        snakeBody.get(snakeBody.size() - 1).getY() - 1));
                break;
            case DOWN:
                snakeBody.add(snakeBody.size(), 
                        new Piece(snakeBody.get(snakeBody.size() - 1).getX(),
                        snakeBody.get(snakeBody.size() - 1).getY() + 1));
                break;
            case LEFT:
                snakeBody.add(snakeBody.size(), 
                        new Piece(snakeBody.get(snakeBody.size() - 1).getX() - 1,
                        snakeBody.get(snakeBody.size() - 1).getY()));
                break;
            case RIGHT:
                snakeBody.add(snakeBody.size(), 
                        new Piece(snakeBody.get(snakeBody.size() - 1).getX() + 1,
                        snakeBody.get(snakeBody.size() - 1).getY()));
                break;
        }
        if (!grow) {
            snakeBody.remove(0);
        }
        if (snakeBody.size() >= 3) {
            grow = false;
        }
    }
    
    public boolean isCollision(Piece p) {
        return snakeBody.get(snakeBody.size() - 1).toString()
                .equals(p.toString());
    }
    
    public boolean isSelfCollision() {
        for (Piece p : snakeBody) {
            for (Piece q : snakeBody) {
                if (p.toString().equals(q.toString()) && !p.equals(q)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public int getScore() {
        return (getLength() - 3) * 10;
    }
}
