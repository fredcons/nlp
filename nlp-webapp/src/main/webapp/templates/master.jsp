<%@ include file="/includes/common.jsp" %>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
        <table>
      <tr>
        <td>
        	<tiles:insertAttribute name="menu" />
        </td> 	
      </tr>
      <tr>
        <td>
          	<tiles:insertAttribute name="body" />
        </td>
      </tr>
    </table>
  </body>
</html>