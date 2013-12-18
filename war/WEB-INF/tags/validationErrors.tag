<%@ tag description="Validation errors" pageEncoding="UTF-8"%>
<%@ attribute name="errors" required="true" type="java.util.Map" description="A hash of fields and their error messages." %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty errors}">
	<div class="error-message">
		<jsp:doBody/>
		<ul>
			<c:forEach var="errorType" items="${errors}">
				<c:forEach var="errorMessage" items="${errorType.value}">
					<li>${errorMessage}</li>
				</c:forEach>
			</c:forEach>
		</ul>
	</div>
</c:if>