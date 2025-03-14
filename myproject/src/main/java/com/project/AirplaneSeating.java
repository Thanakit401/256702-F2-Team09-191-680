package com.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirplaneSeating extends Application {
    private static final int ROWS = 10;
    private static final int COLS = 6;

    private String from;
    private String to;
    private String seatClass;

    public AirplaneSeating() {}

    public AirplaneSeating(String from, String to, String seatClass) {
        this.from = from;
        this.to = to;
        this.seatClass = seatClass;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button[][] seats = new Button[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int adjustedCol = col < 3 ? col : col + 1;

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

        // เพิ่มปุ่ม Payment
        Button paymentButton = new Button("Payment");
        paymentButton.setMinSize(120, 40);
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
        bottomBox.setPadding(new Insets(15, 0, 20, 0));

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 450, 650);
        primaryStage.setTitle("Airplane Seating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getSeatLetter(int col) {
        if (col < 3) return Character.toString((char) ('A' + col));
        return Character.toString((char) ('D' + (col - 3)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
