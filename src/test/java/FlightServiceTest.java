import model.Airport;
import model.FlightSearch;
import org.junit.jupiter.api.Test;
import repositories.FlightsFromCsvRepository;
import repositories.FlightsRepository;
import services.FlightService;

import java.io.InputStream;
import java.util.Date;


/**
 * Created by cheemaa on 23/6/17.
 */
class FlightServiceTest {
    @Test
    void searchFlight() {
        FlightsRepository repository1 = new FlightsFromCsvRepository("flights.csv");

        FlightService fs = new FlightService(repository1);

        FlightSearch search = new FlightSearch(Airport.Madrid, Airport.Barcelona, new Date(), 3, 0, 0);

        fs.searchFlight(search);
    }

}