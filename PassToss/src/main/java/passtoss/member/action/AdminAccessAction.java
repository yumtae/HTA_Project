package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.MemberDAO;

public class AdminAccessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String[] id = request.getParameterValues("select");
		System.out.println("선택한 id 수 =  "+id.length);
		int authority = 0;
		if (request.getParameter("authority") != null) {
			authority = Integer.parseInt(request.getParameter("authority"));
		}
		System.out.println("설정한 권한 = "+authority);
		int result = dao.authorize(id, authority);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == id.length) {
			out.println("alert('" + result + "명의 권한이 설정되었습니다.');");
			out.println("location.href='AdminMemberList.net'");
		} else {
			out.println("alert('가입승인에 실패했습니다.');");
			out.println("history.back();");
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
