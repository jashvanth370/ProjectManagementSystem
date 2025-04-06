package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Comments;
import com.company.ProjectManagementSystem.model.Issue;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.repository.CommentRepository;
import com.company.ProjectManagementSystem.repository.IssueRepository;
import com.company.ProjectManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Comments createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> optionalIssue =issueRepository.findById(issueId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new Exception("User not found"+userId);
        }

        if(optionalIssue.isEmpty()){
            throw new Exception("Issue not found"+issueId);
        }

        Issue issue =optionalIssue.get();
        User user =optionalUser.get();

        Comments comments =new Comments();

        comments.setUser(user);
        comments.setIssue(issue);
        comments.setCreatedDateTime(LocalDateTime.now());
        comments.setContent(content);

        Comments saveComment =commentRepository.save(comments);

        issue.getComments().add(saveComment);


        return saveComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comments> optionalComments  =commentRepository.findById(commentId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new Exception("User not found"+userId);
        }

        if(optionalComments.isEmpty()){
            throw new Exception("Comment not found"+commentId);
        }

        User user =optionalUser.get();
        Comments comments =optionalComments.get();

        if(comments.getUser().equals(user)){
            commentRepository.delete(comments);
        }else {
            throw new Exception("User Not Permitted to Delete this comment");
        }


    }

    @Override
    public List<Comments> findCommentByIssueId(Long issueId) {
        return commentRepository.findCommentsByIssueId(issueId);
    }
}
