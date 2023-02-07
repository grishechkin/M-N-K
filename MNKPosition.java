package game;

public class MNKPosition implements Position {
    private final Board board;
    MNKPosition(Board board) {
        this.board = board;
    }

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public int getK() {
        return board.getK();
    }

    @Override
    public String toString() {
        return board.toString();
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public int getCell(int row, int col) {
        return board.getCell(row, col);
    }

    @Override
    public int getCurrentCell() {
        return board.getCurrentCell();
    }
}
