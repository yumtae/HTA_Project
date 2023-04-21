package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.member.db.Member;
import passtoss.member.db.MemberDAO;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Member m = new Member();
		MemberDAO dao = new MemberDAO();
		
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		String saveFolder="memberupload";
		
		int fileSize=5*1024*1024;
		
		
		//실제 저장 경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(
					request, 
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			
			String id = multi.getParameter("id");
			String pass = multi.getParameter("pass");
			String name = multi.getParameter("name");
			int post = Integer.parseInt(multi.getParameter("post1")) ;
			int jumin1 = Integer.parseInt(multi.getParameter("jumin1")) ;
			int jumin2 = Integer.parseInt(multi.getParameter("jumin2")) ;
			int deptno = Integer.parseInt(multi.getParameter("deptno")) ;
			String email = multi.getParameter("email");
			String address = multi.getParameter("address");		
			String phone = multi.getParameter("phone");		
			
			String jumin =  jumin1 +"-"+jumin2;
			
			m.setId(id);m.setPassword(pass);m.setName(name);m.setPost(post);m.setJumin(jumin);
			m.setDeptno(deptno);m.setEmail(email);m.setAddress(address);m.setPhone(phone);
			
			
			
			String filename = multi.getFilesystemName("board_file");
			m.setProfileImg(filename);
			
			
			
			
			int result = dao.insert(m);
		
			if(result ==0) {
				System.out.println("회원가입 실패");
			
				forward.setRedirect(false);
				request.setAttribute("message", "회원 가입 실패입니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result ==1) {
				out.println("alert('회원 가입이 되었습니다.');");
				out.println("location.href='LoginAction.net';");
			}else if(result == -1) {
				out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
			
			
			
			
			return null;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "이미지업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		
		
		
		
	}

}
