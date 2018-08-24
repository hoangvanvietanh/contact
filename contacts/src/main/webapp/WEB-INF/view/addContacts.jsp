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
	<div id="contrainer">
		<div class="box">
			<input class="setting" type="image" id="setting" alt="setting"
				src="resources/images/Settings.png" />

			<form action="home" >
				<input  class="contacts" type="image" id="contacts" alt="contacts"
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
			<form enctype="multipart/form-data" action="contact?action=insert" method="post">
			<table class="tbl-border">
				<tr>
					<td>Name</td>
					<td class="c" colspan="5"><input type="text" name="name" ></td>
				</tr>
				<tr>
					<td>Photo</td>
					<td class="c" colspan="5"><input type="file" name="file"></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td class="c" colspan="5"><input type="date" name="birthday" 
						placeholder="Input your birthday"></td>
				</tr>
				<tr>
					<td>Sex</td>
					<td class="c" colspan="5"><input type="radio" name="gender" value="male" >Male
								  <input type="radio" name="gender" value="female" >Female
					</td>
				</tr>
				<tr>
					<td>Address</td>
					<td class="c" colspan="5"><input type="text" name="address" 
						placeholder="Input your address"></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td class="c" colspan="5"><input type="text" name="phone" 
				
						placeholder="Input your phone number">
				</tr>
				<tr>
					<td>Email</td>
					
					<td class="c"><input type="text" name="email" 
					placeholder="Input your email"></td>
				</tr>
				<tr>
					<td>Note</td>
					<td class="c" colspan="5"><input type="text" name="note" ></td>
				</tr>
				<tr>
					<td>Action</td>
					<td><button type="submit">Submit</button></td>
					<td colspan="5"><button type="button"
					onclick="window.location.href='<%=Utils.getUrl(request, "/home")%>'">Cancel</button></td>
				</tr>
			</table>
			<div class="action">
				
			</div>
		</form>
		</div>
</body>
</html>