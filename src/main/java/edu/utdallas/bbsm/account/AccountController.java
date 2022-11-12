package edu.utdallas.bbsm.account;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class AccountController {

    private final Map<String, AccountEntity> accounts = new HashMap<>();
    private final HashSet<String> loggedIn = new HashSet<>();

    @RequestMapping(value="/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> register(@RequestBody AccountDto body) {
        var username = body.getUsername();
        var password = body.getPassword();

        System.out.println(username + " " + password);

        var salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        var acc = new AccountEntity(username, hashedPassword, salt);
        accounts.put(acc.getUsername(), acc);
        System.out.println("Register: " + accounts);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> login(@RequestBody AccountDto body) {
        var username = body.getUsername();
        var password = body.getPassword();

        System.out.println(username + " " + password);

        var acc = accounts.get(username);

        if (acc == null) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Login: " + accounts);

        String hashedPassword = BCrypt.hashpw(password, acc.getSalt());
        if (acc.getPassword().equals(hashedPassword)) {
            loggedIn.add(username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/signOut", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> signOut(@RequestBody SignOutDto body) {
        loggedIn.remove(body.getUsername());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/changePassword", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto body) {
        var username = body.getUsername();
        var newPassword = body.getNewPassword();
        var oldPassword = body.getOldPassword();

        System.out.println(username + " " + newPassword + " " + oldPassword);

        var acc = accounts.get(username);

        if (acc == null) {
            System.out.println("No account");
            return ResponseEntity.badRequest().build();
        }

        String oldHashedPassword = BCrypt.hashpw(oldPassword, acc.getSalt());
        System.out.println("ChangePassword: " + acc.getPassword() + " " + oldHashedPassword);

        if (acc.getPassword().equals(oldHashedPassword)) {
            String newHashedPassword = BCrypt.hashpw(newPassword, acc.getSalt());
            acc.setPassword(newHashedPassword);
            System.out.println("ChangePassword: " + acc);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
