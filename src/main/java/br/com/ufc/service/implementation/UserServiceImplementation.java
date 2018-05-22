package br.com.ufc.service.implementation;

import br.com.ufc.model.User;
import br.com.ufc.repository.UserRepository;
import br.com.ufc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
