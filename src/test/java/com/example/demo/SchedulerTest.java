package com.example.demo;
import com.example.demo.utils.Scheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulerTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private Scheduler scheduler;

    @Test
    void testSendScheduledEmail() {
        // Arrange
        String to = "fake-email@example.com";
        String subject = "Scheduled Email: Every 30 seconds";
        String text = "This is a scheduled email sent every 30 seconds from Schedular.";

        // Act
        scheduler.sendScheduledEmail();

        // Assert
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testPrintScheduledMessage() {
        // Act
        scheduler.printScheduledMessage();

        // Assert
        // You can add additional assertions based on the expected behavior of printScheduledMessage
        // For example, check if the message is logged or if there are any specific side effects
    }
}
