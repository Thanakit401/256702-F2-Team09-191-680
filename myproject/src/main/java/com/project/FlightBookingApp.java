package com.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightBookingApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Online Flight Booking");

        Label airlineLabel = new Label("Airline:");
        ComboBox<String> airlineComboBox = createComboBox(new String[]{"Airline A", "Airline B", "Airline C"});
        
        Label fromLabel = new Label("From:");
        ComboBox<String> fromComboBox = createComboBox(new String[]{"Bangkok", "Tokyo", "New York", "London"});
        
        Label toLabel = new Label("To:");
        ComboBox<String> toComboBox = createComboBox(new String[]{"Bangkok", "Tokyo", "New York", "London"});
        
        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker();
        
        Label classLabel = new Label("Class:");
        ComboBox<String> classComboBox = createComboBox(new String[]{"Economy","Premium Economy", "Business", "First Class"});
        
        Label seatsLabel = new Label("Seats:");
        Spinner<Integer> seatsSpinner = new Spinner<>(1, 10, 1);
        
        Button bookButton = new Button("Book Now");
        bookButton.setOnAction(e -> {
            String airline = getSelectedValue(airlineComboBox);
            String from = getSelectedValue(fromComboBox);
            String to = getSelectedValue(toComboBox);
            String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "Not selected";
            String travelClass = getSelectedValue(classComboBox);
            int seats = seatsSpinner.getValue();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Confirmation");
            alert.setHeaderText("Booking Successful!");
            alert.setContentText("Airline: " + airline + "\nFrom: " + from + "\nTo: " + to + "\nDate: " + date + "\nClass: " + travelClass + "\nSeats: " + seats);
            alert.showAndWait();
        });
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
            airlineLabel, airlineComboBox,
            fromLabel, fromComboBox,
            toLabel, toComboBox,
            dateLabel, datePicker,
            classLabel, classComboBox,
            seatsLabel, seatsSpinner,
            bookButton
        );
        
        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private ComboBox<String> createComboBox(String[] options) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(options);
        return comboBox;
    }
    
    private String getSelectedValue(ComboBox<String> comboBox) {
        return comboBox.getValue() != null ? comboBox.getValue() : "Not selected";
    }
    
}

