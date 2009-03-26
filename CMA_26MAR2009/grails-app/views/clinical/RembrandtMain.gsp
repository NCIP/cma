<content tag="tabs"><g:render template="/tabs" model="[location:'clinical']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
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
		<script type="text/javascript">
			var toggleDisable = function() {
			    for (var i = 0; i < arguments.length; i++) {
			      var element = $(arguments[i]);
			      if (element.hasAttribute('disabled')) {
					  element.enable();
				  } else {
					  element.disable();
				  }
			    }
			  }
			Event.observe(window, "load", function()	{
				new Effect.Corner($('sampleKMHeader'), 'top');
				new Effect.Corner($('clinicalHeader'), 'top');
			});
		</script>
	</head>
	<body>
		<h2>Clinical View</h2>
		<fieldset>
		<g:if test="${flash.message}">
        	<div class="message">${flash.message}</div><br/>
        </g:if>
		<div id="clinicalHeader" class="sectionHeader">
			<h5>Clinical Search</h5>
		</div>
		<div class="sectionBody">
		<div id="helptag_clinical_search_criteria" class="help"></div>
			<g:form action="clinicalReport" method="post" name="clinicalForm">
			<table class="formTable">
				<tr>
			        <td class="label">Sample Group(s)
			        	<span class="small">select 1 or more</span>
			        </td>
			        <td>
						<g:select name="sampleGroup" multiple="multiple" size="5" id="sampleGroup" 
						style="width: 200px; overflow: none;" 
						from="${patLists}"></g:select>
					</td>
				</tr>
				<tr>
					<td class="label">Age at Diagnosis
						<span class="small">years range</span>
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('ageAtDxLower', 'ageAtDxUpper');"/>Restrict by Age
						<br/><br/>					
						between
						<g:select name="ageAtDxLower" from="${(0..100).step(10)}" value="0" id="ageAtDxLower" disabled="true"/> 
						and
						<g:select name="ageAtDxUpper" from="${(10..110).step(10)}" value="110" id="ageAtDxUpper" disabled="true"/>
			        </td>
		        </tr>
		        <tr>
			        <td class="label">Gender</td>
			        <td>
			        	<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				<tr>		        
					<td class="label">Survival
						<span class="small">months range</span>
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('survivalLower', 'survivalUpper');"/>Restrict by Survival
						<br/><br/>
						between
						<g:select name="survivalLower" from="${(0..100).step(10)}" value="0" disabled="true" /> 
						and
						<g:select name="survivalUpper" from="${(10..110).step(10)}" value="110" disabled="true" />						
			        </td>
		        </tr>
		        <tr>
			        <td class="label">Disease</td>
			        <td>
			        	<g:select name="disease" from="${diseaseList}" noSelection="${['ANY':'Any']}" />
			        </td>
		        </tr>
		        <tr>
			        <td class="label">Race</td>
			        <td>
			        	<g:select name="race"  from="${raceList}" noSelection="${['ANY':'Any']}" />
			        </td>
		        </tr>
		         <tr>
			        <td class="label">Query Name
			        <span class="small">should be unique</span>
			        </td>
			        <td>
			        	<input type="text" name="queryName"/>
			        </td>
		        </tr>
			</table>
			<div style="text-align:center;">
		        <input type="reset" value="clear"/>
				<input type="submit" value="submit"/>
			</div>
			</g:form>
		</div>
		</fieldset>
		
		
		<!--  start km -->
		<div>
			<div id="sampleKMHeader" class="sectionHeader">
				<h5>Sample-based Kaplan-Meier Graph</h5>
			</div>
			<div class="sectionBody">
				<g:form action="clinicalKM" method="post" name="clinicKMPlotForm">
				<input type="hidden" name="plot" value="SAMPLE_KM_PLOT"/>
				<table align="center" border="0">
		    		<tr style="vertical-align:top">
		    			<td>
			       			Kaplan-Meier survival plot for Sample Data&nbsp;
			        		<br/><br/>
			        		<g:select name="groupNameOne" style="margin-left:20px;width:200px;" id="groupNameOne"
			        			from="${patLists}"></g:select>
			         		vs. 
							<g:select name="groupNameCompare" style="margin-left:20px;width:200px;" id="groupNameCompare"
			        			from="${patLists}"></g:select>
			        		<br/><br/>
						</td>
					</tr>
					<tr>
						<td>
		        			<div style="text-align:center">
			        			<input type="submit" id="submittalButton" class="subButton" value="Go" />
		        			</div>
		        		</td>
		        	</tr>
				</table>
				</g:form>
			</div>
		</div>
		<!--  end km -->
		
	</body>
</html>