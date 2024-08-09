package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.Reservation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * Sends a confirmation email for a given reservation.
     *
     * @param reservation the reservation for which the confirmation email will be sent
     * @throws MessagingException if there is an error during the creation or sending of the email
     */
    public void sendReservationEmail(Reservation reservation) throws MessagingException {
        // Create a MIME message for email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Populate the Thymeleaf template with the reservation details
        Context context = new Context();
        context.setVariable("reservation", reservation);
        String body = templateEngine.process("reservation-confirmation", context);

        // Configure the email details
        helper.setTo(reservation.getUser().getEmail());
        helper.setSubject("Reservation Confirmation");
        helper.setText(body, true); // 'true' indicates the email body is HTML content

        // Send the email
        try {
            mailSender.send(message);
            log.info("Email successfully sent to {}", reservation.getUser().getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to {}", reservation.getUser().getEmail(), e);
        }
    }

    /**
     * Sends a confirmation email for a given order.
     *
     * @param order the reservation for which the confirmation email will be sent
     * @throws MessagingException if there is an error during the creation or sending of the email
     */
    public void sendOrderEmail(Order order) throws MessagingException {
        // Create a MIME message for email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Populate the Thymeleaf template with the reservation details
        Context context = new Context();
        context.setVariable("order", order);
        String body = templateEngine.process("order-confirmation", context);

        // Configure the email details
        helper.setTo(order.getUser().getEmail());
        helper.setSubject("Order Confirmation");
        helper.setText(body, true); // 'true' indicates the email body is HTML content

        // Send the email
        try {
            mailSender.send(message);
            log.info("Email successfully sent to {}", order.getUser().getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to {}", order.getUser().getEmail(), e);
        }
    }
}
