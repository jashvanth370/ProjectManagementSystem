package com.company.ProjectManagementSystem.repository;

import com.company.ProjectManagementSystem.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    List<Comments> findCommentsByIssueId(Long issueId);

}
