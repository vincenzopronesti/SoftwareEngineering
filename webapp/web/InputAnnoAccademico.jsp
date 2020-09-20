<%@page import="Livio.boundary.InterfacciaSegreteriaAperturaAnnoAccademico"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Year"%>

<jsp:useBean id="info" scope="page"
             class="Livio.bean.BeanInfoAnnoAccademico"/>

<% 
	gruppo.boundary.InterfacciaUtenteLogin i;
	InterfacciaSegreteriaAperturaAnnoAccademico segr;
	Livio.boundary.InterfacciaSegreteriaModificaAnnoAccademico s;
	i = (gruppo.boundary.InterfacciaUtenteLogin)session.getAttribute("obj");
	String risposta = "";
	if(request.getParameter("Crea") != null) {
		Year year = Year.parse(request.getParameter("AnnoAccademico").substring(0, 4));
		LocalDate dataInizio = LocalDate.parse(request.getParameter("dataInizio"));
		LocalDate dataFine = LocalDate.parse(request.getParameter("dataFine"));
		segr = i.getInterfacciaSegreteriaAperturaAnnoAccademico();
		 risposta = segr.aperturaAnnoAccademico(year, dataInizio, dataFine).getRisposta();
		
	}
	if(request.getParameter("Modifica") != null) {
		Year y = Year.parse(request.getParameter("AnnoAccademico").substring(0, 4));
		LocalDate dinizio = LocalDate.parse(request.getParameter("dataInizio"));
		LocalDate dfine = LocalDate.parse(request.getParameter("dataFine"));
		s = i.getInterfacciaSegreteriaModificaAnnoAccademico();
		risposta = s.modificaAnnoAccademico(y, dinizio, dfine).getRisposta();
		
	}
	
	
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
  </head>
  <body>
  <form action = "InputAnnoAccademico.jsp" method = "POST">
    <p>&nbsp;&nbsp;&nbsp;
    <label> <b>Anno Accademico:</b> 
      <select name="AnnoAccademico" required="required">
      <% 
      	Year y = Year.now();
      	for(int x = 0;x<50; x++) {
      			String option = y.toString() +"-"+y.plusYears(1).toString();
      			%>
      			<option value="<%=option %>"><%= option%></option>
      <% y= y.plusYears(1);
      		
      		}%>
     
      </select>
      </label>
    </p>
    
    <div style="text-align: left;"><label> <b>Data di inizio Anno Accademico </b><input name="dataInizio" value="Scegli Data "
        required="required" type="date"></label></div>
    <div style="text-align: left;"><label><b>Data di fine Anno Accademico:</b><input name="dataFine" value="Scegli data"
        required="required" type="date"></label></div>
       <div style="text-align: left;"><label> <b> Definisci un nuovo anno accademico</b><input name="Crea" value="Crea"
         type="submit"></label></div>
       <div style="text-align: left;"><label><b>Modifica un Anno Accademico già esistente</b><input name="Modifica" value="Modifica"
         type="submit"></label></div>
       </form>
       <form action = "InputAnnoAccademico.jsp" method = "POST">
        <div style="text-align: left;"><input name="Menu" value="Menu"
         type="submit"></div>
         <div style="text-align: left;"><input name="logout" value="logout"
         type="submit"></div>
         
        </form>
        <p><textarea name="text" cols="88" rows="22" readonly="readonly"><%=risposta %></textarea>
    </p>
  </body>
</html>
