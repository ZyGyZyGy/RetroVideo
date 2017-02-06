<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="${film.titel}" />
</head>
<body>
	<a href=" <c:url value='/films.htm'/> " title="terug naar beginpagina">Reserveren</a>
	<h1>${film.titel}</h1>
	<img class="filmPoster" src=" <c:url value='/images/${film.id}.jpg' /> " alt="${film.titel}">
	<dl>
		<dt>Prijs</dt>
		<dd>${film.prijs}</dd>
		<dt>Voorraad</dt>
		<dd>${film.voorraad}</dd>
		<dt>Gereserveerd</dt>
		<dd>${film.gereserveerd}</dd>
		<dt>Beschikbaar</dt>
		<dd>${film.beschikbaar}</dd>
	</dl>
	<c:url value="/films/reserveren.htm" var="mandjeURL">
		<c:param name="filmid" value="${film.id}"></c:param>
	</c:url>
	<c:if test="${film.beschikbaar > 0}">
		<form method="post" action="${mandjeURL}" id="toevoegform">
			<input type="submit" value="In mandje" id="toevoegknop">
		</form>
	</c:if>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			if (!navigator.cookieEnabled) {
				alert("Dit werkt enkel als cookies aan staan");
				return false;
			}
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>
