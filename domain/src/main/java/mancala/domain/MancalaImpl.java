package mancala.domain;

public class MancalaImpl implements Mancala {

    private final BoardGame game;
    private final Player player1;
    private final Player player2;
    
    public MancalaImpl() {
        this.player1 = new Player(new Kalaha());
        this.player2 = new Player(new Kalaha());
        this.game = new BoardGame(player1, player2);
    }

    @Override
    public boolean isPlayersTurn(int player) {
        Player p = (player == 1) ? player1 : player2;    
        return p.equals(game.getCurrentPlayer()); 
    }

    @Override
	public void playPit(int index) throws MancalaException {
        int nulTmZes = index % 7;
        int mijnIndex = nulTmZes + 1;
        game.makeMove(mijnIndex);
    }
	
	@Override
	public int getStonesForPit(int index) {
        int player = index/7;
        int idx = index % 7;
        Player p = (player == 0) ? player1 : player2;

        if (idx == 6) {
            return p.getScore();
        } else {
            return p.getBowls().get(idx).getNumberOfStones();
        }
    }

	@Override
	public boolean isEndOfGame() {
        return game.hasEnded();
    }

	@Override
	public int getWinner() {
        if(!isEndOfGame()) {
            return 0;
        }

        Player p = game.getWinner();

        if (p == null) {
            return 3;
        }

        if (p.equals(game.getPlayer1())) {
            return 1;
        } else {
            return 2;
        }
    }
}