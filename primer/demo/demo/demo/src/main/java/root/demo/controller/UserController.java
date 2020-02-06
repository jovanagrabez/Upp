package root.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.demo.model.User;
import root.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);

        return user;
    }

    @GetMapping(value = "/recenzenti")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getAllRecenzent();
        return new ResponseEntity<>(users, HttpStatus.OK);
        }


    @GetMapping(value = "/rec")
    public ResponseEntity<List<User>> getRecenzenti() {
        List<User> users = this.userService.getRec();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/urednici")
    public ResponseEntity<List<User>> getUrednici() {
        List<User> users = this.userService.getUrednici();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
