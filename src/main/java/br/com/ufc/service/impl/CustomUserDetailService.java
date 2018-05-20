package br.com.ufc.service.impl;

import br.com.ufc.model.User;
import br.com.ufc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = Optional.ofNullable(userRepository.findByEmail(email))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        List<GrantedAuthority> authorityListOrg = AuthorityUtils.createAuthorityList("ROLE_PARTICIPANT", "ROLE_ORGANIZER");
//        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_PARTICIPANT");
//        return new org.springframework.security.core.userdetails.User
//                (user.getEmail(), user.getPassword(), user.isOrganizer() ? authorityListOrg : authorityListUser);

        return Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
