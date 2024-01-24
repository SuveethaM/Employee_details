package com.example.demo.controller;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/health")
public class HealthcheckController {
    @Autowired
    private HealthIndicator customHealthIndicator;

    @GetMapping
    public Health getHealthStatus() {
        return customHealthIndicator.health();
    }
}

