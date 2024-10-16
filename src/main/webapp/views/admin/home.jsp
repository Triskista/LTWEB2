<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Trang Chủ Của ADMIN
	<form action="/lab03/admin/home" method="post">
		<div class="container">
			<button type="logout">Logout</button>
			<a href="${pageContext.request.contextPath }/admin/categories">Categories</a>
			<a href="${pageContext.request.contextPath }/admin/videos">Videos</a>
		</div>
	</form>
</body>
</html>