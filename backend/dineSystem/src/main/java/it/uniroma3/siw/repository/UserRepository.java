package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Credential;
import it.uniroma3.siw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findByCredential(Credential credential);

    @Query("SELECT u FROM User u WHERE u.credential.username = :username")
    User findByUsername(@Param("username") String username);

}
