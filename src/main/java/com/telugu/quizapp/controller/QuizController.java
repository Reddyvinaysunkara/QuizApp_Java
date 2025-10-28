package com.telugu.quizapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telugu.quizapp.model.Response;

import java.util.List;

//import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;

import com.telugu.quizapp.model.QuestionWrapper;
import com.telugu.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/quiz")
public class QuizController{
	
	@Autowired
    QuizService quizService;
	@PostMapping("/create")
	public ResponseEntity<String>createQuiz(@RequestParam("category") String category, @RequestParam("numQ") int numQ, @RequestParam("title") String title){
		return quizService.createQuiz(category,numQ,title);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);}
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
	{
		return quizService.calculateResult(id,responses);
	}
	
}