package game.players;

import game.game.Move;
import game.game.Position;

public interface Player {
    Move move(Position position);
    void endGame();
}
