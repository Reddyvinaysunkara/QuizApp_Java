package com.telugu.quizapp.service;

import com.telugu.quizapp.dao.QuestionDao;
import com.telugu.quizapp.model.Question;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

//import com.telugu.quizapp.controller.List;
//import com.telugu.quizapp.controller.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
		
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		
	}

	public String updateQuestion(Question question) {
		if(questionDao.existsById(question.getId())) {
			questionDao.save(question);
			return "Question updated Successfully";
		}
		else {
		return "Question not found";
		}
	}

	public String deleteQuestion(int id) {
		if(questionDao.existsById(id)) {
			questionDao.deleteById(id);return "Question deleted Successfully";
			
		}
		else {
			return "Question not Found";
		}
		
		
	}
	

}
 