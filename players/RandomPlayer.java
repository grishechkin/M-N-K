package game.players;

import game.game.Move;
import game.game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private static final Random rand = new Random();

    @Override
    public Move move(Position position) {
        int m = position.getM();
        int n = position.getN();

        Move move = new Move(rand.nextInt(m), rand.nextInt(n));
        while (position.isValid(move)) {
            move = new Move(rand.nextInt(m), rand.nextInt(n));
        }
        return move;
    }

    @Override
    public String toString() {
        return "Random player";
    }

    @Override
    public void endGame() {}
}
