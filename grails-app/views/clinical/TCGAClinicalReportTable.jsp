<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
  
<%@page import="java.util.*, gov.nih.nci.cma.clinical.*, context.UserContextService, gov.nih.nci.caintegrator.application.configuration.*"%>

<%
	//get the reportBeansList
	//read key from request or def to "reportBeansList" - get from cache or session?
	String key = (String) request.getParameter("taskId");
	List rbl = (List) session.getAttribute(key);
	if(rbl == null)	{
		out.println("rbl was null");
		request.setAttribute( "rbl", new ArrayList());
	}
	else	{
		request.setAttribute( "rbl", rbl );
	}
	String noBack = request.getParameter("noBack")!=null ? (String) request.getParameter("noBack") : "false";
	//request.setAttribute( "rbl", rbl );
%>
<html>
<head>
	<title>CMA</title>
	<link href="../css/cma.css" rel="stylesheet" type="text/css" />
	<link href="../css/gtable.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/prototype/prototype.js"></script>
	<script type="text/javascript" src="../js/saveSamplesFromClinical.js"></script>
</head>
<body>
	<div id="superTop"></div>
	<div id="top"></div>
	<div class="list" id="reportTableDiv">
<%if(!noBack.equals("true")) { %> 
	<a id="backToClinical" href="./">Back to Clinical</a><br/> 
<% } %> 
<%
//all this because the g:innvokeTag didnt work at all...
UserContextService userContextService = (UserContextService) SpringContext.getBean("userContextService");
boolean isLogged = false;
if(userContextService!=null && userContextService.isLoggedIn()!=null && userContextService.isLoggedIn().toString().equals("true"))	{
	isLogged = true;
}
 %>

	<div id="saveSamplesDiv"> 
		<input type="text" value="<%=key%>_samples" name="listName" id="listName" /> 
		<input type="button" id="saveSamplesButton" onclick="saveGroupFromClinical('<%=key%>',$('listName').value);" value="Save All Samples from Report" /> 
		<span id="saveSamplesStatus"></span>
	</div>
	<display:table name="requestScope.rbl" defaultsort="1" defaultorder="descending" requestURI="" pagesize="50">
	  <display:caption>Clinical Report: <%= gov.nih.nci.cma.util.SafeHTMLUtil.clean(key) %></display:caption>
	  <display:column property="patientId" title="ID" sortable="true" headerClass="sortable" />
	  <display:column property="tumorTissueSite" sortable="true" headerClass="sortable" />
	  <display:column property="ptid"  sortable="true" headerClass="sortable" />
	  <display:column property="histologicaltype" sortable="true" headerClass="sortable" />
<% if(isLogged)	{ %>
	  
	  <display:column property="gender" sortable="true" headerClass="sortable" />
	  <display:column property="vitalStatus" title="Vital Status" sortable="true" headerClass="sortable" />
	  <display:column property="dob" sortable="false" headerClass="sortable" />
	  <display:column property="dod" sortable="false" headerClass="sortable" />
	  <display:column property="lastFollowUp" title="Last Followup" sortable="false" headerClass="sortable" />
	  <display:column property="firstProcedure" title="First Procedure" sortable="false" headerClass="sortable" />
	  <display:column property="firstExam" title="First Exam" sortable="false" headerClass="sortable" />
	  <display:column property="karnofskyScore" title="Karnofsky" sortable="true" headerClass="sortable" />
	  <display:column property="firstRadiation" title="First Radiation" sortable="false" headerClass="sortable" />
	  <display:column property="dodMinusDop" sortable="true" headerClass="sortable" />
	  <display:column property="dodfuMinusDop" sortable="true" headerClass="sortable" />
	  <display:column property="cfId" sortable="true" headerClass="sortable" />
	  <display:column property="informedConsentAcquired" sortable="true" headerClass="sortable" />
<% } %>
	</display:table>
	</div>
	<div  id="bottom" align="center">
		
	</div>	  
		
</body>
</html>