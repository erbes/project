<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="${team.name}">
	<c:if test="${not empty team}">
		<h1>Enrolled Students in ${team.name}:</h1>
		<ul>
			<c:forEach items="${students}" var="student">
				<li>${student.fullName}</li>
			</c:forEach>
		</ul>
		<a href='/programs/enroll?id=${program.key.id}'>Add Team Member</a>
	</c:if>
</t:mainLayout>