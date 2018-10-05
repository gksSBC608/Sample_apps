package com.mindtree.service;

import java.util.List;

import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.FileRelatedException;
import com.mindtree.exceptions.PersistException;

public interface ServiceDao {

	List<Quiz> getAllQuiz() throws FetchException;

	int addQuestionDetail(Question question, Options options) throws PersistException;

	void generateReport(String id) throws FetchException, FileRelatedException;
}
