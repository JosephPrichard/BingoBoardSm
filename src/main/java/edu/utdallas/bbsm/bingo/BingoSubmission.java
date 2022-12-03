package edu.utdallas.bbsm.bingo;

public class BingoSubmission {
    private BingoSquare sq;
    private String submission;

    public BingoSubmission() {
    }

    public BingoSubmission(BingoSquare sq, String submission) {
        this.sq = sq;
        this.submission = submission;
    }

    public BingoSquare getSq() {
        return sq;
    }

    public void setSq(BingoSquare sq) {
        this.sq = sq;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }
}
