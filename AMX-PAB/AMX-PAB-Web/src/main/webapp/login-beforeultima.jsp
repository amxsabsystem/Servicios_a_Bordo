<%-- 
    Document   : login
    Created on : 4/10/2017, 09:59:56 AM
    Author     : alfredo.estrada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>LOGIN</h1>
		<form method="POST" action="j_security_check">
			<table style="border: 1">
				<tr>
					<td>USER:</td><td><input type="text" name="j_username"></td>
				</tr>
				<tr>
					<td>PASSWORD:</td><td><input type="password" name="j_password"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="ENTER"/></td>
				</tr>
			</table>		
		</form>
    </body>
</html>
