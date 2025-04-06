package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Comments;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface CommentService {
    Comments createComment(Long issueId,Long userId,String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comments> findCommentByIssueId(Long issueId);
}
