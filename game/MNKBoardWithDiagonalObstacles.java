package game.game;

public class MNKBoardWithDiagonalObstacles extends MNKBoard {
    private final static int SIZE = 11;

    public MNKBoardWithDiagonalObstacles(int k, int playersCount) {
        super(SIZE, SIZE, k, playersCount);
        for (int i = 0; i < SIZE; i++) {
            super.board[i][i] = -2;
            super.board[i][SIZE - i - 1] = -2;
            super.doneMoves += 2;
        }
        super.doneMoves--;
    }
}
