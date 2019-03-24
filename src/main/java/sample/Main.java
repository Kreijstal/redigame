package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private int b = 0;
    private int x = 0;
    private int y = 0;
    private BooleanProperty rightPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        primaryStage.setTitle("Hello World");
        Canvas canvas = new Canvas(1200, 575);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(btn);
            }
        });

        btn.setLayoutX(200);
        btn.setLayoutY(300);
*/
        //root.getChildren().addAll(btn);
        root.getChildren().addAll(canvas);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //Every animation frame
                if (rightPressed.get()) {
                    x++;
                }
                if (downPressed.get()) {
                    y++;
                }

                gc.clearRect(0, 0, 1200, 575);
                gc.setFill(Color.hsb(45, 1, 1));
                gc.fillRect(x, y, 100, 100);
            }
        }.start();
        Scene scene = new Scene(root, 1200, 575);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            //This is executed everytime a key is pressed and every now and then while it is pressed
            if (e.getCode() == KeyCode.RIGHT) {
                rightPressed.setValue(true);
            }
            if (e.getCode() == KeyCode.DOWN) {
                downPressed.setValue(true);
            }
        });
        scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            //This is executed everytime a key is released
            if (e.getCode() == KeyCode.RIGHT) {
                rightPressed.setValue(false);
            }
            if (e.getCode() == KeyCode.DOWN) {
                downPressed.setValue(false);
            }

        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
