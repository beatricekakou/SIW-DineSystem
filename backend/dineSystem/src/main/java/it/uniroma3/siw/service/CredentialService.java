package it.uniroma3.siw.service;

import it.uniroma3.siw.dto.CredentialsDTO;
import it.uniroma3.siw.mapper.CredentialsMapper;
import it.uniroma3.siw.model.Credential;
import it.uniroma3.siw.repository.CredentialRepository;
import it.uniroma3.siw.utils.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CredentialService {

    private CredentialRepository credentialsRepository;
    private PasswordEncoder passwordEncoder;

    public Credential getCredentials(String username) {
        return this.credentialsRepository.findByUsername(username);
    }

    public Credential saveCredentials(CredentialsDTO credentials) {
        Credential credentialsEntity = CredentialsMapper.fromDTOToEntity(credentials);
        if(credentials.getRole().equals("USER"))
            credentialsEntity.setRole(UserRole.DEFAULT.getRole());
        else
            credentialsEntity.setRole(UserRole.ADMIN.getRole());

        credentialsEntity.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return credentialsEntity;
    }

    public boolean existsByUsername(String username) {
        return this.credentialsRepository.existsByUsername(username);}

}
