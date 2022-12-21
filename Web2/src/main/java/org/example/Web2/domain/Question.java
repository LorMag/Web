package org.example.Web2.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Question implements Comparable<Question> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long questionId;

    private String questionText;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id")
    private Test testId;

    private String answer;

    public Question() {}

    public Question(Test test_id, String question_text, String answer) {
        this.questionText = question_text;
        this.testId = test_id;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long question_id) {
        this.questionId = question_id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String question_text) {
        this.questionText = question_text;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test test_id) {
        this.testId = test_id;
    }

    public void setAll(String questionText, Test testId){
        this.questionText = questionText;
        this.testId = testId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int compareTo(Question q) {
        return q.getQuestionId().compareTo(questionId);
    }
}
