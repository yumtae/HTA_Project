package passtoss.board.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.board.dept.db.DeptBoardDAO;

public class BoardDeptWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeptBoardDAO dao = new DeptBoardDAO();
		
		HttpSession session = request.getSession();
		int deptno = (Integer)session.getAttribute("deptno");
		System.out.println("deptno = " + deptno);
		
		String dname = dao.getdname(deptno);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		request.setAttribute("dname", dname);
		forward.setPath("/dept_board/deptWrite.jsp");
		return forward;
	}
}
