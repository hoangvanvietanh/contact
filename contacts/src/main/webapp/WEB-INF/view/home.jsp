<%@page import="com.green.example.dao.ContactDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VietAnh</title>
</head>
<body>
	<div id="contrainer">
		<div class="box">
			<input class="setting" type="image" id="setting" alt="setting"
				src="resources/images/Settings.png" />

			<form action="phoneServlet?action=contacts" method="Post">
				<input class="contacts" type="image" id="contacts" alt="contacts"
					src="resources/images/contact.png" />
			</form>

			<form action="phoneServlet?action=call" method="Post">
				<input class="call" type="image" id="call" alt="call"
					src="resources/images/call.png" />
			</form>

			<input class="messages" type="image" id="messages" alt="messages"
				src="resources/images/messages.png" />
		</div>
	</div>
	<div id="main">
		<div class="row" id="list-header">
				<div class="cimage">Image</div>
				<div class="cname">Name</div>
				<div class="cbirthday">Birthday</div>
				<div class="cgender">Gender</div>
				<div class="caddress">Address</div>
				<div class="cphone">Phone</div>
				<div class="cemail">Email</div>
				<div class="cnote">Notes</div>
				<div class="action">Ac1</div>
				<div class="action">Ac2</div>
				
			</div>
		<%
			ContactDao contact = new ContactDao();
		%>
		<c:forEach var='u' items="<%= contact.getList() %>">
			<div class="row list">
				<div class="cimage"><img src="resources/images/${u.photo}" height="30px" width="30px"></div>
				<div class="cname">${u.name}</div>
				<div class="cbirthday">${u.birthday}</div>
				<div class="cgender">${u.sex}</div>
				<div class="caddress">${u.address}</div>
				<div class="cphone">${u.phoneNumber}</div>
				<div class="cemail">${u.email}</div>
				<div class="cnote">${u.note}</div>
				<div ><input type="button" value="BT1"></div>
				<div ><input type="button" value="BT2"></div>
			</div>	
		</c:forEach>
	</div>
</body>
</html>