<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Add Team Member">
	<form class='well' action='' method='POST'>

		<c:if test='${not empty team}'>

			<h1> Add Member to ${team.name}:</h1>
			<form action='' method = 'POST'>
				<c:forEach items="${StudentsList}" var="student">
					<br><input type='radio' name='student' value='${student.key.id}'/> '${student.firstName} '${student.lastName}
				</c:forEach>
					<br><input type='submit' value='Add Team Member'>
			</form>
		</c:if>
</t:mainLayout>
