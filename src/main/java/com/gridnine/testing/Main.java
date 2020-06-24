package com.gridnine.testing;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Arrival date before departure date ---");
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.isToEarlyDeparture(LocalDateTime.now()))
                                     .forEachOrdered(System.out::println);
        System.out.println("--- Departure before a specified point in time ---");
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.isToEarlySegments(LocalDateTime.now()))
                                     .forEachOrdered(System.out::println);
        System.out.println("--- Total time spent on earth for more than two hours ---");
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.moreThanTwoHoursTotal())
                                     .forEachOrdered(System.out::println);
    }
}
