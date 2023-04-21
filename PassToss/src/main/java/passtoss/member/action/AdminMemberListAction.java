package passtoss.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import passtoss.member.db.Member;
import passtoss.member.db.MemberDAO;

public class AdminMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		List<Member> list = null;

		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지=" + page);

		int listcount = 0;
		int index = -1;

		String[] category = { "준회원", "정회원" };
		
		int category_index = 0;
		if (request.getParameter("category") != null) {
			category_index = Integer.parseInt(request.getParameter("category"));
		}
		System.out.println("카테고리 = "+category_index);

		String search_word = "";
		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			listcount = dao.getListCount(category_index);
			list = dao.getMemberList(page, limit, category_index);
		} else {// 검색
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] { "id", "name", "deptno" };
			search_word = request.getParameter("search_word");
			listcount = dao.getListCount(search_field[index], search_word, category_index);
			list = dao.getMemberList(search_field[index], search_word, page, limit, category_index);
		}

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지 수 = " + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수=" + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수=" + startpage);

		if (endpage > maxpage)
			endpage = maxpage;

		request.setAttribute("category_index", category_index);
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("joinlist", list);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		request.setAttribute("category", category[category_index]);

		forward.setPath("AdminPage/joinList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
