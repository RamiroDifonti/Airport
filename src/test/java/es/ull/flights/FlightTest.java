package es.ull.flights;
import es.ull.passengers.Passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    private Flight flight;
    private Passenger passenger1;
    private Passenger passenger2;

    @BeforeEach
    public void setUp() {
        flight = new Flight("AB1234", 2);
        passenger1 = new Passenger("0001", "John", "US");
        passenger2 = new Passenger("0002", "Jane", "ES");
    }

    @Test
    public void testGetFlightNumber() {
        assertEquals("AB1234", flight.getFlightNumber());
    }

    @Test
    public void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
        flight.addPassenger(passenger1);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddPassenger() {
        assertTrue(flight.addPassenger(passenger1));
        assertTrue(flight.addPassenger(passenger2));
    }

    @Test
    public void testAddPassengerExceedingSeats() {
        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);
        Passenger passenger3 = new Passenger("0003", "Alice", "AR");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger3));
        assertEquals("Not enough seats for flight AB1234", exception.getMessage());
    }

    @Test
    public void testRemovePassenger() {
        flight.addPassenger(passenger1);
        assertTrue(flight.removePassenger(passenger1));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void testRemoveNonExistingPassenger() {
        assertFalse(flight.removePassenger(passenger1));
    }
}
