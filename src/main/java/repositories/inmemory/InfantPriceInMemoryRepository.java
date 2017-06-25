package repositories.inmemory;

import repositories.InfantPriceRepository;

import java.util.HashMap;

/**
 * Created by cheemaa on 25/6/17.
 */
public class InfantPriceInMemoryRepository implements InfantPriceRepository {

    private HashMap<String, Double> priceByAirline = new HashMap<>();

    public InfantPriceInMemoryRepository() {
        priceByAirline.put("IB", Double.valueOf(10));
        priceByAirline.put("BA", Double.valueOf(15));
        priceByAirline.put("LH", Double.valueOf(7));
        priceByAirline.put("FR", Double.valueOf(20));
        priceByAirline.put("VY", Double.valueOf(10));
        priceByAirline.put("TK", Double.valueOf(5));
        priceByAirline.put("U2", Double.valueOf(19.9));
    }

    @Override
    public double findByFlightCode(String flightCode) {
        String airlineCode = flightCode.substring(0, 2);
        if(priceByAirline.containsKey(airlineCode)) return priceByAirline.get(airlineCode);
        else return 0;
    }
}
