import model.Airport;
import model.Flight;
import model.FlightSearch;
import model.SearchResult;
import org.junit.jupiter.api.Test;
import repositories.FlightsFromCsvRepository;
import repositories.FlightsRepository;
import services.FlightService;

import java.util.Date;
import java.util.List;

import static model.Constants.DAY_IN_MILLIS;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by cheemaa on 23/6/17.
 */
class FlightServiceTest {
    @Test
    void searchFlight() {
        // Repository using the file from the README
        FlightsRepository repository1 = new FlightsFromCsvRepository("flights.csv");
        FlightService fs = new FlightService(repository1);

        Date todayPlus31Days = new Date( System.currentTimeMillis() + 35 * DAY_IN_MILLIS);
        int numberOfAdults = 1;
        int numberOfChildren = 0;
        int numberOfInfants = 0;

        FlightSearch search1Adult35days = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus31Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> flightResults1adult35days = fs.searchFlights(search1Adult35days);

        Date todayPlus18Days = new Date( System.currentTimeMillis() + 18 * DAY_IN_MILLIS);
        FlightSearch search1Adult18days = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus18Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> flightResults1adult18days = fs.searchFlights(search1Adult18days);

        assertEquals(3, flightResults1adult35days.size());
        assertEquals(3, flightResults1adult18days.size());
        assertEquals(0.8 * flightResults1adult18days.get(0).getTotalPrice(), flightResults1adult35days.get(0).getTotalPrice());
    }

}