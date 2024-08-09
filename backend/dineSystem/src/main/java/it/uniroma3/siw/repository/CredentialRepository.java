package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Credential findByUsername(String username);

    boolean existsByUsername(String username);
}
