package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.FreeBoard;
import passtoss.board.free.db.FreeBoardDAO;

public class FreeReplyViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		FreeBoardDAO fdao = new FreeBoardDAO();
		FreeBoard board = new FreeBoard(); 
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		board = fdao.getDetail(num);
		
		if(board == null) {
			
			System.out.println("글이 존재하지 않습니다.");
			forward.setRedirect(false);
			request.setAttribute("message", "글이 존재하지 않습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("답변 페이지 이동 완료");
		
		request.setAttribute("board", board);
		
		forward.setRedirect(false);
		forward.setPath("free_board/boardReply.jsp");
		return forward;
	}

}
