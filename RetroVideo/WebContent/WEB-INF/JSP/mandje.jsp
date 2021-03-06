<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="Mandje" />
</head>
<body>
	<a href=" <c:url value='/'/> " title="terug naar beginpagina">Reserveren</a>
	<a href=" <c:url value='/klant.htm'/> " title="naar klantengegevens">Klant</a>
	<h1>Mandje</h1>
	<form method="post" action=<c:url value="/films/verwijderen.htm"/>>
		<table>
			<thead>
				<tr>
					<th>Film</th><th>Prijs</th><th><input type="submit" value="Verwijderen"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${filmsInMandje}">
					<tr>
						<td>${film.titel}</td><td>&euro;&nbsp;<fmt:formatNumber value="${film.prijs}"/></td><td><input type="checkbox" name="id" value="${film.id}"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td><b>Totaal:</b></td><td><b>&euro;&nbsp;<fmt:formatNumber value="${totaal}"/></b></td>
				</tr>
			</tfoot>
		</table>
	</form>
	<div class="fout">${verwijderFout}</div>
	<script>
		document.getElementById('verwijderForm').onsubmit = function() {
			document.getElementById('verwijderKnop').disabled = true;
		};
	</script>
</body>
</html>
