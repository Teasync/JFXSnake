package jfxsnake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JFXSnake extends Application {

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(310, 310);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        gc.setStroke(Color.RED);

        GameLogic logic = new GameLogic(30, 30, 10);
        Painter painter = new Painter(gc, logic, canvas);

        Group root = new Group(canvas);

        Scene scene = new Scene(root, 310, 310);
        scene.setOnKeyPressed(new SnakeKeyboardListener(logic.getSnake()));

        stage.setTitle("JFXSnake");
        stage.setScene(scene);
        stage.show();

        Counter c = new Counter();

        AnimationTimer animator = new AnimationTimer() {

            @Override
            public void handle(long now) {
                c.incr();

                if (c.getCount() % 10 == 0) {
                    if (!logic.isGameEnd()) {
                        logic.cycle();
                        painter.drawAll();
                    } else {
                        stop();
                        
                        boolean restart = false;
                        
                        Stage s = new Stage();
                        s.setTitle("Game Over!");
                        VBox sroot = new VBox();
                        sroot.getChildren().add(new Text("Game Over! Score: "
                                + logic.getSnake().getScore()));
                        HBox hbox = new HBox();
                        hbox.getChildren().add(new Button("Close") {
                            {
                                setOnAction(e -> {
                                    s.close();
                                    stage.close();
                                });
                            }
                        });
                        hbox.getChildren().add(new Button("Retry") {
                            {
                                setOnAction(e -> {
                                    s.close();
                                    GameLogic logic = new GameLogic(30, 30, 10);
                                    Painter painter = new Painter(gc, logic, canvas);
                                    start();
                                });
                            }
                        });
                        sroot.getChildren().add(hbox);
                        sroot.setSpacing(20);
                        Scene sc = new Scene(sroot);
                        s.setScene(sc);
                        s.show();
                    }
                }
            }
        };

        animator.start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

class SnakeKeyboardListener implements EventHandler<KeyEvent> {

    private Snake s;

    public SnakeKeyboardListener(Snake s) {
        this.s = s;
    }

    @Override
    public void handle(KeyEvent e) {

        switch (e.getCode()) {
            case UP:
                if (s.getDirection() != Direction.DOWN) {
                    s.setDirection(Direction.UP);
                }
                break;
            case DOWN:
                if (s.getDirection() != Direction.UP) {
                    s.setDirection(Direction.DOWN);
                }
                break;
            case LEFT:
                if (s.getDirection() != Direction.RIGHT) {
                    s.setDirection(Direction.LEFT);
                }
                break;
            case RIGHT:
                if (s.getDirection() != Direction.LEFT) {
                    s.setDirection(Direction.RIGHT);
                }
                break;
        }
    }

}

class Counter {

    private int i;

    public Counter() {
        i = 0;
    }

    public void incr() {
        i++;
    }

    public void decr() {
        i--;
    }

    public int getCount() {
        return i;
    }
}
