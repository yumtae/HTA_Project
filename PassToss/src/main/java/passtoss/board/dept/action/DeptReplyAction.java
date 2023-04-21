package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class DeptReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoard board = new DeptBoard();
		int result = 0;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		board.setBoard_name(id);
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));
		board.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		board.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		board.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		board.setBoard_deptno(Integer.parseInt(request.getParameter("board_deptno")));
		
		result = dao.boardReply(board);
		
		if(result == 0) {
			System.out.println("답글 등록 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "답글 등록 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("답글 등록 성공");
		forward.setRedirect(true);
		forward.setPath("DeptDetailAction.bod?num="+result);
		return forward;
	}

}
