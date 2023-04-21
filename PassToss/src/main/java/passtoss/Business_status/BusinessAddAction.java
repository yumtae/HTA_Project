package passtoss.Business_status;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.Business_status.db.Business_status_Bean;
import passtoss.Business_status.db.Business_status_DAO;



public class BusinessAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		Business_status_Bean bsb = new Business_status_Bean();
		Business_status_DAO dao = new Business_status_DAO();
		
		
		String memo_id = request.getParameter("MEMO_ID");
		String MEMO_CONTENT = request.getParameter("MEMO_CONTENT");
		String yy = request.getParameter("yy");
		String mm = request.getParameter("mm");
		String dd = request.getParameter("dd");
		
		System.out.println(request.getParameter("Priority"));
		System.out.println(request.getParameter("STATUS"));
		int Priority = Integer.parseInt(request.getParameter("Priority"));
		int STATUS = Integer.parseInt(request.getParameter("STATUS"));
		
		String limit_date = yy+"-"+mm+"-"+dd;
		
		
		
		bsb.setMemo_id(memo_id);bsb.setMemo_content(MEMO_CONTENT);bsb.setLimit_date(limit_date);
		bsb.setPriority(Priority);bsb.setStatus(STATUS);

		int result = dao.insert(bsb);

		
		
		if(result != 1) {
			System.out.println("업무현황 등록 실패");
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "업무현황 등록 실패입니다");
			forward.setRedirect(false);
			return forward;
		}
		
		
		forward.setRedirect(true);
		forward.setPath("Business_status.bs");
		return forward;
	}
}
