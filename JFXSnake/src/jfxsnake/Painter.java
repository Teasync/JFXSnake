
package jfxsnake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {
    
    private GraphicsContext gc;
    private GameLogic logic;
    private Canvas c;
    
    public Painter(GraphicsContext gc, GameLogic logic, Canvas c) {
        this.gc = gc;
        this.logic = logic;
        this.c = c;
    }
    
    public void drawAll() {
        gc.clearRect(0, 0, c.getWidth(), c.getHeight());
        gc.setFill(Color.BLACK);
        for (Piece p : logic.getSnake().getSnakeBody()) {
            gc.fillRect(p.getX()*logic.getPieceSize(), 
                    p.getY()*logic.getPieceSize(), logic.getPieceSize(),
                    logic.getPieceSize());
        }
        gc.setFill(Color.RED);
        gc.fillOval(logic.getApple().getX()*logic.getPieceSize(),
                logic.getApple().getY()*logic.getPieceSize(),
                logic.getPieceSize(), logic.getPieceSize());
        gc.strokeRect(0, 0, 310, 310);
    }
}
