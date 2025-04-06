package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.PlanType;
import com.company.ProjectManagementSystem.model.Subscription;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();

        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setUser(user);
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) {
        Subscription subscription= subscriptionRepository.findByUserId(userId);
        if(!isValid(subscription)){
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionStartDate(LocalDate.now());
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription upgrateSubscription(Long userId, PlanType planType) {
        Subscription subscription =subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());

        if(planType.equals(PlanType.MONTHLY)){
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        else {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)){
            return true;
        }

        LocalDate endDate=subscription.getSubscriptionEndDate();
        LocalDate currentDate= LocalDate.now();


        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
