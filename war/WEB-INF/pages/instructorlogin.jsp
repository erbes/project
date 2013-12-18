<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Log In">
	<h1>Instructor Login</h1>
	<c:if test="${not empty error}">
		<div class="error-message">**Username or password is incorrect**</div>
	</c:if>

	<form class='well' action='' method='POST'>
		<div class="field">
			<label for='username'>Username:</label>
			<input type='text' id='username' name='username' />
		</div>
		<div class="field">
			<label for='password'>Password:</label>
			<input type='password' id='password' name='password' />
		</div>
		<input type='submit' value='Log In'/>
	</form>
</t:mainLayout>
