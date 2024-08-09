package it.uniroma3.siw.config.email;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class EmailConfig {

    /*@Value("${email.service-account-key-json}")
    private String serviceAccountKeyJson;

    @Value("${email.user-email}")
    private String userEmail;

    @Value("${email.application-name}")
    private String applicationName;

    @Bean
    public Gmail gmailService() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
                        new ByteArrayInputStream(serviceAccountKeyJson.getBytes(StandardCharsets.UTF_8)))
                .createScoped(Collections.singleton(GmailScopes.GMAIL_SEND))
                .createDelegated(userEmail);

        return new Gmail.Builder(new NetHttpTransport(), Gmail.DEFAULT_JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(applicationName)
                .build();
    }*/
}
