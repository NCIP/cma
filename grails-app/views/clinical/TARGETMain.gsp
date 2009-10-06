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
				//new Effect.Corner($('sampleKMHeader'), 'top');
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
		        
		<g:hasErrors bean="${clinicalView}">
		    <div class="errors">
		        <g:renderErrors bean="${clinicalView}" as="list" />
		    </div>
		</g:hasErrors>
         
		<div id="clinicalHeader" class="sectionHeader">
			<h5>Clinical Search</h5>
		</div>
		<div class="sectionBody">
			<div id="helptag_clinical_search_criteria" class="help"></div>
			<g:form action="clinicalReport" method="post" name="clinicalForm">
			<g:hiddenField name="clinicalFormType" value="TARGET" />
			<table class="formTable">
				<tr>
					<td valign="top" class="label">Sample Group(s)*
					    <span class="small">select 1 or more</span>
					</td>
					<td class="value ${hasErrors(bean:clinicalView,field:'sampleGroups','errors')}">
						<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
						  	<g:if test="${clinicalView == null}">				
								<g:select name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  	</g:if>				
						  	<g:elseif test="${clinicalView.sampleGroups == null}">	
								<g:select name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  	</g:elseif>	
						  	<g:else>	
							<select name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;">
								<g:each in="${patLists}" var="patList">
									<g:set var="isSelected" value="${false}"/>
									<g:each in="${selectedSampleGrpList}" var="listItem">
										<g:if test="${listItem.trim() == patList.trim()}">
											<g:set var="isSelected" value="${true}"/>
										</g:if>
									</g:each>
									<g:if test="${isSelected}">				
										<option value="${patList}" selected="yes">${patList}</option>
									</g:if>
									<g:else>
										<option value="${patList}">${patList}</option>
									</g:else>
								</g:each>
							</select>
						  	</g:else>				
						</div>			
					</td>
				</tr>
				<!--
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
				-->
				<tr>
					<td class="label">WBC
						<span class="small">peripheral blood</span>
					</td>
					<td>
						<g:select name="wbc"  from="${wbcList}" noSelection="${['ANY':'Any']}" />
			        </td>
		        </tr>
		        <tr>
					<td class="label">Day 8
						<span class="small">bone marrow blast %</span>
					</td>
					<td>
						<g:select name="day8mrd" from="${day8mrd}" noSelection="${['ANY':'Any']}" />
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
			        <td class="label">First Event</td>
			        <td>
			        	<g:select name="event"  from="${eventList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">Vital Status</td>
			        <td>
			        	<g:select name="vitalStatus"  from="${vitalStatusList}" noSelection="${['ANY':'Any']}" />
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
			        	<g:select name="testicular"  from="${testicularStatusList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
				 <tr>
			        <td class="label">Gender</td>
			        <td>
			        	<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
	
				 <tr>
			        <td class="label">Race</td>
			        <td>
			        	<g:select name="race"  from="${raceList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
	
				 <tr>
			        <td class="label">Ethnicity</td>
			        <td>
			        	<g:select style="width:200px;" name="ethnicity"  from="${ethnicityList}" noSelection="${['ANY':'Any']}" />
					</td>
				</tr>
	
				<tr>		        
					<td class="label">Age at Diagnosis
						<span class="small">in years</span>
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('ageLower', 'ageUpper');"/>Restrict by Age
						<br/><br/>
						between
						<g:select name="ageLower" from="${(0..30).step(2)}" value="0" disabled="true" /> 
						and
						<g:select name="ageUpper" from="${(2..30).step(2)}" value="20" disabled="true" />						
			        </td>
		        </tr>
	
				<tr>		        
					<td class="label">Years to Event
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('yteLower', 'yteUpper');"/>Restrict by Number of Years
						<br/><br/>
						between
						<g:select name="yteLower" from="${(0..10).step(1)}" value="0" disabled="true" /> 
						and
						<g:select name="yteUpper" from="${(1..10).step(1)}" value="10" disabled="true" />						
			        </td>
		        </tr>
	
				<tr>		        
					<td class="label">Years to Death
					</td>
					<td>
						<input type="checkbox" onclick="toggleDisable('ytdLower', 'ytdUpper');"/>Restrict by Number of Years
						<br/><br/>
						between
						<g:select name="ytdLower" from="${(0..10).step(1)}" value="0" disabled="true" /> 
						and
						<g:select name="ytdUpper" from="${(1..10).step(1)}" value="10" disabled="true" />						
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