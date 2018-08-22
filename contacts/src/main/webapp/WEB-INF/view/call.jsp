<%@page import="com.green.example.dao.ContactDao"%>
<%@page import="com.green.example.entity.EmailContact"%>
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
			<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/ContactDetailController") %>'" >Add</button>
			<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/call-history") %>'" >Call History</button>
		</div>
		<div class="row" id="list-header">
			<div class="cimage">Image</div>
			<div class="cname">Name</div>
			<div class="cphone">Phone</div>
			<div class="action">Ac1</div>

		</div>
		
		<%
				if (!model.isEmpty()) {
				for (Contact contact : model.getContacts()) {
		%>
			<div class="row list">
				<div class="cimage">
					<img src="resources/images/<td><%=contact.getPhoto()%></td>" height="30px" width="30px">
				</div>
				<div class="cname"><%=contact.getName()%></div>
				<div class="cphone"> 
				<% 
					for(PhoneContact phone: model.getPhone()){
						if(phone.getContact().getName().equals(contact.getName())){	
				%>
					<%= phone.getPhone()%>
				<%}} %>
					<div>
						<form action="<%=Utils.getUrl(request, "/CallingController") %>" method="get">
							<input type="hidden" name="contactName" value="<%=contact.getName() %>"/>
							<button type="submit" >Call</button>
						</form>
					</div>
				</div>
			</div>
			<%
				}
			} else {
		%>
		<div>
			Empty!
		</div>
		<% } %>
		
	</div>
</body>
</html>