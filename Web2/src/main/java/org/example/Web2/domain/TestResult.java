package org.example.Web2.domain;

import javax.persistence.*;

@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JoinColumn(name = "test_result_id")
    private Long testResultId;

    private String testName;

    private String testResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    public TestResult() {

    }

    public TestResult(String test_result, User user_id, String testName) {
        this.testResult = test_result;
        this.userId = user_id;
        this.testName = testName;
    }

    public Long getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Long testResultId) {
        this.testResultId = testResultId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String test_result) {
        this.testResult = test_result;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user_id) {
        this.userId = user_id;
    }
}
