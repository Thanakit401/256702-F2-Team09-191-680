package com.project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AirplaneSeating3 extends Application {
    private static final int ROWS = 2; // จำนวนแถวสำหรับ First Class
    private static final int COLS = 4; // จำนวนคอลัมน์ (A, B)
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(50); // เพิ่มระยะห่างระหว่างที่นั่งในแนวนอน
        grid.setVgap(30); // เพิ่มระยะห่างระหว่างแถว
        
        Button[][] seats = new Button[ROWS][COLS];
        
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatLabel = "" + (row + 1) + (char) ('A' + col);
                Button seatButton = new Button(seatLabel);
                seatButton.setMinSize(100, 100); // ปรับขนาดที่นั่งให้ใหญ่ขึ้น
                seatButton.setStyle("-fx-font-size: 18px; -fx-background-color: royalblue; -fx-text-fill: white;");
                
                seatButton.setOnAction(e -> {
                    if ("selected".equals(seatButton.getUserData())) {
                        seatButton.setStyle("-fx-background-color: royalblue; -fx-font-size: 18px; -fx-text-fill: white;");
                        seatButton.setUserData(null);
                    } else {
                        seatButton.setStyle("-fx-background-color: gold; -fx-font-size: 18px; -fx-text-fill: black;"); // ใช้สีทองสำหรับ First Class
                        seatButton.setUserData("selected");
                    }
                });
                
                seats[row][col] = seatButton;
                grid.add(seatButton, col, row);
                GridPane.setHalignment(seatButton, HPos.CENTER);
            }
        }
        
        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setTitle("First Class Seating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
