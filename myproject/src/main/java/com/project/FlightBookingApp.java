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
        ComboBox<String> classComboBox = createComboBox(new String[]{"Economy", "Business", "First Class"});
        
        Label seatsLabel = new Label("Seats:");
        Spinner<Integer> seatsSpinner = new Spinner<>(1, 10, 1);
        
        Button bookButton = new Button("Book Now");
        bookButton.setOnAction(e -> {
            String from = fromComboBox.getValue();
            String to = toComboBox.getValue();
            String selectedClass = classComboBox.getValue();

            if (selectedClass != null && from != null && to != null) {
                Stage seatingWindow = new Stage();
                switch (selectedClass) {
                    case "Economy":
                        new AirplaneSeating(from, to, selectedClass).start(seatingWindow);
                        break;
                    case "Business":
                        new AirplaneSeating2(from, to, selectedClass).start(seatingWindow);
                        break;
                    case "First Class":
                        new AirplaneSeating3(from, to, selectedClass).start(seatingWindow);
                        break;
                    default:
                        showAlert("Please select a valid class.");
                }
            } else {
                showAlert("Please select From, To, and Class before booking.");
            }
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
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}