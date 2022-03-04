package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalahaTest {

    @Test
    public void aKalahaStartsWith0Stones() {
        Kalaha kalaha = new Kalaha();
        assertEquals(0, kalaha.getNumberOfStones());
    }

    @Test
    public void giveStoneIncreasesNumStonesInBowlByOne() {
        Kalaha kalaha = new Kalaha();
        kalaha.giveStone();
        assertEquals(1, kalaha.getNumberOfStones());
    }

}
