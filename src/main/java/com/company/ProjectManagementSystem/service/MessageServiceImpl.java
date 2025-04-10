package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Chat;
import com.company.ProjectManagementSystem.model.Message;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.repository.MessageRepository;
import com.company.ProjectManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;
    @Override
    public Message sendMessage(Long senderId, Long projectId, String content)throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(()->new Exception("User not found with id: "+senderId));

            Chat chat =projectService.getProjectById(projectId).getChat();

            Message message = new Message();

            message.setSender(sender);
            message.setContent(content);
            message.setCreateAT(LocalDateTime.now());
            message.setChat(chat);

            Message sendMessage= messageRepository.save(message);

            chat.getMessages().add(sendMessage);

            return sendMessage;
    }

    @Override
    public List<Message> getMessageByProjectId(Long projectId) throws Exception {
        Chat chat=projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc =messageRepository.findByChatIdOrderByCreateATAsc(chat.getId());

        return findByChatIdOrderByCreatedAtAsc;
    }
}
