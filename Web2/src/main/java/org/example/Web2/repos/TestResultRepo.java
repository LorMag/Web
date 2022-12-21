package org.example.Web2.repos;

import org.example.Web2.domain.Test;
import org.example.Web2.domain.TestResult;
import org.example.Web2.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestResultRepo extends CrudRepository<TestResult, Long> {
    TestResult findByTestResultId(Long id);

    List<TestResult> findByUserId(User user);
}
