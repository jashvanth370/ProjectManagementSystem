package com.company.ProjectManagementSystem.service;

import com.company.ProjectManagementSystem.model.Chat;
import com.company.ProjectManagementSystem.model.Project;
import com.company.ProjectManagementSystem.model.User;
import com.company.ProjectManagementSystem.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;
    @Override
    public Project createproject(Project project, User user) throws Exception {
        Project craeteProject = new Project();

        craeteProject.setOwner(user);
        craeteProject.setTags(project.getTags());
        craeteProject.setName(project.getName());
        craeteProject.setDescription(project.getDescription());
        craeteProject.setCategory(project.getCategory());
        craeteProject.getTeam().add(user);

        Project savedProject = projectRepository.save(craeteProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);

        Chat projectChat=chatService.creatsChat(chat);
        savedProject.setChat(projectChat);


        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects =projectRepository.findByTeamContainingOrOwner(user,user);

        if(category!=null){
            projects=projects.stream().filter(project -> project.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        if(tag!=null){
            projects=projects.stream().filter(project -> project.getTags().contains(tag))
                    .collect(Collectors.toList());
        }
        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isEmpty()){
            throw new Exception("Project Not Found");
        }
        return optionalProject.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        getProjectById(projectId);
        projectRepository.deleteById(projectId);

    }

    @Override
    public Project updateProject(Project updateProject, Long id) throws Exception {
        Project project = getProjectById(id);

        project.setName(updateProject.getName());
        project.setDescription(updateProject.getDescription());
        project.setTags(updateProject.getTags());

        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        if(!project.getTeam().contains(user)){
            project.getChat().getUser().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);
    }

    @Override
    public void removeUserFromProject(Long projectId, long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        if(project.getTeam().contains(user)){
            project.getChat().getUser().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {

        return null;
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {
        String partialName =  "%"+keyword+"%";

        List<Project> projects=projectRepository.findByNameContainingAndTeamContaining(keyword,user);

        return null;
    }
}
