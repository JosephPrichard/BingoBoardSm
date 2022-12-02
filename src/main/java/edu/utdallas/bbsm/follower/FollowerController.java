package edu.utdallas.bbsm.follower;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class FollowerController {
    private final HashSet<Follow> follows = new HashSet<>();

    @RequestMapping(value="/follows", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> follow(@RequestBody Follow follow) {
        follows.add(follow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/follows", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> unFollow(@RequestBody Follow follow) {
        follows.remove(follow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/follows", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getFollow(@RequestBody Follow follow) {
        var has = follows.contains(follow);
        System.out.print(has);
        return new ResponseEntity<>(Boolean.toString(has), HttpStatus.OK);
    }
}
