<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title="Pagina niet gevonden"/>
</head>
<body class="foutpagina">
	<a href=" <c:url value='/films.htm'/> " title="terug naar beginpagina">Reserveren</a>
	<h1>Pagina niet gevonden (error 404)</h1>
	<img src=" <c:url value='/images/popcorn.jpg'/> ">
	<p>De pagina die u zocht bestaat niet op onze website</p>
</body>
</html>
