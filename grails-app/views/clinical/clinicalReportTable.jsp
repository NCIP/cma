<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.*, gov.nih.nci.cma.clinical.*"%>

<%
	//get the reportBeansList
	List rbl = (List) session.getAttribute("reportBeansList");
	if(rbl == null)	{
		out.println("rbl was null");
		session.setAttribute("reportBeansList", new ArrayList());
	}
	//request.setAttribute( "rbl", rbl );
%>
<html>
<head>
	<title>CMA</title>
	<link href="../css/cma.css" rel="stylesheet" type="text/css" />
	<link href="../css/gtable.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="superTop"></div>
	<div id="top"></div>
	
	<display:table name="sessionScope.reportBeansList" defaultsort="1" defaultorder="descending" requestURI="" pagesize="10">
	  <display:column property="sampleId" title="ID" sortable="true" headerClass="sortable" />
	  <display:column property="disease" sortable="true" headerClass="sortable" />
	  <display:column property="grade" />
	  <display:column property="mriDesc"  title="MRI" />
	  <display:column property="karnofsky"/>
	</display:table>
	
	<div  id="bottom" align="center">
		
	</div>	  
		
</body>
</html>