package game;

public class ExceptionPlayer implements Player {
    @Override
    public Move move(Position position) throws RuntimeException {
        throw new RuntimeException("RE");
    }

    @Override
    public void endGame() {}
}
