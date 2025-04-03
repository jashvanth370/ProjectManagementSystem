package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Chat;
import com.company.ProjectManagementSystem.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepository;
    @Override
    public Chat creatsChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
