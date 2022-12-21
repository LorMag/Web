package org.example.Web2.domain;

import javax.persistence.*;

@Entity
public class TestRate {
    @Id
    private Long testId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    private Integer testRate;

    public TestRate() {
    }

    public TestRate(Long testId, User user_id, Integer test_rate) {
        this.testId = testId;
        this.userId = user_id;
        this.testRate = test_rate;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long test_id) {
        this.testId = test_id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user_id) {
        this.userId = user_id;
    }

    public Integer getTestRate() {
        return testRate;
    }

    public void setTestRate(Integer test_rate) {
        this.testRate = test_rate;
    }
}
