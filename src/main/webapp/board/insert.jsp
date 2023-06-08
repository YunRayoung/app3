<%@page import="dao.BoardDao"%>
<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청 파라미터값 조회
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// Board객체를 생성해서 요청파라미터로 제출받은 게시물 저장
	Board board = new Board();
	board.setTitle(title);
	board.setContent(content);
	
	// 게시물 등록 수행하기
	BoardDao boardDao = new BoardDao();
	boardDao.insertBoard(board);
	
	// 재요청URl 응답
	response.sendRedirect("list.jsp");
	
	
	
	
	
%>