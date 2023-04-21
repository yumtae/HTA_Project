package passtoss.board.free.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FreeFileDownAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName = request.getParameter("filename");
		System.out.println("[fileName] = " + fileName);
		
		String savePath = "boardupload";
		
		ServletContext context = request.getSession().getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		String sFilePath = sDownloadPath + "\\" + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		
		response.setContentType(sMimeType);
		
		String sEncoding = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=" + sEncoding);
		
		try(
				//웹 브라우저로의 출력 스트림 생성합니다.
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				
				//sFilePath로 지정한 파일에 대한 입력 스트림을 생성합니다.
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
			)
		   {
				int numRead;
				
				//read(b, 0, b.length) : 바이트 배열 b의 0번 부터 b.length 크기 만큼 읽어옵니다.
				while((numRead = in.read(b,0,b.length)) != -1) {//읽을 데이터가 존재하는경우
					out2.write(b, 0, numRead); //바이트 배열 b의 0번 부터 numRead크기 만큼 브라우저로 출력
				}
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		
		return null;
	}
}
