package passtoss.board.free.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.board.free.db.FreeBoard;
import passtoss.board.free.db.FreeBoardDAO;

public class FreeModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		ActionForward forward = new ActionForward();
		FreeBoardDAO fdao = new FreeBoardDAO();
		FreeBoard fboard = new FreeBoard(); 
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
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
			
			fboard.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));;
			fboard.setBoard_notice(Integer.parseInt(multi.getParameter("board_notice")));
			fboard.setBoard_subject(multi.getParameter("board_subject"));
			fboard.setBoard_content(multi.getParameter("board_content"));
			fboard.setBoard_name(multi.getParameter("board_name"));
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check); // 체크값을 인식을 못함 
			if(check != null) { //파일 첨부를 변경하지 않으면
				fboard.setBoard_file(check);
				
			} else {
				//업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
				String filename = multi.getFilesystemName("board_file");
				fboard.setBoard_file(filename);
			}
			
			//DAO에서 수정 메서드 호출하여 수정합니다.
			result = fdao.boardModify(fboard);
			if(result == 0) {
				System.out.println("게시글 수정 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시글 수정에 실패하셨습니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시글 수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("FreeDetailAction.bof?num="+fboard.getBoard_num()); 
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
