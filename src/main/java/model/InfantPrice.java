package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheemaa on 23/6/17.
 */
public class InfantPrice {

    static final Map<String, Double> map;

    static {
        map = new HashMap<String, Double>();
        map.put("IB", Double.valueOf(10));
        map.put("BA", Double.valueOf(15));
        map.put("LH", Double.valueOf(7));
        map.put("FR", Double.valueOf(20));
        map.put("VY", Double.valueOf(10));
        map.put("TK", Double.valueOf(5));
        map.put("U2", Double.valueOf(19.9));
    }

    public static double getPriceForFlight(String flightCode) {
        String airlineCode = flightCode.substring(0, 2);
        if(map.containsKey(airlineCode)) return map.get(airlineCode);
        else return 0;
    }
}
