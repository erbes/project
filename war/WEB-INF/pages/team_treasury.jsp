<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="teams">
		
		<c:forEach items="${programs}" var="team">

			<li>
			<h2><a href="teams/single?id=${team.key.id}">${team.name}</a></h2>
	
			
			total income:
			${team.revenue}
			</li>
			<hr/>
		</c:forEach>
		</ul>
		<br/>
		<br/>
</t:mainLayout>