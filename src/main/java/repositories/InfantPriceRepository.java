package repositories;

/**
 * Created by cheemaa on 25/6/17.
 */
public interface InfantPriceRepository {
    double findByFlightCode(String flightCode);
}
