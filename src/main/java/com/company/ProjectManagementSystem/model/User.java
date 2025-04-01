package com.company.ProjectManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    @Column(nullable = false)
    private String email;
    private String password;

    private int projectSize;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee" , cascade = CascadeType.ALL)
    private List<Issue> assignIssue = new ArrayList<>();

    @OneToMany
    private Project project;




}
