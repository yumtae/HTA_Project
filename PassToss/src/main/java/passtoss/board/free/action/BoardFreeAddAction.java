package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.board.free.db.FreeBoard;
import passtoss.board.free.db.FreeBoardDAO;

public class BoardFreeAddAction implements Action{

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
			
			fboard.setBoard_notice(Integer.parseInt(multi.getParameter("board_notice")));
			fboard.setBoard_subject(multi.getParameter("board_subject"));
			fboard.setBoard_content(multi.getParameter("board_content"));
			fboard.setBoard_name(id);
			
			String filename = multi.getFilesystemName("board_file");
			fboard.setBoard_file(filename);
			
			result = fdao.boardInsert(fboard);
			
			if(result == 0) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 글 등록에 실패하셨습니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("FreeList.bof"); 
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
