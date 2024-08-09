package it.uniroma3.siw.validator;

import it.uniroma3.siw.dto.UserDTO;
import it.uniroma3.siw.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@AllArgsConstructor
public class UserValidator implements Validator {

    private UserService userService;

    final Integer MAX_NAME_LENGTH = 30;
    final Integer MIN_NAME_LENGTH = 4;


    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;
        String firstName = user.getFirstName().trim();
        String lastName = user.getLastName().trim();
        String email = user.getEmail().trim();

        if (firstName.isEmpty())
            errors.reject("user.emptyFirstName");
        else if (firstName.length() < MIN_NAME_LENGTH || firstName.length() > MAX_NAME_LENGTH)
            errors.reject("user.incorrectLengthFirstName");

        if (lastName.isEmpty())
            errors.reject("user.emptyLastName");
        else if (lastName.length() < MIN_NAME_LENGTH || lastName.length() > MAX_NAME_LENGTH)
            errors.reject("user.incorrectLengthLastName");

        if (email.isEmpty())
            errors.reject("user.emptyEmail");
        else if (!isValidEmail(email))
            errors.reject("user.invalidEmail");

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }


}
