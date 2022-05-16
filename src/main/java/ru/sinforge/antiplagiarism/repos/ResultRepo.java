package ru.sinforge.antiplagiarism.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import ru.sinforge.antiplagiarism.domain.Result;
import ru.sinforge.antiplagiarism.domain.User;

import java.util.List;

@Repository
public interface ResultRepo extends CrudRepository<Result, Long> {
    List<Result> findByUser(User user);
    List<Result> findAll();
}
