<%@page import="utils.Utils"%>
<%@page import="com.green.example.model.ContactDetailModel"%>
<%@page import="com.green.example.entity.EmailContact"%>
<%@page import="com.green.example.entity.PhoneContact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/calling.css">
<script src="resources/js/examples.min.js"> </script>
<title>Insert title here</title>
</head>
<%
		ContactDetailModel model = (ContactDetailModel) request.getAttribute("model");
%>
<body>
		<% if (model.isErrContactNotFound()) { %>
		<p class="error">Contact not found!</p>
	<%} else { %>
	<div class="relative">
		<div class="box" id="chronoExample">
			<div class="vtName">
				<%
					if(model.getPhoneContact().getContact().getName().equals("No_Name")){
				%>
				<div class="c"><%=model.getPhoneContact().getPhone()%></div><%}else{ %>
				<div class="c"><%=model.getPhoneContact().getContact().getName()%></div><%} %>
			</div>
			
			<div class="timer" >
              <div class="values">00:00:00</div>
              <div>
                <button style="display: none" class="resetButton startButton" id="myButton"></button>
              </div>
            </div>
			<form action="call?phoneHis=<%= model.getPhoneContact().getPhone()%>" method="post">
				<input class="stopButton" type="image" id="callEnd" alt="callEnd"
					src="resources/images/callEnd2.png" />
			</form><%} %>
		</div>
	</div>
	<script>

		window.onload=function(){
		  document.getElementById("myButton").click();
		};

    </script>
</body>
</html>