package edu.utdallas.bbsm.bingo;

import java.util.Arrays;

public class BingoBoard {

    private final String[][] board = new String[5][5];
    private final String[][] submissions = new String[5][5];

    public BingoBoard() {
        // generates some fake data for testing purposes
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = ((i * 5 + j) + 'A') + "";
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public void fillSquare(BingoSquare sq, String submission) {
        submissions[sq.getRow()][sq.getCol()] = submission;
    }

    public boolean isInBounds(BingoSquare sq) {
        return sq.getCol() >= 0 && sq.getCol() < 5 && sq.getRow() >= 0 && sq.getRow() < 5;
    }

    public String getSubmission(BingoSquare sq) {
        return submissions[sq.getRow()][sq.getCol()];
    }

    public boolean isCompleted() {
        // check horizontally and vertically
        int i, j, count;
        for(i = 0; i < 5; i++) {
            j = 0;
            count = 0;
            // Checks horizontally for a win.
            while(submissions[i][j] != null) {
                count++;
                j++;
                if(count == 5)
                    return true;
            }

            j = 0;
            count = 0;
            // Checks vertically for a win.
            while(submissions[i][j] != null) {
                count++;
                j++;
                if(count == 5)
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BingoBoard{" +
            "board=" + Arrays.deepToString(board) + "," +
            "marked=" + Arrays.deepToString(submissions) +
            '}';
    }
}
