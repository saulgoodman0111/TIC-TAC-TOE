package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Button buttons[][]=new Button[3][3];
    private Label playerxscorelabel, playerOscorelabel;
    private int playerxscore=0, playerOscore=0;

    private boolean playerxturn=true;

    private BorderPane createcontent(){
        BorderPane root=new BorderPane();

        //title
        Label titlelabel=new Label("Tic-Tac-Toe");
        titlelabel.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
        root.setTop(titlelabel);
        BorderPane.setAlignment(titlelabel, Pos.CENTER);

        //game board
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button=new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                button.setOnAction(event -> buttonclicked(button));
                buttons[i][j]=button;
                gridPane.add(button,j,i);

            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);
        //score
        HBox scoreboard=new HBox(20);
        scoreboard.setAlignment(Pos.CENTER);
        playerxscorelabel=new Label("Player X : 0");
        playerxscorelabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerOscorelabel=new Label("Player O : 0");
        playerOscorelabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreboard.getChildren().addAll(playerxscorelabel, playerOscorelabel);
        root.setBottom(scoreboard);


        return root;
    }
    private void buttonclicked(Button button){
        if(button.getText().equals("")){
            if(playerxturn)
                button.setText("X");
            else{
                button.setText("O");
           }
            playerxturn=!playerxturn;
            checkwinner();
        }
        return;
    }

    private void checkwinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty())
            {
                // we will show winner
                String winner=buttons[row][0].getText();
                showwinnerdialog(winner);
                updatescore(winner);
                reset();
                return;
            }
        }
        //col
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty())
            {
                // we will show winner
                String winner=buttons[0][col].getText();
                showwinnerdialog(winner);
                updatescore(winner);
                reset();
                return;
            }
        }

        //diagonal
            if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][2].getText())
                    && !buttons[0][0].getText().isEmpty())
            {
                // we will show winner
                String winner=buttons[0][0].getText();
                showwinnerdialog(winner);
                updatescore(winner);
                reset();
                return;
            }

        if(buttons[2][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty())
        {
            // we will show winner
            String winner=buttons[2][0].getText();
            showwinnerdialog(winner);
            updatescore(winner);
            reset();
            return;
        }

        //tie
        boolean tie=true;
        for(Button row[]:buttons)
        {
            for(Button button:row){
                if(button.getText().isEmpty()==true){
                    tie=false;
                    break;
                }
            }
        }
        if(tie==true){
            showtie();
            reset();
        }

    }

    private void showwinnerdialog(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Winner is "+winner+" !");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showtie(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("It's a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updatescore(String winner){
        if(winner.equals("X")){
            playerxscore++;
            playerxscorelabel.setText("Player X : "+playerxscore);
        }
        else{
            playerOscore++;
            playerOscorelabel.setText("Player O : "+playerOscore);
        }
    }

    private void reset(){
        for(Button row[]:buttons)
        {
            for(Button button:row){
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createcontent());
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}