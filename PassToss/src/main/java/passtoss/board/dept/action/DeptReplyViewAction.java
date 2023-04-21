package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class DeptReplyViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoard board = new DeptBoard();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		board = dao.getDetail(num);
		
		HttpSession session = request.getSession();
		int deptno = (Integer)session.getAttribute("deptno");
		String dname = dao.getdname(deptno);
		
		if(board == null) {
			System.out.println("글이 존재하지 않습니다.");
			forward.setRedirect(false);
			request.setAttribute("message", "글이 존재하지 않습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("답변 페이지 이동 완료");
		
		request.setAttribute("board", board);
		request.setAttribute("dname", dname);
		
		forward.setRedirect(false);
		forward.setPath("dept_board/deptReply.jsp");
		return forward;
	}
}
