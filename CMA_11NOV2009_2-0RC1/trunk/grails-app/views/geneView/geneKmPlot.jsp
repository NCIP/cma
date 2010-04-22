<%@ page language="java"
	import="java.util.*,org.json.simple.JSONObject, gov.nih.nci.caintegrator.util.CaIntegratorConstants, gov.nih.nci.cma.util.SafeHTMLUtil, org.directwebremoting.WebContextFactory"
	pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
	String sessionId = request.getSession().getId();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
            
            // String _sessionId = WebContextFactory.get().getSession().getId();
             //System.out.println("sid: " + _sessionId);
%>

<html>
<head>

<base href="<%=basePath%>">
<script type='text/javascript' src='dwrspring/engine.js'></script>
<script type='text/javascript' src='dwrspring/util.js'></script>  
  
<script type='text/javascript'src="dwrspring/interface/KMPlotService.js"></script>
<script type='text/javascript'src="dwrspring/interface/KMUserListHelper.js"></script>
<script type='text/javascript'src="dwrspring/interface/KMReporterService.js"></script>
<script type='text/javascript' src="kmPlot/js/km.js"></script>
<script type='text/javascript' src="<%=basePath %>js/prototype/prototype.js"></script>


<script type='text/javascript'>
	  /* loads parameters needed from the request into native js local
	  variables */
	  function initializeLookups(){
	  	var basePath = "<%=basePath%>";
	  	var sessionId = "<%=sessionId%>";
	  	getUserLists(sessionId);
	  	getArrayPlatforms(sessionId);
	  }
	  
	   /*This method formats a paramMap from the request and sends it
     	  to the KMPlotService to create a chart. Future impl may not
     	  necesitate JSON object
     	  -KR
     	*/
     	function kmPlotRequestAdapter(){	
     		<%JSONObject listItems = new JSONObject();
     		Iterator it = request.getParameterMap().entrySet().iterator();
     	    while (it.hasNext()) {
     	        Map.Entry pairs = (Map.Entry)it.next();
     	       	Object o = pairs.getValue();
    	        String[] requestValues = (String[])o;
    	        // Clean parameters
    	        for (int i = 0; i < requestValues.length; i++) {
    	        	requestValues[i] = SafeHTMLUtil.clean(requestValues[i]);
    	        }    	       
     	        listItems.put((String)pairs.getKey(),requestValues[0]);     	       
     	    }
     	    if(request.getAttribute("reporter")!=null && request.getAttribute("taskId")!=null){
     	       listItems.put("reporter",request.getAttribute("reporter"));
     	       listItems.put("taskId",request.getAttribute("taskId"));
     	    }
     	    if(request.getAttribute("control_taskId")!=null){
     	       listItems.put("control_taskId",request.getAttribute("control_taskId"));
     	    }
     		%>       				     		
			KMPlotService.createKMPlot(<%=listItems%>,kmPlotCallback);			
		}
		
		/* This method formats a paramMap from a form and sends it
     	   to the KMPlotService to create a chart. -KR
     	 */
function kmPlotFormAdapter(formId) {
	loadingImage();
	var sampleForm = $(formId).serialize();
	var temp = new Array();	
	temp = sampleForm.split("&");
	var values = new Object();
	for (var i = 0; i < temp.length; i++) {
		var Mvalues = temp[i].split("=");
		values[Mvalues[0]] = Mvalues[1];
	}
	try	{
		KMPlotService.createKMPlot(values, kmPlotCallback);
	}catch(e)	{
		console.log(e);
	}
}


/*This callback method takes a JSON array with
 some necessary plotInfo like the name of the file to find
in the temporary space. This is acheived by calling the
JFree Servlet and requesting the desired filename. Also
included are the sample Groups used and the p-value rank -KR
*/
function kmPlotCallback(data) {	
console.log("in callback");

	try	{
				var plotInfo = eval('(' + data + ')');								
				var fileName = plotInfo.fileInfo;
				var basepath = "<%=basePath%>";
				var servletPath = "servlet/DisplayChart?filename=";
				var html = "<img src=\"" + basepath + servletPath + fileName + "\"/>";
				setTimeout(function () {
					$("geneKmPlot").innerHTML = html;
				}, 1000);
				var sampleGroupNames = plotInfo.sampleGroupNames;
				var logRank = plotInfo.logRankPValue;
				var logHTML = "";	
				if(sampleGroupNames.length > 0){
					var reportHTML = "";
					var statisticsHTML = "";					
					for(var i=0; i<sampleGroupNames.length; i++){
						var groupName = sampleGroupNames[i].name;
						var groupCount = sampleGroupNames[i].count;
						var ids = sampleGroupNames[i].ids;
						reportHTML += "&nbsp;&nbsp;<a href=\"#\" onclick=\"javascript:spawnx('clinicalReport.do?method=runReport&taskId=" + groupName + "QuickSearch&ids=" + ids + "', 750, 500,'clinical_report');\">" + groupName + "</a>&nbsp;&nbsp;";
						statisticsHTML += "&nbsp;&nbsp;" + groupName + ":&nbsp;&nbsp;" + groupCount + "&nbsp;samples<br />";
					}
					if(logRank!=null){
						logHTML = sampleGroupNames[0].name + "&nbsp;vs.&nbsp;" + sampleGroupNames[1].name + "&nbsp;=" + logRank;
					}
					$("reportHTML").innerHTML = reportHTML;
					$("statisticsHTML").innerHTML = statisticsHTML;
					$("logHTML").innerHTML = logHTML;
				}
	}
	catch(err)	{
				if($('geneKmPlot'))	{
					$("geneKmPlot").innerHTML = err + "<br />Did you make a selection?";
					$("geneKmPlot").style.display = "";
				}
	console.log(err);
	}
}
/* Loads an indicator image while image is retrieved */
function loadingImage() {
	$("geneKmPlot").innerHTML = "<img src=\"images/indicator.gif\" /> loadingImage...";
}

