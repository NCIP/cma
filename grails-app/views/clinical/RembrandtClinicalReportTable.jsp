<%--L
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cma/LICENSE.txt for details.
L--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.*, gov.nih.nci.cma.clinical.*"%>

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
	<script type="text/javascript" src="../js/Help.js"></script>
	<script type="text/javascript" src="../js/saveSamplesFromClinical.js"></script>
	<script type="text/javascript">
		Event.observe(window, "load", function()	{
        	var helpUrl = "${grailsApplication.config.gov.nih.nci.cma.helpUrl}";
 
 			Help.url = helpUrl+"/cmaPortal/index.html?single=false&context=cmaPortal&topic=";
			Help.guideUrl = helpUrl;
			//Help.url = "../" + Help.url;
			Help.parsePage({pth: "../images" });
	 	});
	</script>
</head>
<body>
	<div id="superTop"></div>
	<div id="top"></div>
	<div class="list" id="reportTableDiv">
<%if(!noBack.equals("true")) { %> 
	<a id="backToClinical" href="./">Back to Clinical</a><br/> 
<% } %> 
	
	<div id="helptag_clinical_report_table" class="help"></div>
	<div id="saveSamplesDiv"> 
		<input type="text" value="<%=key%>_samples" name="listName" id="listName" /> 
		<input type="button" id="saveSamplesButton" onclick="saveGroupFromClinical('<%=key%>',$('listName').value);" value="Save All Samples from Report" /> 
		<span id="saveSamplesStatus"></span>
	</div>
	<display:table name="requestScope.rbl" defaultsort="1" defaultorder="descending" requestURI="" pagesize="50">
	  <display:caption>Clinical Report: <%= gov.nih.nci.cma.util.SafeHTMLUtil.clean(key) %></display:caption>
	  <display:column property="sampleId" title="ID" sortable="true" headerClass="sortable" />
	  <display:column property="disease" sortable="true" headerClass="sortable" />
	  <display:column property="gender" sortable="true" headerClass="sortable" />
	  <display:column property="grade" sortable="true" headerClass="sortable" />
	  <display:column property="mriDesc"  title="MRI" />
	  <display:column property="karnofsky" sortable="true" headerClass="sortable" />
	  <display:column property="ageGroup"  title="Age Group" />
<display:column property="survivalLengthRange" sortable="true" headerClass="sortable" />
<display:column property="race" sortable="true" headerClass="sortable" />
<display:column property="institution" sortable="true" headerClass="sortable" />
<display:column property="neurologicalExamOutcome" />
<display:column property="followupMonth" />
<display:column property="steroidDoseStatus" />
<display:column property="antiConvulsantStatus" />
<display:column property="priorTherapyRadiationSite" />
<display:column property="priorTherapyRadiationFractionDose" />
<display:column property="priorTherapyRadiationFractionNumber" />
<display:column property="priorTherapyRadiationType" />
<display:column property="priorTherapyChemoAgentName" />
<display:column property="priorTherapyChemoCourseCount" />
<display:column property="priorTherapySurgeryProcedureTitle" />
<display:column property="priorTherapySurgeryTumorHistology" />
<display:column property="priorTherapySurgeryOutcome" />
<display:column property="onStudyTherapyRadiationSite" />
<display:column property="onStudyTherapyRadiationNeurosisStatus" />
<display:column property="onStudyTherapyRadiationFractionDose" />
<display:column property="onStudyTherapyRadiationFractionNumber" />
<display:column property="onStudyTherapyRadiationType" />
<display:column property="onStudyTherapyChemoAgentName" />
<display:column property="onStudyTherapyChemoCourseCount" />
<display:column property="onStudyTherapySurgeryProcedureTitle" />
<display:column property="onStudyTherapySurgeryIndication" />
<display:column property="onStudyTherapySurgeryHistoDiagnosis" />
<display:column property="onStudyTherapySurgeryOutcome" />
	</display:table>
	</div>
	<div  id="bottom" align="center">
		
	</div>	  

</body>
</html>