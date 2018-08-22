<%@page import="utils.Utils"%>
<%@page import="com.green.example.model.ContactDetailModel"%>
<%@page import="com.green.example.entity.EmailContact"%>
<%@page import="com.green.example.entity.PhoneContact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/home.css">
<title></title>
<style type="text/css">
#testdiv {
	width: 250px;
	height: 300px;
	background-color: #FEE1FE;
	float: left;
	margin: 6px;
	text-align: center;
}

td.c {
	text-align: left;
}
</style>
</head>
<body>
	<%
		ContactDetailModel model = (ContactDetailModel) request.getAttribute("model");
	%>
	
	<div id="contrainer">
		<div class="box">
			<input class="setting" type="image" id="setting" alt="setting"
				src="resources/images/Settings.png" />

			<form action="home" >
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
			<div class="cbirthday">Birthday</div>
			<div class="cgender">Gender</div>
			<div class="caddress">Address</div>
			<div class="cphone">Phone</div>
			<div class="cemail">Email</div>
			<div class="cnote">Notes</div>
		</div>
		
			<% if (model.isErrContactNotFound()) { %>
				<p class="error">Contact not found!</p>
			<%} else { %>
			<div class="row list">
				<div class="cimage">
					<img src="resources/images/<td><%=model.getPhoto()%></td>" height="30px" width="30px">
				</div>
				<div class="cname"><%=model.getName()%></div>
				<%}%>
				<div style="border:none" class="cbirthday"><input type="date" name="birthday" value="<%=model.getBirthday()%>"></div>
				<div class="cgender"><%=model.getSex()%></div>
				<div style="border:none" class="caddress"><input type="text" name="address" value="<%=model.getAddress() %>"></div>
				<% 
					for(PhoneContact phone: model.getPhone()){
						if(phone.getContact().getName().equals(model.getName())){	
				%>
				<div class="cphone"> 
					<input type="text" name="phone" value= "<%= phone.getPhone()%>"><%}}%></div>
				<% 
					for(EmailContact email: model.getEmail()){
						if(email.getContact().getName().equals(model.getName())){
					%>
				<div class="cemail"> 
					<input type="text" name="email"value= "<%= email.getEmail()%>"><%}} %></div>
				<div class="cnote"><input type="text" name="note" value=" <%=model.getNote()%>"></div>
				</div>
			</div>
			
			
			<% if (model.isErrContactNotFound()) { %>
				<p class="error">Contact not found!</p>
			<%} else { %>
			<table class="tbl-border">
				<tr>
					<td>Full Name</td>
					<td><input name="name" value="<%=model.getName() %>" /></td>
				<tr>
				<% } %>
				
				<tr>
					<td>Gender</td>
					<td>
						<%=model.getSex()%>
					</td>
				<tr>
				<tr>
					<td>Birth Date</td>
					<td><input type="date" name="birthday" value="<%=model.getBirthday()%>"></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" value="<%=model.getAddress() %>"></td>
				<tr>
				<tr>
					<td>Note</td>
					<td><input type="text" name="note" value=" <%=model.getNote()%>"></td>
				<tr>
			</table>
</body>
</html>