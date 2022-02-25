package mancala.domain;

public class BoardGame {

    final private Player player1;
    final private Player player2;
    private Player currentPlayer;

    public BoardGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean hasEnded() {
        return !player1.hasStonesLeft() || !player2.hasStonesLeft();
    }

    public Player getWinner() {
        if(!hasEnded()) {
            return null;
        }
        if (player1.getScore() > player2.getScore()) {
            return player1;
        } else if (player1.getScore() < player2.getScore()) {
            return player2;
        }
        return null;
    }

    private Player getOpponent() {
        if (getCurrentPlayer() == player1) {
            return player2;
        }
        else {
            return  player1;
        }
    }

    public void makeMove(int bowlNumber) {
        AllCups last = getCurrentPlayer().makeMove(bowlNumber, getOpponent().getBowls());

        if (last != null && !(last instanceof Kalaha)) {
            currentPlayer = getOpponent();
        }

        if (hasEnded()) {
            player1.moveAllPlayerStonesToKalaha();
            player2.moveAllPlayerStonesToKalaha();
        }
    }


}
