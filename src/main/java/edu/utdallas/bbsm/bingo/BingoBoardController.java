package edu.utdallas.bbsm.bingo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BingoBoardController {

    private final BingoBoard board = new BingoBoard();

    @RequestMapping(value="/currentBingoBoard", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCurrentBingoBoard() {
        System.out.println(board);
        return new ResponseEntity<>(board.toString(), HttpStatus.OK);
    }

    @RequestMapping(value="/isSubmitted", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> isSubmitted(@RequestBody BingoSquare sq) {
        boolean isFilled = board.isFilled(sq);
        System.out.println(isFilled);
        return new ResponseEntity<>(Boolean.toString(isFilled), HttpStatus.OK);
    }

    @RequestMapping(value="/fillSquare", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> fillSquare(@RequestBody BingoSquare sq) {
        board.fillSquare(sq);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/isCompleted", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> isCompleted() {
        boolean isCompleted = board.isCompleted();
        System.out.println(isCompleted);
        return new ResponseEntity<>(Boolean.toString(isCompleted), HttpStatus.OK);
    }
}
