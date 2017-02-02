<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="Rapport" />
</head>
<body> 
	<a href=" <c:url value='/films.htm'/> " title="terug naar beginpagina">Reserveren</a>
	<h1>Rapport</h1>
	<p>De reservatie is ${empty mislukteFilms ? : "OK" : "MISLUKT"}
	<p>De reservatie van deze films is mislukt:<br>
		<c:forEach var="film" items="mislukteFilms">
			${film.titel}
		</c:forEach>
	</p>
</body> 
</html> 