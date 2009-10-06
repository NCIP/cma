<content tag="tabs"><g:render template="/tabs" model="[location:'gene']"/></content> 
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
     		initializeLookups();
    	});
    </script>
	
	<table border="0" width="670px" cellspacing="10" cellpadding="5"><tr>
	<td>
		<div style="border:0px solid #000;margin:5px;align-text:left;width=170px">		
		<div id="helptag_expression_based_km" class="help"></div>
		<fieldset class="gray">
			<legend class="red" style="font-size:1.2em">
				Gene Expression Filter
			</legend>
			
		<form id="geKMForm">
		<table border="0" width="100%"><tr>
		
<!-- Remove below input element when the selection below is re-enabled -->
			<input type="hidden" name="plotType" id="plotType" value="Gene Expression">
<!-- Remove below TD element when above selection is restored -->
			<td width="30%" colspan="2">Gene Symbol:<br /><input type="text" id="geneSymbol" name="geneSymbol" size="10" onclick="enableLookupButton();" />&nbsp;
			
			</td>
			<td width="40%">	
				Available Reporters: <br />			    
				<select name="reporter" id="reporter">
			       <option value="${cc_NOT_INCLUDED}">${cc_NOT_INCLUDED}<option>
				</select>
				<input type="button" onclick="getReporterList($('geneSymbol').value ,$('geArrayPlatformId').options[$('geArrayPlatformId').selectedIndex].value, $('groupNameGE').options[$('groupNameGE').selectedIndex].value, $('reporter').name);" id="lookupButton" value="lookup reporters" />
				<span id="reporterStatus"></span>
				<input type="hidden" name="taskId" id="taskId" value="${cc_NOT_INCLUDED}">
		    </td></tr>
			
			<tr>
				<td valign="top" colspan="3" class="value ${hasErrors(bean:geneView,field:'geArrayPlatform','errors')}">
					<g:set var="platformList" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
					<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');}" name="geArrayPlatform" id="geArrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" optionValue="platformName" optionKey="fileName"></g:select>&nbsp;&nbsp;

					<input type="hidden" id="platformName" name="platformName" value=""/>
					<span id="currentPlatform"></span>
					<g:each in="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}">
						<span class="platformDesc" style="display:none;" id="${it.platformName}PlatformDesc">${it.displayString}</span>
					</g:each>
				</td>
				<!--			
				<td colspan="3">
					Select Array Platform:<br/>	
		    		<select name="geArrayPlatform" id="geArrayPlatformId" onclick="enableLookupButton();">
						<option value="${cc_NOT_INCLUDED}">${cc_NOT_INCLUDED}<option>
					</select>
				</td>
				-->
			</tr>
			
			<tr><td width="50%">
			Select a List:<br />
			<select name="groupNameGE" id="groupNameGE" onclick="enableLookupButton();">
				<option value="${cc_NOT_INCLUDED}">${cc_NOT_INCLUDED}<option>
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
				value="${cc_GENE_EXP_KMPLOT}" />
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
					<img src="${createLinkTo(dir:'images',file:'indicator.gif')}" id="statusImg" />
					&nbsp;
					<b>loadingImage...</b>
				</div>
			</td>
		</tr>
	</table>
	<div>
		<div id="helptag_clinical_report" class="help"></div>
		<b class="otherBold">View Clinical Data:</b>
		<span id="reportHTML"></span><br />
	</div>
	<br/><br/>
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
	
	
			