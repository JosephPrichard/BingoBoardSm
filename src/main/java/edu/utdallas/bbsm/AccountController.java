package edu.utdallas.bbsm;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    private Map<String, Account> accounts = new HashMap<>();

    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> login(String username, String password) {
        var salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        var acc = new Account(username, hashedPassword, salt);
        accounts.put(acc.getUsername(), acc);
        System.out.println("Register: " + accounts);

        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }
}
