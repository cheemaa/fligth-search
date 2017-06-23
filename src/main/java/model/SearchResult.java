package model;

/**
 * Created by cheemaa on 23/6/17.
 */
public class SearchResult {
    private String flightCode;
    private double totalPrice;

    public SearchResult(String flightCode, double totalPrice) {
        this.flightCode = flightCode;
        this.totalPrice = totalPrice;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
