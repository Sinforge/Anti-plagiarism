package ru.sinforge.antiplagiarism.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sinforge.antiplagiarism.domain.User;

public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
