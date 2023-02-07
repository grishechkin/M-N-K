package game;

public interface Player {
    Move move(Position position);
    void endGame();
}
