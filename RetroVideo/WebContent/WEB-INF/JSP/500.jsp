<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title="Internal server error"/>
</head>
<body class="foutpagina">
	<a href=" <c:url value='/films.htm'/> " title="terug naar beginpagina">Reserveren</a>
	<h1>Internal server error (error 500)</h1>
	<img src=" <c:url value='/images/popcorn.jpg'/> ">
	<p>Er is een fout opgetreden en uw request kon niet worden verwerkt.</p>
</body>
</html>
