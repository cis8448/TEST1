<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String rememberId = request.getParameter("rememberId");
String id		  = request.getParameter("id");
String pw 	  	  = request.getParameter("pass");

session.setAttribute("id", request.getParameter("id"));
session.setMaxInactiveInterval(300);

if(rememberId != null&&rememberId.equals("keep")){
Cookie CookieId = new Cookie("id",id);
Cookie CookieRe = new Cookie("rememberId",rememberId);
	
	response.addCookie(CookieId);
	response.addCookie(CookieRe);
}else{
	Cookie CookieId = new Cookie("id","");
	Cookie CookieRe = new Cookie("rememberId","");
		
		response.addCookie(CookieId);
		response.addCookie(CookieRe);
}
	pageContext.forward("/boardList.bbs");


%>
</body>
</html>