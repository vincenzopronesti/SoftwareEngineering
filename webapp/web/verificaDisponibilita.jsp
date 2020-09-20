<%@page import="gruppo.boundary.InterfacciaUtenteLogin"%>
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
	System.out.println("start");
  	InterfacciaUtenteLogin i;
 	i=(InterfacciaUtenteLogin) session.getAttribute("obj");
	InterfVerificaDisponibilita boundary;
	boundary= i.getInterfacciaVerificaDisponibilita();


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
 		int posti = Integer.parseInt(request.getParameter("numeroPosti"));
 		
 		
		String dataInserita = request.getParameter("data");
		LocalDate data;
		if(dataInserita.length()>0)
			data=LocalDate.parse(dataInserita);
		else
			data=LocalDate.now();
 		String Fascia = request.getParameter("FasciaOraria");

 		BeanDataFasciaOraria beanTemp= new BeanDataFasciaOraria (gruppo.entity.FasciaOraria.valueOf(Fascia),data);
 		String titoloC = request.getParameter("titolo");

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
 	 
		BeanDiRispostaVerificaDisponibilita risposta =boundary.VerificaDisponibilita(BeanCar , beanTemp);
 		//logica di produzione stringa risposta...
		String outText="";
		if(risposta.getNotAula())
			outText="NON CI SONO AULE CON LE CARATTERISTICHE INDICATE...";
		else if(risposta.isErroreSintattico())
			outText="ERRORE SINTTATICO..";
		else {//ci sono aule disponibili ... compongo risposta in base agl'id
			List<Integer> aule= risposta.getIdAuleDisponibili();
			outText+="Disponibili \t"+aule.size()+"\t aule....";
		for(int k=0;k<aule.size();k++){
			outText+="\n=>>> ID AULA DISPONIBILE\t";
			outText+=aule.get(k);
			outText+="\n";
		} 
		}
		System.out.println(outText); 
		session.setAttribute("risposta", outText);	//settata  risposta...
		%>
		<jsp:forward page="rispostaDisplay.jsp"/>
		<%
		}
  
 %>  
 <!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>verificaDisponibilita</title>
  </head>
  <body>
    <form action="verificaDisponibilita.jsp" name="myform" method="POST">
      <p style="text-align: center;"><u><strong>VISUALIZZA DISPONIBILITA AULE</strong></u></p>
      <p style="text-align: left;"><u><em>visualizza aule disponibili con
            determinate caratteristiche in una determinata data e fascia oraria</em></u></p>
      <p style="text-align: left;"><u><em></em></u></p>
      <hr style="color: white;">
      <p style="text-align: left;"><u><em></em><br>
        </u></p>
      <p style="text-align: left;"><strong>Inserisci Data(se vuota sara
          utilizzata la data
          odierna)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <br>
        </strong></p>
      <p style="text-align: right;"><strong>Inserisci Fascia Oraria</strong></p>
      <input name="data" type="date">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
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
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <br>
      <div style="text-align: right;">&nbsp;
        <select name="FasciaOraria" required="required">
          <option value="prima">9-11</option>
          <option value="seconda">11-13</option>
          <option value="terza">14-16</option>
          <option value="quarta">16-18</option>
        </select>
      </div>
      <p style="text-align: left;"><strong><br>
        </strong></p>
      <p style="text-align: left;"><strong><br>
        </strong></p>
      <p style="text-align: left;"><strong><br>
        </strong></p>
      <p style="text-align: left;"><strong>Inserisci numero Posti</strong></p>
      <input name="numeroPosti" min="1" max="255" required="required" value="1"

        type="number">
      <p style="text-align: left;"><strong><br>
        </strong></p>
      <p style="text-align: left;"><strong>Inserisci campi opzionali aula:</strong></p>
      <p style="text-align: left;"><strong>&nbsp;microfono&nbsp; &nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; proiettore&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; lavagna&nbsp;&nbsp;&nbsp; &nbsp;
          &nbsp; &nbsp;&nbsp; lavagnaInterattiva&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
          Ethernet &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </strong><strong>PreseCorrente&nbsp;&nbsp;&nbsp;
          &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
          &nbsp;&nbsp;&nbsp; &nbsp; </strong><u><strong><br>
          </strong></u></p>
      <u><strong></strong></u> <input name="microfono" type="checkbox">&nbsp;&nbsp;&nbsp;
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

        name="preseCorrente" type="checkbox">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <input name="ok"

value="OK" type="submit"> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </form>
    <form action="verificaDisponibilita.jsp" name="myform" method="POST">
      <div style="text-align: right;"><input name="menu" value="MENU" type="submit"><br>
      </div>
      <div style="text-align: right;"> <input name="loggout" value="LOGGOUT" type="submit"></div>
      <p><br>
      </p>
    </form>
  </body>
</html>


