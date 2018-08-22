<%@page import="utils.Utils"%>
<%@page import="com.green.example.model.ContactDetailModel"%>
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

			<form action="home" method="Post">
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
	<% if (model.isErrContactNotFound()) { %>
		<p class="error">Contact not found!</p>
	<%} else { %>
	<div id="main">
		<div id="testdiv">
		<form enctype="multipart/form-data" action="userServlet?action=insert"
			method="Post">
			<table>
				<tr>
					<td>Name</td>
					<td class="c"><%=model.getName() %></td>
				</tr>
				<% } %>
				<tr>
					<td>Photo</td>
					<td class="c"><input type="file" name="file"></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td class="c"><input type="date" name="birthday" value="<%=model.getBirthday() %>
						placeholder="Input your birthday"></td>
				</tr>
				<tr>
					<td>Sex</td>
					<td class="c"><input type="radio" name="gender" value="male">
						Male <input type="radio" name="gender" value="female">
						Female</td>
				</tr>
				<tr>
					<td>Address</td>
					<td class="c"><input type="text" name="address" <%=model.getAddress() %>
						placeholder="Input your address"></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td class="c"><input type="text" name="phone"
						placeholder="Input your phone number"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td class="c"><input type="text" name="email"
						placeholder="Input your email"></td>
				</tr>
				<tr>
					<td>Note</td>
					<td class="c"><input type="text" name="note" <%=model.getNote() %>></td>
				</tr>
			</table>
			<div class="action">
				<button type="submit">Submit</button>
				<button type="button"
					onclick="window.location.href='<%=Utils.getUrl(request, "/home")%>'">Cancel</button>
			</div>
		</form>
	</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#test").validate({
				rules:
				{
					name: "required",
					sdt: "required",
					email: "required",
				},
				messages:
				{
					name: "Check your last name,please!!!",
					sdt: "Check your phone number,please!!!",
					email: "Check your email,please!!!"
				}
			});
			$("#test").submit(function(){
				if($("#test").validate().form())
				{
					aldert("Form ok");
					return true;
				}
				else
				{
					aldert("Something wrong");
					return false;
				}
			});
			
		});
	</script>
</body>
</html>