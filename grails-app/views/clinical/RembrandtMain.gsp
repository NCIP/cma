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
		        
		    <g:hasErrors bean="${rembrandtClinicalView}">
		    	<div class="errors">
		        	<g:renderErrors bean="${rembrandtClinicalView}" as="list" />
		        </div>
		    </g:hasErrors>
		    
			<div id="clinicalHeader" class="sectionHeader">
				<h5>Clinical Search</h5>
			</div>
			
			<div class="sectionBody">
			<div id="helptag_clinical_search_criteria" class="help"></div>
				<g:form action="clinicalReport" method="post" name="clinicalForm">
				<g:hiddenField name="clinicalFormType" value="Rembrandt" />
				<table class="formTable">
					<tr>
				        <td valign="top" class="label">Sample Group(s)*
				        	<span class="small">select 1 or more</span>
				        </td>
						<td class="value ${hasErrors(bean:rembrandtClinicalView,field:'sampleGroup','errors')}">
							<div id="sampleGroup" style="vertical-align: middle; display: table-cell;">
					  			<g:if test="${rembrandtClinicalView == null}">				
									<g:select name="sampleGroup" multiple="multiple" size="5" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
					  			</g:if>				
						  		<g:elseif test="${rembrandtClinicalView.sampleGroup == null}">	
									<g:select name="sampleGroup" multiple="multiple" size="5" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
					  			</g:elseif>	
					  			<g:else>	
									<select name="sampleGroup" multiple="multiple" size="5" style="width: 200px; overflow: none;">
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
						<td class="label">Age at Diagnosis
							<span class="small">years range</span>
						</td>
						<td>
					  		<g:if test="${rembrandtClinicalView == null}">				
								<input type="checkbox" name="age" value="age" onclick="toggleDisable('ageAtDxLower', 'ageAtDxUpper');"/>Restrict by Age
								<br/><br/>					
								between
								<g:select name="ageAtDxLower" from="${(0..100).step(10)}" value="0" id="ageAtDxLower" disabled="true"/> 
								and
								<g:select name="ageAtDxUpper" from="${(10..110).step(10)}" value="110" id="ageAtDxUpper" disabled="true"/>
					  		</g:if>				
					  		<g:elseif test="${rembrandtClinicalView.age != 'age'}">				
								<input type="checkbox" name="age" value="age" onclick="toggleDisable('ageAtDxLower', 'ageAtDxUpper');"/>Restrict by Age
								<br/><br/>					
								between
								<g:select name="ageAtDxLower" from="${(0..100).step(10)}" value="0" id="ageAtDxLower" disabled="true"/> 
								and
								<g:select name="ageAtDxUpper" from="${(10..110).step(10)}" value="110" id="ageAtDxUpper" disabled="true"/>
					  		</g:elseif>				
					  		<g:else>
					  			<g:if test="${rembrandtClinicalView.age == 'age'}">				
									<input type="checkbox" name="age" value="age" checked="yes" onclick="toggleDisable('ageAtDxLower', 'ageAtDxUpper');"/>Restrict by Age
					  			</g:if>				
								<br/><br/>					
								between
				        		<select name="ageAtDxLower">
									<g:each in="${(0..100).step(10)}" var="theAge">
										<g:if test="${theAge == (new Integer(rembrandtClinicalView.ageAtDxLower)).intValue()}">				
											<option value="${theAge}" selected="yes">${theAge}</option>
										</g:if>
										<g:else>
											<option value="${theAge}">${theAge}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        		and
				        		<select name="ageAtDxUpper">
									<g:each in="${(0..100).step(10)}" var="theAge">
										<g:if test="${theAge == (new Integer(rembrandtClinicalView.ageAtDxUpper)).intValue()}">				
											<option value="${theAge}" selected="yes">${theAge}</option>
										</g:if>
										<g:else>
											<option value="${theAge}">${theAge}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        	</g:else>
				        </td>
			        </tr>
			        <tr>
				        <td class="label">Gender</td>
				        <td>
					  		<g:if test="${rembrandtClinicalView == null}">				
				        		<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
				        	</g:if>
					  		<g:else>				
				        		<select name="gender">
									<g:each in="${genderList}" var="theGender">
										<g:if test="${theGender == rembrandtClinicalView.gender}">				
											<option value="${theGender}" selected="yes">${theGender}</option>
										</g:if>
										<g:else>
											<option value="${theGender}">${theGender}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        	</g:else>
						</td>
					</tr>
					<tr>		        
						<td class="label">Survival
							<span class="small">months range</span>
						</td>
						<td>
					  		<g:if test="${rembrandtClinicalView == null}">				
								<input type="checkbox" name="survival" value="survival" onclick="toggleDisable('survivalLower', 'survivalUpper');"/>Restrict by Survival
								<br/><br/>
								between
								<g:select name="survivalLower" from="${(0..100).step(10)}" value="0" disabled="true" /> 
								and
								<g:select name="survivalUpper" from="${(10..110).step(10)}" value="110" disabled="true" />						
						  	</g:if>				
					  		<g:elseif test="${rembrandtClinicalView.survival != 'survival'}">				
								<input type="checkbox" name="survival" value="survival" onclick="toggleDisable('survivalLower', 'survivalUpper');"/>Restrict by Survival
								<br/><br/>
								between
								<g:select name="survivalLower" from="${(0..100).step(10)}" value="0" disabled="true" /> 
								and
								<g:select name="survivalUpper" from="${(10..110).step(10)}" value="110" disabled="true" />						
					  		</g:elseif>				
					  		<g:else>
								<input type="checkbox" name="survival" value="survival" checked="yes" onclick="toggleDisable('ageAtDxLower', 'ageAtDxUpper');"/>Restrict by Age
								<br/><br/>					
								between
				        		<select name="survivalLower">
									<g:each in="${(0..100).step(10)}" var="theSurvival">
										<g:if test="${theSurvival == (new Integer(rembrandtClinicalView.survivalLower)).intValue()}">				
											<option value="${theSurvival}" selected="yes">${theSurvival}</option>
										</g:if>
										<g:else>
											<option value="${theSurvival}">${theSurvival}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        		and
				        		<select name="survivalUpper">
									<g:each in="${(0..100).step(10)}" var="theSurvival">
										<g:if test="${theSurvival == (new Integer(rembrandtClinicalView.survivalUpper)).intValue()}">				
											<option value="${theSurvival}" selected="yes">${theSurvival}</option>
										</g:if>
										<g:else>
											<option value="${theSurvival}">${theSurvival}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        	</g:else>
				        </td>
			        </tr>
			        <tr>
				        <td class="label">Disease</td>
				        <td>
					  		<g:if test="${rembrandtClinicalView == null}">				
					        	<g:select name="disease" from="${diseaseList}" noSelection="${['ANY':'Any']}" />
				        	</g:if>
					  		<g:else>				
				        		<select name="disease">
									<g:each in="${diseaseList}" var="theDisease">
										<g:if test="${theDisease == rembrandtClinicalView.disease}">				
											<option value="${theDisease}" selected="yes">${theDisease}</option>
										</g:if>
										<g:else>
											<option value="${theDisease}">${theDisease}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        	</g:else>
				        </td>
			        </tr>
			        <tr>
				        <td class="label">Race</td>
				        <td>
					  		<g:if test="${rembrandtClinicalView == null}">				
					        	<g:select name="race" from="${raceList}" noSelection="${['ANY':'Any']}" />
				        	</g:if>
					  		<g:else>				
				        		<select name="race">
									<g:each in="${raceList}" var="theRace">
										<g:if test="${theRace == rembrandtClinicalView.race}">				
											<option value="${theRace}" selected="yes">${theRace}</option>
										</g:if>
										<g:else>
											<option value="${theRace}">${theRace}</option>
										</g:else>
				        			</g:each>
				        		</select>
				        	</g:else>
				        </td>
			        </tr>
				    <tr>
				        <td valign="top" class="label">Query Name
				        <span class="small">should be unique</span>
				        </td>
				        <td valign="top" class="value ${hasErrors(bean:rembrandtClinicalView,field:'queryName','errors')}">
				        	<input type="text" name="queryName" value="${fieldValue(bean:rembrandtClinicalView,field:'queryName')}"/>					        
				        </td>
				     </tr>
				     <!--
			         <tr>
				        <td class="label">Query Name
				        <span class="small">should be unique</span>
				        </td>
				        <td>
				        	<input type="text" name="queryName"/>
				        </td>
			        </tr>
			        -->
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
			<div id="helptag_km_plots_overview" class="help"></div>
				<g:form action="clinicalKM" method="post" name="clinicKMPlotForm">
				<input type="hidden" name="plot" value="SAMPLE_KM_PLOT"/>
				<table align="center" border="0">
		    		<tr style="vertical-align:top">
		    			<td>
			       			Kaplan-Meier survival plot for Sample Data&nbsp;
			        		<br/><br/>
			        		<g:select name="groupNameOne" style="margin-left:20px;width:200px;" id="groupNameOne"
			        			from="${nsLists}"></g:select>
							<g:if test="${nsLists.size() >= 2}">
				         		vs. 
								<g:select name="groupNameCompare" style="margin-left:20px;width:200px;" id="groupNameCompare"
				        			from="${nsLists}"></g:select>
							</g:if>
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