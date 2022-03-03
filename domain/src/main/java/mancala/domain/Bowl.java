package mancala.domain;

public class Bowl implements AllCups {

    private int numStones;

    public Bowl() {
        this.numStones = 4;
    }

    public int getNumberOfStones() {
        return numStones;
    }

    public void takeAllStones() {
        numStones = 0;
    }

    public void giveStone() {
        numStones++;
    }

}
