package com.company.ProjectManagementSystem.controller;

import com.company.ProjectManagementSystem.model.Chat;
import com.company.ProjectManagementSystem.model.Message;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.requst.CreateMessageRequest;
import com.company.ProjectManagementSystem.service.MessageService;
import com.company.ProjectManagementSystem.service.ProjectService;
import com.company.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @PostMapping("/send")
    private ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request)throws Exception{
        User user=userService.findUserById(request.getSenderId());

        if(user==null)throw new Exception("User not found with id: "+request.getSenderId());

        Chat chat=projectService.getProjectById(request.getProjectId()).getChat();

        if(chat==null)throw new Exception("chat not found");

        Message sendMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sendMessage);

    }

    @GetMapping("chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId)
    throws Exception{
        List<Message> messages =messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
