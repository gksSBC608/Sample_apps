/**
 * 
 */
package com.mindtree.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.mindtree.dao.QuizDao;
import com.mindtree.dao.jdbc.QuizDaoImpl;
import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.FileRelatedException;
import com.mindtree.exceptions.PersistException;

/**
 * @author M1030608
 *
 */
public class Service implements ServiceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.service.ServiceDao#getAllQuiz()
	 */
	@Override
	public List<Quiz> getAllQuiz() throws FetchException {
		// TODO Auto-generated method stub
		try {
			return new QuizDaoImpl().getAllQuiz();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage(), e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.service.ServiceDao#addQuestionDetail(com.mindtree.entity
	 * .Question, com.mindtree.entity.Options)
	 */
	@Override
	public int addQuestionDetail(Question question, Options options)
			throws PersistException {
		// TODO Auto-generated method stub
		try {

			int u = new QuizDaoImpl().addQuestion(question);
			if (u > 0)
				new QuizDaoImpl().addOptions(options);
			else
				System.out
						.println("Question not added so Option addition rejected");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PersistException(e.getMessage());
		}

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.service.ServiceDao#generateReport()
	 */
	@Override
	public void generateReport(String quizId) throws FetchException,
			FileRelatedException {
		// TODO Auto-generated method stub
		List<Options> list = null;
		try {
			list = new QuizDaoImpl().generateReport(quizId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FetchException(e.getMessage());
		}
		for (Options option : list) {
			try {
				backup(option);
			} catch (IOException e) {
				throw new FileRelatedException("Problem in writing to file");
			}
		}
	}

	/*
	 * This method implements file handling to back up details releted to the
	 * quiz to a file It uses an object of Options class as its argument.
	 */
	private void backup(Options option) throws IOException {
		// TODO Auto-generated method stub

		String routePath = this.getClass().getClassLoader()
				.getResource(File.separator).getPath();
		System.out.println(routePath);
		String newLine = System.getProperty("line.separator");
		String fileName = option.getQuiz().getName() + ".txt";
		System.out.println(fileName);

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				routePath + File.separator + ".." + File.separator + fileName),
				true));
		bw.write("Quiz Details" + newLine);
		bw.write("**************************************" + newLine);
		bw.write("QUIZ NAME  " + option.getQuiz().getName() + newLine);
		bw.write("QUESTION TEXT  " + option.getQuestion().getqName() + newLine);
		bw.write("Correct Option :   " + option.getCorrectOption() + newLine);
		bw.write("-----------------------------------------------------------------------"
				+ newLine);

		bw.write("OPTIONS" + newLine);
		bw.write("**************************************\n" + newLine);

		int c = 1;
		for (String o : option.getOptions()) {
			if (o != null)
				bw.write(c++ + ". " + o + newLine);
		}

		bw.write(newLine);
		bw.write("-------------End---------------------" + newLine);
		bw.close();

	}

	public String generateQuestionId() {
		String currId = null;
		try {
			currId = new QuizDaoImpl().getMaxQuestionId();
		} catch (DaoException e) {

		}
		
		if(currId==null)
			return "Q1001";
		String qId = null;
		int numeric = Integer.parseInt(currId.substring(1)) + 1;
		 qId = "Q" + numeric;
		return qId;
	}

}
