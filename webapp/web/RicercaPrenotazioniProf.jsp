<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Year"%>
<%@page import="Vincenzo.bean.BeanAnnoAccademico"%>  
<%@page import="Vincenzo.bean.BeanPrenotazioniProfessore"%>  
<%@page import="gruppo.entity.PrenotazioneDidattica"%>  
<%@page import="gruppo.entity.PrenotazioneConferenza"%>  
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!--
<jsp:useBean id="BeanConferenza" scope="request"
             class="gruppo.bean.BeanSpecificheConferenza"/>
            
<jsp:useBean id="BeanCar" scope="request"
             class="gruppo.bean.BeanCaratteristicheAula"/>
    -->

    
    
 <%
 	
 	String s;
        String res = "";
 	gruppo.boundary.InterfacciaUtenteLogin i;
	Vincenzo.boundary.InterfacciaProfessoreVisualizzazioneStorico interfacciaVisualizzazioneStorico;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
 	if(request.getParameter("Ok") != null) {
            String annoAccademico = request.getParameter("annoAccademico");           
            interfacciaVisualizzazioneStorico = i.getInterfacciaProfessoreVisualizzazioneStorico();
            BeanAnnoAccademico beanAA = new BeanAnnoAccademico(annoAccademico);
            BeanPrenotazioniProfessore risposta = interfacciaVisualizzazioneStorico.storicoPrenotazioni(beanAA);
//            String res = "";
            if (risposta.isAnnoAccademicoValido()) {
            res = "Prenotazioni esami<br>";
            List<PrenotazioneDidattica> listPreEs = risposta.getPreEs();
            List<PrenotazioneConferenza> listPreConf = risposta.getPreConf();
            if (listPreEs.isEmpty()) {
                res += ">> Non ci sono prenotazioni per esami<br><br>";
            } else {
                Iterator<PrenotazioneDidattica> j = listPreEs.iterator();
                while (j.hasNext()) {
                    PrenotazioneDidattica pE = j.next();
                    res += ">> " + pE + "<br>";
                    
                }
                res += "<br>";
            }
            res += "Prenotazioni per conferenze<br>";
            if (listPreConf.isEmpty()) {
                res += ">> Non ci sono prenotazioni per conferenze<br><br>";
            } else {
                Iterator<PrenotazioneConferenza> j = listPreConf.iterator();
                while (j.hasNext()) {
                    PrenotazioneConferenza pC = j.next();
                    res += ">> " + pC + "<br>";
                }
                res += "<br>";
            }
        } else {
            res = "Errore: Anno accademico inserito non valido\n";
        }
//            if (interfacciaVisualizzazioneStorico.definizioneSessione(beanSessione)) {
//                out.println("sessione richiesta definita correttamente");
//            } else {
//                out.println("impossibile definire la sessione richiesta");
//            }
//        out.println(res);   	
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ricerca prenotazioni per anno accademico</h1>
        <form method="POST" action="RicercaPrenotazioniProf.jsp" name="myform">
            <div>
                <h2>Anno accademico:</h2>
                <select name="annoAccademico" required="required">
                    <%  Year y = Year.now();
                        for(int j = 0; j < 10; j++) { %>
                            <option value="<%= y.minusYears(2).toString()+"-"+y.minusYears(1).toString()%>"><%= y.minusYears(2).toString()+"-"+y.minusYears(1).toString()%></option>
                    <%      y = y.plusYears(1);
                        } %>
                </select>
            </div>
            <div>
                <input type="submit" value="Ok" name="Ok" />
            </div>
            <br>
            <br>
            <%
                if(request.getParameter("Ok") != null) {
                    %>
                    <p>
                        <% out.println(res); %>
                    </p>
                    <%
                }
            %>
        </form>
        <form method="POST" action="RicercaPrenotazioniProf.jsp" name="myform">
            <div>
                <input type="submit" value="Menu" name="Menu" />
            </div>
            <div>
                <input type="submit" value="Logout" name="Logout" />
            </div>
        </form>
    </body>
</html>
