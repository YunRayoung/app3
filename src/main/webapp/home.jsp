<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html lang="ko">
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp">
		<jsp:param name="menu" value="홈"/>
</jsp:include>
<div class="container">
	<h1>쇼핑몰 관리자 시스템</h1>
	<p>회원가입, 로그인, 로그아웃, 상품 조회, 구매 기능을 제공합니다.</p>
</div>
</body>
</html>