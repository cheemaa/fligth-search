package pojos;

import java.util.Date;

/**
 * Created by cheemaa on 23/6/17.
 */
public class FlightSearch {
    private String origin;
    private String destination;
    private Date date;
    private int numberOfAdults;
    private int numberOfChildren;
    private int numberOfInfants;

    public FlightSearch(String origin, String destination, Date date, int numberOfAdults, int numberOfChildren, int numberOfInfants) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.numberOfInfants = numberOfInfants;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public int getNumberOfInfants() {
        return numberOfInfants;
    }
}
