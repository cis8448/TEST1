<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
	<body>
		<h3>게시판 새 글 쓰기</h3>
		<form action="boardWrite.bbs" method="post">
			<table border="1">
				<tr>
					<td colspan="4" align="right"><a href="boardList.bbs">목록으로</a></td>
				</tr>
				<tr>
					<td>글제목</td>
					<td colspan="3"><input type="text" name="subject" maxlength="50" size="50"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="name" maxlength="50" size="20"></td>
					<td>비밀번호</td>
					<td><input type="password" name="password" maxlength="20" size="12"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td colspan="3"><textarea rows="8" cols="45" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
					<input type="submit" value="글올리기">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>