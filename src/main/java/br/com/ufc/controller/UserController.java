package br.com.ufc.controller;

import br.com.ufc.model.User;
import br.com.ufc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ptcp")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

//
//    @PostMapping("/organizer")
//    public  ResponseEntity<?> save(@Valid @RequestBody User user) {
//        user.setOrganizer(false);
//        return  new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }
}
