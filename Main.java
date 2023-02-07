package game;

import game.game.*;
import game.players.ExceptionPlayer;
import game.players.HumanPlayer;
import game.players.Player;
import game.players.RandomPlayer;

import java.util.Scanner;

public class Main {
    private static final int MAX_M = 11;
    private static final int MAX_N = 11;
    private static final int MAX_K = 11;
    private static final int MAX_TOURNAMENT_PARTICIPANTS_COUNT = 10;
    private static final int MAX_PLAYERS_COUNT = 4;
    private static final int PLAYER_TYPES_COUNT = 3;
    private static final int BOARD_TYPES_COUNT = 2;

    public static void main(String[] args) {
        System.out.println("WELCOME TO MNK GAME!!!");

        Scanner in = new Scanner(System.in);

        System.out.println("Choose board");
        System.out.println("1 - Common mnk board");
        System.out.println("2 - Board with diagonal obstacles");
        int boardTypeUser = getUserInput(in, BOARD_TYPES_COUNT);
        BoardType boardType;
        if (boardTypeUser == 1) {
            boardType = BoardType.COMMON;
        } else {
            boardType = BoardType.DIAGONAL;
        }

        int m = 0, n = 0;
        if (boardType != BoardType.DIAGONAL) {
            System.out.println("Write M (Max value is " + MAX_M + ")");
            m = getUserInput(in, MAX_M);
            System.out.println("Write N (Max value is " + MAX_M + ")");
            n = getUserInput(in, MAX_N);
        }
        System.out.println("Write K (Max value is " + MAX_M + ")");
        int k = getUserInput(in, MAX_K);

        System.out.println("Do you want to play with logs?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        boolean log = getUserInput(in, 2) == 1;

        System.out.println("Do you want to play tournament?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        if (getUserInput(in, 2) == 1) {
            tournament(in, boardType, log, m, n, k);
        } else {
            commonGame(in, boardType, log, m, n, k);
        }
    }

    private static void commonGame(Scanner in, BoardType boardType, boolean log, int m, int n, int k) {
        System.out.println("How many players?(Max value is " + MAX_PLAYERS_COUNT + ")");
        int playersCount = getUserInput(in, MAX_TOURNAMENT_PARTICIPANTS_COUNT);
        Player[] players = new Player[playersCount];

        for (int i = 0; i < playersCount; i++) {
            printPlayersList();
            System.out.println("Choose " + (i + 1) + " player(Write player number):");
            players[i] = getPlayer(getUserInput(in, PLAYER_TYPES_COUNT));
        }

        Board board = getBoard(boardType, m, n, k, playersCount);
        Game game = new Game(players, log);
        int result = game.play(board);

        if (result == 0) {
            System.out.println("DRAW!");
        } else {
            System.out.println("PLAYER " + result + " WIN!");
        }

        in.close();
        for (int i = 0; i < playersCount; i++) {
            players[i].endGame();
        }
    }
    private static void tournament(Scanner in, BoardType boardType, boolean log, int m, int n, int k) {
        System.out.println("How many participants?(Max value is " + MAX_TOURNAMENT_PARTICIPANTS_COUNT + ")");
        int participantsCount = getUserInput(in, MAX_TOURNAMENT_PARTICIPANTS_COUNT);

        Player[] players = new Player[participantsCount];
        for (int i = 1; i <= participantsCount; i++) {
            printPlayersList();
            System.out.println("Choose " + i + " player(Write player number):");
            players[i - 1] = getPlayer(getUserInput(in, PLAYER_TYPES_COUNT));
        }

        int[] score = new int[participantsCount];
        for (int i = 0; i < participantsCount; i++) {
            for (int j = 0; j < participantsCount; j++) {
                if (i == j) {
                    continue;
                }

                System.out.println("Player " + (i + 1) + " vs " + "Player " + (j + 1));
                System.out.println("FIGHT!");
                Game game = new Game(players, log);

                Board board = getBoard(boardType, m, n, k, 2);
                int result = game.play(board);

                if (result == 1) {
                    System.out.println("PLAYER " + (i + 1) + " WIN!");
                    score[i] += 3;
                } else if (result == 2) {
                    System.out.println("PLAYER " + (j + 1) + " WIN!");
                    score[j] += 3;
                } else {
                    System.out.println("DRAW!");
                    score[i]++;
                    score[j]++;
                }
            }
        }

        System.out.println("The tournament is over");
        System.out.println("Score table:");
        for (int i = 0; i < participantsCount; i++) {
            System.out.println("Player " + (i + 1) + " score is " + score[i]);
        }
    }

    private static int getUserInput(Scanner in, int maxInput) {
        while (true) {
            if (!in.hasNextLine()) {
                System.out.println("SYSTEM IN BROKEN");
                return 1;
            }
            Scanner line = new Scanner(in.nextLine());
            if (!line.hasNextInt()) {
                System.out.println("Incorrect input, try again");
                continue;
            }
            int input = line.nextInt();
            if (input > maxInput || input <= 0 || line.hasNext()) {
                System.out.println("Incorrect input, try again");
                continue;
            }
            return input;
        }
    }

    private static Player getPlayer(int number) {
        return switch (number) {
            case 1 -> new HumanPlayer();
            case 2 -> new RandomPlayer();
            case 3 -> new ExceptionPlayer();
            default -> null;
        };
    }

    private static void printPlayersList() {
        System.out.println("Player list:");
        System.out.println("1 - Human player");
        System.out.println("2 - Random player");
        System.out.println("3 - Exception player");
    }

    private static Board getBoard(BoardType boardType, int m, int n, int k, int playersCount) {
        if (boardType == BoardType.DIAGONAL) {
            return new MNKBoardWithDiagonalObstacles(k, playersCount);
        } else {
            return new MNKBoard(m, n, k, playersCount);
        }
    }
}
