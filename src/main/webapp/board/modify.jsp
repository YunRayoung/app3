<%@page import="dao.BoardDao"%>
<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청파라미터값 조회
	int no= Integer.parseInt(request.getParameter("no"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 요청파라미터로 전달받은 게시물번호로 데이터베이스에서 게시물정보 조회
	BoardDao boardDao = new BoardDao();
	Board board = boardDao.getBoardByNo(no);
	
	// 요청파라미터로 전달받은 수정된 게시물을 Board객체에 저장해서 갚을 덮어쓰기
	board.setTitle(title);
	board.setContent(content);

	// 수정된 정보가 포함된 Board객체를 전달해서 데이터베이스에 반영시키기
	boardDao.insertBoard(board);
	
	// 재요청URl 응답
	response.sendRedirect("insert.jsp");
%>