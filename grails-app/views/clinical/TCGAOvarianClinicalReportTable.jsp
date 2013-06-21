<%--L
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cma/LICENSE.txt for details.
L--%>

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
<%
//all this because the g:innvokeTag didnt work at all...
UserContextService userContextService = (UserContextService) SpringContext.getBean("userContextService");
boolean isLogged = false;
if(userContextService!=null && userContextService.isLoggedIn()!=null && userContextService.isLoggedIn().toString().equals("true"))	{
	isLogged = true;
}
 %>
	<div id="helptag_clinical_report_table" class="help"></div>
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

<% if(isLogged)	{ %>
	  <display:column property="vitalStatus" title="Vital Status" sortable="true" headerClass="sortable" />
	  <display:column property="dob" sortable="false" headerClass="sortable" />
	  <display:column property="dod" sortable="false" headerClass="sortable" />
	  <display:column property="lastFollowUp" title="Last Followup" sortable="false" headerClass="sortable" />
	  <display:column property="race" title="Race" sortable="true" headerClass="sortable" />
	  <display:column property="gender" sortable="true" headerClass="sortable" />
	  <display:column property="informedConsentAcquired" sortable="true" headerClass="sortable" />
	  <display:column property="histologicalType" sortable="true" headerClass="sortable" />
	  <display:column property="pretreatmentTherapy" sortable="true" headerClass="sortable" />
	  <display:column property="radiationTherapy" sortable="true" headerClass="sortable" />
	  <display:column property="initPathologicDxDate" title="Initial Pathologic Dx Date" sortable="false" headerClass="sortable" />
	  <display:column property="initPathologicDxMethod" title="Initial Pathologic Dx Method" sortable="false" headerClass="sortable" />
	  <display:column property="chemotherapy" title="Chemotherapy" sortable="false" headerClass="sortable" />
	  <display:column property="immunotherapy" title="Immunotherapy" sortable="false" headerClass="sortable" />
	  <display:column property="hormonalTherapy" title="hormonalTherapy" sortable="false" headerClass="sortable" />
	  <display:column property="targetedMolecularTherapy" title="Targeted Molecular Therapy" sortable="false" headerClass="sortable" />
	  <display:column property="tumorPrgr" title="Tumor Progression Date" sortable="false" headerClass="sortable" />
	  <display:column property="tumorRecur" title="Tumor Recurrence Date" sortable="false" headerClass="sortable" />
	  <display:column property="ethnicity" title="Ethnicity" sortable="false" headerClass="sortable" />
	  <display:column property="additionalRadiationTherapy" title="Additional Radiation Therapy" sortable="false" headerClass="sortable" />
	  <display:column property="additionalChemotherapy" title="Additional Chemotherapy" sortable="false" headerClass="sortable" />
	  <display:column property="additionalImmunotherapy" title="Additional Immunotherapy" sortable="false" headerClass="sortable" />
	  <display:column property="additionalHormoneTherapy" title="Additional Hormone Therapy" sortable="false" headerClass="sortable" />
	  <display:column property="additionalDrugTherapy" title="Additional Drug Therapy" sortable="false" headerClass="sortable" />
	  <display:column property="anatomicOrganSubdivision" title="AnatomicOrganSubdivision" sortable="false" headerClass="sortable" />
	  <display:column property="personNeoplasmStatus" title="Person Neoplasm Status" sortable="false" headerClass="sortable" />
	  <display:column property="siteOfTumorFirstRecurrence" title="Site Of Tumor First Recurrence" sortable="false" headerClass="sortable" />
	  <display:column property="tumorStage" title="Tumor Stage" sortable="false" headerClass="sortable" />
	  <display:column property="tumorGrade" title="Tumor Grade" sortable="false" headerClass="sortable" />
	  <display:column property="tumorResidualDisease" title="Tumor Residual Disease" sortable="false" headerClass="sortable" />
	  <display:column property="primaryTherapyOutcomeSuccess" title="Primary Therapy Outcome Success" sortable="false" headerClass="sortable" />
	  <display:column property="jewishOrigin" title="jewishOrigin" sortable="false" headerClass="sortable" />
	  <display:column property="dodMinusDop" sortable="true" headerClass="sortable" />
	  <display:column property="dodfuMinusDop" sortable="true" headerClass="sortable" />
	  <display:column property="dodMinusDx" sortable="true" headerClass="sortable" />
	  <display:column property="dodfuMinusDx" sortable="true" headerClass="sortable" />
<% } %>
	</display:table>
	</div>
	<div  id="bottom" align="center">
		
	</div>	  
		
</body>
</html>