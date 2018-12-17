<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="board.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
	<body>
			<h3>게시판 새 글 쓰기</h3>
			<table border="1">
				<tr>
					<td colspan="4" align="right"><a href="boardList.bbs">목록으로</a></td>
				</tr>
				<tr>
					<td>글제목</td>
					<td colspan="3"><input type="text" name="subject" maxlength="50" size="50" value="${boardRead.subject}" disabled="disabled"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="name" maxlength="50" size="20" value="${boardRead.name}" disabled="disabled"></td>
					<td>조회수:${boardRead.readCnt}</td>
					<td>답글수:${boardRead.childCnt}</td>
				</tr>
				<tr>
					<td>본문</td>
					<td colspan="3"><textarea rows="8" cols="45" name="content" disabled="disabled">${boardRead.content}"</textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
					<a href="boardUpdatePassword.bbs?num=${boardRead.num}">수정</a>
					<a href="boardDeletePassword.bbs?num=${boardRead.num}">삭제</a>
					<a href="boardReplyForm.bbs?num=${boardRead.num}">답글</a>
					</td>
				</tr>
			</table>
	</body>
</html>