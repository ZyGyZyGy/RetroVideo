<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="Rapport" />
</head>
<body> 
	<a href=" <c:url value='/'/> " title="terug naar beginpagina">Reserveren</a>
	<h1>Rapport</h1>
	<p>De reservatie is ${empty failedFilms ? "OK" : "MISLUKT"}
	<c:if test="${not empty failedFilms}">
		<div>
		De reservatie van deze films is mislukt:<br>
		<ul class="zonderbolletjes">
			<c:forEach var="film" items="${failedFilms}">
				<li><b>${film.titel}</b></li>
			</c:forEach>
		</ul>
		</div>
	</c:if>
</body> 
</html> 