/* retrieves user-defined lists */
function getUserLists(sessionId){
	KMUserListHelper.getPatientLists(sessionId,setPatientLists);
}

/* retrieves array platforms */
function getArrayPlatforms(){
	KMReporterService.getPlatformMappings(setPlatforms);
}

function setPatientLists(data){		
		DWRUtil.removeAllOptions("groupNameOne");
    	DWRUtil.removeAllOptions("groupNameCompare");
    	DWRUtil.removeAllOptions("groupNameGE");
    	DWRUtil.addOptions("groupNameOne", data);
    	DWRUtil.addOptions("groupNameGE", data);
    	DWRUtil.addOptions("groupNameCompare", data);    
}

function setPlatforms(txt){		
		var res = eval('(' + txt + ')');
		DWRUtil.removeAllOptions("geArrayPlatformId");				
		for(var i=0;i<res.length;i++){			
			var oOption = document.createElement("OPTION");
			oOption.text=res[i].label;
			oOption.value=res[i].value;
			$('geArrayPlatformId').options.add(oOption);
		}
		 
}

function enableLookupButton(){
		$('lookupButton').disabled = false;
		DWRUtil.removeAllOptions($('reporter'));
		DWRUtil.addOptions($('reporter'), ['<%=CaIntegratorConstants.NOT_INCLUDED%>']);
}

function getReporterList(gene, platform, listName, elementToUpdate){    	
    	if(gene.length>0 && platform.length>0){   
    		$('reporterStatus').innerHTML = "<img src='images/indicator.gif' id='statusImg' />&nbsp;<b>retrieving reporters...</b>"; 		
    		KMReporterService.getReportersByExpressionasJSON(gene, platform, listName, elementToUpdate, createReporterList);
    	}
	}
	
function createReporterList(txt){  	
    	try	{
				
				var res = eval('(' + txt + ')');				
				var result = res[0].results;
				var elementToUpdate = res[0].elementId;
				var reporters = res[0].reporters;
				var taskId = res[0].taskId;
				var control_taskId = res[0].control_taskId;
				
				if(result == "found"){				
					DWRUtil.removeAllOptions(elementToUpdate);    			
    				DWRUtil.addOptions(elementToUpdate, reporters);
    				$('taskId').value = taskId;
    				$('control_taskId').value = control_taskId;
    			}
    			else{
    				DWRUtil.removeAllOptions(elementToUpdate);    			
    				DWRUtil.addOptions(elementToUpdate, ['NONE']);
    				alert("No reporters found for: " + res[0].gene);
    			}
    			$('lookupButton').disabled = true;  
    			$('reporterStatus').innerHTML = ""; 
    			$('geSubmitButton').disabled = false;  			
			}
		catch(err){
				alert(err);
			}    	
	}
 

		
	</script>
</head>

<body>
	<!-- check to see if request comes from outside this page -->
	<%
	    if (request.getParameterMap().containsKey("plot")) {
	%>
	<script type='text/javascript'>
     			kmPlotRequestAdapter();
    </script>
	<%
	    }
	%>
	<script type='text/javascript'>
     			initializeLookups();
     			var basePath = "<%=basePath%>";
	  			var sessionId = "<%=sessionId%>";
    </script>
	
	<table border="0" width="670px" cellspacing="10" cellpadding="5"><tr>
	<td>
		<div style="border:0px solid #000;margin:5px;align-text:left;width=170px">		
		<fieldset class="gray">
			<legend class="red" style="font-size:1.2em">
			
<!-- Restore below legend when copy number filter is enabled -->
<!-- 
				Gene Expression / Copy Number Filter
-->
				Gene Expression Filter
			</legend>
			
		<form id="geKMForm">
		<table border="0" width="100%"><tr>
		
<!-- Remove below input element when the selection below is re-enabled -->
			<input type="hidden" name="plotType" id="plotType" value="Gene Expression">
<!--  Disable below selection temporarily. Restore when copy number filter is enabled -->
<!-- 
			<td width="40%">Plot Type:<br/> 
			<select name="plotType" id="plotType" onchange="thresholdDiv(this,'upGEY', 'upCNY', 'downGEY', 'downCNY');">
				<option value ="1">Gene Expression</option>
				<option value ="2">Copy Number</option>
			</select>	
			<br />
			</td>
			<td width="20%">Gene Symbol:<br /><input type="text" id="geneSymbol" name="geneSymbol" size="10" onclick="enableLookupButton();" />&nbsp;
