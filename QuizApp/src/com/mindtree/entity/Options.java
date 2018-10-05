/**
 * 
 */
package com.mindtree.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M1030608
 *
 */
public class Options {
	private int id;
	private String[] options = new String[4];
	private int correctOption;
	private Question question;
	private Quiz quiz;

	/**
	 * 
	 */
	public Options() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param options
	 * @param correctOption
	 * @param question
	 * @param quiz
	 */
	public Options(int id, int correctOption, Question question, Quiz quiz,
			String... options) {
		super();
		this.id = id;
		this.options = options;
		this.correctOption = correctOption;
		this.question = question;
		this.quiz = quiz;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the options
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(String... options) {
		this.options = options;
	}

	/**
	 * @return the correctOption
	 */
	public int getCorrectOption() {
		return correctOption;
	}

	/**
	 * @param correctOption
	 *            the correctOption to set
	 */
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz
	 *            the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
