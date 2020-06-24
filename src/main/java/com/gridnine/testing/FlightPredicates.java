package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * This utility is designed for a specific task. Inheritance is undesirable
 */
public interface FlightPredicates {

    /**
     * Departure before a specified point in time
     *
     * @param time - initial time
     * @return - Predicate<Flight> for working with segments
     */
    static Predicate<Flight> isToEarlySegments(LocalDateTime time) {
        return p -> p.getSegments().stream().anyMatch(date -> !date.getDepartureDate().isBefore(time));
    }

    /**
     * Arrival date before departure date
     *
     * @param time - initial time
     * @return - Predicate<Flight> for working with segments
     */
    static Predicate<Flight> isToEarlyDeparture(LocalDateTime time) {
        return p -> p.getSegments().stream().anyMatch(date -> !date.getArrivalDate().isBefore(date.getDepartureDate()));
    }

    /**
     * Total time spent on earth for more than two hours
     *
     * @return - Predicate<Flight> for working with segments
     */
    static Predicate<Flight> moreThanTwoHoursTotal() {
        return p -> {
            List<Segment> segments = p.getSegments();
            return IntStream.range(0, segments.size() - 1)
                .mapToLong(i -> ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()))
                .sum() > 2;
        };
    }

    /**
     * Stop for more than two hours
     *
     * @return - Predicate<Flight> for working with segments
     */
    static Predicate<Flight> stopForMoreThanTwoHours() {
        return p -> {
            List<Segment> segments = p.getSegments();
            return IntStream.range(0, segments.size() - 1)
                .anyMatch(i -> ChronoUnit.HOURS.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()) > 2);
        };
    }
}