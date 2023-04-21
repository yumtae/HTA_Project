package passtoss.board.dept.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class BoardDeptListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		List<DeptBoard> noticelist = new ArrayList<DeptBoard>();
		List<DeptBoard> boardlist = new ArrayList<DeptBoard>();
		
		int page = 1;
		int limit = 7;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" + page);
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit =" + limit);
		
		int listcount = 0; 
		int index = -1;
		
		String search_word = "";
		
		HttpSession session = request.getSession();
		int deptno = (Integer)session.getAttribute("deptno");
		System.out.println("deptno = " + deptno);
		
		String dname = dao.getdname(deptno);
		
		if(request.getParameter("search_word") == null 
				|| request.getParameter("search_word").equals("")) {
			listcount = dao.getListCount(deptno);
			noticelist = dao.getBoardList(deptno);
			boardlist = dao.getBoardList(page, limit, deptno);
			
		} else {
			index =Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"all", "board_subject", "board_name"};
			search_word = request.getParameter("search_word");
			listcount = dao.getListCount(search_field[index], search_word, deptno);
			noticelist = dao.getBoardList(search_field[index], search_word, deptno);
			boardlist = dao.getBoardList(search_field[index], search_word, page, limit, deptno);
		}
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수 = " + maxpage);
		
		int startpage = ((page -1) / 10) *10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 : " + startpage);
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 : "+ endpage);
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if(state == null) {	
			System.out.println("state==null");
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage); 
			
			request.setAttribute("startpage", startpage);
			
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount);
			
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("noticelist", noticelist);
			
			request.setAttribute("limit", limit);
			request.setAttribute("deptno", deptno);
			request.setAttribute("dname", dname);
			
			request.setAttribute("search_field", index);
			request.setAttribute("search_word", search_word);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			
			forward.setPath("/dept_board/deptView.jsp"); 
			return forward; 
		
		}else {
			System.out.println("state=ajax");
			
			JsonObject object = new JsonObject();	
			object.addProperty("page", page);	
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("deptno", deptno);
			object.addProperty("dname", dname);
			
			JsonElement je = new Gson().toJsonTree(boardlist);
			JsonElement je_no = new Gson().toJsonTree(noticelist);
			System.out.println("boardlist=" + je.toString());
			object.add("boardlist", je);
			object.add("noticelist", je_no);
			
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(object);
			System.out.println(object.toString());
			return null;
		} //else end 
	} // execute end
}
