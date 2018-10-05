/**
 * 
 */
package com.mindtree.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;





import com.mindtree.entity.Quiz;

/**
 * @author M1030608
 *
 */
public class QuizTag extends TagSupport {

	private List<Quiz> quizList;
	
	private String successMsg;

	/**
	 * @return the successMsg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * @param successMsg the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * 
	 */
	public QuizTag() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param quizList
	 */
	public QuizTag(List<Quiz> quizList) {
		super();
		this.quizList = quizList;
	}

	/**
	 * @return the quizList
	 */
	public List<Quiz> getQuizList() {
		return quizList;
	}

	/**
	 * @param quizList the quizList to set
	 */
	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		
		
		String toWrite = "<h2>HOME PAGE</h2>";
		toWrite += "<span class =success>"+successMsg +"</span><br><h3>Quiz List</h3>"; 
		try {
			out.print(toWrite);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}

}
