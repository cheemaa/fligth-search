package services;

import model.Flight;
import model.FlightSearch;
import model.SearchResult;
import repositories.FlightsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightService {

    private FlightsRepository flightsRepository;

    public FlightService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<SearchResult> searchFlight(FlightSearch search) {
        List<SearchResult> results = new ArrayList<SearchResult>();

        List<Flight> flights = flightsRepository.findByOriginAndDestination(search.getOrigin(), search.getDestination());

        return results;
    }
}
