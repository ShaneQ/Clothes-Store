package com.baeldung.resource.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.baeldung.resource.spring.properties.SCCEmailProperties;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailService {

    private AmazonSimpleEmailService client;

    private SCCEmailProperties properties;

    public EmailService(AmazonSimpleEmailService client,
            SCCEmailProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    public void sendEmailActivatedAdmin(String firstName, String lastName, String email) {

        String emailContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>User Registered</title>\n" +
                "</head>\n" +
                "<body style=\"background: whitesmoke; padding: 30px; height: 100%\">\n" +
                "<h5 style=\"font-size: 18px; margin-bottom: 6px\">Hey Iaina,</h5>\n" +
                "<p style=\"font-size: 16px; font-weight: 500\">A new user has registered with the below details</p>\n"
                +
                "<p>Name " + firstName + " " + lastName + "</p>\n" +
                "<p>Email " + email + "</p>\n" +
                "</body>\n" +
                "</html>";

        String emailSubject = "SCC User has registered";

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(properties.getAdminEmail()))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset("UTF-8").withData(emailContent)))
                            .withSubject(new Content().withCharset("UTF-8").withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("Registration email sent to admin");
        } catch (Exception e) {
            log.error("Failed to send admin email", e);
        }
    }

    public void sendEmailActivatedUser(String firstName, UUID userId, String email) {

        String emailContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Registration Complete</title>\n" +
                "</head>\n" +
                "<body style=\"background: whitesmoke; padding: 30px; height: 100%\">\n" +
                "<h5 style=\"font-size: 18px; margin-bottom: 6px\">Hey " + firstName + ",</h5>\n" +
                "<p style=\"font-size: 16px; font-weight: 500\">Your Second Closet Club is now active. "
                + "Click <a href=\"https://2ndclosetclub.com/shop\">here</a> to browse our wardrobe</p>\n"

                +
                "</body>\n" +
                "</html>";

        String emailSubject = "Welcome to the 2nd Closet Club";

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset("UTF-8").withData(emailContent)))
                            .withSubject(new Content().withCharset("UTF-8").withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("User Activation email sent to {}", userId);
        } catch (Exception e) {
            log.error("Failed to send email to user {}", userId, e);
        }
    }

    public void sendEmailBookingAdmin(Long bookingId, Long productId, String productName, Long inventoryId, LocalDate startDate) {
        String emailContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Registration Complete</title>\n" +
                "</head>\n" +
                "<body style=\"background: whitesmoke; padding: 30px; height: 100%\">\n" +
                "<h5 style=\"font-size: 18px; margin-bottom: 6px\">Hey Iaina,</h5>\n" +
                "<p style=\"font-size: 16px; font-weight: 500\">you just recieved a booking with the below information. "
                +
                "<p>Booking Id: "+ bookingId+"</p>"+
                "<p>Product Id: "+ productId+"</p>"+
                "<p>Inventory Id: "+ inventoryId+"</p>"+
                "<p>Start Date: "+ startDate.format(DateTimeFormatter.ISO_DATE)+"</p>"+
                "</body>\n" +
                "</html>";

        String emailSubject = "New Booking";

        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(properties.getAdminEmail()))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(
                                    new Content().withCharset("UTF-8").withData(emailContent)))
                            .withSubject(new Content().withCharset("UTF-8").withData(emailSubject)))
                    .withSource(properties.getSenderEmail());
            client.sendEmail(sendEmailRequest);
            log.info("Booking request email to Admin for booking id {}", bookingId);
        } catch (Exception e) {
            log.error("Failed to send email to Admin for booking id {}", bookingId, e);
        }
    }
}
