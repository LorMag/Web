package org.example.Web2.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long testId;

    private String testName;

    private String description;

    private Integer testRating;

    private Integer numberOfQuestions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User authorId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "testId",  cascade = CascadeType.REMOVE)
    private Set<Question> questions;

    public Test() { }

    public Test(String name, String description, User author) {
        this.testName = name;
        this.description = description;
        this.authorId = author;
        this.numberOfQuestions = 0;
        this.testRating = 0;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long test_id) {
        this.testId = test_id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String test_name) {
        this.testName = test_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTestRating() {
        return testRating;
    }

    public void setTestRating(Integer test_rating) {
        this.testRating = test_rating;
    }

    public void likeTest() {
        this.testRating++;
    }

    public void dislikeTest() {
        this.testRating--;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User author_id) {
        this.authorId = author_id;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer number_of_questions) {
        this.numberOfQuestions = number_of_questions;
    }

    public void setAll(String testName, Integer testRating){
        this.testName = testName;
        this.testRating = testRating;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>(this.questions);
        Collections.sort(questions);
        Collections.reverse(questions);
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
