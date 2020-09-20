<%@page import="Livio.boundary.InterfacciaUtenteVisualizzaPrenotazioniAttive"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%  
	gruppo.boundary.InterfacciaUtenteLogin i;
	InterfacciaUtenteVisualizzaPrenotazioniAttive user;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
	user = i.getInterfacciaUtenteViusualizzaPrenotazioniAttive();
	String risposta = user.visualizzaPrenotazioniAttive().getRisposta();
	
	if(request.getParameter("Menu") != null) {
			%>
			<jsp:forward page="MainMenu.jsp"/>
			<%
	}
	if(request.getParameter("logout")  != null) {
			i.loggout();
			%>
		<jsp:forward page="login.jsp"/>
		<%
	}


%>








<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
.input {
  background-color: white;
}

</style></head>
  <body>
    
    <form action = "PrenotazioniAttive.jsp" method = "POST">
    <p class="input"><input name="Menu" value="Menu" type="submit">&nbsp;&nbsp;<input
name="logout" value="logout" type="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </form></p>
      <p><textarea name="text" cols="88" rows="22" readonly="readonly"><%=risposta %></textarea>
    </p>
    
  </body>
</html>