-->
<!-- Remove below TD element when above selection is restored -->
			<td width="30%" colspan="2">Gene Symbol:<br /><input type="text" id="geneSymbol" name="geneSymbol" size="10" onclick="enableLookupButton();" />&nbsp;
			
			</td>
			<td width="40%">	
				Available Reporters: <br />			    
				<select name="reporter" id="reporter">
			       <option value="<%=CaIntegratorConstants.NOT_INCLUDED%>"><%=CaIntegratorConstants.NOT_INCLUDED%><option>
				</select>
				<input type="button" onclick="getReporterList($('geneSymbol').value ,$('geArrayPlatformId').options[$('geArrayPlatformId').selectedIndex].value, $('groupNameGE').options[$('groupNameGE').selectedIndex].value, $('reporter').name);" id="lookupButton" value="lookup reporters" />
				<span id="reporterStatus"></span>
				<input type="hidden" name="taskId" id="taskId" value="<%=CaIntegratorConstants.NOT_INCLUDED%>">
				<input type="hidden" name="control_taskId" id="control_taskId" value="<%=CaIntegratorConstants.NOT_INCLUDED%>">
		    </td></tr>
			
			<tr><td colspan="3">
			Select Array Platform:<br />	
		    <select name="geArrayPlatform" id="geArrayPlatformId" onclick="enableLookupButton();">
				<option value="<%=CaIntegratorConstants.NOT_INCLUDED%>"><%=CaIntegratorConstants.NOT_INCLUDED%><option>
			</select></td></tr>
			<tr><td width="50%">
			Select a List:<br />
			<select name="groupNameGE" id="groupNameGE" onclick="enableLookupButton();">
				<option value="<%=CaIntegratorConstants.NOT_INCLUDED%>"><%=CaIntegratorConstants.NOT_INCLUDED%><option>
			</select></td>
			<td nowrap width="25%">
			<div id="upGEY" style="display:block;">
				&nbsp;&nbsp;Up-Regulated:<br />
				&nbsp;&nbsp;&ge;
				<select name="upGE" id="upGE">
					<option value ="2.0">2.0</option>
					<option value ="3.0">3.0</option>
					<option value ="4.0">4.0</option>
					<option value ="5.0">5.0</option>
					<option value ="6.0">6.0</option>
					<option value ="7.0">7.0</option>
				</select> Fold
			</div>
			<div id="upCNY" style="display:none;">
				&nbsp;&nbsp;Amplified:<br />
				&nbsp;&nbsp;&ge;
				<select name="upCN" id="upCN">
					<option value ="2.0">2.0</option>
					<option value ="3.0">3.0</option>
					<option value ="4.0">4.0</option>
					<option value ="5.0">5.0</option>
					<option value ="6.0">6.0</option>
					<option value ="7.0">7.0</option>
				</select> Copy
			</div>
			</td>
			<td nowrap width="25%">
			<div id="downGEY" style="display:block;">
				&nbsp;Down-Regulated:<br />
				&nbsp;&ge;
				<select name="downGE" id="downGE">
					<option value ="2.0">2.0</option>
					<option value ="3.0">3.0</option>
					<option value ="4.0">4.0</option>
					<option value ="5.0">5.0</option>
					<option value ="6.0">6.0</option>
					<option value ="7.0">7.0</option>
				</select> Fold
			</div>
			<div id="downCNY" style="display:none;">
				&nbsp;Deleted:<br />
				&nbsp;&le;
				<select name="downCN" id="downCN">
					<option value ="2.0">2.0</option>
					<option value ="3.0">3.0</option>
					<option value ="4.0">4.0</option>
					<option value ="5.0">5.0</option>
					<option value ="6.0">6.0</option>
					<option value ="7.0">7.0</option>
				</select> Copy
			</div>
			</td>
		    </tr>
			<tr><td colspan="3">
			<input type="button" value="submit" id="geSubmitButton" disabled="true" onclick="kmPlotFormAdapter('geKMForm');" />
			<input type="hidden" name="plot"
				value="<%=CaIntegratorConstants.GENE_EXP_KMPLOT%>" />
			</td></tr>
			</table>
		</form>
		</fieldset>
	</div>
	</td></tr></table>

	<table height="400px" border="0">
		<tr>
			<td>
				<div id="geneKmPlot">
					<img src="images/indicator.gif" id="statusImg" />
					&nbsp;
					<b>loadingImage...</b>
				</div>
			</td>
		</tr>
	</table>
	<div align="center">
			<b class="otherBold">View Clinical Data</b><br />
				<span id="reportHTML"></span><br />
	</div>
	<fieldset class="gray" style="text-align:left">
		<legend class="red">Statistical	Report:</legend>
				<div>
				
				<b class="otherBold">Number of Samples in group</b><br />
				<span id="statisticsHTML"></span><br />
				<b class="otherBold">Log-rank p-value(for significance of difference of survival between group of samples)</b><br />
				<span id="logHTML"></span><br />
				</div>
	</fieldset>
</body>
</html>
	
	
			