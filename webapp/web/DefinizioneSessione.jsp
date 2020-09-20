<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Year"%>
<%@page import="Vincenzo.bean.BeanSessione"%>   
<%@page import="gruppo.entity.TipoSessione"%>  

    
    
 <%
 	
 	String res = "";
 	gruppo.boundary.InterfacciaUtenteLogin i;
	Vincenzo.boundary.InterfacciaSegretarioDefinizioneSessione interfacciaDefinizioneSessione;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
 	if(request.getParameter("Ok") != null) {
            String annoAccademico = request.getParameter("annoAccademico");
            LocalDate dataInizioSessione = LocalDate.parse(request.getParameter("dataInizio"));
            LocalDate dataFineSessione = LocalDate.parse(request.getParameter("dataFine"));
            String tipo = request.getParameter("tipoSessione");
            TipoSessione tipologia = TipoSessione.valueOf(tipo);
            BeanSessione beanSessione = new BeanSessione(annoAccademico, dataInizioSessione, 
                    dataFineSessione, tipologia);
            interfacciaDefinizioneSessione = i.getInterfacciaSegretarioDefinizioneSessione();
            if (interfacciaDefinizioneSessione.definizioneSessione(beanSessione)) {
                res = "sessione richiesta definita correttamente";
//                out.println("sessione richiesta definita correttamente");
            } else {
                res = "impossibile definire la sessione richiesta";
//                out.println("impossibile definire la sessione richiesta");
            }
            	
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
        <h1>Definizione sessione</h1>
        <form method="POST" action="DefinizioneSessione.jsp" name="myform">
            <div>
                <h2>Data inizio:</h2>
                <input name="dataInizio" type="date"  required="required"/>
            </div>                
            <div>
                <h2>Data fine:</h2>
                <input name="dataFine" type="date"  required="required"/>
            </div>
            <div>
                <h2>Tipo sessione:</h2>
                <select name="tipoSessione"  required="required">
                    <option value="estiva">estiva</option>
                    <option value="invernale">invernale</option>
                    <option value="autunnale">autunnale</option>
                    <option value="straordinaria">straordinaria</option>
                </select>
            </div>
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
        <form method="POST" action="DefinizioneSessione.jsp" name="myform1">
            <div>
                <input type="submit" value="Menu" name="Menu" />
            </div>
            <div>
                <input type="submit" value="Logout" name="Logout" />
            </div>
        </form>
    </body>
</html>
