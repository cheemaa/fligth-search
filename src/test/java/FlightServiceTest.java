import model.Airport;
import model.FlightSearch;
import org.junit.jupiter.api.Test;
import repositories.FlightsFromCsvRepository;
import repositories.FlightsRepository;
import services.FlightService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by cheemaa on 23/6/17.
 */
class FlightServiceTest {
    @Test
    void searchFlight() {
        FlightsRepository repository1 = new FlightsFromCsvRepository("flights.csv");

        FlightService fs = new FlightService(repository1);

        FlightSearch search = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, new Date(), 3, 0, 0);

        assertEquals(3, fs.searchFlights(search).size());
    }

}