package ru.sinforge.antiplagiarism.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sinforge.antiplagiarism.domain.User;

@Repository
public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
