<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="New Team">
	<form class='well' action='' method='POST'>
		<label for='name'>Team name:</label>
		<input type='text' id='name' name='name' />
		<br>
		<label for='price'>Price:</label>
		<input type='text' id='price' name='price' />
		<br>
		
		
		
		<input type='submit' value='Create team' />
	</form>


</t:mainLayout>