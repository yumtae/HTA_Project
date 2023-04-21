package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.free.db.FreeBoard;
import passtoss.board.free.db.FreeBoardDAO;

public class FreeModifyViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		FreeBoard board = new FreeBoard();
		FreeBoardDAO fdao = new FreeBoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		board = fdao.getDetail(num);
		
		if(board == null) {
			System.out.println("수정할 내용 가져오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "사내게시판 수정 상세 보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("수정할 내용 가져오기 성공");
		
		request.setAttribute("board", board);
		forward.setRedirect(false);
		forward.setPath("free_board/freeModifyView.jsp");
		return forward;
	}

}
