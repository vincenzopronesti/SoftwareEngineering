<%@page import="gruppo.boundary.InterfacciaUtenteLogin"%>
<%@page import="gruppo.DAO.PrenotazioneDao"%>
<%@ page language="java"  contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>



<% 
	InterfacciaUtenteLogin i;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
	if(request.getParameter("PrenotazioneButton")	!= null){
		if(i.getType().equals("Professore")) {
			%>
			<jsp:forward page="PrenotazioneConCaratteristiche.jsp"/>
			<%
		}
		else {
			%>
            <p style="text-color:red;">Azione Non Consentita al segretario</p>
            <%
		}
	}
	if(request.getParameter("PrenotazioneIdButton")	!= null){
		if(i.getType().equals("Professore")) {
			%>
			<jsp:forward page="PrenotazioneConId.jsp"/>
			<%
		}
		else {
			%>
            <p style="text-color:red;">Azione Non Consentita al segretario</p>
            <%
		}
	}
	if(request.getParameter("VisualizzaPrenotazioniAtt") != null) {
		%>
		<jsp:forward page="PrenotazioniAttive.jsp"/>
		<%
	}
	if(request.getParameter("ModificaAnnoAccademico")	!= null){
		if(i.getType().equals("Segretario")) {
			%>
			<jsp:forward page="InputAnnoAccademico.jsp"/>
			<%
		}
		else {
			%>
            <p style="text-color:red;">Azione Non Consentita al Professore</p>
            <%
		}
	}
	if(request.getParameter("visualizzaPrenotazioniAttiveTotali")	!= null){
		if(i.getType().equals("Segretario")) {
			%>
			<jsp:forward page="visualizzaPrenotazioniAttiveTot.jsp"/>
			<%
		}
		else {
			%>
            <p style="text-color:red;">Azione Non Consentita al Professore</p>
            <%
		}
	}
	if(request.getParameter("visualizzaDisponibilita")	!= null){
		 
			%>
			<jsp:forward page="verificaDisponibilita.jsp"/>
			<%
		}
        if(request.getParameter("DefinizioneSessioneButton")	!= null){
		if(i.getType() == "Segretario") {
			%>
			<jsp:forward page="DefinizioneSessione.jsp"/>
			<%
		}
		else {
			%>
            <p style="text-color:red;">Azione Non Consentita</p>
            <%
		}
	}

       if(request.getParameter("VisualizzazioneStoricoPrenotazioniButton") != null){
		if(i.getType() == "Segretario") {
			%>
			<jsp:forward page="RicercaPrenotazioniSegr.jsp"/>
			<%
		}
		else if (i.getType() == "Professore") {
			%>
                        <jsp:forward page="RicercaPrenotazioniProf.jsp"/>
            <%
		} else {
			%>
            <p style="text-color:red;">Azione Non Consentita</p>
            <%
		}
	}
	 
             
	if(request.getParameter("Logout")  != null) {
 			i.loggout();
 			%>
			<jsp:forward page="login.jsp"/>
			<%
 	}
	
	

%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>MENU</title>
    <style type="text/css">
.button {
  background-color: #ffff66;
}

</style></head>
  <body class="button" style=" background-color: white;">
    <div style="text-align: center;">
      <form action="MainMenu.jsp" method="POST" style="background-color: white;">
        <div style="text-align: center; height: 20px;"><u><b><strong> MENU
                PRINCIPALE</strong></b></u></div>
        <div style="text-align: left;"><br>
        </div>
        <div style="background-color: white; text-align: left;"><br>
        </div>
        <div style="text-align: left;"><input name="PrenotazioneButton" value="Prenotazione Conferenza Con  Caratteristiche aule"

type="submit"></div>
        <div style="text-align: left;"><input name="PrenotazioneIdButton" value="Prenotazione Conferenza Con un aula Specifica"

type="submit"><br>
        </div>
        <div style="text-align: left;"><input name="VisualizzaPrenotazioniAtt" value="Visualizza Prenotazioni Attive"

type="submit"><br>
        </div>
        <div style="text-align: left;"><input name="ModificaAnnoAccademico" value="Apri o Modifca un Anno Accademico"

type="submit"><br>
        </div>
        <div style="text-align: left;"><input name="visualizzaDisponibilita" value="VisualizzaDisponibilita"

type="submit"><br>
        </div>
        <div style="text-align: left;"><input name="visualizzaPrenotazioniAttiveTotali"

value="VisualizzaPrenotazioniAttiveTotali" type="submit"><br>
        </div>
        <div style="text-align: left;"><input name="DefinizioneSessioneButton" value="Definizione sessione" type="submit"></div>
        <div style="text-align: left;"><input name="VisualizzazioneStoricoPrenotazioniButton" value="Visualizzazione storico prenotazioni"
type="submit"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <p style="text-align: left;"><input name="Logout" value="Logout" type="submit"><br>
        </p>
        <div style="text-align: left;"><br>
          <br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
        <div style="text-align: left;"><br>
        </div>
      </form>
    </div>
  </body>
</html>

