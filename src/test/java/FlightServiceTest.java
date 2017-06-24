import model.Airport;
import model.Flight;
import model.FlightSearch;
import model.SearchResult;
import org.junit.jupiter.api.BeforeAll;
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
        // Repository using the file given in the instructions
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

        Date todayPlus7Days = new Date( System.currentTimeMillis() + 7 * DAY_IN_MILLIS);
        FlightSearch search1Adult7days = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus7Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> flightResults1adult7days = fs.searchFlights(search1Adult7days);

        Date todayPlus1Day = new Date( System.currentTimeMillis() + 1 * DAY_IN_MILLIS);
        FlightSearch search1Adult1day = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus1Day, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> flightResults1adult1day = fs.searchFlights(search1Adult1day);

        /* Test case: Invalid search parameters */
        FlightSearch nonexistingOriginAirport = new FlightSearch("Unknown", Airport.Frakfurt, todayPlus31Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> invalidOrigin = fs.searchFlights(nonexistingOriginAirport);
        FlightSearch nonexistingDestinationAirport = new FlightSearch(Airport.Frakfurt, "Unknown", todayPlus31Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> invalidDestination = fs.searchFlights(nonexistingDestinationAirport);
        FlightSearch sameOriginAndDestinationSearch = new FlightSearch(Airport.Frakfurt, Airport.Frakfurt, todayPlus31Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> sameOriginAndDestination = fs.searchFlights(sameOriginAndDestinationSearch);
        FlightSearch pastDateSearch = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus31Days, numberOfAdults, numberOfChildren, numberOfInfants);
        List<SearchResult> pastDate = fs.searchFlights(pastDateSearch);
        FlightSearch invalidNumberOfAdultsSearch = new FlightSearch(Airport.Amsterdam, Airport.Frakfurt, todayPlus31Days, -1, numberOfChildren, numberOfInfants);
        List<SearchResult> invalidNumberOfAdults = fs.searchFlights(invalidNumberOfAdultsSearch);

        assertEquals(0, invalidOrigin.size());
        assertEquals(0, invalidDestination.size());
        // TODO: Implement remaining test cases


        // The number of the flights returned should not be affected by the date
        assertEquals(3, flightResults1adult35days.size());
        assertEquals(3, flightResults1adult18days.size());
        assertEquals(3, flightResults1adult7days.size());
        assertEquals(3, flightResults1adult1day.size());

        /* Test cases: 1 adult and variation of the date to see how it affects the price */

        // The price of the flights booked with more than 30 days from today are 80% of those booked between 16 to 30 days from now
        assertEquals(0.8 * flightResults1adult18days.get(0).getTotalPrice(), flightResults1adult35days.get(0).getTotalPrice());
        assertEquals(0.8 * flightResults1adult18days.get(1).getTotalPrice(), flightResults1adult35days.get(1).getTotalPrice());
        assertEquals(0.8 * flightResults1adult18days.get(2).getTotalPrice(), flightResults1adult35days.get(2).getTotalPrice());
        // The price of the tickets booked with 3 to 15 days previous to the flight date have a surcharge of 20%
        assertEquals(1.2 * flightResults1adult18days.get(0).getTotalPrice(), flightResults1adult7days.get(0).getTotalPrice());
        assertEquals(1.2 * flightResults1adult18days.get(1).getTotalPrice(), flightResults1adult7days.get(1).getTotalPrice());
        assertEquals(1.2 * flightResults1adult18days.get(2).getTotalPrice(), flightResults1adult7days.get(2).getTotalPrice());
        // The price of the tickets booked with less than 3 days previous to the flight date have a surcharge of 50%
        assertEquals(1.5 * flightResults1adult18days.get(0).getTotalPrice(), flightResults1adult1day.get(0).getTotalPrice());
        assertEquals(1.5 * flightResults1adult18days.get(1).getTotalPrice(), flightResults1adult1day.get(1).getTotalPrice());
        assertEquals(1.5 * flightResults1adult18days.get(2).getTotalPrice(), flightResults1adult1day.get(2).getTotalPrice());
    }

}