package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.MemberDAO;

public class MemberBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;
		MemberDAO dao = new MemberDAO();

		String[] select = request.getParameterValues("select");

		String[] category = { "board_free", "board_dept" };
		int category_index = 0;
		
		if (request.getParameter("category") != null) {
			category_index = Integer.parseInt(request.getParameter("category"));
		}
		
		result = dao.boardDelete(select, category[category_index]);

		if (result == false) {
			System.out.println("게시물 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('선택한 " + select.length + "개 게시물과 하위에 포함된 게시물이 삭제 되었습니다.')");
		out.println("location.href='memberBoardList.net';");
		out.println("</script>");
		out.close();
		return null;
	}

}
