<%@page import="com.green.example.dao.ContactDao"%>
<%@page import="com.green.example.entity.PhoneHistory"%>
<%@page import="com.green.example.entity.PhoneContact"%>
<%@page import="com.green.example.entity.Contact"%>
<%@page import="com.green.example.model.HomeModel"%>
<%@page import="utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VietAnh</title>
</head>
<%
	HomeModel model = (HomeModel) request.getAttribute("model");
%>
<body>
	<div id="contrainer">
		<div class="box">
			<input class="setting" type="image" id="setting" alt="setting"
				src="resources/images/Settings.png" />

			<form action="home">
				<input class="contacts" type="image" id="contacts" alt="contacts"
					src="resources/images/contact.png" />
			</form>

			<form action="call">
				<input class="call" type="image" id="call" alt="call"
					src="resources/images/call.png" />
			</form>

			<input class="messages" type="image" id="messages" alt="messages"
				src="resources/images/messages.png" />
		</div>
	</div>
	
	<div id="main">
	<div class="actionnn">
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/contact") %>'" >Add</button>
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/PhoneHistoryController") %>'" >Call History</button>
	</div>
		<div class="row" id="list-header">

			<div class="cphone">Name</div>
			<div class="cphone">Phone</div>
			<div class="cbirthday">Date Time</div>
		</div>
		
		<%
				if (!model.isPhoneHistoryEmpty()) {
				for(PhoneHistory history: model.getPhoneHistory()){
		%>
			<div class="row list">
				<div class="cphone"> <%= history.getPhone().getContact().getName()%></div>
				<div class="cphone"> <%= history.getPhone().getPhone() %></div>
				<div class="cbirthday"><%=history.getDate() %></div>
			</div>
			
			<%
				
			}} else {
		%>
		<div>
			Empty!
		</div>
		<% } %>
	</div>
</body>
</html>