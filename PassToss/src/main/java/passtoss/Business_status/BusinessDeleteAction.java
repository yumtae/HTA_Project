package passtoss.Business_status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.Business_status.db.Business_status_DAO;



public class BusinessDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		Business_status_DAO dao = new Business_status_DAO();
		
		System.out.println(request.getParameter("memo_seq"));
		int memo_seq = Integer.parseInt(request.getParameter("memo_seq"));
		
		
		int result = dao.delete(memo_seq);

		
		if(result != 1) {
			System.out.println("삭제실패");
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "업무현황 삭제 실패입니다");
			forward.setRedirect(false);
			return forward;
		}
		
		
		forward.setRedirect(true);
		forward.setPath("Business_status.bs");
		return forward;
	}
}
