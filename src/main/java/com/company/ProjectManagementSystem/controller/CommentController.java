package com.company.ProjectManagementSystem.controller;

import com.company.ProjectManagementSystem.model.Comments;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.requst.CreateCommentRequest;
import com.company.ProjectManagementSystem.response.MessageResponse;
import com.company.ProjectManagementSystem.service.CommentService;
import com.company.ProjectManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody CreateCommentRequest request,
                                                  @RequestHeader("Authorization") String jwt) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        Comments createComment =commentService.createComment(
                request.getIssueId(),
                user.getId(),
                request.getContent()
        );
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);

    }

    @PostMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                  @RequestHeader("Authorization") String jwt) throws Exception{
        User user =userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());

        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setMessage("Comment deleted successfully");

        return new ResponseEntity<>(messageResponse,HttpStatus.OK);

    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comments>> getCommentByIssueId(@PathVariable Long issueId){
        List<Comments> comments =commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }


}
