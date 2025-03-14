package com.project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AirplaneSeating2 extends Application {
    private static final int ROWS = 5; // จำนวนแถวสำหรับ Business Class
    private static final int COLS = 4; // จำนวนคอลัมน์ (A, B, C)
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20); // เพิ่มระยะห่างระหว่างที่นั่งในแนวนอน
        grid.setVgap(15); // เพิ่มระยะห่างระหว่างแถว
        
        Button[][] seats = new Button[ROWS][COLS];
        
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatLabel = "" + (row + 1) + (char) ('A' + col);
                Button seatButton = new Button(seatLabel);
                seatButton.setMinSize(70, 70); // ปรับขนาดที่นั่งให้ใหญ่ขึ้น
                
                seatButton.setOnAction(e -> {
                    if ("selected".equals(seatButton.getUserData())) {
                        seatButton.setStyle("-fx-background-color: lightgray; -fx-font-size: 14px;");
                        seatButton.setUserData(null);
                    } else {
                        seatButton.setStyle("-fx-background-color: gold; -fx-font-size: 14px;"); // ใช้สีทองสำหรับ Business Class
                        seatButton.setUserData("selected");
                    }
                });
                
                seats[row][col] = seatButton;
                grid.add(seatButton, col, row);
                GridPane.setHalignment(seatButton, HPos.CENTER);
            }
        }
        
        Scene scene = new Scene(grid, 400, 450);
        primaryStage.setTitle("Business Class Seating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
