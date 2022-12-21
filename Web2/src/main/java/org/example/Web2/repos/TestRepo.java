package org.example.Web2.repos;

import org.example.Web2.domain.Test;
import org.example.Web2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestRepo extends JpaRepository<Test, Long> {
    List<Test> findByAuthorId(User user);

    Test findByTestId(Long id);
}
