package game.players;

import game.game.Move;
import game.game.Position;

public class ExceptionPlayer implements Player {
    @Override
    public Move move(Position position) throws RuntimeException {
        throw new RuntimeException("RE");
    }

    @Override
    public void endGame() {}
}
