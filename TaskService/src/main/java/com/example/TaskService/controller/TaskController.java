package com.example.TaskService.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.TaskService.model.Task;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final Map<Long, Task> tasks = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public String addTask(@RequestBody Task task) {
        
        String userUrl = "http://user-service/users/" + task.getUserId();
        try {
            String userResponse = restTemplate.getForObject(userUrl, String.class);
            System.out.println(" User verified: " + userResponse);
        } catch (Exception e) {
            return " User not found! Task creation failed.";
        }

        
        tasks.put(task.getId(), task);

        
        String notifyUrl = "http://notification-service/notify/" + task.getUserId();
        String notifyResponse = restTemplate.getForObject(notifyUrl, String.class);
        System.out.println(" Notification response: " + notifyResponse);

        return "Task created successfully and notification sent!";
    }

    @GetMapping
    public Collection<Task> getAllTasks() {
        return tasks.values();
    }
}
