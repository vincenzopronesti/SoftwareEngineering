<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Year"%>
<%@page import="Vincenzo.bean.BeanAnnoAccademico"%>  
<%@page import="Vincenzo.bean.BeanPrenotazioniSegretario"%>  
<%@page import="gruppo.entity.PrenotazioneSedutaLaurea"%>  
<%@page import="gruppo.entity.PrenotazioneTestDiIngresso"%>  
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>


    
    
 <%
 	
 	String s;
        String res = "";
 	gruppo.boundary.InterfacciaUtenteLogin i;
	Vincenzo.boundary.InterfacciaSegretarioVisualizzazioneStorico interfacciaVisualizzazioneStorico;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
 	if(request.getParameter("Ok") != null) {
            String annoAccademico = request.getParameter("annoAccademico");           
            interfacciaVisualizzazioneStorico = i.getInterfacciaSegretarioVisualizzazioneStorico();
            BeanAnnoAccademico beanAA = new BeanAnnoAccademico(annoAccademico);
            BeanPrenotazioniSegretario risposta = interfacciaVisualizzazioneStorico.storicoPrenotazioni(beanAA);
//            String res = "";
        if (risposta.isAnnoAccademicoValido()) {
            res = "Prenotazioni sedute di laurea<br>";
            List<PrenotazioneSedutaLaurea> listPreSed = risposta.getPreSed();
            List<PrenotazioneTestDiIngresso> listPreTest = risposta.getPreTest();
            if (listPreSed.isEmpty()) {
                res += ">> Non ci sono prenotazioni per sedute di laurea<br><br>";
            } else {
                Iterator<PrenotazioneSedutaLaurea> j = listPreSed.iterator();
                while (j.hasNext()) {
                    PrenotazioneSedutaLaurea pSL = j.next();
                    res += ">> " + pSL + "<br>";
                }
                res += "<br>";
            }
            res += "Prenotazioni per test di ingresso<br>";
            if (listPreTest.isEmpty()) {
                res += ">> Non ci sono prenotazioni per test di ingresso\n\n";
            } else {
                Iterator<PrenotazioneTestDiIngresso> j = listPreTest.iterator();
                while (j.hasNext()) {
                    PrenotazioneTestDiIngresso pTI = j.next();
                    res += ">> " + pTI + "<br>";
                }
                res += "<br>";
            }
        } else {
            res = "Errore: Anno accademico inserito non valido<br>";
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
        <form method="POST" action="RicercaPrenotazioniSegr.jsp" name="myform">
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
        <form method="POST" action="RicercaPrenotazioniSegr.jsp" name="myform">
            <div>
                <input type="submit" value="Menu" name="Menu" />
            </div>
            <div>
                <input type="submit" value="Logout" name="Logout" />
            </div>
        </form>
    </body>
</html>
