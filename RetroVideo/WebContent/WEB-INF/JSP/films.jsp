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
			<c:url value="/films.htm" var="genreURL">
				<c:param name="genreid" value="${genre.id}"></c:param>
			</c:url>
			<c:choose>
				<c:when test="${empty geselecteeredeGenreId}">
					<li><a href="<c:out value='${genreURL}'/>">${genre.naam}</a></li>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${geselecteeredeGenreId == genre.id}">
							<li>${genre.naam}</li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:out value='${genreURL}'/>">${genre.naam}</a></li>
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
			<c:url value="/films/detail.htm" var="detailURL">
				<c:param name="id" value="${film.id}"/>
			</c:url>
			<li><a href=" <c:out value='${detailURL}'/> "><img class="filmPoster" src=" <c:url value='/images/${film.id}.jpg' /> " alt="${film.titel}"
			title="${film.reservatieMogelijk}"></a></li>
		</c:forEach>
	</ul>
</c:if>
</body> 
</html> 