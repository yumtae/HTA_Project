package passtoss.calendar.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import passtoss.calendar.db.CalDAO;


/*
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/


public class CalDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String data = request.getParameter("alldata");
 		System.out.println(data);
 		
 		
 		JsonElement element = JsonParser.parseString(data);
 		 JsonObject jo = element.getAsJsonObject();


CalDAO dao=new CalDAO();
	

String num= jo.get("num").toString().replaceAll("\"","");

System.out.println(num);



dao.calDel(num);
return null;
	
	}

}
