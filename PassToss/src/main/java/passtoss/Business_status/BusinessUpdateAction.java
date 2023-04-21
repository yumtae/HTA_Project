package passtoss.Business_status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.Business_status.db.Business_status_DAO;

public class BusinessUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		Business_status_DAO dao = new Business_status_DAO();

			System.out.println();
		
		int memo_seq = Integer.parseInt(request.getParameter("seq"));
		String status = request.getParameter("status");
		
		
		int result = dao.update(memo_seq,status);

		
		if(result != 1) {
			System.out.println("업데이트실패");
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "업무현황 변경 실패입니다");
			forward.setRedirect(false);
			return forward;
		}
		
		
		forward.setRedirect(true);
		forward.setPath("Business_status.bs");
		return forward;
	}

}
