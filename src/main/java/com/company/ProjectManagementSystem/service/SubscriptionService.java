package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.PlanType;
import com.company.ProjectManagementSystem.model.Subscription;
import com.company.ProjectManagementSystem.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId);

    Subscription upgrateSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
