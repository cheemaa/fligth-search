package repositories;

import model.Flight;

import java.util.List;

/**
 * Created by cheemaa on 23/6/17.
 */
public interface FlightsRepository {
    List<Flight> findByOriginAndDestination(String origin, String destination);
}
