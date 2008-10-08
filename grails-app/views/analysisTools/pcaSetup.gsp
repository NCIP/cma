<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
	</head>
	<body>
		<h3>Principal Component Analysis</h3>
		<g:form controller="analysisTools" action="pcaSubmit" method="post">
		<fieldset>
		<legend>Select Patient Group</legend>
			<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
				<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" 
				style="width: 200px; overflow: none;" 
				from="${patLists}"></g:select>
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Filter Genes/Reporters</legend>
			Advanced Filter Settings? 
			<g:checkBox onclick="document.getElementById('advY').toggle();" name="adv" id="adv"/><br/>
			<div id="advY" style="display:none;">
				<br/><br/>
				&nbsp;&nbsp;Constrain reporters by variance (Gene Vector) percentile:&nbsp;&nbsp;&ge;			
				<input type="text" name="variancePercentile" id="variancePercentile" size="4" value="70"/>&nbsp;&nbsp;%
				
				<br/><br/>	
			  	&nbsp;&nbsp;Use differentially expressed genes:
				&nbsp;&nbsp;

				<g:select name="geneReporterName" id="geneList" 
				style="width: 200px; overflow: none;"
				noSelection="['':'none']"  
				from="${geneLists}"></g:select>	
				<br/><br/>	
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Select Array Platform</legend>
            <!-- sets the hidden field to the platformName - do not change -->
			<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;" name="arrayPlatform" id="arrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.listOrderByPlatformName()}" optionValue="platformName" optionKey="fileName"></g:select>
			<input type="hidden" id="platformName" name="platformName" value=""/>
			
		</fieldset>
		
		<fieldset>
			<legend>Name Analysis Result*</legend>
			<input type="text" id="analysisResultName" value="" size="50" name="analysisResultName"/>
		</fieldset>
		
		<div align="center">
			<input type="reset" value="clear"/>
			<input type="submit" value="submit"/>
		</div>	
		</g:form>
	</body>
</html>