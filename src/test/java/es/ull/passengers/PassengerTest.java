package es.ull.passengers;
import es.ull.flights.Flight;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PassengerTest {

    @Test
    public void testValidPassenger() {
        // Arrange
        String validIdentifier = "ID123";
        String validName = "John Doe";
        String validCountryCode = "US";

        // Act
        Passenger passenger = new Passenger(validIdentifier, validName, validCountryCode);

        // Assert
        assertEquals(validIdentifier, passenger.getIdentifier());
        assertEquals(validName, passenger.getName());
        assertEquals(validCountryCode, passenger.getCountryCode());
    }

    @Test
    public void testInvalidCountryCode() {
        // Arrange
        String invalidIdentifier = "ID456";
        String validName = "Jane Smith";
        String invalidCountryCode = "XYZ"; // Invalid country code

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                new Passenger(invalidIdentifier, validName, invalidCountryCode));
        assertEquals("Invalid country code", exception.getMessage());
    }

    @Test
    public void testJoinFlight() {
        // Arrange
        Passenger passenger = new Passenger("ID789", "Alice Johnson", "GB");
        Flight flight = new Flight("AB1234", 2);

        // Act
        passenger.joinFlight(flight);

        // Assert
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightWithNoSeats() {
        // Arrange
        Passenger passenger1 = new Passenger("ID111", "Tom Brown", "FR");
        Passenger passenger2 = new Passenger("ID222", "Lisa Davis", "DE");
        Flight flight = new Flight("CD5678", 1);
        passenger1.joinFlight(flight);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                passenger2.joinFlight(flight));
        assertEquals("Not enough seats for flight CD5678", exception.getMessage());
    }
}