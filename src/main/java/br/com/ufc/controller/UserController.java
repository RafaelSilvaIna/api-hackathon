package br.com.ufc.controller;

import br.com.ufc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/participant")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
