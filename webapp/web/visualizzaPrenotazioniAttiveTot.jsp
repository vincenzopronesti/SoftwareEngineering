<%@page import="Andrea.boundary.InterfacciaVisualizzaPrenAttTot"%>
<%@page import="Andrea.bean.BeanDiRispostaVerificaDisponibilita"%>
<%@page import="Andrea.bean.BeanDataFasciaOraria"%>
<%@page import="Andrea.boundary.InterfVerificaDisponibilita"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
           
<jsp:useBean id="BeanCar" scope="request"
             class="gruppo.bean.BeanCaratteristicheAula"/>

<%

	gruppo.boundary.InterfacciaUtenteLogin i;
	i=(gruppo.boundary.InterfacciaUtenteLogin) session.getAttribute("obj");
	InterfacciaVisualizzaPrenAttTot boundary;
	boundary= i.getInterfacciaVisPrenAttTot();
	
	if(request.getParameter("menu") != null) {
		System.out.println("menupressed");
			%>
			<jsp:forward page="MainMenu.jsp"/>
			<%
	}
	if(request.getParameter("loggout")  != null) {
			i.loggout();
			%>
		<jsp:forward page="login.jsp"/>
		<%
	}
	
	
		if(request.getParameter("ok") != null) {
			System.out.println("startUC");
			int posti = Integer.parseInt(request.getParameter("numeroPosti"));
			 
			BeanCar.setNumPosti(posti);
			if(request.getParameter("microfono") != null)
				BeanCar.setMicrofono(true);
			if(request.getParameter("lavagna") != null)
				BeanCar.setLavagna(true);
			if(request.getParameter("lavagnaInterattiva") != null)
				BeanCar.setLavagnaInterattiva(true);
			if(request.getParameter("proiettore") != null)
				BeanCar.setProiettore(true);
			if(request.getParameter("ethernet") != null)
				BeanCar.setEthernet(true);
			if(request.getParameter("presaCorrente") != null)
				BeanCar.setPreseCorrente(true);
			//end part aula section...
	 		String tipoEvento = request.getParameter("TipoDiEvento");
			if(request.getParameter("baseCaratteristiche")==null)
				BeanCar=null;
			if(request.getParameter("baseTipoEvento")==null)
				tipoEvento="";
			//logica per discriminare quali filtri abilitare....vedi uc
			System.out.println(tipoEvento+BeanCar);
			String outString="";
			outString=boundary.visualizzaPrenotazioniSuEventoEAula(BeanCar, tipoEvento);
			session.setAttribute("risposta", outString);
			%>
			<jsp:forward page="rispostaDisplay.jsp"/>
			<%
		}

%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>visualizzaPrenotazioniAttiveTot</title>
  </head>
  <body>
    <form action="visualizzaPrenotazioniAttiveTot.jsp" name="myform" method="POST">
      <p style="text-align: center;">&nbsp;<strong>VISUALIZZA PRENOTAZIONI
          ATTIVE TOTALI</strong></p>
      <p style="text-align: left;"><em>visualizza le prenotazioni attive per un
          certo tipo di evento, o che coinvolgono Aule con delledeterminate
          caratteristiche, dalla data corrente in poi </em></p>
      <em> </em>
      <p style="text-align: left;"><em>inserendo informazioni per filtrare e
          abilitando i filtri a destra</em></p>
      <hr style="color: white;">
      <p style="text-align: left;"><br>
      </p>
      <ul>
        <li>IN BASE a Caratteristiche Aula... &nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; </li>
      </ul>
      <div style="text-align: right;"><input name="baseCaratteristiche" type="checkbox">Filtra
        prenotazioni in base a caratteristiche aula</div>
      <ul>
      </ul>
      <p>&nbsp;numero minimo posti&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <input name="numeroPosti" min="1"

          max="255" value="1" type="number">&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; </p>
      <p>&nbsp;Inserisci campi opzionali aula: </p>
      <p style="text-align: left;">&nbsp;microfono&nbsp; &nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; proiettore&nbsp;&nbsp;&nbsp; &nbsp;
        &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; lavagna&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;
        &nbsp;&nbsp; &nbsp; lavagnaInterattiva&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ethernet &nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; PreseCorrente&nbsp;&nbsp;&nbsp; &nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <strong>&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </strong><u><strong><br>
          </strong></u></p>
      <p> <u><strong></strong></u>&nbsp;<input name="microfono" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <input name="proiettore" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <input name="lavagna" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; <input name="lavagnaInterattiva" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; <input name="ethernet" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; <input

          name="preseCorrente" type="checkbox">&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; </p>
      <p> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; </p>
      <hr style="color: white;">
      <p>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; </p>
      <ul>
        <li>IN BASE a Tipo di Evento....</li>
      </ul>
      <div style="text-align: right;"><input name="baseTipoEvento" checked="checked" type="checkbox">Filtra
        prenotazioni in base a Tipo Evento</div>
      <ul>
      </ul>
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      <select name="TipoDiEvento">
        <option value=""> </option>
        <option value="CONFERENZA">CONFERENZA</option>
        <option value="SEDUTADILAUREA">SEDUTADILAUREA</option>
        <option value="TESTDIINGRESSO">TESTDIINGRESSO</option>
        <option value="ESAME">ESAME</option>
      </select>
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
      &nbsp;&nbsp;&nbsp; <input name="ok" value="OK" type="submit"></form>
    <div style="text-align: right;"><br>
    </div>
    <form action="visualizzaPrenotazioniAttiveTot.jsp" name="myform" method="POST">
      <div style="text-align: right;">&nbsp; <input name="menu" value="MENU" type="submit"></div>
      <div style="text-align: right;"><br>
      </div>
      <div style="text-align: right;"><input name="loggout" value="LOGGOUT" type="submit"></div>
    </form>
  </body>
</html>

