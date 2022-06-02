package com.baeldung.resource.service;

import static org.apache.commons.codec.CharEncoding.UTF_8;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.baeldung.resource.exceptions.ResourceNotFoundException;
import com.baeldung.resource.persistence.model.BookingRequest;
import com.baeldung.resource.spring.properties.SCCEmailProperties;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@AllArgsConstructor
@Component
public class EmailService {

    private final AmazonSimpleEmailService client;

    private final SCCEmailProperties properties;

    private final SpringTemplateEngine templateEngine;

    public void sendEmailActivatedAdmin(String fullName, String email) {
        Map<String, Object> props = new HashMap<>();
        props.put("name", fullName);
        props.put("email", email);
        String html = getTemplateHtml(props, "admin/new-user-register");

        String emailSubject = "SCC User has registered";

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(properties.getAdminEmail()))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset(UTF_8).withData(html)))
                            .withSubject(new Content().withCharset(UTF_8).withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("Registration email sent to admin");
        } catch (Exception e) {
            log.error("Failed to send admin email", e);
        }
    }

    private String getTemplateHtml(Map<String, Object> props, String template) {
        Context context = new Context();
        context.setVariables(props);
        return templateEngine.process(template, context);
    }

    public void sendEmailActivatedUser(String firstName, UUID userId, String email) {

        String emailSubject = "Welcome to the 2nd Closet Club";
        Map<String, Object> props = new HashMap<>();
        props.put("name", firstName);
        String html = getTemplateHtml(props, "public/new-user-activation");

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset(UTF_8).withData(html)))
                            .withSubject(new Content().withCharset(UTF_8).withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("User Activation email sent to {}", userId);
        } catch (Exception e) {
            log.error("Failed to send email to user {}", userId, e);
        }
    }

    public void sendEmailBookingAdmin(BookingRequest bookingRequest) {
        String sizeName = bookingRequest.getProduct().getSizes().stream()
                .filter(something -> something.getId().equals(bookingRequest.getProductInventory().getId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("test"))
                .getSize().getName();
        Map<String, Object> props = new HashMap<>();
        props.put("bookingId", bookingRequest.getId());
        props.put("fullName", bookingRequest.getUser().getFullName());
        props.put("productId", bookingRequest.getProduct().getId());
        props.put("productName", bookingRequest.getProduct().getName());
        props.put("productSize", sizeName);
        props.put("bookingStartDate", bookingRequest.getStartDate().format(DateTimeFormatter.ISO_DATE));

        String html = getTemplateHtml(props, "admin/new-booking-request");
        String emailSubject = "New Booking Request";

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(properties.getAdminEmail()))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset(UTF_8).withData(html)))
                            .withSubject(new Content().withCharset(UTF_8).withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("Booking request email to Admin for booking id {}", bookingRequest.getId());
        } catch (Exception e) {
            log.error("Failed to send email to Admin for booking id {}", bookingRequest.getId(), e);
        }
    }
}
