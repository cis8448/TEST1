<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
	<body>
		<h3>게시글 수정을 위한 비밀번호 확인</h3>
		<form action="boardUpdateCheck.bbs" method="post">
			<input type="password" name="password">
			<input type="hidden" name="num" value="${num}">
			<input type="submit" value="입력">
		</form>
	</body>
</html>