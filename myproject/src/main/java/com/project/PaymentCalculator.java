package com.project;

public class PaymentCalculator {

    public static int calculateFare(String from, String to, String seatClass) {
        int baseFare = getBaseFare(from, to);

        switch (seatClass) {
            case "Economy":
                return baseFare;
            case "Business":
                return baseFare + 2000;
            case "First Class":
                return baseFare + 5000;
            default:
                return baseFare;
        }
    }

    private static int getBaseFare(String from, String to) {
        if (from.equals(to)) return 0;

        if ((from.equals("Bangkok") && to.equals("Tokyo")) || (from.equals("Tokyo") && to.equals("Bangkok")))
            return 5000;
        if ((from.equals("Bangkok") && to.equals("New York")) || (from.equals("New York") && to.equals("Bangkok")))
            return 12000;
        if ((from.equals("London") && to.equals("Tokyo")) || (from.equals("Tokyo") && to.equals("London")))
            return 9000;

        return 7000; // ค่าเริ่มต้นถ้า route ไม่ match
    }
    
}
