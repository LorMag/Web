package org.example.Web2.controllers;

import org.example.Web2.domain.Test;
import org.example.Web2.repos.QuestionRepo;
import org.example.Web2.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Test> tests = testRepo.findAll();
        tests.forEach(test -> {
            test.setNumberOfQuestions(questionRepo.countByTestId(test));
            testRepo.save(test);
        });
        model.addAttribute("tests", tests);
        return "main";
    }
}
