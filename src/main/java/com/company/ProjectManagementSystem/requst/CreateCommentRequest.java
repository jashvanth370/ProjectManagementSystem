package com.company.ProjectManagementSystem.requst;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private Long issueId;
    private String content;
}
