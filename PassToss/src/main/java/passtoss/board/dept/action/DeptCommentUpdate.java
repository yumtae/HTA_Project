package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.dept.db.Commentdept;
import passtoss.board.dept.db.CommentdeptDAO;

public class DeptCommentUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentdeptDAO dao = new CommentdeptDAO();
		Commentdept co = new Commentdept();
		
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());
		
		co.setNum(Integer.parseInt(request.getParameter("num")));
		
		int ok = dao.commentsUpdate(co);
		response.getWriter().print(ok);
		
		return null;
	}

}
