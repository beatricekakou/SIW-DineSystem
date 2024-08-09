package it.uniroma3.siw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Data
public class JwtUtil {

    // Secret key used for signing the JWT
    @Value("${jwt.secret.key}")
    private String secretKeyProperty;
    private static SecretKey secretKey;



    @PostConstruct
    public void init() {
        // Decodifica la chiave segreta codificata in Base64 e genera una SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyProperty);
        JwtUtil.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    // Metodo per ottenere la chiave segreta
    public static SecretKey getSecretKey() {
        return secretKey;
    }

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username the username for which the token is generated
     * @return the generated JWT token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(); // Create a map to hold claims
        return createToken(claims, username); // Create and return the token
    }

    /**
     * Creates a JWT token with the specified claims and subject.
     *
     * @param claims  the map of claims to be included in the token
     * @param subject the subject (typically the username) of the token
     * @return the created JWT token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        // Build and return the JWT token
        return Jwts.builder()
                .setClaims(claims) // Set the claims
                .setSubject(subject) // Set the subject (username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration time (10 hours)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // Sign the token with the secret key
                .compact(); // Compact the JWT into a string
    }

    /**
     * Extracts the username from the given token.
     *
     * @param token the JWT token from which the username is extracted
     * @return the extracted username
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extracts all claims from the given token.
     *
     * @param token the JWT token from which the claims are extracted
     * @return the extracted claims
     */
    private Claims extractAllClaims(String token) {
        // Parse and return claims from the token
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKeyProperty()) // Use the SecretKey to parse the token
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the token has expired.
     *
     * @param token the JWT token to check for expiration
     * @return true if the token has expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        // Return true if the token has expired
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Validates the given token against the provided username.
     *
     * @param token    the JWT token to be validated
     * @param username the username to validate against the token
     * @return true if the token is valid for the given username and not expired, false otherwise
     */
    public boolean validateToken(String token, String username) {
        // Extract the username from the token
        final String extractedUsername = extractUsername(token);
        // Validate the token by checking the username and expiration status
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

}
