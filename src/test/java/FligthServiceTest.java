import org.junit.jupiter.api.Test;

import java.util.Date;


/**
 * Created by cheemaa on 23/6/17.
 */
class FligthServiceTest {
    @Test
    void searchFlight() {

        FligthService fs = new FligthService();

        FlightSearch search = new FlightSearch(Airport.Madrid, Airport.Barcelona, new Date(), 3, 0, 0);

        fs.searchFlight(search);
    }

}