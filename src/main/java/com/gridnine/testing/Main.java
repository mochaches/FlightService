package com.gridnine.testing;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.isToEarlyDeparture(LocalDateTime.now()))
                                     .forEachOrdered(System.out::println);
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.isToEarlySegments(LocalDateTime.now()))
                                     .forEachOrdered(System.out::println);
        FlightBuilder.createFlights().stream()
                                     .filter(FlightPredicates.moreThanTwoHoursTotal())
                                     .forEachOrdered(System.out::println);
    }
}
