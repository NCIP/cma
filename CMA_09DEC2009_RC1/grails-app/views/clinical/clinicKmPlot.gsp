<content tag="tabs"><g:render template="/tabs" model="[location:'clinical']"/></content> 
<content tag="side">&nbsp;</content>

<html>
<head>
	<meta name="layout" content="splashLayout" />
	<script type='text/javascript' src='../dwrspring/engine.js'></script>
	<script type='text/javascript' src='../dwrspring/util.js'></script>  
	  
	<script type='text/javascript'src="../dwrspring/interface/KMPlotService.js"></script>
	<script type='text/javascript'src="../dwrspring/interface/KMUserListHelper.js"></script>
	<script type='text/javascript'src="../dwrspring/interface/KMReporterService.js"></script>
	

	<script type='text/javascript'>

	  var sessionId = "${sessionId}";
	   /*This method formats a paramMap from the request and sends it
     	  to the KMPlotService to create a chart. Future impl may not
     	  necesitate JSON object
     	  -KR
     	*/
     	function kmPlotRequestAdapter(){			
			KMPlotService.createKMPlot(${listItems},kmPlotCallback);
		}
	
	</script>
	
	<g:javascript library="legacy/km"/>
	
</head>

<body>
	<!-- check to see if request comes from outside this page -->
	<g:if test="${plot != null }">
		<script type='text/javascript'>
			Event.observe(window, "load", function()	{
	    		kmPlotRequestAdapter();
	    	});
	    </script>
	</g:if>
	<script type='text/javascript'>
		Event.observe(window, "load", function()	{
     		//initializeLookups();
     		getUserLists(sessionId);
    	});
    </script>
	
	<div style="border:0px solid #000;margin:5px;text-align:center;width:650px">		
		<fieldset class="gray">
			<legend class="red" style="font-size:1.2em">
				Sample-based Filter
			</legend>
			<form id="sampleKMForm">
			<table border="0" width="650px" cellspacing="10" cellpadding="5">
				<tr>
					<td style="text-align:center">
						<select name="groupNameOne" id="groupNameOne">
							<option value ="lists">lists</option>
						</select> vs.
						<select name="groupNameCompare" id="groupNameCompare">
							<option value ="lists">lists</option>
						</select>	<br /><br />
						<input type="button" value="submit" onclick="kmPlotFormAdapter('sampleKMForm');" />
						<input type="hidden" name="plot" value="SAMPLE_KM_PLOT" />
					</td>
				</tr>
			</table>
			</form>
		</fieldset>
	</div>
	<table height="400px" border="0">
		<tr>
			<td>
				<div id="geneKmPlot">
					<img src="${createLinkTo(dir:'images',file:'indicator.gif')}" id="statusImg" />
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
	
	
			