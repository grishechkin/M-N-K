package game.game;

import game.game.Move;

public interface Position {
    int getN();
    int getM();
    int getK();
    int getCell(int row, int col);
    int getCurrentCell();
    boolean isValid(Move move);
}
