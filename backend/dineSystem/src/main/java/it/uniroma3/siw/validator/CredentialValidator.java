package it.uniroma3.siw.validator;

import it.uniroma3.siw.dto.CredentialsDTO;
import it.uniroma3.siw.service.CredentialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class CredentialValidator  implements Validator {
    final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 4;

    private CredentialService credentialsService;

    @Override
    public void validate(Object o, Errors errors) {
        CredentialsDTO credentials = (CredentialsDTO) o;
        String username = credentials.getUsername().trim();
        String password = credentials.getPassword().trim();

        if (username.isEmpty())
            errors.reject("credentials.emptyUsername");
        else if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH)
            errors.reject("credentials.incorrectUsernameLength");
        else if (this.credentialsService.existsByUsername(username))
            errors.reject("credentials.duplicate");

        if (password.isEmpty())
            errors.reject("credentials.emptyPassword");
        else if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
            errors.reject("credentials.incorrectPasswordLength");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CredentialsDTO.class.equals(clazz);
    }
}
