package com.company.ProjectManagementSystem.controller;

import com.company.ProjectManagementSystem.model.PlanType;
import com.company.ProjectManagementSystem.model.Subscription;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.service.SubscriptionService;
import com.company.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader ("Authorization") String jwt
    ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Subscription subscription= subscriptionService.getUserSubscription(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(
            @RequestHeader ("Authorization") String jwt,
            @RequestParam PlanType planType
            ) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Subscription subscription= subscriptionService.upgrateSubscription(user.getId(),planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
