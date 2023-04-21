package passtoss.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import passtoss.member.db.Member;
import passtoss.member.db.MemberDAO;

public class MemberUpdateProcessAction implements Action {

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
		//System.out.println("realFolder = " + realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(
					request, 
					realFolder,
					fileSize,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			int post = Integer.parseInt(multi.getParameter("post1")) ;
			String email = multi.getParameter("email");
			String address = multi.getParameter("address");		
			String phone = multi.getParameter("phone");		
			
			
			m.setId(id);m.setName(name);m.setPost(post);
			m.setEmail(email);m.setAddress(address);m.setPhone(phone);
			
			
			
			String filename = multi.getFilesystemName("member_file");

			
			if(filename != null) {  //파일을 선택한 경우
				m.setProfileImg(filename);
			}else if(multi.getParameter("check") != ""){ //기존파일을 그대로 사용할 경우
				m.setProfileImg(multi.getParameter("check"));
			}
			
			
			
			
			int result = dao.updateMember(m);
			Member memberinfo = dao.memberinfo(id);
			HttpSession session = request.getSession();
			session.setAttribute("memberinfo", memberinfo);
			
			
			if(result ==0) {
				System.out.println("변경 실패");
			
				forward.setRedirect(false);
				request.setAttribute("message", "변경 실패입니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result ==1) {
				out.println("alert('변경 되었습니다.');");
				out.println("location.href='memberinfo.net';");
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
