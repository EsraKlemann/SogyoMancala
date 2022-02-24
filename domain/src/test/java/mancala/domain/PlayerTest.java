package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {

    @Test
    public void aPlayerHas6Bowls() {
        Player player = new Player(new Kalaha());
        List bowls = player.getBowls();
        assertEquals(6, bowls.size());
    }

    @Test
    public void aPlayerHas24StonesWhenTheyHave6Bowls() {
        Player player = new Player(new Kalaha());
        List <Bowl> bowls = player.getBowls();
        int numberOfStonesPlayer = player.getNumberOfStonesPlayer();
        assertEquals(24, numberOfStonesPlayer);
    }

    @Test
    public void takingStonesFromAPlayersBowlLeavesThemWith20TotalStonesAnd0StonesInBowl () {
        Player player = new Player(new Kalaha());
        List <Bowl> bowls = player.getBowls();
        Bowl bowl = player.getBowls().get(0);
        bowl.takeAllStones();
        int numberOfStonesPlayer = player.getNumberOfStonesPlayer();
        int numberOfStonesBowl = player.getBowls().get(0).getNumberOfStones();
        assertEquals(20, numberOfStonesPlayer);
        assertEquals(0,numberOfStonesBowl);
    }

    @Test
    public void getScoreIsEqualToNumberOfStonesInPlayersKalaha() {
        Kalaha kalaha = new Kalaha();
        Player player = new Player(kalaha);
        for (int i = 0; i < 15; i++) kalaha.giveStone();
        assertEquals(kalaha.getNumberOfStones(), player.getScore());
    }

    @Test
    public void whenBowlsHaveStonesThePlayerHasStonesLeft() {
        Player player = new Player(new Kalaha());
        assertTrue(player.hasStonesLeft());
    }

    @Test
    public void ifEveryBowlIsEmptyThenPlayerHasNoStonesLeft(){
        Player player = new Player(new Kalaha());
        player.getBowls().forEach(bowl -> bowl.takeAllStones());
        assertFalse(player.hasStonesLeft());
    }

    @Test
    public void whenPlayerMakesMoveTheChosenBowlIsMadeEmpty() {
        Player player = new Player(new Kalaha());
        player.makeMove(1, new ArrayList<>());
        int chosenBowlNumberOfStones = player.getBowls().get(0).getNumberOfStones();
        assertEquals(0, chosenBowlNumberOfStones);
    }

    @Test
    public void whenPlayerMakesMoveTheNextBowlIsGivenAStone(){
        Player player = new Player(new Kalaha());
        player.makeMove(1, new ArrayList<>());
        int nextBowlNumberOfStones = player.getBowls().get(1).getNumberOfStones();
        assertEquals(5, nextBowlNumberOfStones);
    }

    @Test
    public void whenBowl1IsSelectedForMoveTheStonesAreDistributedOverTheNextBowls() {
        Player player = new Player(new Kalaha());
        player.makeMove(1, new ArrayList<>());
        assertEquals(0, player.getNumStonesThisBowl(0, player));
        assertEquals(5, player.getNumStonesThisBowl(1, player));
        assertEquals(5, player.getNumStonesThisBowl(2, player));
        assertEquals(5, player.getNumStonesThisBowl(3, player));
        assertEquals(5, player.getNumStonesThisBowl(4, player));
        assertEquals(4, player.getNumStonesThisBowl(5, player));
    }

    @Test
    public void whenBowl3IsSelectedToMoveTheStonesAreDistributedOverTheNextBowlsAndPlayerKalaha() {
        Player player = new Player(new Kalaha());
        player.makeMove(3, new ArrayList<>());
        assertEquals(4, player.getNumStonesThisBowl(0, player));
        assertEquals(4, player.getNumStonesThisBowl(1, player));
        assertEquals(0, player.getNumStonesThisBowl(2, player));
        assertEquals(5, player.getNumStonesThisBowl(3, player));
        assertEquals(5, player.getNumStonesThisBowl(4, player));
        assertEquals(5, player.getNumStonesThisBowl(5, player));
        assertEquals(1, player.getScore());
    }

    @Test
    public void movingStonesAlongAllBowls() {
        Player player = new Player(new Kalaha());
        for (int i = 0; i < 12; i++) {
            player.getBowls().get(0).giveStone();
        }
        List<Bowl> opponentBowls = Arrays.asList(new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl());

        player.makeMove(1, opponentBowls);

        assertEquals(1, player.getBowls().get(0).getNumberOfStones());
        assertEquals(6, player.getBowls().get(1).getNumberOfStones());
        assertEquals(6, player.getBowls().get(2).getNumberOfStones());
        assertEquals(6, player.getBowls().get(3).getNumberOfStones());
        assertEquals(5, player.getBowls().get(4).getNumberOfStones());
        assertEquals(5, player.getBowls().get(5).getNumberOfStones());
        assertEquals(1, player.getScore());
        assertEquals(5, opponentBowls.get(0).getNumberOfStones());
        assertEquals(5, opponentBowls.get(1).getNumberOfStones());
        assertEquals(5, opponentBowls.get(2).getNumberOfStones());
        assertEquals(5, opponentBowls.get(3).getNumberOfStones());
        assertEquals(5, opponentBowls.get(4).getNumberOfStones());
        assertEquals(5, opponentBowls.get(5).getNumberOfStones());
    }


    @Test
    public void stealingOpponentStonesWhenPlayersLastStoneEndsInEmptyBowlOfPlayer () {
        Player player = new Player(new Kalaha());
        player.getBowls().get(5).takeAllStones();
        List<Bowl> opponentBowls = Arrays.asList(new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl());
        player.makeMove(2, opponentBowls);
        assertEquals(0, opponentBowls.get(0).getNumberOfStones());
        assertEquals(5, player.getScore());
    }


    @Test
    public void turnDoesNotSwitchWhenLastStoneEndsInKalaha () {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        game.makeMove(3);
        assertEquals(4, player1.getNumStonesThisBowl(0, player1));
        assertEquals(4, player1.getNumStonesThisBowl(1, player1));
        assertEquals(0, player1.getNumStonesThisBowl(2, player1));
        assertEquals(5, player1.getNumStonesThisBowl(3, player1));
        assertEquals(5, player1.getNumStonesThisBowl(4, player1));
        assertEquals(5, player1.getNumStonesThisBowl(5, player1));
        assertEquals(1, player1.getScore());
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void turnSwitchesWhenLastStoneDoesNotEndInKalaha () {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        game.makeMove(1);
        assertEquals(0, player1.getNumStonesThisBowl(0, player1));
        assertEquals(5, player1.getNumStonesThisBowl(1, player1));
        assertEquals(5, player1.getNumStonesThisBowl(2, player1));
        assertEquals(5, player1.getNumStonesThisBowl(3, player1));
        assertEquals(5, player1.getNumStonesThisBowl(4, player1));
        assertEquals(4, player1.getNumStonesThisBowl(5, player1));
        assertEquals(0, player1.getScore());
        assertEquals(player2, game.getCurrentPlayer());
    }

}
