<%@ page language="java"%>
<%@ page import="java.util.*"%>

<html>
	<head>
		<title>SYSTEM PROPERTIES</title>
	</head>
	<style>
	table {
		border-collapse: collapse;
	}

	table, td, th {
		border: 1px solid black;
	}
	</style>
<body>
<%
	Properties systProps = System.getProperties();
	Set<Object> keys = systProps.keySet();
%>

	<table>
		<tr>
			<td>ATRIBUTO</td>
			<td>VALOR</td>
		</tr>
<%
	for(Object key:keys){
		String value = systProps.getProperty(key.toString());
%>
		<tr>
			<td><%=key%></td>
			<td><%=value%></td>
		</tr>
<%
	}
%>
		<tr>
			<td>LOCALE</td>
			<td><%=java.util.Locale.getDefault()%></td>
		</tr>
		<tr>
			<td>CHAR_SET</td>
			<td><%=java.nio.charset.Charset.defaultCharset()%></td>
		</tr>
	</table>
</body>
</html>
