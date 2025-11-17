package com.example.NotificationService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notify")
public class NotificationController {

	@GetMapping("/{userId}")
    public String sendNotification(@PathVariable Long userId) {
        return "Notification sent to user: " + userId;
    }
}
