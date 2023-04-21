package passtoss.board.free.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bof")
public class FreeBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		
			case "/FreeList.bof":
				action = new BoardFreeListAction();
				break;
				
			case "/FreeWrite.bof":
				action = new BoardFreeWriteAction();
				break;
				
			case "/BoardFreeAdd.bof":
				action = new BoardFreeAddAction();
				break;
				
			case "/FreeDetailAction.bof":
				action = new BoardFreeDetailAction();
				break;
				
			case "/FreeModifyView.bof":
				action = new FreeModifyViewAction();
				break;
				
			case "/FreeModifyAction.bof":
				action = new FreeModifyAction();
				break;
				
			case "/FreeReplyView.bof":
				action = new FreeReplyViewAction();
				break;
				
			case "/FreeReplyAction.bof":
				action = new FreeReplyAction();
				break;
				
			case "/FreeDeleteAction.bof":
				action = new FreeDeleteAction();
				break;
				
			case "/FreeFileDown.bof":
				action = new FreeFileDownAction();
				break;
				
			case "/FreeCommentAdd.bof":
				action = new FreeCommentAdd();
				break;
				
			case "/FreeCommentList.bof":
				action = new FreeCommentList();
				break;
				
			case "/FreeCommentDelete.bof":
				action = new FreeCommentDelete();
				break;
				
			case "/FreeCommentUpdate.bof":
				action = new FreeCommentUpdate();
				break;
				
			case "/FreeCommentReply.bof":
				action = new FreeCommentReply();
				break;
		
		}
		forward = action.execute(request, response);
		
		if(forward != null) {
			if(forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());
			} else { 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
}
