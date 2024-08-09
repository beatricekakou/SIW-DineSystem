package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.AuthRequest;
import it.uniroma3.siw.dto.CredentialsDTO;
import it.uniroma3.siw.dto.UserDTO;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.CustomUserDetailsService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.utils.JwtUtil;
import it.uniroma3.siw.validator.CredentialValidator;
import it.uniroma3.siw.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;
    private UserService userService;
    private CredentialService credentialService;
    private UserValidator userValidator;
    private CredentialValidator credentialValidator;

    /**
     * Authenticates the user and generates a JWT token upon successful authentication.
     *
     * @param authRequest the authentication request containing username and password
     * @return ResponseEntity with the JWT token if authentication is successful, otherwise an error message
     */

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user using the provided credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            // Return 401 Unauthorized if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Load user details and generate a JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // Return the JWT token in the response
        return ResponseEntity.ok(token);
    }

    /**
     * Registers a new user in the system.
     *
     * @param userDTO the user data transfer object containing user details
     * @return ResponseEntity indicating the result of the registration process
     */
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO, BindingResult result) {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors occurred.");
        }

        CredentialsDTO credentialsDTO = userDTO.getCredentials();
        credentialValidator.validate(credentialsDTO, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors in credentials.");
        }

        if (credentialService.existsByUsername(credentialsDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }

        if (userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        userService.createUser(userDTO); // Assuming createUser handles both User and Credentials

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // Binding validators
    @InitBinder("userDTO")
    protected void initUserBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @InitBinder("credentialsDTO")
    protected void initCredentialBinder(WebDataBinder binder) {
        binder.addValidators(credentialValidator);
    }
}


