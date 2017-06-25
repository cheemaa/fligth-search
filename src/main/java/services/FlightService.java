package services;

import model.Flight;
import model.FlightSearch;
import model.InfantPrice;
import model.SearchResult;
import repositories.FlightsRepository;

import java.security.InvalidParameterException;
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

    private void validateInputParameters(FlightSearch search) {
        if(search == null)
            throw new InvalidParameterException("Empty search parameters");
        else if(search.getDate() == null || search.getDate().getTime() < new Date().getTime())
            throw new InvalidParameterException("Invalid date");
        else if(search.getOrigin() == null || search.getDestination() == null
                || search.getOrigin() == search.getDestination())
            throw new InvalidParameterException("Invalid origin and/or destination airport");
        else if(search.getNumberOfAdults() < 0 || search.getNumberOfChildren() < 0 || search.getNumberOfInfants() < 0)
            throw new InvalidParameterException("Invalid number of adults, children or infants");
        else if(search.getNumberOfAdults() == 0 && search.getNumberOfChildren() == 0 && search.getNumberOfInfants() == 0)
            throw new InvalidParameterException("Select at least one person");
    }

    public List<SearchResult> searchFlights(FlightSearch search) {
        validateInputParameters(search);

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
