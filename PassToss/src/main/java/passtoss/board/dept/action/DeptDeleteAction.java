package passtoss.board.dept.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.board.dept.db.DeptBoardDAO;

public class DeptDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		int result = dao.boardDelete(num);
		
		if(result == 0) {
			System.out.println("글 삭제 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("글 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='DeptList.bod';");
		out.println("</script>");
		out.close();
				
		return null;
	}

}
