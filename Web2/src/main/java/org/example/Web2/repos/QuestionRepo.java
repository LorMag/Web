package org.example.Web2.repos;

import org.example.Web2.domain.Question;
import org.example.Web2.domain.Test;
import org.example.Web2.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepo extends CrudRepository<Question, Long> {
    List<Question> findByTestId(Test test);

    Integer countByTestId(Test test);

    Question getQuestionByQuestionId(Long id);

    Question getOne(Long questionId);
}
