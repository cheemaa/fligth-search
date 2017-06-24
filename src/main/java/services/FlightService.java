package services;

import model.Flight;
import model.FlightSearch;
import model.InfantPrice;
import model.SearchResult;
import repositories.FlightsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static model.Constants.CHILDREN_PRICE_MODIFIER;

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
                double adultsPrice = search.getNumberOfAdults() * flight.getPriceForDate(search.getDate());
                double childrenPrice = search.getNumberOfChildren() * CHILDREN_PRICE_MODIFIER * flight.getPriceForDate(search.getDate());
                double infantsPrice = search.getNumberOfInfants() * InfantPrice.getPriceForFlight(flight.getFlightCode());

                double totalPrice = adultsPrice + childrenPrice + infantsPrice;

                SearchResult result = new SearchResult(flight.getFlightCode(), totalPrice);
                results.add(result);
            }
        }

        return results;
    }
}
