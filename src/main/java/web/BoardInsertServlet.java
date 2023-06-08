package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vo.Board;
import vo.Customer;

/*
 * @MultipartConfig
 * 	- 서블릿이 enctype="multipart/form-data" 요청을 처리하도록 지원한다.
 * 	- 주요 속성값
 * 		fileSizeThreshold - 파일 업로드 시에 메모리에 저장되는 임시 파일 크기 지정
 * 						  - 지정한 크기를 넘어서면 실제로 임시파일로 저장된다.
 * 		maxFileSize - 업로드할 파일의 최대 크기 지정
 * 		maxRequestSize - 요청시의 최대 크기 지정
 * 		location - 파일 업로드시에 임시저장 디렉토리 지정
 */
@MultipartConfig(
		fileSizeThreshold = 1024*1024*10,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*100)
@WebServlet(urlPatterns = "/board/insert")
public class BoardInsertServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에서 로그인된 고객아이디 조회하기
		HttpSession session = request.getSession(false);
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null) {
			response.sendRedirect("../loginform.jsp?err=req&job=" + URLEncoder.encode("게시글등록", "utf-8"));
			return;
		}
		
		// 요청파라미터 조회
		// 일반 입력필드의 값 조회
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 첨부파일 입력필드의 처리
		Part upfilePart = request.getPart("upfile");
		
		String filename = null;
		if (upfilePart.getSize() > 0) {
		filename = System.currentTimeMillis() + upfilePart.getSubmittedFileName();
		//String contentType = upfilePart.getContentType();
		//long size = upfilePart.getSize();
		
		// 업로드된 처음파일을 지정된 폴더에 저장시키기
		InputStream in = upfilePart.getInputStream();
		OutputStream out = new FileOutputStream(new File("C:\\Users\\jhta\\workspace\\files", filename));
		IOUtils.copy(in, out);
		}
		
//		System.out.println("첨부파일명: " + filename);
//		System.out.println("첨부파일타입: " + contentType);
//		System.out.println("첨부파일크기: " + size);
		
		//System.out.println("제목: " + title);
		//System.out.println("내용: " + content);
		
		// 업무로직 수행
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setCustomer(new Customer(loginId));
		board.setFilename(filename);
		
		BoardDao boardDao = new BoardDao();
		boardDao.insertBoard(board);
		
		// 재요청 URL 전송
		response.sendRedirect("list.jsp");
	}
}
