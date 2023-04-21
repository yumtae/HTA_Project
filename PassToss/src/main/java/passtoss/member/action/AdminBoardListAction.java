package passtoss.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import passtoss.admin.board.Board;
import passtoss.admin.board.BoardDAO;

public class AdminBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		List<Board> boardlist = null;

		int page = 1;
		int limit = 10;
		int listcount = 0;
		int index = -1;
		String search_word = "";

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit = " + limit);

		int category_index = 0;
		if (request.getParameter("category") != null) {
			category_index = Integer.parseInt(request.getParameter("category"));
		}

		String[] boardtable = { "board_free", "board_dept" };
		String[] categorylist = { "사내게시판", "부서게시판" };
		String[] search_field = { "board_subject", "board_name" };
		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			// 사내, 부서, 공지사항 게시판 별로 나누기
			if (category_index == 0) {
				listcount = dao.getListCount(boardtable[category_index]);
				boardlist = dao.getfreeBoardList(page, limit);
			} else if (category_index == 1) {
				// 추가예정
				listcount = dao.getListCount(boardtable[category_index]);
				boardlist = dao.getdeptBoardList(page, limit);
			}
		} else {
			index = Integer.parseInt(request.getParameter("search_field"));
			search_word = request.getParameter("search_word");
			if (category_index == 0) {								
				listcount = dao.getListCount(search_field[index], search_word, boardtable[category_index]);
				boardlist = dao.getfreeBoardList(search_field[index], search_word, page, limit);
			} else if (category_index == 1) {
				listcount = dao.getListCount(search_field[index], search_word,boardtable[category_index]);
				boardlist = dao.getdeptBoardList(search_field[index], search_word,page, limit);
			}
		}

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수 = " + maxpage);
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 :" + startpage);
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수:" + endpage);
		if (endpage > maxpage)
			endpage = maxpage;

		String state = request.getParameter("state");

		if (state == null) {
			System.out.println("state==null");
			request.setAttribute("category", categorylist[category_index]);
			request.setAttribute("category_index", category_index);
			request.setAttribute("page", page); // 현재 페이지 수
			request.setAttribute("maxpage", maxpage); // 최대 페이지 수

			// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);

			// 현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);

			request.setAttribute("boardlist", boardlist);
			request.setAttribute("listcount", listcount); // 총 글의 수
			request.setAttribute("search_word", search_word);
			request.setAttribute("search_field", index);
			request.setAttribute("limit", limit);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);

			// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("AdminPage/boardList.jsp");
			return forward;
		} else {
			System.out.println("state=ajax");

			// 위에서 request로 담았던 것을 JsonObject에 담습니다.
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("category_index", category_index);

			// JsonObject에 List 형식을 담을 수 있는 addProperty()가 존재하지 않습니다.
			// List 형식을 JsonElement로 바꿔줘야 object에 저장할 수 있습니다.

			// List => JsonElement
			JsonElement je = new Gson().toJsonTree(boardlist);
			System.out.println("boardlist = " + je.toString());
			object.add("boardlist", je);

			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(object);
			System.out.println(object.toString());
			return null;
		}
	}

}
