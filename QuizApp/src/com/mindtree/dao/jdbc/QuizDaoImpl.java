/**
 * 
 */
package com.mindtree.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.dao.QuizDao;
import com.mindtree.entity.Options;
import com.mindtree.entity.Question;
import com.mindtree.entity.Quiz;
import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public class QuizDaoImpl implements QuizDao {

	/**
	 * 
	 */
	public QuizDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.QuizDao#getAllQuiz()
	 */
	@Override
	public List<Quiz> getAllQuiz() throws DaoException {
		// TODO Auto-generated method stub
		String sql = "select * from quiz  ";
		List<Quiz> al = new ArrayList<Quiz>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				Quiz quiz = new Quiz(rs.getString(1), rs.getString(2));
				al.add(quiz);
			}
			return al;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.QuizDao#addQuestion(com.mindtree.entity.Question)
	 */
	@Override
	public int addQuestion(Question question) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		String sql = "insert into questions values(?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, question.getId());
			ps.setString(2, question.getqName());
			ps.setString(3, question.getQuiz().getId());
			return ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {

			try {
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.QuizDao#addOptions(com.mindtree.entity.Options)
	 */
	@Override
	public int addOptions(Options options) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		String sql = "insert into options values(null,?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			int count = 1;
			for (String opt : options.getOptions()) {
				ps.setString(count++, opt);

			}
			ps.setInt(count, options.getCorrectOption());
			ps.setString(count + 1, options.getQuiz().getId());
			ps.setString(count + 2, options.getQuestion().getId());
			int u = ps.executeUpdate();
			return u;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		} finally {

			try {
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.QuizDao#generateReport()
	 */
	@Override
	public List<Options> generateReport(String quizId) throws DaoException {
		String sql ="select (select quiz_name from quiz where id = o.quiz_id), ";
		sql += "(select questions from questions where id = o.question_id), ";
		sql += " o.option1,o.option2,o.option3, o.option4, o.correct_option ";
		sql += " from options o where quiz_id = '"+quizId+"'";

		List<Options> al = new ArrayList<Options>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				Quiz quiz = new Quiz(null, rs.getString(1));
				Question question = new Question(null, rs.getString(2), null);
				String[] optionList = { rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) };
				Options options = new Options(0, rs.getInt(7), question, quiz,
						optionList);
				al.add(options);
			}
			return al;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	public String getMaxQuestionId() throws DaoException {

		String sql = "select max(id) from questions";
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String id = null;
			while (rs.next()) {
				id = rs.getString(1);
			}
			return id;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}

		

	}
	
	public String getQuizId(String name) throws DaoException {

		String sql = "select id  from quiz where quiz_name = '"+name+"'";
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			String id = null;
			while (rs.next()) {
				id = rs.getString(1);
			}
			return id;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}

		

	}


}
