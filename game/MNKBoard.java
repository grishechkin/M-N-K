package game.game;

import java.util.HashMap;
import java.util.Map;

public class MNKBoard implements Board {
    protected final int[][] board;
    private final int K;
    private final int M;
    private final int N;
    protected int doneMoves = 0;
    private int currentCell;
    private final Position position;
    private final int playersCount;
    private final static Map<Integer, String> intToCell = new HashMap<>(Map.of(
            0, "X",
            1, "O",
            2, "-",
            3, "|",
            -1, ".",
            -2, "#"
            ));

    public MNKBoard(int m, int n, int k, int playersCount) {
        board = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }
        this.playersCount = playersCount;
        this.K = k;
        this.M = m;
        this.N = n;
        currentCell = 0;
        position = new MNKPosition(this);
    }

    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public Result makeMove(Move move) {
        board[move.getRow()][move.getColumn()] = currentCell;
        if (checkEndGame(move.getRow(), move.getColumn(), currentCell)) {
            return Result.WIN;
        }
        currentCell = (currentCell + 1) % playersCount;
        if (++doneMoves == M * N) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    @Override
    public int getCell(int row, int col) {
        return board[row][col];
    }

    @Override
    public int getCurrentCell() {
        return currentCell;
    }

    @Override
    public int getN() {
        return N;
    }

    @Override
    public int getM() {
        return M;
    }

    @Override
    public int getK() {
        return K;
    }

    @Override
    public boolean isValid(Move move) {
        return move.getRow() < 0 || move.getColumn() < 0 ||
                move.getRow() >= M || move.getColumn() >= N ||
                board[move.getRow()][move.getColumn()] != -1;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Current turn - ").append(intToCell.get(currentCell)).append("\n");
        answer.append("  ");
        for (int i = 0; i < N; i++) {
            answer.append(" ");
            answer.append(i + 1);
        }
        answer.append("\n");
        for (int i = 0; i < M; i++) {
            answer.append(i + 1);
            if (i + 1 <= 9) {
                answer.append(" ");
            }
            for (int j = 0; j < N; j++) {
                answer.append(" ");
                answer.append(intToCell.get(board[i][j]));
            }
            answer.append("\n");
        }
        return new String(answer);
    }
    private int getCountCells(int row, int col, int type, int colCoef, int rowCoef) {
        int count = 0;
        int pos = 0;
        while (rowCoef * pos + row < M && colCoef * pos + col < N &&
               rowCoef * pos + row >= 0 && colCoef * pos + col >= 0 &&
               board[rowCoef * pos + row][colCoef * pos + col] == type) {
            pos++;
            count++;
        }
        pos = -1;
        while (rowCoef * pos + row < M && colCoef * pos + col < N &&
               rowCoef * pos + row >= 0 && colCoef * pos + col >= 0 &&
               board[rowCoef * pos + row][colCoef * pos + col] == type) {
            pos--;
            count++;
        }
        return count;
    }

    private boolean checkEndGame(int row, int col, int type) {
        return getCountCells(row, col, type, 0, 1) >= K ||
               getCountCells(row, col, type, 1, 0) >= K ||
               getCountCells(row, col, type, 1, 1) >= K ||
               getCountCells(row, col, type, 1, -1) >= K;
    }

    @Override
    public void setCurrentCell(int type) {
        currentCell = type;
    }
}
