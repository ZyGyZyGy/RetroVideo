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
<header>
	<nav>
		<ul>
		<c:forEach var="genre" items="${genres}">
			<c:choose>
				<c:when test="${empty geselecteeredeGenreId}">
					<li><a href="<c:url value='/films.htm?genreid=${genre.id}'/>">${genre.naam}</a></li>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${geselecteeredeGenreId == genre.id}">
							<li>${genre.naam}</li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value='/films.htm?genreid=${genre.id}'/>">${genre.naam}</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</ul>
	</nav>
</header>
<c:if test="${not empty films}">
	<ul class="zonderBolletje">
		<c:forEach var="film" items="${films}">
			<li><img class="filmPoster" src=" <c:url value='/images/${film.id}.jpg' /> " alt="${film.titel}"
			title="${film.reservatieMogelijk}"></li>
		</c:forEach>
	</ul>
</c:if>
</body> 
</html> 