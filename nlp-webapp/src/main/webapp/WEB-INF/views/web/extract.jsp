<%@ include file="/includes/common.jsp" %>

<tiles:insertDefinition name="nlp.master">

	<tiles:putAttribute name="menu">
	 	My Menu
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form action="/web/extract" method="post" enctype="multipart/form-data">
			
			Raw text : <textarea rows="5" cols="80" name="text"></textarea>
			<br/>
			File : <input type="file" name="file" />
			<br/>
			Url : <input type="text" name="url" />	
			<br/>		
			<input type="submit" />
		</form>
		
		<c:if test="${textExtractionResponse != null}">
			
			Response time : ${textExtractionResponse.responseMetadata.executionTime}
			<br />
			Error : ${textExtractionResponse.responseMetadata.errorMessage}
			<br />
			Extracted content : ${textExtractionResponse.content}
			<br />
				
		</c:if>
		
	</tiles:putAttribute>

</tiles:insertDefinition>