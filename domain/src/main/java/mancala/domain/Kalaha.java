package mancala.domain;

public class Kalaha implements AllCups {

    private int numStones;

    public Kalaha() {
        this.numStones = 0;
    }

    public int getNumberOfStones() {
        return numStones;
    }

    public void giveStone() {
        numStones ++;
    }

    public void giveStones(int stones) {
        numStones += stones;
    }

}
