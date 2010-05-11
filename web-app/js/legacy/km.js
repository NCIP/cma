
//requires: global vars basePath and sessionId to have been set already

/* loads parameters needed from the request into native js local
	  variables */
function initializeLookups() {
	getUserLists(sessionId);
	//getArrayPlatforms(sessionId);
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
	try {
		KMPlotService.createKMPlot(values, kmPlotCallback);
	}
	catch (e) {
		//console.log(e);
	}
}
/*This callback method takes a JSON array with
 some necessary plotInfo like the name of the file to find
in the temporary space. This is acheived by calling the
JFree Servlet and requesting the desired filename. Also
included are the sample Groups used and the p-value rank -KR
*/
function kmPlotCallback(data) {
	 
	try {
		//is this a gene or clinical based KM?
		var kmtype = "gene";
		if(location.href.indexOf('geneView')==-1)
			kmtype = "clinical";
				
		var plotInfo = eval("(" + data + ")");
		var fileName = plotInfo.fileInfo;
		var servletPath = "servlet/DisplayChart?filename=";
		var html = "<img src=\"" + "../" + servletPath + fileName + "\"/>";
		setTimeout(function () {
			$("geneKmPlot").innerHTML = html;
		}, 1000);
		var sampleGroupNames = plotInfo.sampleGroupNames;
		var logRank = plotInfo.logRankPValue;
		var logRank2 = plotInfo.logRankPValue2;
		var logRank3 = plotInfo.logRankPValue3;
		var logHTML = "";
		if (sampleGroupNames.length > 0) {
			var reportHTML = "";
			var statisticsHTML = "";
			for (var i = 0; i < sampleGroupNames.length; i++) {
				var groupName = sampleGroupNames[i].name;
				var groupCount = sampleGroupNames[i].count;
				var ids = sampleGroupNames[i].ids;
				if(kmtype == "clinical")
					reportHTML += "&nbsp;&nbsp;<a href=\"#\" onclick=\"javascript:spawn('../clinical/clinicalSampleGroup?taskId=" + groupName +"', 750, 500,'clinical_report');\">" + groupName + "</a>&nbsp;&nbsp;";
				else
					reportHTML += "&nbsp;&nbsp;<a href=\"#\" onclick=\"javascript:spawn('../clinical/clinicalSampleGroup?taskId=" + groupName +"KM&ids=" + ids + "', 750, 500,'clinical_report');\">" + groupName + "</a>&nbsp;&nbsp;";

				statisticsHTML += "&nbsp;&nbsp;" + groupName + ":&nbsp;&nbsp;" + groupCount + "&nbsp;samples<br />";
			}
			if (logRank != null) {
				logHTML = sampleGroupNames[0].name + "&nbsp;vs.&nbsp;" + sampleGroupNames[1].name + "&nbsp;=" + logRank;
			}
			if (logRank2 != null) {
				logHTML += "<br/>" + sampleGroupNames[0].name + "&nbsp;vs.&nbsp;" + sampleGroupNames[2].name + "&nbsp;=" + logRank2;
			}
			if (logRank3 != null) {
				logHTML += "<br/>" + sampleGroupNames[1].name + "&nbsp;vs.&nbsp;" + sampleGroupNames[2].name + "&nbsp;=" + logRank3;
			}
			$("reportHTML").innerHTML = reportHTML;
			$("statisticsHTML").innerHTML = statisticsHTML;
			$("logHTML").innerHTML = logHTML;
		}
	}
	catch (err) {
		if ($("geneKmPlot")) {
			$("geneKmPlot").innerHTML = err + "<br />Did you make a selection?";
			$("geneKmPlot").style.display = "";
		}
		
	}
}

function spawn(url, winw, winh) {
    var w = window.open(url, "_blank", "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + ",scrollbars=yes,resizable=yes");
}

/* Loads an indicator image while image is retrieved */
function loadingImage() {
	$("geneKmPlot").innerHTML = "<img src=\"../images/indicator.gif\" /> loadingImage...";
}
/* retrieves user-defined lists */
function getUserLists(sessionId) {
	KMUserListHelper.getPatientLists(sessionId, setPatientLists);
}
/* retrieves array platforms */
function getArrayPlatforms() {
	KMReporterService.getPlatformMappings(setPlatforms);
}
function setPatientLists(data) {
	DWRUtil.removeAllOptions("groupNameOne");
	DWRUtil.removeAllOptions("dualGroupNameOne");
	DWRUtil.removeAllOptions("singleGroupNameOne");
	DWRUtil.removeAllOptions("groupNameCompare");
	DWRUtil.removeAllOptions("groupNameGE");
	DWRUtil.addOptions("groupNameOne", data);
	DWRUtil.addOptions("dualGroupNameOne", data);
	DWRUtil.addOptions("singleGroupNameOne", data);
	DWRUtil.addOptions("groupNameGE", data);
	DWRUtil.addOptions("groupNameCompare", data);
}

function setPlatforms(txt) {
	var res = eval("(" + txt + ")");
	DWRUtil.removeAllOptions("geArrayPlatformId");
	var oOption = document.createElement("OPTION");
	oOption.text = "choose platform";
	oOption.value = "";
	$("geArrayPlatformId").options.add(oOption);
	for (var i = 0; i < res.length; i++) {
		oOption = document.createElement("OPTION");
		oOption.text = res[i].label;
		oOption.value = res[i].value;
		$("geArrayPlatformId").options.add(oOption);
	}
}
function enableLookupButton() {
	$("lookupButton").disabled = false;
	DWRUtil.removeAllOptions($("reporter"));
	DWRUtil.addOptions($("reporter"), ["NONE"]);
}
function getReporterList(gene, platform, listName, elementToUpdate) {
	if (gene.length > 0 && platform.length > 0) {
		$("reporterStatus").innerHTML = "<img src='../images/indicator.gif' id='statusImg' />&nbsp;<b>retrieving reporters...</b>";
		KMReporterService.getReportersByExpressionasJSON(gene, platform, listName, elementToUpdate, createReporterList);
	}
}
function createReporterList(txt) {
	try {
		var res = eval("(" + txt + ")");
		var result = res[0].results;
		var elementToUpdate = res[0].elementId;
		var reporters = res[0].reporters;
		var taskId = res[0].taskId;
		var control_taskId = res[0].control_taskId;
		if (result == "found") {
			DWRUtil.removeAllOptions(elementToUpdate);
			DWRUtil.addOptions(elementToUpdate, reporters);
			$("taskId").value = taskId;
			$("control_taskId").value = control_taskId;
		} else {
			DWRUtil.removeAllOptions(elementToUpdate);
			DWRUtil.addOptions(elementToUpdate, ["NONE"]);
			alert("No reporters found for: " + res[0].gene);
		}
		$("lookupButton").disabled = true;
		$("reporterStatus").innerHTML = "";
		$("geSubmitButton").disabled = false;
	}
	catch (err) {
		alert(err);
	}
}

