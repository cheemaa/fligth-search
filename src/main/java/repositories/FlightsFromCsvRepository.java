package repositories;

import model.Flight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightsFromCsvRepository implements FlightsRepository {

    private HashMap<String, List<Flight>> flightsMap = new HashMap<>();

    public FlightsFromCsvRepository(String fileName) {
        String pathStr = this.getClass().getClassLoader().getResource(fileName).getPath();
        Path path = Paths.get(pathStr);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> addFlightToRoute(parseFlight(s)));
        } catch (IOException ex) {

        }
    }

    private void addFlightToRoute(Flight flight) {
        String key = flight.getRouteKey();
        if(flightsMap.containsKey(flight.getRouteKey())) {
            flightsMap.get(key).add(flight);
        }
        else {
            List<Flight> routeFlights = new ArrayList<>();
            routeFlights.add(flight);
            flightsMap.put(key, routeFlights);
        }
    }

    private Flight parseFlight(String lineFromFile) {
        String[] data = lineFromFile.split(",");
        String origin = data[0];
        String destination = data[1];
        String flightCode = data[2];
        double price = Double.valueOf(data[3]);

        return new Flight(flightCode, origin, destination, price);
    }

    public List<Flight> findByOriginAndDestination(String origin, String destination) {
        String key = origin + "," + destination;

        return flightsMap.get(key);
    }
}
