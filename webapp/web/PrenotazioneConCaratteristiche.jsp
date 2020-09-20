<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
    
<jsp:useBean id="BeanConferenza" scope="request"
             class="gruppo.bean.BeanSpecificheConferenza"/>
            
<jsp:useBean id="BeanCar" scope="request"
             class="gruppo.bean.BeanCaratteristicheAula"/>
    

    
    
 <%
 	
 	String s;
 	gruppo.boundary.InterfacciaUtenteLogin i;
	gruppo.boundary.InterfacciaPrenotazioneConferenzeProfessore prof;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
 	if(request.getParameter("Ok") != null) {
 		int posti = Integer.parseInt(request.getParameter("NumPosti"));
 		
 		LocalDate d = LocalDate.parse(request.getParameter("dataInizio"));
 		LocalDate d1 = LocalDate.parse(request.getParameter("dataFine"));
 		String Fascia = request.getParameter("FasciaOraria");
 		prof = i.getInterfacciaProf();
 		BeanConferenza.setDataInizio(d);
 		BeanConferenza.setDataFine(d1);
 		String titoloC = request.getParameter("titolo");
 		BeanConferenza.setTitoloConferenza(titoloC);
 		BeanConferenza.setUsernameProf(i.getUsername());
 		BeanConferenza.setFasciaOraria(gruppo.entity.FasciaOraria.valueOf(Fascia));
 		BeanCar.setNumPosti(posti);
 		if(request.getParameter("Microfono") != null)
 			BeanCar.setMicrofono(true);
 		if(request.getParameter("Lavagna") != null)
 			BeanCar.setLavagna(true);
 		if(request.getParameter("LavagnaI") != null)
 			BeanCar.setLavagnaInterattiva(true);
 		if(request.getParameter("Proiettore") != null)
 			BeanCar.setProiettore(true);
 		if(request.getParameter("Ethernet") != null)
 			BeanCar.setEthernet(true);
 		if(request.getParameter("PresaCorrente") != null)
 			BeanCar.setPreseCorrente(true);
 		%>
 		<br>
 		<%
 		String output= (prof.prenotazioneConCaratteristiche(BeanConferenza, BeanCar)).toString();
 		session.setAttribute("risposta", output);
 		%>
		<jsp:forward page="rispostaDisplay.jsp"/>
		<%
 		
 	}
 	if(request.getParameter("Menu") != null) {
 		System.out.println("menuPressed");
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
    <form action="PrenotazioneConCaratteristiche.jsp" name="myform" method="POST">
      <p style="text-align: center;">&nbsp;<b><u>PRENOTAZIONE AULA CONFERENZA</u></b><br>
        <legend> </legend></p>
      <p><legend><br>
        </legend></p>
      <p><legend>Inserire il titolo della conferenza </legend><input name="titolo"

          required="required" type="text">&nbsp;&nbsp; &nbsp; </p>
      <p>Inserire numero minimo di posti</p>
      <p><input name="NumPosti" required="required" value="1" min="1" max="255"

          type="number">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </p>
      <p>&nbsp;&nbsp;&nbsp; </p>
      <p></p>
      <p> <label>Inserisci la data di inizio della conferenza. <input name="dataInizio"

            required="required" type="date"> </label> <br>
        <label>Inserisci la data di fine della conferenza . &nbsp; <input

            name="dataFine" required="required" type="date"> </label><label>
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <br>
        </label></p>
      <p><label>Inserire la Fascia oraria
          <select name="FasciaOraria" required="required">
            <option value="prima">9-11</option>
            <option value="seconda">11-13</option>
            <option value="terza">14-16</option>
            <option value="quarta">16-18</option>
          </select>
        </label> </p>
      <hr style="color: white;">Inserire Caratteristiche opzionali per l'aula
      <p><label>Microfono<input name="Microfono" type="checkbox"></label><label>
          Proiettore<input name="Proiettore" type="checkbox"></label><label>Lavagna<input

            name="Lavagna" type="checkbox"></label><label>Lavagna Interattiva<input

            name="LavagnaI" type="checkbox"></label><label>Presa Corrente<input

            name="PreseCorrente" type="checkbox"></label><label>Ethernet <input

            name="Ethernet" type="checkbox">&nbsp; <br>
        </label></p>
      <p><label></label><input name="Ok" value="Ok" type="submit"></p>
      <p><br>
        <br>
      </p>
    </form>
    <form action="PrenotazioneConCaratteristiche.jsp" name="myform" method="POST">
      <p><input name="Menu" value="Menu" type="submit"><br>
      </p>
      <p><input name="Logout" value="Logout" type="submit"><br>
      </p>
    </form>
  </body>
</html>
