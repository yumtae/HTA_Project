package passtoss.board.dept.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.board.dept.db.DeptBoard;
import passtoss.board.dept.db.DeptBoardDAO;

public class DeptModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoard board = new DeptBoard();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int deptno = (Integer)session.getAttribute("deptno");
		System.out.println("deptno = " + deptno);
		
		String realFolder="";
		
		String saveFolder="boardupload";
		
		int fileSize = 5*1024*1024; 
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder= " + realFolder);
		
		int result= 0;
		try {
			
			MultipartRequest multi = new MultipartRequest(
					request,
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			if(!id.equals(multi.getParameter("board_name"))) {
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디가 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			
			board.setBoard_notice(Integer.parseInt(multi.getParameter("board_notice")));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_name(multi.getParameter("board_name"));
			board.setBoard_deptno(Integer.parseInt(multi.getParameter("board_deptno")));
			board.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check); 
			if(check != null) { //파일 첨부를 변경하지 않으면
				board.setBoard_file(check);
				
			} else {
				//업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
				String filename = multi.getFilesystemName("board_file");
				board.setBoard_file(filename);
			}
			
			result = dao.boardModify(board);
			
			if(result == 0) {
				System.out.println("게시글 수정 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시글 수정에 실패하셨습니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시글 수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("DeptDetailAction.bod?num="+board.getBoard_num()); 
			return forward;
		} catch(IOException ex) {
			ex.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		} 
	}
}
