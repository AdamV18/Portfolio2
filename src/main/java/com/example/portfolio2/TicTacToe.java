package com.example.portfolio2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    Button[][] grid = new Button[3][3];
    boolean xTurn = true;
    Label gameStatus = new Label("X start");
    Button replayButton = new Button("Replay");

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Button(" ");
                grid[i][j].setPrefSize(100, 100);
                grid[i][j].setOnAction(e -> makeDraw((Button) e.getSource()));
                gridPane.add(grid[i][j], j, i); // Add button to GridPane
            }
        }

        gameStatus.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: blue");

        // Styling and action for the Replay button
        replayButton.setStyle("-fx-font-size: 18pt; -fx-background-color: green; -fx-text-fill: white");
        replayButton.setOnAction(e -> resetGame());

        VBox root = new VBox(gridPane, gameStatus, replayButton);
        Scene scene = new Scene(root, 300, 400);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void makeDraw(Button button) {

        button.setText(xTurn ? "X" : "O");
        button.setStyle(xTurn ? "-fx-font-size: 30pt; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: blue;" : "-fx-font-size: 30pt; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: red");
        button.setDisable(true);
        xTurn = !xTurn; // Switch turn
        gameStatus.setText(xTurn ? "X's turn" : "O's turn");
        gameStatus.setStyle(xTurn ? "-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: blue" : "-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: red");
        checkWinner();
    }


    private void checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkEqual(grid[i][0], grid[i][1], grid[i][2]) || checkEqual(grid[0][i], grid[1][i], grid[2][i])) {
                announceWinner(grid[i][i].getText());
                return;
            }
        }
        // Check diagonals
        if (checkEqual(grid[0][0], grid[1][1], grid[2][2]) || checkEqual(grid[0][2], grid[1][1], grid[2][0])) {
            announceWinner(grid[1][1].getText());
        }

        // Check for draw
        else if (isBoardFull()) {
            gameStatus.setText("It's a draw!");
            gameStatus.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: black");
        }
    }

    private boolean checkEqual(Button b1, Button b2, Button b3) {
        return !b1.getText().equals(" ") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void announceWinner(String winner) {
        gameStatus.setText("The winner is: " + winner + "!");
        gameStatus.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: black");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setDisable(true);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setText(" ");
                grid[i][j].setDisable(false);
                grid[i][j].setStyle(""); // Reset button styles
            }
        }
        xTurn = true; // Reset turn to X
        gameStatus.setText("X start");
        gameStatus.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold; -fx-text-fill: blue");
    }
}
