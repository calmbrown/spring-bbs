<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/bbs/EditAct">
		<table>
			<tr>
				<td>
					<table width="100%" cellpadding="0" border="0">
						<tr>
							<td>글쓰기</td>
						</tr>
					</table>
					<table>
						<tr>
							<td>&nbsp;</td>
							<td align="center">제목</td>
							<td><input name="bbs_title" size="50" maxlength="100" value="<c:out value="${detail.bbs_title}"/>"></td>
							
							<td>&nbsp;</td>
						</tr>
						<tr height="1">
							<td colspan="4"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="center">내용</td>
							<td><textarea name="bbs_content" rows="13" cols="50"><c:out value="${detail.bbs_content}"/></textarea></td>
							<td>&nbsp;</td>
						</tr>
						<tr height="1">
							<td colspan="4"></td>
						</tr>
						<tr height="1">
							<td colspan="4"></td>
						</tr>
						<tr align="center">
							<td>&nbsp;</td>
							<td colspan="2"><input type="submit" value="수정"></td>
							<td>&nbsp;</td>
						</tr>
						<input type="hidden" name="bbs_id" value="<c:out value="${detail.bbs_id}"/>">
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>