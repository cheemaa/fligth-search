package model;

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
}
