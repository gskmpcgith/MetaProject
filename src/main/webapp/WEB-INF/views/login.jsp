<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>
	<form th:action="@{/login}" method="post">
	    <div>
	        <label>Username:</label>
	        <input type="text" name="username"/>
	    </div>
	    <div>
	        <label>Password:</label>
	        <input type="password" name="password"/>
	    </div>
	    <div>
	        <button type="submit">Login</button>
	    </div>
	</form>
</body>
</html>