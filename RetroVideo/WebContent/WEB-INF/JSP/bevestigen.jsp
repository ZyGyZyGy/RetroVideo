<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html>
<html lang="nl">
<head>
<vdab:head title="Bevestigen" />
</head>
<body>
	<a href=" <c:url value='/'/> " title="terug naar beginpagina">Reserveren</a>
	<a href=" <c:url value='/films/reserveren.htm'/> " title="naar mandje">Mandje</a>
	<a href=" <c:url value='/klant.htm'/> " title="naar klantengegevens">Klant</a>
	<h1>Bevestigen</h1>
	<p>${not empty aantalFilmsInMandje ? aantalFilmsInMandje : 0}&nbsp;${aantalFilmsInMandje == 1 ? "film" : "films"} voor ${klant.naam}</p>
	<c:if test="${not empty aantalFilmsInMandje}">
		<form method="post" id="toevoegform">
			<input type="submit" value="Bevestigen" id="toevoegknop">
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
