<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Teams">
		<ul class="accordion">
		<c:forEach items="${teams}" var="team">

			<li>
			<h2><a href="teams/single?id=${team.key.id}">${team.name}</a></h2>
			<div class ="content">
			<c:forEach items="${student}" var="student">
				${student.firstName}
			</c:forEach>
			<br/>
			${team.price}
			
		</c:forEach>
		</ul>
		<footer>
		<a href='teams/new' class="button blue">New Team</a>
		</footer>
		<br/>
		<br/>
</t:mainLayout>
