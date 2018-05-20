package br.com.ufc.service;

import br.com.ufc.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save( User user);
}
