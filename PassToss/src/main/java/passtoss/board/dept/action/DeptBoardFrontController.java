package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.bod")
public class DeptBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		
			case "/DeptList.bod":
				action = new BoardDeptListAction();
				break;
				
			case "/DeptWrite.bod":
				action = new BoardDeptWriteAction();
				break;
				
			case "/BoardDeptAdd.bod":
				action = new BoardDeptAddAction();
				break;
				
			case "/DeptDetailAction.bod":
				action = new BoardDeptDetailAction();
				break;
				
			case "/DeptModifyView.bod":
				action = new DeptModifyViewAction();
				break;
				
			case "/DeptModifyAction.bod":
				action = new DeptModifyAction();
				break;
				
			case "/DeptReplyView.bod":
				action = new DeptReplyViewAction();
				break;
				
			case "/DeptReplyAction.bod":
				action = new DeptReplyAction();
				break;
				
			case "/DeptDeleteAction.bod":
				action = new DeptDeleteAction();
				break;
				
			case "/DeptFileDown.bod":
				action = new DeptFileDownAction();
				break;
				
			case "/DeptCommentAdd.bod":
				action = new DeptCommentAdd();
				break;
				
			case "/DeptCommentList.bod":
				action = new DeptCommentList();
				break;
				
			case "/DeptCommentUpdate.bod":
				action = new DeptCommentUpdate();
				break;
				
			case "/DeptCommentDelete.bod":
				action = new DeptCommentDelete();
				break;
				
			case "/DeptCommentReply.bod":
				action = new DeptCommentReply();
				break;
		}
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
