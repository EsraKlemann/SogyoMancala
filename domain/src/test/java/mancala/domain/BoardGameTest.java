package mancala.domain;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardGameTest {

    @Test
    public void  boardGameSetsUpTwoPlayers() {
        Player p1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(p1, player2);

        assertEquals(p1, game.getPlayer1());
        assertEquals(player2, game.getPlayer2());
    }

    @Test
    public void atStartGamePlayer1HasTurnPlayer2HasNot() {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);

        Player currentPlayer = game.getCurrentPlayer();
        assertEquals(player1, currentPlayer);
        assertNotEquals(player2, currentPlayer);
    }

    @Test
    public void gameHasNotEndedAtStart () {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);

        assertFalse(game.hasEnded());
    }

    @Test
    public void ifEveryBowlOfAPlayerIsEmptyTheGameEnds() {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.getBowls().forEach(bowl -> bowl.takeAllStones());
        assertTrue(game.hasEnded());
    }

    @Test
    public void ifGameHasNotEndedThereIsNoWinnerYet() {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        assertFalse(game.hasEnded());
    }

    @Test
    public void ifGameHasNotEndedAndPlayer1MoreStonesInKalahaThenThereIsNoWinner() {
        Kalaha kalahap1 = new Kalaha();
        Player player1 = new Player(kalahap1);
        kalahap1.giveStone();
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        assertFalse(game.hasEnded());
    }

    @Test
    public void whenGameEndsAndPlayer1HasMoreStonesInKalahaThenPlayer1Wins() {
        Kalaha kalahap1 = new Kalaha();
        Player player1 = new Player(kalahap1);
        kalahap1.giveStone();
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.getBowls().forEach(bowl -> bowl.takeAllStones());
        assertEquals(player1, game.getWinner());
    }

    @Test
    public void whenGameEndsAndPlayer2HasMoreStonesInKalahaThenPlayer2Wins() {
        Player player1 = new Player(new Kalaha());
        Kalaha kalahap2 = new Kalaha();
        Player player2 = new Player(kalahap2);
        kalahap2.giveStone();
        BoardGame game = new BoardGame(player1, player2);
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.getBowls().forEach(bowl -> bowl.takeAllStones());
        assertEquals(player2, game.getWinner());
    }

    @Test
    public void whenGameEndsAndBothPlayersHaveEqualAmountsOfStonesInKalahaNobodyWins() {
        Player player1 = new Player(new Kalaha());
        Player player2 = new Player(new Kalaha());
        BoardGame game = new BoardGame(player1, player2);
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.getBowls().forEach(bowl -> bowl.takeAllStones());
        assertNull(game.getWinner());
    }

    

}
