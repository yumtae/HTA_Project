package passtoss.board.dept.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class BoardDeptDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoard board = new DeptBoard();
		
		ArrayList<DeptBoard> board_num_next_prev = new ArrayList<DeptBoard>();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		HttpSession session = request.getSession();
		int deptno = (Integer)session.getAttribute("deptno");
		System.out.println("deptno = " + deptno);
		
		//다음,이전값
		board_num_next_prev = dao.getNextPrevNum(num, deptno);
				
		request.setAttribute("board_num_next_prev", board_num_next_prev);
		
		dao.setReadCountUpdate(num);
		
		board = dao.getDetail(num);
		
		if(board == null) {
			System.out.println("게시글 불러오기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("board", board);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("dept_board/deptContentView.jsp"); 
		return forward;
	}

}
