<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
	<nav>
		<ul>
			<li><a href="<c:url value='/films?genreid=${ }'/>">${ }</a></li>
		</ul>
	</nav>
</header>
