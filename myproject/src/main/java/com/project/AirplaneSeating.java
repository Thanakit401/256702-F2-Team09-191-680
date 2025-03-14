package com.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AirplaneSeating extends Application {
    private static final int ROWS = 10; // เพิ่มจำนวนแถว
    private static final int COLS = 6; // รวมที่นั่ง 3-ทางเดิน-3
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(5); // กำหนดช่องว่างแนวนอน
        grid.setVgap(5); // กำหนดช่องว่างแนวตั้ง
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Button[][] seats = new Button[ROWS][COLS];
        
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int adjustedCol = col < 3 ? col : col + 1; // ขยับคอลัมน์ฝั่งขวาให้เว้นที่ว่างตรงกลาง
                
                String seatLabel = "" + (row + 1) + getSeatLetter(col);
                Button seatButton = new Button(seatLabel);
                seatButton.setMinSize(50, 50);
                
                seatButton.setOnAction(e -> {
                    if ("selected".equals(seatButton.getUserData())) {
                        seatButton.setStyle("-fx-background-color: lightgray;");
                        seatButton.setUserData(null);
                    } else {
                        seatButton.setStyle("-fx-background-color: green;");
                        seatButton.setUserData("selected");
                    }
                });
                
                seats[row][col] = seatButton;
                grid.add(seatButton, adjustedCol, row);
            }
        }
        
        Scene scene = new Scene(grid, 450, 500);
        primaryStage.setTitle("Airplane Seating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private String getSeatLetter(int col) {
        if (col < 3) return Character.toString((char) ('A' + col)); // A, B, C
        return Character.toString((char) ('D' + (col - 3))); // D, E, F
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
