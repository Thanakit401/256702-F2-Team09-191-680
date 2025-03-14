package com.project;

import javafx.scene.control.Alert;

public class Payment {
    private String airline;
    private String from;
    private String to;
    private String seatClass;
    private int seats;
    private double price;

    public Payment(String airline, String from, String to, String seatClass, int seats) {
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.seatClass = seatClass;
        this.seats = seats;
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        double basePrice = 100.0;
        switch (seatClass) {
            case "Economy": basePrice *= 1.0; break;
            case "Business": basePrice *= 2.0; break;
            case "First Class": basePrice *= 3.0; break;
            default: basePrice *= 1.0;
        }
        return basePrice * seats;
    }

    public void processPayment() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Payment of $" + price + " for " + seats + " seat(s) in " + seatClass + " class has been completed.");
        alert.showAndWait();
    }
}
