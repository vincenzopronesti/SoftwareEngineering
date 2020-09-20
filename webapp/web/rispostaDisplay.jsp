 

<%@page import="gruppo.boundary.InterfacciaUtenteLogin"%>
<%@page import="gruppo.entity.Aula"%>
<%@page import="Andrea.bean.BeanDiRispostaVerificaDisponibilita"%>
<%@ page import="java.util.List" %>
<%

	InterfacciaUtenteLogin i;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");

	String risposta= (String) session.getAttribute("risposta");
	System.out.println(risposta);
	if (request.getParameter("menu") != null )
	{	
		System.out.println("menuPressed");
			%>
			<jsp:forward page="MainMenu.jsp"/>
			<%
	}
	 
	/* 
	}*/
	
	//out.write(outText); 
	//String a;
	//a=outText;
%>
<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
    <p><textarea name="text" cols="88" rows="22" readonly="readonly"><%=risposta %></textarea>
    </p>
    <form action="rispostaDisplay.jsp" name="myform" method="POST">
      <p style="text-align: right;"><input name="menu" value="MENU" type="submit"></p>
    </form>
    <br>
    <p><br>
    </p>
  </body>
</html>

