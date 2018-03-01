<%-- 
    Document   : secured
    Created on : 8/10/2017, 04:29:29 PM
    Author     : alfredo.estrada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SECURED</title>
    </head>
    <body>
        <h1>SECURED</h1>
		<h3>
			PRINCIPAL: <%=request.getUserPrincipal()%>
		</h3>
    </body>
</html>
