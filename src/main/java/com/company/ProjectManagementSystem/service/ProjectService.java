package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Chat;
import com.company.ProjectManagementSystem.model.Project;
import com.company.ProjectManagementSystem.model.User;

import java.util.List;

public interface ProjectService {
    public Project createproject(Project project,User user) throws Exception;

    List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;

    Project getProjectById(Long projectId) throws  Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    Project updateProject(Project updateProject, Long id)throws Exception;

    void addUserToProject(Long projectId,long userId)throws  Exception;

    void removeUserFromProject(Long projectId,long userId)throws  Exception;

    Chat getChatByProjectId(Long projectId)throws  Exception;

    List<Project> searchProjects(String keyword, User user) throws Exception;

}
