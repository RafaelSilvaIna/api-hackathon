package br.com.ufc.service;

import br.com.ufc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> getUsers(Pageable pageable);
    User saveUser(User user);
}
