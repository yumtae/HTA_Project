package passtoss.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import passtoss.member.db.MemberDAO;

public class AdminMemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("id = "+id);
		MemberDAO dao = new MemberDAO();
		
		JsonObject obj=new JsonObject();
		
		obj = dao.member_info(id);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(obj);
		System.out.println(obj.toString());
		return null;		
	}
}
