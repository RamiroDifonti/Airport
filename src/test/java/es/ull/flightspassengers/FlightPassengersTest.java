package es.ull.flightspassengers;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightPassengersTest {

    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AB1234", 2);
        Passenger passenger = new Passenger("ID1", "John Doe", "US");

        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightWithNoSeats() {
        Flight flight = new Flight("AB1234", 1);
        Passenger passenger1 = new Passenger("ID1", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID2", "Jane Smith", "AR");

        passenger1.joinFlight(flight);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                passenger2.joinFlight(flight));
        assertEquals("Not enough seats for flight AB1234", exception.getMessage());
        assertEquals(1, flight.getNumberOfPassengers());
    }
}

