package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.UserDTO;
import it.uniroma3.siw.mapper.UserMapper;
import it.uniroma3.siw.model.Credential;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private  CredentialService credentialService;

    public User createUser(UserDTO user) {
        User userEntity = UserMapper.fromDTOToEntity(user);
        Credential credentialsEntity = credentialService.saveCredentials(user.getCredentials());
        userEntity.setCredential(credentialsEntity);
        return this.userRepository.save(userEntity);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public Optional<User> getUserByUserName(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }
}
