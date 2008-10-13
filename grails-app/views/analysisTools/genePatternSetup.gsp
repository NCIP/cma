<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
  <g:javascript src="project.js" />
<!--  <g:javascript src="window.js" /> -->

  <script type="text/javascript">
  
  function dropdownChromo(el, div, resetParamId) {     
    var el = el.value; //the element value to check	  			   				
    var hideable = div;  //the element to show or hide
    var showDiv = false;
    if (el == 'Copy Number') {
        showDiv = true;
    } else {
        showDiv = false;
    }
    if (showDiv) {
       // if (win) {
            Effect.BlindDown(hideable);
       // } else {
       //     $(hideable).style.display = "block";
       // }
    } else {							
							//its default settings							
       // if (win) {
            setBackToDefault(resetParamId);
            Effect.BlindUp(hideable);
       // } else {
       //     setBackToDefault(resetParamId);
       //     $(hideable).style.display = "none";
       // }
    }
  }
  
  </script>
  
	</head>
	
	<body>
		<h3>Gene Pattern Analysis</h3>
		<g:form name="gpForm" controller="analysisTools" action="genePatternSubmit" method="post">
		
		<fieldset>
		<legend>Select an Analysis Module</legend>
			<div id="analysisModule" style="vertical-align: middle; display: table-cell;">
				<g:select name="analysisModuleName" size="1" 
				style="width: 200px; overflow: none;" 
				onchange="dropdownChromo(this,'advZ','advZ');"
				from="${moduleList}"></g:select>				
			</div>
		</fieldset>
		
		<div id="advZ" style="display:none;">
			<fieldset>
				<legend>Select a chromosome</legend>		
				<g:select onchange="\$('chromosomeName').value = this.options[this.selectedIndex].text;" 
					name="chromosomeSelections" size="8" 
					style="width: 100px; overflow: none;"
					from="${['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','X','Y']}">
				</g:select>
			</fieldset>
		</div>
		<input type="hidden" id="chromosomeName" name="chromosomeName" value=""/>
		
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

