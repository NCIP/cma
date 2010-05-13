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
	  
	    /* This method formats a paramMap from the request and sends it
     	   to the KMPlotService to create a chart. Future impl may not
     	   necesitate JSON object
     	   -KR
     	 */
     	function kmPlotRequestAdapter(){			
			KMPlotService.createKMPlot(${listItems},kmPlotCallback);
		}
			
		function updateListView() {
			var listCount = document.getElementById('groupNameCompare').options.length;

			if ( listCount >= 2 ) {
				$('dualGroupNameOne').disabled = false;
				$('groupNameCompare').disabled = false;
				$('single_patient_list').hide();
				$('dual_patient_lists').show();
			} else {
				$('dualGroupNameOne').disabled = true;
				$('groupNameCompare').disabled = true;
				$('dual_patient_lists').hide();
				$('single_patient_list').show();
			}
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
     		updateListView();
    	});
    </script>
	
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
					<span id="logHTML"></span><br />
				</div>
	</fieldset>
	
	<div style="border:0px solid #000;margin:5px;text-align:center;width:650px">
	<div id="helptag_km_plots_overview" class="help"></div>		
		<fieldset class="gray">
			<legend class="red" style="font-size:1.2em">
				Sample-based Filter
			</legend>
			<form id="sampleKMForm">
			<div id="dual_patient_lists">
			<table border="0" width="650px" cellspacing="10" cellpadding="5">
				<tr>
					<td style="text-align:center">
						<select name="groupNameOne" id="dualGroupNameOne">
							<option value ="lists">lists</option>
						</select> 
						vs.
						<select name="groupNameCompare" id="groupNameCompare">
							<option value ="lists">lists</option>
						</select>	
						<br /><br />
					</td>
				</tr>
			</table>
			</div>
			<div id="single_patient_list">
			<table border="0" width="650px" cellspacing="10" cellpadding="5">
				<tr>
					<td style="text-align:center">
						<select name="groupNameOne" id="singleGroupNameOne">
							<option value ="lists">lists</option>
						</select> 
						<br /><br />
					</td>
				</tr>
			</table>
			</div>
			<table border="0" width="650px" cellspacing="10" cellpadding="5">
				<tr>
					<td style="text-align:center">
						<input type="button" value="submit" onclick="kmPlotFormAdapter('sampleKMForm');" />
						<input type="hidden" name="plot" value="SAMPLE_KM_PLOT" />
					</td>
				</tr>
				</div>
			</table>
			</form>
		</fieldset>
	</div>

</body>
</html>
	
	
			