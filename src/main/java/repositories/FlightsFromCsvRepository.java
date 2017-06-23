package repositories;

import model.Flight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightsFromCsvRepository implements FlightsRepository {

    private final static String RESOURCES_PATH = "/resources/";
    private List<Flight> flights = new ArrayList<Flight>();

    public FlightsFromCsvRepository(String fileName) {
        Path path = Paths.get(RESOURCES_PATH + fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> flights.add(parseFlight(s)));
        } catch (IOException ex) {

        }
    }

    private Flight parseFlight(String lineFromFile) {
        return null;
    }

    public List<Flight> findByOriginAndDestination(String origin, String destination) {
        return null;
    }
}
