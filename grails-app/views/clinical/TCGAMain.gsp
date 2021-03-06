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
				width:200px;
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
					<g:hiddenField name="clinicalFormType" value="TCGA-GBM" />
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
							<!--
					        <td>
								<g:select name="sampleGroup" multiple="multiple" size="5" id="sampleGroup" 
								style="width: 200px; overflow: none;" 
								from="${patLists}"></g:select>
							</td>
							-->
						</tr>
						<!--
						<tr>
							<td valign="top" class="label">Patient ID
								<span class="small"></span>
							</td>
							<td valign="top" class="value ${hasErrors(bean:clinicalView,field:'patientId','errors')}">
								<input type="text" name="patientId" id="patientId" value="${fieldValue(bean:clinicalView,field:'patientId')}"/>
							</td>
				        </tr>
				        -->
				        <tr>
					        <td valign="top" class="label">Tumor Tissue Site</td>
					        <td valign="top" class="value ${hasErrors(bean:clinicalView,field:'tumorTissueSite','errors')}">
					        	<g:select name="tumorTissueSite"  from="${tumorTissueSiteList}" noSelection="${['ANY':'Any']}" />
							</td>
						</tr>
						<!--  -------------- -->
						<g:isLoggedIn>
							<tr>
						        <td class="label">Vital Status</td>
						        <td>
						        	<g:select name="vitalStatus"  from="${vitalStatusList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Gender</td>
						        <td>
						        	<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							<tr>		        
								<td class="label">Karnofsky
									<span class="small">score range</span>
								</td>
								<td>
									<input type="checkbox" onclick="toggleDisable('karnofskyScoreLower', 'karnofskyScoreUpper');"/>Restrict by Karnofsky
									<br/><br/>
									between
									<g:select name="karnofskyScoreLower" from="${(0..100).step(10)}" value="0" disabled="true" /> 
									and
									<g:select name="karnofskyScoreUpper" from="${(10..110).step(10)}" value="110" disabled="true" />						
						        </td>
					        </tr>
					        <tr>
						        <td class="label">Informed Consent
						        	<span class="small">informed consent acquired?</span>
						        </td>
						        <td>
						        	<input type="checkbox" name="informedConsentAcquired" value="yes"/>Yes
						        </td>
					        </tr>
					        <tr>		        
								<td class="label">Dod minus Dop
									<span class="small">date of death minus date of procedure (days)</span>
								</td>
								<td>
									<input type="checkbox" onclick="toggleDisable('dodMinusDopLower', 'dodMinusDopUpper');"/>Restrict by Dod minus Dop
									<br/><br/>
									between
									<g:select name="dodMinusDopLower" from="${(0..3500).step(100)}" value="0" disabled="true" /> 
									and
									<g:select name="dodMinusDopUpper" from="${(100..3600).step(100)}" value="100" disabled="true" />						
						        </td>
					        </tr>
					        <tr>		        
								<td class="label">Dodfu minus Dop
									<span class="small">date of death or last followup minus date of procedure (days)</span>
								</td>
								<td>
									<input type="checkbox" onclick="toggleDisable('dodfuMinusDopLower', 'dodfuMinusDopUpper');"/>Restrict by Dodfu minus Dop
									<br/><br/>
									between
									<g:select name="dodfuMinusDopLower" from="${(0..3500).step(100)}" value="0" disabled="true" /> 
									and
									<g:select name="dodfuMinusDopUpper" from="${(100..3600).step(100)}" value="100" disabled="true" />						
						        </td>
					        </tr>
				        </g:isLoggedIn>
				        <!--  ----------------------- -->
				         <tr>
					        <td valign="top" class="label">Query Name
					        <span class="small">should be unique</span>
					        </td>
					        <td valign="top" class="value ${hasErrors(bean:clinicalView,field:'queryName','errors')}">
					        	<input type="text" name="queryName" value="${fieldValue(bean:clinicalView,field:'queryName')}"/>
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
		
		<br clear="all"/>
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
			        			from="${nsLists}">
			        		</g:select>
							<g:if test="${nsLists.size() >= 2}">
				         		vs. 
								<g:select name="groupNameCompare" style="margin-left:20px;width:200px;" id="groupNameCompare"
				        			from="${nsLists}">
				        		</g:select>
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