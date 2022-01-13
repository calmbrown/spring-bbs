<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbs_detail</title>
</head>
<body>

<table width=80% height=500px border=1 align=center>
	<tr height=30px>
	<td align="center" colspan="1">제목</td>
	<td align="center" colspan="3"> <c:out value="${detail.bbs_title }"/></td>
	</tr>
	<tr>
	<td align="center" width=25%>작성자</td>
	<td align="center" width=25%> <c:out value="${detail.author }"/></td>
	<td align="center" width=25%>작성일</td>
	<td align="center" width=25%> <c:out value="${detail.date }"/></td>
	</tr>
	<tr>
	<td align="center" colspan="4">내용</td>
	</tr>
	<tr height = 500px>
	<td colspan="4"><c:out value="${detail.bbs_content }"/></td>
	</tr>
	<tr>
	<td colspan="2">
	<button onclick="location.href='/bbs/bbs_list/edit/${detail.bbs_id}';">수정</button>
	</td>
	<td colspan="2">
	
	<form name="submitForm" method="POST" action="/bbs/Delete">
	<input type="hidden" name="bbs_id" value="${detail.bbs_id }">
	<button>삭제</button>
	</form>
	</td>
	</tr>
	
</table>

</body>
</html>