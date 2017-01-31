<%@page contentType='text/html' pageEncoding='UTF-8' session='false' trimDirectiveWhitespaces ='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>  
<%@taglib prefix='vdab' uri='http://vdab.be/tags' %> 
<!doctype html> 
<html lang="nl"> 
<head> 
<vdab:head title="Reservaties"/>
</head> 
<body> 
<h1>Reservaties</h1>
<vdab:menu/>
	<ul class="zonderBolletje">
		<c:forEach var="film" items="${films}">
			<li><img src=" <c:url value='/images/${film.id}.jpg' /> " alt="${film.titel}"></li>
		</c:forEach>
	</ul>
</body> 
</html> 