package passtoss.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import passtoss.member.db.Member;
import passtoss.member.db.MemberDAO;


public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		Member memberinfo = new Member();
		MemberDAO dao = new MemberDAO();
		
		HttpSession session = request.getSession();
		String id =(String) session.getAttribute("id");
	
		memberinfo  = dao.memberinfo(id);
		request.setAttribute("memberinfo", memberinfo);
		
		
		forward.setRedirect(false);
		forward.setPath("member/memberInfo.jsp");
		return forward;
		
		
	}

}
