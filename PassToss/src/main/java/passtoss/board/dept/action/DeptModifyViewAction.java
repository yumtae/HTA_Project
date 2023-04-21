package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class DeptModifyViewAction implements Action{

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
		System.out.println("deptno = " + deptno);
		
		if(board == null) {
			System.out.println("수정할 내용 가져오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "부서게시판 수정 상세 보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("수정할 내용 가져오기 성공");
		
		request.setAttribute("board", board);
		request.setAttribute("board_deptno", deptno);
		forward.setRedirect(false);
		forward.setPath("dept_board/deptModifyView.jsp");
		return forward;
	}

}
