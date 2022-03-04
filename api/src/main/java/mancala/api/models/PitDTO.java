package mancala.api.models;

public class PitDTO {
    private int index;
    private int nrOfStones;

    public int getIndex() {
        return index;
    }

    public int getNrOfStones() {
        return nrOfStones;
    }

    public PitDTO(int index, int nrOfStones) {
        this.index = index;
        this.nrOfStones = nrOfStones;
    }
}
