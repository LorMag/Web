package org.example.Web2.controllers;

import org.example.Web2.domain.*;
import org.example.Web2.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/tests/test")
public class TestController {
    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private TestRateRepo testRateRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TestResultRepo testResultRepo;

    //---------------------------------Создание теста----------------------------------------------
    @GetMapping("/create")
    public String createTest(){
        return "createTest";
    }

    @PostMapping("/create")
    public String createTest(@AuthenticationPrincipal User user,
                             @RequestParam String testName,
                             @RequestParam String description){
        Test test = new Test(testName, description, user);
        user.upUserRating();
        testRepo.save(test);
        userRepo.save(user);
        return "redirect:/profile";
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Удаление теста----------------------------------------------
    @GetMapping("/{id}/delete")
    public String deleteTest(@PathVariable Long id, @AuthenticationPrincipal User user){
        user.downUserRating();
        userRepo.save(user);
        testRepo.delete(testRepo.getOne(id));
        return "redirect:/profile";
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Изменение теста---------------------------------------------
    @GetMapping("/edit/{id}")
    String editTest(@PathVariable("id") Long testId, Model model) {
        Test test = testRepo.findByTestId(testId);
        test.setNumberOfQuestions(questionRepo.countByTestId(test));
        testRepo.save(test);
        Iterable<Question> questions = questionRepo.findByTestId(test);
        model.addAttribute("test", test);
        model.addAttribute("questions", questions);
        return "editTest";
    }

    @PostMapping("/edit/{id}")
    String editTest(@PathVariable("id") Long testId, @RequestParam String testName, @RequestParam String description) {
        Test test = testRepo.findByTestId(testId);
        test.setTestName(testName);
        test.setDescription(description);
        testRepo.save(test);
        return "redirect:/tests/test/edit/" + testId;
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Пройти тест-------------------------------------------------
    @GetMapping("/{id}")
    String passTest(@PathVariable ("id") Long testId, Model model) {
        Test test = testRepo.findByTestId(testId);
        model.addAttribute("test", test);
        return "testPass";
    }

    @PostMapping("/{id}")
    String passTest(@PathVariable ("id") Long testId,
                    @RequestParam ("answer") List<String> answers,
                    @AuthenticationPrincipal User user) {
        Test test = testRepo.findByTestId(testId);
        List<Question> questions = questionRepo.findByTestId(test);
        int positiveAnswers = 0;
        for(int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(questions.get(i).getAnswer())) {
                positiveAnswers++;
            }
        }
        String result = "Вы набрали " + positiveAnswers + " правильных ответов из " + questions.size() + '.';
        TestResult testResult = new TestResult(result, user, test.getTestName());
        testResultRepo.save(testResult);
        return "redirect:/tests/test/" + testId + "/result/" + testResult.getTestResultId();
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Результат теста---------------------------------------------
    @GetMapping("/{id}/result/{result_id}")
    String testResult(@PathVariable ("result_id") Long resultId, Model model) {
        TestResult testResult = testResultRepo.findByTestResultId(resultId);
        model.addAttribute("testResult", testResult);
        return "testResult";
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Изменение вопроса-------------------------------------------
    @GetMapping("/edit/{test_id}/question/edit/{question_id}")
    String editQuestion(@PathVariable("test_id") Long testId,
                        @PathVariable("question_id") Long questionId,
                        Model model) {
        Question question = questionRepo.getQuestionByQuestionId(questionId);
        model.addAttribute("question", question);
        return "editQuestion";
    }

    @PostMapping("/edit/{test_id}/question/edit/{question_id}")
    String editQuestion(@PathVariable("test_id") Long testId,
                        @PathVariable("question_id") Long questionId,
                        @RequestParam ("questionText") String questionText,
                        @RequestParam ("answer") String answer) {
        Question question = questionRepo.getQuestionByQuestionId(questionId);
        question.setQuestionText(questionText);
        question.setAnswer(answer);
        questionRepo.save(question);
        return "redirect:/tests/test/edit/" + testId;
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Создание вопроса--------------------------------------------
    @GetMapping("/{id}/createQuestion")
    public String createQuestion(@PathVariable("id") Long testId, Model model){
        Test test = testRepo.findByTestId(testId);
        model.addAttribute("test", test);
        return "createQuestion";
    }

    @PostMapping("/{id}/createQuestion")
    public String createQuestion(@RequestParam String questionText,
                                 @RequestParam String answer,
                                 @PathVariable ("id") Long testId){
        Test test = testRepo.findByTestId(testId);
        Question question = new Question(test, questionText, answer);
        questionRepo.save(question);
        return "redirect:/tests/test/edit/" + testId;
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Удаление вопроса--------------------------------------------
    @GetMapping("/edit/{test_id}/question/delete/{question_id}")
    public String deleteQuestion(@PathVariable ("test_id") Long testId,
                                 @PathVariable ("question_id") Long questionId){
        questionRepo.delete(questionRepo.getOne(questionId));
        return "redirect:/tests/test/edit/" + testId;
    }

    //----------------------------------Лайк теста-------------------------------------------------
    @GetMapping("/{id}/like")
    public String likeTest(@PathVariable ("id") Long testId, @AuthenticationPrincipal User user) {
        TestRate testRate = testRateRepo.findByTestIdAndUserId(testId, user);
        Test test = testRepo.findByTestId(testId);
        User author = test.getAuthorId();
        if (testRate == null) {
            TestRate newTestRate = new TestRate(testId, user, 1);
            testRateRepo.save(newTestRate);
            test.likeTest();
            if (user != author) {
                author.upUserRating();
            }
        }
        else if (testRate.getTestRate() == -1) {
            testRate.setTestRate(1);
            test.likeTest();
            test.likeTest();
            if (user != author) {
                author.upUserRating();
                author.upUserRating();
            }
        }
        else if (testRate.getTestRate() == 1) {
            testRateRepo.delete(testRate);
            test.dislikeTest();
            if (user != author) {
                author.downUserRating();
            }
        }
        testRepo.save(test);
        userRepo.save(author);
        return "redirect:/main";
    }
    //---------------------------------------------------------------------------------------------


    //---------------------------------Дизлайк теста------------------------------------------------
    @GetMapping("/{id}/dislike")
    public String dislikeTest(@PathVariable ("id") Long testId, @AuthenticationPrincipal User user) {
        TestRate testRate = testRateRepo.findByTestIdAndUserId(testId, user);
        Test test = testRepo.findByTestId(testId);
        User author = test.getAuthorId();
        if (testRate == null) {
            TestRate newTestRate = new TestRate(testId, user, -1);
            testRateRepo.save(newTestRate);
            test.dislikeTest();
            if (user != author) {
                author.downUserRating();
            }
        }
        else if (testRate.getTestRate() == 1) {
            testRate.setTestRate(-1);
            test.dislikeTest();
            test.dislikeTest();
            if (user != author) {
                author.downUserRating();
                author.downUserRating();
            }
        }
        else if (testRate.getTestRate() == -1) {
            testRateRepo.delete(testRate);
            test.likeTest();
            if (user != author) {
                author.upUserRating();
            }
        }
        testRepo.save(test);
        userRepo.save(author);
        return "redirect:/main";
    }

}
