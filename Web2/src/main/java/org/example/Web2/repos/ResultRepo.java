package org.example.Web2.repos;

import org.example.Web2.domain.TestResult;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepo extends CrudRepository<TestResult, Long> {
}
