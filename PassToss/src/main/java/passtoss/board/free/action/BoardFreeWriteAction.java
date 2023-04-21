package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFreeWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);	
		forward.setPath("/free_board/freeWrite.jsp");
		return forward;
	}
}
