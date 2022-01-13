<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.app.app.Dto.BbsDto" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width=80% height=70% border=1 align=center>
	<tr>
	<th width=10% align="">글 번호</th>
	<th width=20%>작성자</th>
	<th width=30%>글 제목</th>
	<th width=40%>작성일</th>
	</tr>
	<%-- 
	<%
	ArrayList<BbsDto> bbs = new ArrayList<BbsDto>();
	bbs = (ArrayList<BbsDto>)request.getAttribute("list");
	for(int i=0; i<bbs.size(); i++){
	%>
	<tr align="center">
	<td> <%=bbs.get(i).getBbs_id() %>
	<td> <%=bbs.get(i).getAuthor() %>
	<td align="left"><a href='bbs_list/<%=bbs.get(i).getBbs_id()%>'> <%=bbs.get(i).getBbs_title() %></a></td>
	<td> <%=bbs.get(i).getDate() %></td>
	</tr>
	<%
	}
	%>
 	--%>
	 
	<c:forEach var="bbs_list" items="${list }">
		<tr align="center">
		<td> <c:out value="${bbs_list.bbs_id }"/></td>
		<td> <c:out value="${bbs_list.author }"/></td>
		<td align="left"><a href='/bbs/bbs_list/<c:out value="${bbs_list.bbs_id }"/>'> <c:out value="${bbs_list.bbs_title }"/></a></td>
		<td> <c:out value="${bbs_list.date }"/></td>
		</tr>
	</c:forEach>
	 
	<tr>
	</tr>
</table>
</body>
</html>