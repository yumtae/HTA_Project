package passtoss.board.free.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.CommentfreeDAO;


public class FreeCommentDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CommentfreeDAO dao = new CommentfreeDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		int result = dao.commentsDelete(num);
		PrintWriter out = response.getWriter();
		out.print(result);
		
		return null;
	}

}
