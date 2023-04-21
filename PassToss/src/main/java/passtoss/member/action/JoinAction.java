package passtoss.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member/joinForm.jsp");
				return forward;
				
	}

}
