package org.example.Web2.repos;

import org.example.Web2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUserId(Long userId);
}
