package mancala.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private Kalaha kalaha;
    private List<Bowl> bowls;

    public Player(Kalaha kalaha) {
        this.kalaha = kalaha;
        bowls = Arrays.asList(new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl(), new Bowl());
    }

    public List<Bowl> getBowls() {
        return bowls;
    }

    public int getNumberOfStonesPlayer() {
        int numberOfStonesBowls = getBowls().stream().mapToInt(bowl -> bowl.getNumberOfStones()).sum();
        return numberOfStonesBowls;
    }

    public void moveAllPlayerStonesToKalaha() {
        int playerStones = getNumberOfStonesPlayer();

        for (Bowl bowl : bowls) {
            bowl.takeAllStones();
        }

        kalaha.giveStones(playerStones);
    }

    public int getScore() {
        return kalaha.getNumberOfStones();
    }

    public boolean hasStonesLeft() {
        return getNumberOfStonesPlayer() > 0;
    }

    public int getNumStonesThisBowl(int bowlNumber, Player player) {
        int numOfStones = player.getBowls().get(bowlNumber).getNumberOfStones();
        return numOfStones;
    }

    public AllCups makeMove(int bowlNumber, List<Bowl> opponentBowls) {
        List<AllCups> allBowls = new ArrayList<>(getBowls());
        allBowls.add(kalaha);
        allBowls.addAll(opponentBowls);
        Bowl selectedBowl = getBowls().get(bowlNumber - 1);
        int numberOfStones = selectedBowl.getNumberOfStones();
        selectedBowl.takeAllStones();
        AllCups last = null;

        for (int i = bowlNumber; i < bowlNumber + numberOfStones; i++) {
            last = allBowls.get(i % allBowls.size());
            last.giveStone();
        }

        if (last instanceof Kalaha) {
            return last;
        }

        Bowl lastBowl = (Bowl) last;

        stealStones(lastBowl, opponentBowls);

        return last;
    }

    public void stealStones(Bowl lastBowl, List<Bowl> opponentBowls) {
        if (getBowls().contains(lastBowl) && lastBowl.getNumberOfStones() == 1) {
            lastBowl.takeAllStones();
            kalaha.giveStone();
            Bowl opponentsBowl = opponentBowls.get(5 - getBowls().indexOf(lastBowl));
            for (int i = 0; i < opponentsBowl.getNumberOfStones(); i++) {
                kalaha.giveStone();
            }
            opponentsBowl.takeAllStones();
        }
    }

}
