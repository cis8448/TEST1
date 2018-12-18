<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>로그인 페이지</title>
</head>
	<body>
	<form action="LoginCheck.bbs" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" value="${cookie.id.value}"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">아이디 저장<input type="checkbox" name="rememberId" value="keep"  ${cookie.rememberId.value == "keep" ? "checked=\"checked\"" :""}></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="로그인"></td>
				</tr>
			</table>
		</form>
	</body>
</html>