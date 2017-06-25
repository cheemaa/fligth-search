package repositories.csv;

import org.junit.jupiter.api.Test;
import pojos.Airport;
import repositories.FlightsRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by cheemaa on 25/6/17.
 */
class FlightsCsvRepositoryTest {
    @Test
    void findByOriginAndDestination() {

        // Test case: filename null
        try {
            FlightsRepository flightsRepository = new FlightsCsvRepository(null);
            fail("InvalidParameterException must be returned when filename is null");
        }
        catch(InvalidParameterException ipe) {
            assertEquals("filename cannot be null", ipe.getMessage());
        } catch (Exception e) {
            fail("InvalidParameterException must be returned when filename is null");
        }

        // Test case: nonexisting file
        try {
            FlightsRepository flightsRepository = new FlightsCsvRepository("inventedFilename");
            fail("FileNotFoundException must be returned when file does not exist");
        }
        catch(FileNotFoundException fnfe) {
            assertEquals("inventedFilename not found", fnfe.getMessage());
        } catch (Exception e) {
            fail("FileNotFoundException must be returned when file does not exist");
        }

        // Test case: defected file
        try {
            FlightsRepository flightsRepository = new FlightsCsvRepository("defectedFile.csv");
            fail("Defected file must throw exception");
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }

        // Test case: defected file
        try {
            FlightsRepository flightsRepository = new FlightsCsvRepository("invalidPrice.csv");
            fail("Defected file must throw exception");
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
        }

        // Test case: good file
        try {
            FlightsRepository flightsRepository = new FlightsCsvRepository("flights.csv");
            assertEquals(2, flightsRepository.findByOriginAndDestination(Airport.Copenhagen, Airport.Frakfurt).size());
        }
        catch (Exception e) {
            fail("Working file must not throw exceptions");
        }
    }

}