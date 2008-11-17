<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				new Effect.Corner($$('.sectionHeader')[0], 'top');
			});
		</script>
		<style>
			/* experimenting with some CSS here */
			.formTable td	{
				vertical-align:top;
				padding-top:10px;
				padding-left:20px;
			}
			.formTable td.label	{
				font-weight:bold;
				padding-right:30px;
				text-align:right;
			}
			.formTable .small	{
				display:block;
				color: gray;
				font-size:10px;
				font-weight:normal;
			}
			
		</style>
	</head>
	<body>
		<h2>Analysis Tools : Principal Component Analysis</h2>
		<fieldset>
		<g:if test="${flash.message}">
        	<div class="message">${flash.message}</div><br/>
        </g:if>
        <div class="sectionHeader">
			<h5>Principal Component Analysis</h5>
		</div>
		<div class="sectionBody">
        
		<g:form controller="analysisTools" action="pcaSubmit" method="post">
		<table class="formTable">
				<tr>
			        <td class="label">Select Patient Group*
				        <span class="small">select 1 or more</span>
			        </td>
			        <td>
						<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
							<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" 
							style="width: 200px; overflow: none;" 
							from="${patLists}"></g:select>
						</div>
					</td>
				</tr>
		
		<tr>
			<td class="label">Filter Genes/Reporters
				<span class="small">check box for advanced features</span>
			</td>
			<td>
				Advanced Filter Settings?
				<g:checkBox onclick="document.getElementById('advY').toggle();" name="adv" id="adv"/><br/>
			</td>
		</tr>
		<tr>
			<td colspan="2"> 
				<div id="advY" style="display:none; text-align:right;background-color:#E0E0E0;">
					Constrain reporters by variance (Gene Vector) percentile:&nbsp;&nbsp;&ge;			
					<input type="text" name="variancePercentile" id="variancePercentile" size="4" value="70"/>&nbsp;&nbsp;%
					
					<br/><br/>	
				  	Use differentially expressed genes:
					<g:select name="geneReporterName" id="geneList" 
					style="width: 200px; overflow: none;"
					noSelection="['':'none']"  
					from="${geneLists}"></g:select>	
					<br/><br/>	
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="label">Select Array Platform*</td>
           	<td>
	            <!-- sets the hidden field to the platformName - do not change -->
				<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;" name="arrayPlatform" id="arrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.listOrderByPlatformName()}" optionValue="platformName" optionKey="fileName"></g:select>
				<input type="hidden" id="platformName" name="platformName" value=""/>
			</td>
		</tr>
		
		<tr>
			<td class="label">Name Analysis Result*
				<span class="small">should be unique</span>
			</td>
			<td>
				<input type="text" id="analysisResultName" value="" size="50" name="analysisResultName"/>
			</td>
		</tr>
		</table>
		<div align="center">
			<input type="reset" value="clear"/>
			<input type="submit" value="submit"/>
		</div>	
		</g:form>
		</div>
		</fieldset>
	</body>
</html>