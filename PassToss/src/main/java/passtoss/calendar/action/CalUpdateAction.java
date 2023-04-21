package passtoss.calendar.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import passtoss.calendar.db.CalDAO;
import passtoss.calendar.db.CalVO;

public class CalUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getParameter("alldata");
		System.out.println(data);

		JsonElement element = JsonParser.parseString(data);
		JsonObject jo = element.getAsJsonObject();

		CalDAO dao = new CalDAO();
		CalVO vo=new CalVO();
	String title = jo.get("title").toString().replaceAll("\"","");
		String num = jo.get("num").toString().replaceAll("\"", "");
//String num = jo.get("num").toString();
		String start = jo.get("start").toString().replaceAll("\"", "").substring(0, 10);

		String end = jo.get("end").toString().replaceAll("\"", "").substring(0, 10);

		
		
		//System.out.println(title);
		System.out.println(num);
		System.out.println(start);
		System.out.println(end);
		

		vo.setTitle(title);
		vo.setStart1(start);
		vo.setEnd1(end);
		vo.setNum(Integer.parseInt(num));
		
		dao.calUpdate(vo);
	
		//vo.setNum(num);
		return null;


		

	}

}
