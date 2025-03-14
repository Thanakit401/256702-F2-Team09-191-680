package com.project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirplaneSeating3 extends Application {
    private static final int ROWS = 2; // จำนวนแถวสำหรับ First Class
    private static final int COLS = 4; // จำนวนคอลัมน์ (A, B)

    private String from;
    private String to;
    private String seatClass;

    public AirplaneSeating3() {}

    public AirplaneSeating3(String from, String to, String seatClass) {
        this.from = from;
        this.to = to;
        this.seatClass = seatClass;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(50);
        grid.setVgap(30);

        Button[][] seats = new Button[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatLabel = "" + (row + 1) + (char) ('A' + col);
                Button seatButton = new Button(seatLabel);
                seatButton.setMinSize(100, 100);
                seatButton.setStyle("-fx-font-size: 18px; -fx-background-color: royalblue; -fx-text-fill: white;");

                seatButton.setOnAction(e -> {
                    if ("selected".equals(seatButton.getUserData())) {
                        seatButton.setStyle("-fx-background-color: royalblue; -fx-font-size: 18px; -fx-text-fill: white;");
                        seatButton.setUserData(null);
                    } else {
                        seatButton.setStyle("-fx-background-color: gold; -fx-font-size: 18px; -fx-text-fill: black;");
                        seatButton.setUserData("selected");
                    }
                });

                seats[row][col] = seatButton;
                grid.add(seatButton, col, row);
                GridPane.setHalignment(seatButton, HPos.CENTER);
            }
        }

        // สร้างปุ่ม Payment ด้านล่างตรงกลาง
        Button paymentButton = new Button("Payment");
        paymentButton.setMinSize(150, 45);
        paymentButton.setStyle("-fx-font-size: 16px;");
        paymentButton.setOnAction(e -> {
            int fare = PaymentCalculator.calculateFare(from, to, seatClass);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Summary");
            alert.setHeaderText("Total Fare");
            alert.setContentText("From: " + from + "\nTo: " + to + "\nClass: " + seatClass + "\nTotal: " + fare + " THB");
            alert.showAndWait();
        });

        VBox bottomBox = new VBox(paymentButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20));

        // ใช้ BorderPane ครอบ layout ทั้งหมด
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 620, 400);
        primaryStage.setTitle("First Class Seating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
