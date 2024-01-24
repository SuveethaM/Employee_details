package com.example.demo.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    private MailSender mailSender;

    // Define pointcut expressions for the methods in EmployeeController
    @Before("execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void sendEmailBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String to = "fake-email@example.com";
        String subject = "Method Execution Started: " + methodName;
        String text = "The method " + methodName + " in EmployeeController has started its execution.";
        sendEmail(to, subject, text);

    }

    @After("execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void sendEmailAfterMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String to = "fake-email@example.com";
        String subject = "Method Execution Completed: " + methodName;
        String text = "The method " + methodName + " in EmployeeController has completed its execution.";
        sendEmail(to, subject, text);
    }
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
