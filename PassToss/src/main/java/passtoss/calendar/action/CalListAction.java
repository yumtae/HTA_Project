package passtoss.calendar.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import passtoss.calendar.db.CalDAO;



public class CalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CalDAO dao = new CalDAO();
		JsonArray array  = new JsonArray();
		array=dao.calList();
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(array);
		System.out.println(array);
		
		return null; // BoardFrontController.java로 리턴됩니다.
	}

}
