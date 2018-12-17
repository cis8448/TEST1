<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
	<body>
		<h3>게시판 목록 조회</h3>
		
		<table border="1">
			<tr>
				<td colspan="7" align="right">
				<a href="boardWriteForm.bbs">[새글쓰기]</a>
				</td>
			</tr>
			<tr>
				<td>글 번호</td>
				<td>글 제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>작성시간</td>
				<td>조회수</td>
				<td>답글수</td>
			</tr>
			<c:forEach items="${boardList}" var="dto">
				<tr>
					<td>
						<a href="boardRead.bbs?num${dto.num}">${dto.num}</a>
					</td>
					<td>
						<c:forEach begin="1" end="${dto.lev}">
							<%="&nbsp;&nbsp;" %>
						</c:forEach>
						<a href="boardRead.bbs?num=${dto.num}">${dto.subject}</a>
					</td>
					<td>${dto.name}</td>
					<td>${dto.writeDate}</td>
					<td>${dto.writeTime}</td>
					<td>${dto.readCnt}</td>
					<td>${dto.childCnt}</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="7">
						<a href="boardList.bbs">[첫페이지로 이동]</a>
							<c:forEach var="i" begin="1" end="${pageCnt}">
								<a href="boardList.bbs?curPage=${i}">[${i}]</a>
							</c:forEach>
					</td>
				</tr>
			</table>
	</body>
</html>