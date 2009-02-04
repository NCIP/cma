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
					<td class="label">WBC
						<span class="small">peripheral blood</span>
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('wbcLower', 'wbcUpper');"/>Restrict by WBC
						<br/><br/>					
						between
						<g:select name="wbcLower" from="${(0..100).step(10)}" value="0" id="wbcLower" disabled="true"/> 
						and
						<g:select name="wbcUpper" from="${(10..110).step(10)}" value="110" id="wbcUpper" disabled="true"/>
			        </td>
		        </tr>
		        <tr>
					<td class="label">Day 8
						<span class="small">bone marrow blast %</span>
					</td>
					<td>
						<g:select name="day8mrd"  from="${day8mrd}" noSelection="${['ANY':'Any']}" />
			        </td>
		        </tr>
		        <tr>
					<td class="label">Day 29
						<span class="small">bone marrow blast %</span>
					</td>
					<td>
						<g:select name="day29mrd"  from="${day29mrd}" noSelection="${['ANY':'Any']}" />
			        </td>
		        </tr>
		         <tr>
			        <td class="label">Event</td>
			        <td>
			        	<g:select name="event"  from="${eventList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">Death</td>
			        <td>
			        	<g:select name="death"  from="${deathList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
		        <tr>
			        <td class="label">Congenital Abnormality</td>
			        <td>
			        	<g:select name="congenitalAbnormality"  from="${congenitalAbnormalityList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">TEL Status</td>
			        <td>
			        	<g:select name="telStatus"  from="${telStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">Trisomies 4+10</td>
			        <td>
			        	<g:select name="trisomies"  from="${trisomiesList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">MLL Status</td>
			        <td>
			        	<g:select name="mllStatus"  from="${mllStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">E2A Status</td>
			        <td>
			        	<g:select name="e2aStatus"  from="${e2aStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">BCR Status</td>
			        <td>
			        	<g:select name="bcrStatus"  from="${bcrStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				<tr>
			        <td class="label">CNS Status</td>
			        <td>
			        	<g:select style="width:200px;" name="cnsStatus"  from="${cnsStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				<tr>
			        <td class="label">Testicular Status</td>
			        <td>
			        	<g:select name="testicularStatus"  from="${testicularStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">Gender</td>
			        <td>
			        	<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
	
				<tr>		        
					<td class="label">Age
						<span class="small">years range</span>
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('ageLower', 'ageUpper');"/>Restrict by Age
						<br/><br/>
						between
						<g:select name="ageLower" from="${(0..100).step(10)}" value="0" disabled="true" /> 
						and
						<g:select name="ageUpper" from="${(10..110).step(10)}" value="110" disabled="true" />						
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
	</body>
</html>