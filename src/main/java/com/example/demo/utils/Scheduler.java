package com.example.demo.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//public class Scheduler {
//    @Autowired
//    private MailSender mailSender;

//    @Scheduled(cron = "0 0/1 * * * * ") // Every 5 minutes
//    public void sendScheduledEmail() {
//        String to = "fake-email@example.com";
//        String subject = "Scheduled Email: Every 5 minutes";
//        String text = "This is a scheduled email sent every 5 minutes.";
//        System.out.println(" Every 5 minutes");
//        sendEmail(to, subject, text);
//    }
//
//    @Scheduled(cron = "0 0 * * * ?") // On the hour
//    public void sendHourlyEmail() {
//        String to = "fake-email@example.com";
//        String subject = "Hourly Email";
//        String text = "This is an hourly scheduled email.";
//        System.out.println(" Every 5 minutes");
//        sendEmail(to, subject, text);
//    }
//    private void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.mail.MailSender;
    import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

    @Component
    public class Scheduler {
        @Autowired
        private MailSender mailSender;

        // Scheduled email method for demonstration (every 10 minutes)
        @Scheduled(cron = "0 0/3 * * * *")
        public void sendScheduledEmail() {
            String to = "fake-email@example.com";
            String subject = "Scheduled Email: Every 10 minutes";
            String text = "This is a scheduled email sent every 1 minutes from ProductController.";
            System.out.println("For every 3 minutes");
            // You can call EmailAspect's sendEmail method here if needed
        }

        // New scheduled method to print a message in the console every 1 minute
        @Scheduled(cron = "0 0/1 * * * *")
        public void printConsoleMessage() {
            System.out.println("For every one minute");
        }
    }

