package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
public class Scheduler {
    @Autowired
    private MailSender mailSender;

    @Scheduled(cron = "0 0/1 * * * * ") // Every 5 minutes
    public void sendScheduledEmail() {
        String to = "fake-email@example.com";
        String subject = "Scheduled Email: Every 5 minutes";
        String text = "This is a scheduled email sent every 5 minutes.";
        System.out.println(" Every 5 minutes");
        sendEmail(to, subject, text);
    }

    @Scheduled(cron = "0 0 * * * ?") // On the hour
    public void sendHourlyEmail() {
        String to = "fake-email@example.com";
        String subject = "Hourly Email";
        String text = "This is an hourly scheduled email.";
        System.out.println(" Every 5 minutes");
        sendEmail(to, subject, text);
    }
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
