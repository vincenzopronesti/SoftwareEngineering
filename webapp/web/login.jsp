
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>


<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->

<jsp:useBean id="login" scope="request"
             class="gruppo.boundary.InterfacciaUtenteLogin"/>
<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
	<jsp:setProperty name="login" property="*"/>

<%
	

    if(request.getParameter("Login") != null) {
   			login.setPassword(request.getParameter("password"));
   			login.setUsername(request.getParameter("username"));
   			if(login.loggin()) {
   				String s = login.getUsername();
   				session.setAttribute("obj", login);
   				
   			 %>
           	<jsp:forward page="MainMenu.jsp"/>
           <% 
   			}
   			else {
   			 %>
             <p class = "text-info"> dati errati</p>
             <%
   			}
        }
    else {  
    	%>
        <p class="text-info">Accesso non effettuato</p>
        <%
    }
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>ProvaLogin</title>
    <style type="text/css">
#id:hover {
  background-color: #70dc5a;
}

#id1 {
  background-color: white !important;
}

input {
  background-color: white;
  color: black;
}

</style></head>
  <body>
    <div style="text-align: justify;"><input name="username" type="hidden"></div>
    <form action="login.jsp" name="myform" method="POST">
      <p><legend>Inserire Username</legend></p>
      <p><input name="username" autocomplete="off" required="required"
          type="text"></p>
      <p>Inserire Password </p>
      <p><input name="password" required="required" autocomplete="off" id="id1"
          type="password"></p>
      <div style="margin-left: 40px;">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <button name="Login"
          id="id">Login</button></div>
      <div style="margin-left: 40px;">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </div>
      <div style="margin-left: 40px;">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </div>
    </form>
  </body>
</html>