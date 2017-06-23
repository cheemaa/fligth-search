package services;

import model.Flight;
import model.FlightSearch;
import model.SearchResult;
import repositories.FlightsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightService {
    private FlightsRepository flightsRepository;

    public FlightService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<SearchResult> searchFlights(FlightSearch search) {
        List<SearchResult> results = new ArrayList<SearchResult>();

        List<Flight> flights = flightsRepository.findByOriginAndDestination(search.getOrigin(), search.getDestination());

        if(flights != null) {
            for(Flight flight : flights) {

            }
        }

        return results;
    }
}
