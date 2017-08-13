package jfxsnake;

import java.util.Random;

public class GameLogic {

    private Piece apple;
    private Snake snake;
    private int width;
    private int height;
    private Random r;
    private boolean eaten;
    private boolean gameEnd;
    private int pieceSize;

    public GameLogic(int w, int h, int p) {
        r = new Random();
        width = w;
        height = h;
        eaten = false;
        gameEnd = false;
        pieceSize = p;

        snake = new Snake(width / 2, height / 2, Direction.DOWN);
        apple = new Piece(r.nextInt(w), r.nextInt(h));
    }

    public void newApple() {
        if (eaten) {
            apple = new Piece(r.nextInt(width - 2) + 1, r.nextInt(height - 2) + 1);
            eaten = false;
        }
    }

    public Piece getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getPieceSize() {
        return pieceSize;
    }

    public void cycle() {
        snake.move();

        if (snake.isCollision(apple)) {
            eaten = true;
            snake.grow();
        }

        newApple();

        if (snake.isSelfCollision()) {
            gameEnd();
        }

        if (snake.isSelfCollision()
                || snake.getSnakeBody().get(snake.getLength() - 1).getX() >= width
                || snake.getSnakeBody().get(snake.getLength() - 1).getX() <= 0
                || snake.getSnakeBody().get(snake.getLength() - 1).getY() >= height
                || snake.getSnakeBody().get(snake.getLength() - 1).getY() <= 0) {
            //System.out.println("GAME END WHAT THE FUCK IS WRONG");
            gameEnd();
        }
    }

    private void gameEnd() {
        gameEnd = true;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }
}
