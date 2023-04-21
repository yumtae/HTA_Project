package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.Commentfree;
import passtoss.board.free.db.CommentfreeDAO;

public class FreeCommentUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentfreeDAO dao = new CommentfreeDAO();
		Commentfree co = new Commentfree();
		
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());
		
		co.setNum(Integer.parseInt(request.getParameter("num")));
		
		int ok = dao.commentsUpdate(co);
		response.getWriter().print(ok);
		
		return null;
	}
	
}
