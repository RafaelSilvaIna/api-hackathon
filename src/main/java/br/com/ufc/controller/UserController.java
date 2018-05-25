package br.com.ufc.controller;

import br.com.ufc.model.User;
import br.com.ufc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/participant")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(PageRequest.of(pageable.getPageNumber(), 10)), HttpStatus.OK);
    }
}
