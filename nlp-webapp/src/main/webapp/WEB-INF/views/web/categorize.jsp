<%@ include file="/includes/common.jsp" %>

<tiles:insertDefinition name="nlp.master">

	<tiles:putAttribute name="body">
	
		<form:form action="/web/categorize" commandName="categorizationRequest" method="post" enctype="multipart/form-data">
			
			Raw text : <form:textarea rows="5" cols="80" path="text"></form:textarea>
			<br/>
			File : <form:input type="file" path="file" />
			<br/>
			Url : <form:input type="text" path="url" />	
			<br/>		
			<input type="submit" />
		</form:form>
		
		<c:if test="${categorizationResponse != null}">
			
			Response time : ${categorizationResponse.responseMetadata.executionTime}
			<br />
			Error : ${categorizationResponse.responseMetadata.errorMessage}
			<br />
			Category : ${categorizationResponse.categorization.category.name}
			<br />
				
		</c:if>
	
	</tiles:putAttribute>

</tiles:insertDefinition>
