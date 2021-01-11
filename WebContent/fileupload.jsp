<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ファイルアップロード</title>
</head>
<body>
	<h1>ファイルアップロード</h1>
	<p><%= request.getAttribute("message") %></p>
	<form action="fileupload" method="post" enctype="multipart/form-data">
		<p>
			<input type="file" name="txtfile" value=""/>
		</p>
		<p>
			<input type="submit" value="送信"/>
		</p>
	</form>
</body>
</html>