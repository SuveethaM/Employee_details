package com.example.demo.utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    private MailSender mailSender;

    @After("execution(* com.example.demo.controller.EmployeeController.*(..))")
    public void sendEmailAfterMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String to = "fake-email@example.com";
        String subject = methodName;
        String text = "The method " + methodName + " in EmployeeController has completed its execution.";
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
