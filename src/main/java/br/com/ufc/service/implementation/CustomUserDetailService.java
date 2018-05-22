package br.com.ufc.service.implementation;

import br.com.ufc.model.User;
import br.com.ufc.repository.OrganizerRepository;
import br.com.ufc.repository.ParticipantRepository;
import br.com.ufc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final OrganizerRepository organizerRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository, ParticipantRepository participantRepository, OrganizerRepository organizerRepository) {
        this.participantRepository = participantRepository;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
