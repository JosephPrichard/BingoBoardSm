package edu.utdallas.bbsm.bingo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BingoBoardController {

    private final BingoBoard board = new BingoBoard();

    @RequestMapping(value="/currentBingoBoard", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<BingoBoard> getCurrentBingoBoard() {
        System.out.println(board);
        return ResponseEntity.ok(board);
    }

    @RequestMapping(value="/submission", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> submission(@RequestBody BingoSquare sq) {
        var submission = board.getSubmission(sq);
        System.out.println(sq.getCol() + " " + sq.getRow() + " " + submission);
        return ResponseEntity.ok(submission);
    }

    @RequestMapping(value="/fillSquare", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BingoSubmission> fillSquare(@RequestBody BingoSubmission submission) {
        if (!board.isInBounds(submission.getSq())) {
            return ResponseEntity.badRequest().build();
        }
        board.fillSquare(submission.getSq(), submission.getSubmission());
        return ResponseEntity.ok(submission);
    }

    @RequestMapping(value="/isCompleted", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> isCompleted() {
        boolean isCompleted = board.isCompleted();
        System.out.println(isCompleted);
        return ResponseEntity.ok(isCompleted);
    }
}
