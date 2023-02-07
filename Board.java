package game;

public interface Board {
    Result makeMove(Move move);
    Position getPosition();
    int getN();
    int getM();
    int getK();
    int getCell(int row, int col);
    int getCurrentCell();
    boolean isValid(Move move);
    void setCurrentCell(int type);
}
