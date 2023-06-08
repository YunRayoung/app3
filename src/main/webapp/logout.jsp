<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 해당 클라이언트의 HttpSession객체를 무효화(폐기)시킨다.
	session.invalidate();

	// home.jsp를 재요청하는 URL을 응답으로 보낸다.
	response.sendRedirect("home.jsp");			// 다시 요청하면 session 사라짐 // 새로 만든 session에는 정보가 없음
%>
