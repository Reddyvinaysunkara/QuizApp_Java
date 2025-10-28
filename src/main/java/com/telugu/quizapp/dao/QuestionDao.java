package com.telugu.quizapp.dao;

import org.springframework.stereotype.Repository;

import com.telugu.quizapp.model.Question;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	// @Query(value="select * from question q where q.category=:category ORDER BY RANDOM() LIMIT:numQ",nativeQuery=true)
	  // @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM()", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category,  Pageable pageable);
	//List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);


}
