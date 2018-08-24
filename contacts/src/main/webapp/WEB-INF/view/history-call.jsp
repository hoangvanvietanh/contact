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
<link rel="stylesheet" type="text/css" href="resources/css/history.css">
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
		
		<form action="PhoneHistoryController?action=deleteAll" method="post">
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/AddContactController") %>'" >Add</button>
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/PhoneHistoryController") %>'" >Call History</button>
		<button type="submit">Delete All History</button>
		</form>
	</div>
		<div class="row" id="list-header">

			<div class="rname">Name</div>
			<div class="rphone">Phone</div>
			<div class="rday">Date Time</div>
			
			<div class="action"></div>	
			
			<div class="rname">Name</div>
			<div class="rphone">Phone</div>
			<div class="rday">Date Time</div>
			
			<div class="action"></div>
			
			<div class="rname">Name</div>
			<div class="rphone">Phone</div>
			<div class="rday">Date Time</div>
		</div>
		
		<%
				if (!model.isPhoneHistoryEmpty()) {
				for(PhoneHistory history: model.getPhoneHistory()){
		%>
			<div class="row2 list">
				<div class="cname"> <%= history.getPhone().getContact().getName()%></div>
				<div class="cphone"> <%= history.getPhone().getPhone() %></div>
				<div class="cday"><%=history.getDate() %></div>
				<div>
					<form action="PhoneHistoryController?action=delete" method="post">
						<input type="hidden" name="date" value="<%=history.getDate() %>"/>
						<button type="submit" >del</button>
					</form>
				</div>
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