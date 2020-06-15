
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;


public class Main extends Application implements EventHandler<ActionEvent> {
    int hasBeenMoved = 0;
    int firsTime = 0;
    int counter = 0;
    int start = 0;
    int r = 213;
    int g = 208;
    int b = 196;
    Stage stage;
    Button new_game = new Button("New Game");
    Button quit = new Button("     Quit      ");
    Group root = new Group();
    public static Label labels[] = new Label[16];
    public static Rectangle rectangle[] = new Rectangle[16];
    public Label score = new Label("Score");
    public Label howMuch = new Label("0");
    Scene scene = new Scene(root, 1000,800,Color.rgb(175,166,143));

    public static void main(String[] args) {
        int number = 0;
        int x = 30;
        for (int i = 0; i < 4; i++){
            int y= 30;
            for (int j = 0; j < 4; j++){
                labels[number] = new Label("");
                rectangle[number] = new Rectangle(170,170);
                rectangle[number].relocate(x, y);
                rectangle[number].setFill(Color.rgb(213,208,196));
                labels[number].relocate(rectangle[number].getLayoutX()+62,rectangle[number].getLayoutY()+30);
                y = 190+y;
                number++;
            }
            x = 190+x;
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        new_game.relocate(380, 290);
        new_game.setFont(Font.font(40));
        new_game.setTextFill(Color.BLACK);
        new_game.setOnAction(this);

        quit.relocate(380 ,400);
        quit.setFont(Font.font(40));
        quit.setTextFill(Color.BLACK);
        quit.setOnAction(this);
        root.getChildren().addAll(new_game,quit);
        stage.setScene(scene);
        stage.show();


    }
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == quit){
            stage.close();
        }else if (event.getSource() == new_game){
            start = 1;
            root.getChildren().removeAll(quit,new_game);
            if (firsTime == 0 && start == 1){
                howMuch.relocate(830,380);
                howMuch.setFont(Font.font(40));
                score.relocate(830,320);
                score.setFont(Font.font(40));
                root.getChildren().addAll(score,howMuch);
            }
            root.getChildren().addAll(rectangle);
            root.getChildren().addAll(labels);
            putNumber(rectangle);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override

                public void handle(KeyEvent event) {
                    hasBeenMoved = 0;
                    root.getChildren().removeAll(rectangle);
                    root.getChildren().removeAll(labels);
                    if (event.getCode() == KeyCode.UP){
                        if (start == 1) {
                            moveUp();
                        }

                    }else if (event.getCode() == KeyCode.DOWN){
                        if (start == 1) {
                            moveDown();
                        }
                    }else if(event.getCode() == KeyCode.RIGHT) {
                        if (start == 1) {
                            moveRight();
                        }
                    }else if(event.getCode() == KeyCode.LEFT){
                        if (start == 1) {
                            moveLeft();
                        }
                    }else if (event.getCode() == KeyCode.ENTER && start == 0){
                        if (counter == 0 ) {
                            root.getChildren().removeAll(quit, new_game);
                            start = 1;
                        }
                        else {
                        }
                    }
                    if (event.getSource() == quit){
                        stage.close();
                    }else if (event.getSource() == new_game){
                        start = 1;
                        root.getChildren().removeAll(quit,new_game);
                    }
                    Label label = new Label();

                    for (int i = 0; i < 16; i++){
                        String num;
                        int mines = 0;
                        int dicrease = 0;
                        if (!(labels[i].getText().matches(""))) {
                            mines = Integer.parseInt(labels[i].getText())%3;
                            dicrease = Integer.parseInt(labels[i].getText())%150;
                            num = Integer.toString(Integer.parseInt(labels[i].getText()));
                        }else {
                            num = "";
                        }
                        if ( !(labels[i].getText().matches("")) && Integer.parseInt(labels[i].getText()) < 10 ){
                            labels[i].setText(num);
                            labels[i].relocate(rectangle[i].getLayoutX()+62,rectangle[i].getLayoutY()+30);
                            labels[i].setFont(Font.font(80));
                        }else if ( !(labels[i].getText().matches("")) && Integer.parseInt(labels[i].getText()) < 100 ){
                            labels[i].setText(num);
                            labels[i].relocate(rectangle[i].getLayoutX()+42,rectangle[i].getLayoutY()+30);
                            labels[i].setFont(Font.font(80));
                        }else if ( !(labels[i].getText().matches("")) && Integer.parseInt(labels[i].getText()) < 1000){
                            labels[i].setText(num);
                            labels[i].relocate(rectangle[i].getLayoutX()+22,rectangle[i].getLayoutY()+30);
                            labels[i].setFont(Font.font(80));
                        }else if ( !(labels[i].getText().matches("")) && Integer.parseInt(labels[i].getText()) < 10000) {
                            labels[i].setText(num);
                            labels[i].relocate(rectangle[i].getLayoutX() + 12, rectangle[i].getLayoutY() + 30);
                            labels[i].setFont(Font.font(60));

                        }


                        if ((labels[i].getText().matches(""))){
                            labels[i].setText("");
                            rectangle[i].setFill(Color.rgb(r, g, b));
                        }else if (Integer.parseInt(labels[i].getText()) == 4){
                            rectangle[i].setFill(Color.rgb(r-100, g, b));
                        }else if(Integer.parseInt(labels[i].getText()) == 32) {
                            rectangle[i].setFill(Color.rgb(r-10, g-32, b-32));
                        }
                        else if(Integer.parseInt(labels[i].getText()) == 2){
                            rectangle[i].setFill(Color.rgb(r-50, g, b));
                        }else if (Integer.parseInt(labels[i].getText()) == 8){
                            rectangle[i].setFill(Color.rgb(r-25, g-100, b));
                        }else if (mines == 0){
                            rectangle[i].setFill(Color.rgb(r-dicrease, g, b));
                        }else if (mines == 1){
                            rectangle[i].setFill(Color.rgb(r, g-dicrease, b));
                        }else if (mines == 2){
                            rectangle[i].setFill(Color.rgb(r, g, b-dicrease));
                        }


                    }


                    int newScore = 0;
                    for (int i = 0; i < 16; i++){
                        if (!(labels[i].getText().matches(""))){
                            newScore += Integer.parseInt(labels[i].getText());
                        }
                    }

                    if (start == 1){
                        root.getChildren().addAll(rectangle);
                        if (hasBeenMoved == 1 )
                            putNumber(rectangle);
                        root.getChildren().addAll(labels);
                        firsTime = 1;
                        root.getChildren().removeAll(howMuch);
                        howMuch.setText(Integer.toString(newScore));
                        root.getChildren().add(howMuch);
                        if (hasBeenMoved == 0){
                            int end = 0;
                            for (int i = 0; i < 16; i++){
                                if (labels[i].getText().matches("")){
                                    end = 1;
                                }
                            }
                            if (end == 0){
                                root.getChildren().removeAll(rectangle);
                                root.getChildren().removeAll(labels);
                                root.getChildren().removeAll(score);
                                root.getChildren().removeAll(howMuch);
                                Label label2 = new Label("Game Over");
                                label2.setFont(Font.font(80));
                                label2.relocate(300,200);
                                score.setFont(Font.font(80));
                                score.relocate(400,350);
                                howMuch.relocate(430,500);
                                howMuch.setFont(Font.font(80));
                                root.getChildren().addAll(howMuch,score,label2);
                                counter = 1;
                                start = 0;
                            }
                        }

                    }

                }
            });
            stage.show();

        }
    }

    public void moveLeft(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 0 && i != 1 && i != 2 && i != 3) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i - 4].getText().matches(""))) {
                            labels[i - 4].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }

        for (int i = 0; i < 16; i++) {
            if (i != 0 && i != 1 && i != 2 && i != 3) {
                if (!(labels[i].getText().matches("")) && !(labels[i - 4].getText().matches("")) && Integer.parseInt(labels[i - 4].getText()) == Integer.parseInt(labels[i].getText())) {
                    labels[i - 4].setText(Integer.toString(Integer.parseInt(labels[i].getText()) * 2));
                    labels[i].setText("");
                    hasBeenMoved = 1;

                }

            }

        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 0 && i != 1 && i != 2 && i != 3) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i - 4].getText().matches(""))) {
                            labels[i - 4].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }
    }
    public void moveRight(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 12 && i != 13 && i != 14 && i != 15) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i + 4].getText().matches(""))) {
                            labels[i + 4].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }

        for (int i = 0; i < 16; i++) {
            if (i != 12 && i != 13 && i != 14 && i != 15) {
                if (!(labels[i].getText().matches("")) && !(labels[i + 4].getText().matches("")) && Integer.parseInt(labels[i + 4].getText()) == Integer.parseInt(labels[i].getText())) {
                    labels[i + 4].setText(Integer.toString(Integer.parseInt(labels[i].getText()) * 2));
                    labels[i].setText("");
                    hasBeenMoved = 1;
                }

            }

        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 12 && i != 13 && i != 14 && i != 15) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i + 4].getText().matches(""))) {
                            labels[i + 4].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }
    }
    public void moveUp(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 0 && i != 4 && i != 8 && i != 12) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i - 1].getText().matches(""))) {
                            labels[i - 1].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }

        for (int i = 0; i < 16; i++) {
            if (i != 0 && i != 4 && i != 8 && i != 12) {

                if (!(labels[i].getText().matches("")) && !(labels[i - 1].getText().matches("")) && Integer.parseInt(labels[i - 1].getText()) == Integer.parseInt(labels[i].getText())) {
                    labels[i - 1].setText(Integer.toString(Integer.parseInt(labels[i].getText()) * 2));
                    labels[i].setText("");
                    hasBeenMoved = 1;

                }

            }

        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 0 && i != 4 && i != 8 && i != 12) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i - 1].getText().matches(""))) {
                            labels[i - 1].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;

                        }
                    }
                }

            }
        }
    }
    public void moveDown(){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 3 && i != 7 && i != 11 && i != 15) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i + 1].getText().matches(""))) {
                            labels[i + 1].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;

                        }
                    }
                }

            }
        }

        for (int i = 0; i < 16; i++) {
            if (i != 3 && i != 7 && i != 11 && i != 15) {

                if (!(labels[i].getText().matches("")) && !(labels[i + 1].getText().matches("")) && Integer.parseInt(labels[i + 1].getText()) == Integer.parseInt(labels[i].getText())) {
                    labels[i + 1].setText(Integer.toString(Integer.parseInt(labels[i].getText()) * 2));
                    labels[i].setText("");
                    hasBeenMoved = 1;

                }

            }

        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 16; i++) {
                if (i != 3 && i != 7 && i != 11 && i != 15) {
                    if (!(labels[i].getText().matches(""))) {
                        if ((labels[i + 1].getText().matches(""))) {
                            labels[i + 1].setText(labels[i].getText());
                            labels[i].setText("");
                            hasBeenMoved = 1;
                        }
                    }
                }

            }
        }
    }
    public void putNumber(Rectangle[] rectangle){
        Random rand = new Random();
        int rec1 = 0;
        while (true){
            rec1 = rand.nextInt(16);
            if ((labels[rec1].getText().matches(""))){
                break;
            }
        }
        int num1 = rand.nextInt(2);
        String numberInside;
        if (num1 == 0){
            num1 = 2;
            numberInside = "2";
        }else {
            num1 = 4;
            numberInside = "4";
        }
        rectangle[rec1].setFill(Color.rgb(r-((num1)*25), g , b));
        rectangle[rec1].setX(num1);
        labels[rec1].setText(numberInside);
        labels[rec1].relocate(rectangle[rec1].getLayoutX()+62,rectangle[rec1].getLayoutY()+30);
        labels[rec1].setFont(Font.font(80));
        labels[rec1].setTextFill(Color.BLACK);
    }


}

