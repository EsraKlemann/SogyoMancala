package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {

    @Test
    public void aNormalBowlStartsWith4Stones() {
        Bowl bowl = new Bowl();
        assertEquals(4, bowl.getNumberOfStones());
    }

    @Test
    public void takingStonesRemovesAllStones() {
        Bowl bowl = new Bowl();
        bowl.takeAllStones();
        assertEquals(0, bowl.getNumberOfStones());
    }

    @Test
    public void giveStoneIncreasesNumStonesInBowlByOne() {
        Bowl bowl = new Bowl();
        bowl.giveStone();
        assertEquals(5, bowl.getNumberOfStones());
    }

}
