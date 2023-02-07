package game.players;

import game.game.Move;
import game.game.Position;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in = new Scanner(System.in);
    @Override
    public Move move(Position position) {
        System.out.println(position);
        System.out.println("Print row and column numbers:");
        int row, col;
        while (true) {
            if (!in.hasNextLine()) {
                System.out.println("SYSTEM IN BROKEN");
                return null;
            }
            Scanner line = new Scanner(in.nextLine());
            if (line.hasNextInt()) {
                row = line.nextInt();
            } else {
                System.out.println("Incorrect input, try again");
                continue;
            }
            if (line.hasNextInt()) {
                col = line.nextInt();
            } else {
                System.out.println("Incorrect input, try again");
                continue;
            }
            if (line.hasNext()) {
                System.out.println("Incorrect input, try again");
                continue;
            }
            break;
        }
        return new Move(row - 1, col - 1);
    }

    @Override
    public String toString() {
        return "Human player";
    }

    @Override
    public void endGame() {
        in.close();
    }
}
