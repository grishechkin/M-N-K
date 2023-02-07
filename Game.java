package game;

public class Game {
    private final int playersCount;
    private final Player[] players;
    private final boolean[] loose;
    private int looseCounter = 0;
    private boolean log;

    Game(Player[] players, boolean log) {
        this.playersCount = players.length;
        this.players = players;
        this.loose = new boolean[playersCount];
        this.log = log;
    }

    private Result oneTurn(int player, Board board) {
        try {
            board.setCurrentCell(player);
            Move move = players[player].move(board.getPosition());
            while (board.getPosition().isValid(move)) {
                System.out.println("Incorrect input, please try again");
                move = players[player].move(board.getPosition());
            }
            return board.makeMove(move);
        } catch (RuntimeException e) {
            return Result.LOSE;
        }
    }

    public int play(Board board) {
        if (log) {
            System.out.println(board);
        }
        while (true) {
            for (int i = 0; i < playersCount; i++) {
                if (loose[i]) {
                    continue;
                }
                if (looseCounter + 1 == playersCount) {
                    return i + 1;
                }

                Result result = oneTurn(i, board);
                if (log) {
                    System.out.println(board);
                }

                if (result == Result.WIN) {
                    return i + 1;
                } else if (result == Result.DRAW) {
                    return 0;
                } else if (result == Result.LOSE) {
                    loose[i] = true;
                    looseCounter++;
                }
            }
        }
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
