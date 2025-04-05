package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {
        public void sendInvitation (String email, Long projectId) throws MessagingException;
        public Invitation acceptInvitation(String token, Long userId) throws Exception;
        public String getTokenByUserMail(String userEmail);
        void deleteToken(String token);
}
