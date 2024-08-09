package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Credential;
import it.uniroma3.siw.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credentials = credentialRepository.findByUsername(username);
        if (Objects.isNull(credentials)) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(credentials.getUsername(), credentials.getPassword(), new ArrayList<>());
    }
}