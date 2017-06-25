package repositories.csv;

import com.sun.media.sound.InvalidDataException;
import pojos.Flight;
import repositories.FlightsRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightsCsvRepository implements FlightsRepository {

    private HashMap<String, List<Flight>> flightsMap = new HashMap<>();

    public FlightsCsvRepository(String fileName) throws IOException {
        if(fileName == null) {
            throw new InvalidParameterException("filename cannot be null");
        }
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        if(resource == null) {
            throw new FileNotFoundException(fileName + " not found");
        }
        String pathStr = resource.getPath();
        Path path = Paths.get(pathStr);
        Stream<String> lines = Files.lines(path);
        lines.forEach(s -> addFlightToRoute(parseFlight(s)));
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
