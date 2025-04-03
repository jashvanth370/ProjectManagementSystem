package com.company.ProjectManagementSystem.repository;

import com.company.ProjectManagementSystem.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
