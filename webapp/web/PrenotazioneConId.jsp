<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>

<jsp:useBean id="BeanConferenza" scope="request"
             class="gruppo.bean.BeanSpecificheConferenza"/>
<jsp:useBean id="BeanId" scope="request"
             class="gruppo.bean.BeanIdAula"/>
             
 <% 
 	gruppo.boundary.InterfacciaUtenteLogin i;
	 i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
	gruppo.boundary.InterfacciaPrenotazioneConferenzeProfessore prof;
 	if(request.getParameter("Ok")!= null) {
 		int idAula = Integer.parseInt(request.getParameter("idAula"));
 		BeanId.setIdAula(idAula);
 		LocalDate d = LocalDate.parse(request.getParameter("dataInizio"));
 		LocalDate d1 = LocalDate.parse(request.getParameter("dataFine"));
 		String Fascia = request.getParameter("FasciaOraria");
 		BeanConferenza.setDataInizio(d);
 		BeanConferenza.setDataFine(d1);
 		String titoloC = request.getParameter("titolo");
 		BeanConferenza.setTitoloConferenza(titoloC);
 		BeanConferenza.setUsernameProf(i.getUsername());
 		BeanConferenza.setFasciaOraria(gruppo.entity.FasciaOraria.valueOf(Fascia));
 		prof = i.getInterfacciaProf();
 		String output= prof.prenotazioneConId(BeanConferenza, BeanId).toString();
 		session.setAttribute("risposta", output);
 		%>
		<jsp:forward page="rispostaDisplay.jsp"/>
		<%
 	}
 	if(request.getParameter("Menu") != null) {
			%>
			<jsp:forward page="MainMenu.jsp"/>
			<%
	}
	if(request.getParameter("Logout")  != null) {
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
  </head>
  <body>
    <form action="PrenotazioneConId.jsp" method="POST">
      <p style="text-align: center;"><label><u><strong><b>PRENOTAZIONE AULA
                CONFERENZA</b></strong></u><br>
        </label></p>
      <p><label> Inserire l'id dell'Aula per la conferenza&nbsp; <br>
        </label></p>
      <p><label><input name="idAula" value="IdAula" required="required" min="1"

            max="255" step="1" type="number"><br>
        </label></p>
      <p><legend> Inserire il titolo della conferenza&nbsp;&nbsp;&nbsp; </legend><input

          name="titolo" required="required" type="text">&nbsp;&nbsp; &nbsp; </p>
      <label>Inserisci la data di inizio. <input name="dataInizio" required="required"

          type="date"> </label> <br>
      <p><label>Inserisci la data di fine. <input name="dataFine" required="required"

            type="date"> </label> </p>
      <p><label> Fascia Oraria
          <select name="FasciaOraria" required="required">
            <option value="prima">9-11</option>
            <option value="seconda">11-13</option>
            <option value="terza">14-16</option>
            <option value="quarta">16-18</option>
          </select>
          <br>
        </label></p>
      <br>
      <p><input name="Ok" value="Ok" type="submit"><br>
      </p>
    </form>
    <form action="PrenotazioneConId.jsp" method="POST">
      <p><br>
        <input name="Menu" value="Menu" type="submit"><br>
        <input name="Logout" value="Logout" type="submit"></p>
    </form>
  </body>
</html>
