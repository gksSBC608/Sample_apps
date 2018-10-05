/**
 * 
 */
package com.mindtree.entity;

/**
 * @author M1030608
 *
 */
public class Question {
	private String id;
	private String qName;
	private Quiz quiz;

	/**
	 * 
	 */
	public Question() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param qName
	 * @param quiz
	 */
	public Question(String id, String qName, Quiz quiz) {
		super();
		this.id = id;
		this.qName = qName;
		this.quiz = quiz;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the qName
	 */
	public String getqName() {
		return qName;
	}

	/**
	 * @param qName
	 *            the qName to set
	 */
	public void setqName(String qName) {
		this.qName = qName;
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
