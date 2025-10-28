package com.telugu.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.telugu.quizapp.dao.QuestionDao;
import com.telugu.quizapp.dao.QuizDao;
import com.telugu.quizapp.model.Question;
import com.telugu.quizapp.model.QuestionWrapper;
import com.telugu.quizapp.model.Quiz;
import com.telugu.quizapp.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		/*public List<Question> getRandomQuestionsByCategory(String category, int limit) {
	        Pageable pageable = PageRequest.of(0, limit); // page 0, with size = limit
	        return questionRepository.findRandomQuestionsByCategory(category, pageable);
	    }*/
		 Pageable pageable = PageRequest.of(0, numQ); // page 0, with size = limit
		List<Question>questions = questionDao.findRandomQuestionsByCategory(category,pageable);
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional <Quiz>quiz = quizDao.findById(id);
		List<Question>questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper>questionsForUser = new ArrayList<>(); 
		for(Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions =quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
             right++;
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
