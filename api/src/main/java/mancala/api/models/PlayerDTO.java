package mancala.api.models;

public class PlayerDTO {
    private final String name;
    private final String type;
    private final boolean hasTurn;
    private final PitDTO[] pits;

    public PlayerDTO(mancala.domain.Mancala mancala,
            String name, boolean isFirstPlayer) {
        this.name = name;
        type = isFirstPlayer ? "player1" : "player2";
        hasTurn = mancala.isPlayersTurn(isFirstPlayer ? mancala.PLAYER_ONE : mancala.PLAYER_TWO);
        this.pits = new PitDTO[7];
        var firstHole = isFirstPlayer ? 0 : 7;
        for (int i = 0; i < 7; ++i) {
            this.pits[i] = new PitDTO(i + firstHole, mancala.getStonesForPit(i + firstHole));
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getHasTurn() {
        return hasTurn;
    }

    public PitDTO[] getPits() {
        return pits;
    }
}
