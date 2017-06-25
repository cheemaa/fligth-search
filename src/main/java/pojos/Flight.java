package pojos;

import java.util.Date;

import static constants.Constants.DAY_IN_MILLIS;

/**
 * Created by cheemaa on 23/6/17.
 */
public class Flight {
    private String flightCode;
    private String origin;
    private String destination;
    private double price;

    public Flight(String flightCode, String origin, String destination, double price) {
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public String getRouteKey() {
        return origin + "," + destination;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceForDate(Date flightDate) {
        Date today = new Date();
        double diffInDays = (flightDate.getTime() - today.getTime()) / DAY_IN_MILLIS;

        double modifier;

        if(diffInDays > 30) {
            modifier = 0.8;
        }
        else if(diffInDays > 16) {
            modifier = 1;
        }
        else if(diffInDays > 3) {
            modifier = 1.2;
        }
        else {
            modifier = 1.5;
        }

        return price * modifier;
    }


}
