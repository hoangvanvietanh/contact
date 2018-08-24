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
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/AddContactController") %>'" >Add</button>
		<button type="button" onclick="window.location.href='<%=Utils.getUrl(request, "/PhoneHistoryController") %>'" >Call History</button>
	</div>
		<div class="row" id="list-header">
			<div class="rimage">Image</div>
			<div class="rname">Name</div>
			<div class="rbirthday">Birthday</div>
			<div class="rgender">Gender</div>
			<div class="raddress">Address</div>
			<div class="rphone">Phone</div>
			<div class="remail">Email</div>
			<div class="rnote">Notes</div>
			<div class="raction">Acion</div>
		</div>
		
		<%
				if (!model.isEmpty()) {
				for (Contact contact : model.getContacts()) {
					if(contact.getName().equals("No_Name")==false){
		%>
			<div class="row list">
				<div class="cimage">
					<img src="resources/images/<%=contact.getPhoto()%>" height="30px" width="30px">
				</div>
				<div class="cname"><%=contact.getName()%></div>
				<div class="cbirthday"><%=contact.getBirthday()%></div>
				<div class="cgender"><%=contact.getSex()%></div>
				<div class="caddress"><%=contact.getAddress()%></div>
				<div class="cphone"> 
				<% 
					for(PhoneContact phone: model.getPhone()){
						if(phone.getContact().getName().equals(contact.getName())){	
				%>
					<%= phone.getPhone()%><br>
				<%}} %>
				</div>
				<div class="cemail"> 
				<% 
					for(EmailContact email: model.getEmail()){
						if(email.getContact().getName().equals(contact.getName())){
				%>
					<%= email.getEmail()%><br>
				<%}} %>
				</div>
				<div class="cnote"><%=contact.getNote()%></div>
				<div>
					<form action="<%=Utils.getUrl(request, "/ContactDetailController") %>" method="get">
						<input type="hidden" name="contactName" value="<%=contact.getName() %>"/>
						<button type="submit" >Edit</button>
					</form>
				</div>
				<div>
					<form action="<%=Utils.getUrl(request, "/contact") %>" method="get">
						<input type="hidden" name="name" value="<%=contact.getName() %>"/>
						<button type="submit" >Delete</button>
					</form>
				</div>
			</div>
			<%
				}}
			} else {
		%>
		<div>
			Empty!
		</div>
		<% } %>
	</div>
</body>
</html>