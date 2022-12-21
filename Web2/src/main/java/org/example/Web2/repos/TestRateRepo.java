package org.example.Web2.repos;

import org.example.Web2.domain.TestRate;
import org.example.Web2.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface TestRateRepo extends CrudRepository<TestRate, Long> {
    TestRate findByTestIdAndUserId(Long testId, User userId);
}
