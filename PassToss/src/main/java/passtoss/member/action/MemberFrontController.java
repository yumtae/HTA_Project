package passtoss.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class MemberFrontController extends HttpServlet {
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

		switch (command) {
			case "/AdminMemberList.net":
				action = new AdminMemberListAction();
				break;
			case "/AdminAccess.net":
				action = new AdminAccessAction();
				break;				
			case "/LoginAction.net":
	            action = new LoginAction();
	            break;
			case "/logout.net":
	    		action = new MemberLogOutAction();
	    		break;
	    		
			case "/loginProcess.net":
	             action = new LoginProcessAction();
	             break;
			case "/joinProcess.net":
	             action = new JoinProcessAction();
	             break;
			case "/join.net":
	             action = new JoinAction();
	             break;	 
			case "/updateProcess.net":
	    		action = new MemberUpdateProcessAction();
	    		break;
			case "/memberinfo.net":
	             action = new MemberInfoAction();
	             break;	 
			case "/idcheck.net":
	             action = new MemberIdCheckAction();
	             break;              
			case "/AdminMemberInfo.net":
				action = new AdminMemberInfoAction();
				break;
			case "/AdminboardList.net":
				action = new AdminBoardListAction();
				break;
			case "/AdminDelete.net":
				action = new AdminDeleteAction();
				break;
			case "/AdminBoardDelete.net":
				action = new AdminBoardDeleteAction();
				break;
			case "/memberBoardList.net":
				action = new MemberBoardListAction();
				break;
			case "/memberBoardDelete.net":
				action = new MemberBoardDeleteAction();
				break;
		}
		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) {
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
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
