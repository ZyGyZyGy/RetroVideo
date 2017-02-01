<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="Klant" />
</head>
<body>
	<a href=" <c:url value='/films.htm'/> " title="terug naar beginpagina">Reserveren</a>
	<a href=" <c:url value='/klant.htm'/> " title="naar klantengegevens">Klant</a>
	<h1>Klant</h1>
	<form>
		<label>Familienaam bevat:
		<input type="text" name="familienaam" required autofocus>
		</label>
		<div>${fout}</div>
		<input type="submit" value="Zoeken">
	</form>
	<c:if test="${not empty klanten}">
		<table>
			<thead>
				<tr>
					<th>Naam</th><th>Straat - Huisnummer</th><th>Postcode</th><th>Gemeente</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="klant" items="${klanten}">
					<tr>
						<td>${klant.voornaam}&nbsp;${klant.familienaam}</td><td>${klant.straatNummer}</td><td>${klant.postcode}</td><td>${klant.gemeente}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<script>
		document.getElementById('verwijderForm').onsubmit = function() {
			document.getElementById('verwijderKnop').disabled = true;
		};
	</script>
</body>
</html>
