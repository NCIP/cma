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
					<g:hiddenField name="clinicalFormType" value="TCGA-Ovarian" />
					<table class="formTable">
						<tr>
					        <td valign="top" class="label">Sample Group(s)*
					        	<span class="small">select 1 or more</span>
					        </td>
							<td class="value ${hasErrors(bean:clinicalView,field:'sampleGroups','errors')}">
								<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
						  			<g:if test="${clinicalView == null}">				
										<g:select name="sampleGroups" multiple="multiple" size="5" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
						  			</g:if>				
						  			<g:elseif test="${clinicalView.sampleGroups == null}">	
										<g:select name="sampleGroups" multiple="multiple" size="5" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
						  			</g:elseif>	
						  			<g:else>	
										<select name="sampleGroups" multiple="multiple" size="5" style="width: 200px; overflow: none;">
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
						<tr>
							<td valign="top" class="label">Patient ID
								<span class="small"></span>
							</td>
							<td valign="top" class="value ${hasErrors(bean:clinicalView,field:'patientId','errors')}">
								<input type="text" name="patientId" id="patientId" value="${fieldValue(bean:clinicalView,field:'patientId')}"/>
							</td>
				        </tr>
				        <tr>
					        <td valign="top" class="label">Tumor Tissue Site</td>
					        <td valign="top" class="value ${hasErrors(bean:clinicalView,field:'tumorTissueSite','errors')}">
					        	<g:select name="tumorTissueSite"  from="${tumorTissueSiteList}" noSelection="${['ANY':'Any']}" />
							</td>
						</tr>
						<!--  -------------- -->
						<g:isLoggedIn>
							<!--
							<tr>
						        <td class="label">Prior Glioma</td>
						        <td>
						        	<g:select name="priorGlioma"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							-->
							<tr>
						        <td class="label">Vital Status</td>
						        <td>
									<g:select onchange="if(\$(this.options[this.selectedIndex]).value == 'LIVING'){ \$('living').toggle();}else{\$('deceased').toggle();};" name="vitalStatus"  from="${vitalStatusList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							<tr>		        
								<td class="label">Date of Birth
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td>
									<input type="checkbox" name="dob" value="dob" onclick="toggleDisable('dobBegin', 'dobEnd');"/>Restrict by date
									<br/><br/>					
									between&nbsp;&nbsp;<input type="text" name="dobBegin" id="dobBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'dobBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="dobEnd" id="dobEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'dobEnd')}"/>
						        </td>
					        </tr>
							<tr>		        
								<td class="label">Date of Death
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td>
									<input type="checkbox" name="dod" value="dod" onclick="toggleDisable('dodBegin', 'dodEnd');"/>Restrict by date
									<br/><br/>
									between&nbsp;&nbsp;<input type="text" name="dodBegin" id="dodBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'dodBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="dodEnd" id="dodEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'dodEnd')}"/>
						        </td>
					        </tr>
							<tr>		        
								<td class="label">Date of Last Follow Up
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td>
									<input type="checkbox" name="lastFollowUp" value="lastFollowUp" onclick="toggleDisable('lastFollowUpBegin', 'lastFollowUpEnd');"/>Restrict by date
									<br/><br/>
									between&nbsp;&nbsp;<input type="text" name="lastFollowUpBegin" id="lastFollowUpBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'lastFollowUpBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="lastFollowUpEnd" id="lastFollowUpEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'lastFollowUpEnd')}"/>
						        </td>
					        </tr>
					        <tr>
						        <td class="label">Race</td>
						        <td>
						        	<g:select name="race"  from="${raceList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							<!--
					        <tr>
						        <td class="label">Smoking History</td>
						        <td>
						        	<g:select name="smokingHistory"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Alcohol Consumption</td>
						        <td>
						        	<g:select name="alcoholConsumption"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Experimental Exposure</td>
						        <td>
						        	<g:select name="experimentalExposure"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							-->
					        <tr>
						        <td class="label">Gender</td>
						        <td>
						        	<g:select name="gender"  from="${genderList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Informed Consent
						        	<span class="small">informed consent acquired?</span>
						        </td>
						        <td>
						        	<input type="checkbox" name="informedConsentAcquired" value="Yes"/>Yes
						        </td>
					        </tr>
							<tr>
								<td valign="top" class="label">Histological Type
									<span class="small"></span>
								</td>
								<td valign="top">
						        	<g:select name="histologicalType"  from="${histologicalTypeList}" noSelection="${['ANY':'Any']}" />
								</td>
					        </tr>
					        <!--
							<tr>
								<td valign="top" class="label">Revision
									<span class="small"></span>
								</td>
								<td valign="top">
									<input type="text" name="revision" id="revision" value="${fieldValue(bean:clinicalOvarian,field:'revision')}"/>
								</td>
					        </tr>
							<tr>
								<td valign="top" class="label">BCR Site Id
									<span class="small"></span>
								</td>
								<td valign="top">
									<input type="text" name="bcrSiteId" id="bcrSiteId" value="${fieldValue(bean:clinicalOvarian,field:'bcrSiteId')}"/>
								</td>
					        </tr>
							-->
					        <tr>
						        <td class="label">Pretreatment History</td>
						        <td>
						        	<g:select name="pretreatmentTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Radiation Therapy</td>
						        <td>
						        	<g:select name="radiationTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							
							<tr>
								<td valign="top" class="label">Date of Initial Pathologic Diagnosis Date
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td valign="top">
									<input type="checkbox" name="initPathologicDxDate" value="initPathologicDxDate" onclick="toggleDisable('initPathologicDxDateBegin', 'initPathologicDxDateEnd');"/>Restrict by date<br/><br/>
									Between&nbsp;&nbsp;<input type="text" name="initPathologicDxDateBegin" id="initPathologicDxDateBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'initPathologicDxDateBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="initPathologicDxDateEnd" id="initPathologicDxDateEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'initPathologicDxDateEnd')}"/>
								</td>
					        </tr>
							<tr>
								<td valign="top" class="label">Initial Pathologic Dx Method
									<span class="small"></span>
								</td>
								<td valign="top">
						        	<g:select name="initPathologicDxMethod"  from="${initPathologicDxMethodList}" noSelection="${['ANY':'Any']}" />
								</td>
					        </tr>
					        <tr>
						        <td class="label">Chemotherapy</td>
						        <td>
						        	<g:select name="chemotherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Immunotherapy</td>
						        <td>
						        	<g:select name="immunotherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>

					        <tr>
						        <td class="label">Hormonal Therapy</td>
						        <td>
						        	<g:select name="hormonalTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>

					        <tr>
						        <td class="label">Targeted Molecular Therapy</td>
						        <td>
						        	<g:select name="targetedMolecularTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							<tr>
								<td valign="top" class="label">Date of Tumor Progression
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td valign="top">
									<input type="checkbox" name="tumorPrgr" value="tumorPrgr" onclick="toggleDisable('tumorPrgrBegin', 'tumorPrgrEnd');"/>Restrict by date<br/><br/>
									Between&nbsp;&nbsp;<input type="text" name="tumorPrgrBegin" id="tumorPrgrBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'tumorPrgrBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tumorPrgrEnd" id="tumorPrgrEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'tumorPrgrEnd')}"/>
								</td>
					        </tr>
							<tr>
								<td valign="top" class="label">Date of Tumor Recurrence
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td valign="top">
									<input type="checkbox" name="tumorRecur" value="tumorRecur" onclick="toggleDisable(tumorRecurBegin', 'tumorRecurEnd');"/>Restrict by date<br/><br/>
									Between&nbsp;&nbsp;<input type="text" name="tumorRecurBegin" id="tumorRecurBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'tumorRecurBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tumorRecurEnd" id="tumorRecurEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'tumorRecurEnd')}"/>
								</td>
					        </tr>
					        <tr>
						        <td class="label">Ethnicity</td>
						        <td>
						        	<g:select name="ethnicity"  from="${ethnicityList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Additional Radiation Therapy</td>
						        <td>
						        	<g:select name="additionalRadiationTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Additional Chemotherapy</td>
						        <td>
						        	<g:select name="additionalChemotherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Additional Immunotherapy</td>
						        <td>
						        	<g:select name="additionalImmunotherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Additional Hormone Therapy</td>
						        <td>
						        	<g:select name="additionalHormonalTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Additional Drug Therapy</td>
						        <td>
						        	<g:select name="additionalDrugTherapy"  from="${yesNoList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Person Neoplasm Status</td>
						        <td>
						        	<g:select name="personNeoplasmStatus"  from="${personNeoplasmStatusList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Site of Tumor First Recurrence</td>
						        <td>
						        	<g:select name="siteOfTumorFirstRecurrence"  from="${siteOfTumorFirstRecurrenceList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Tumor Stage</td>
						        <td>
						        	<g:select name="tumorStage"  from="${tumorStageList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Tumor Grade</td>
						        <td>
						        	<g:select name="tumorGrade"  from="${tumorGradeList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Tumor Residual Disease</td>
						        <td>
						        	<g:select name="tumorResidualDisease"  from="${tumorResidualDiseaseList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
					        <tr>
						        <td class="label">Primary Therapy Outcome Success</td>
						        <td>
						        	<g:select name="primaryTherapyOutcomeSuccess"  from="${primaryTherapyOutcomeSuccessList}" noSelection="${['ANY':'Any']}" />
								</td>
							</tr>
							<tr>
								<td valign="top" class="label">Jewish Origin
									<span class="small"></span>
								</td>
								<td valign="top">
						        	<g:select name="jewishOrigin"  from="${jewishOriginList}" noSelection="${['ANY':'Any']}" />
								</td>
					        </tr>
					        <!--
							<tr>
								<td valign="top" class="label">SurProcPrfm
									<span class="small">date range (mm/dd/yyyy format)</span>
								</td>
								<td valign="top">
									<input type="checkbox" name="surProcPrfm" value="surProcPrfm" onclick="toggleDisable(surProcPrfmBegin', 'surProcPrfmEnd');"/>Restrict by date<br/><br/>
									Between&nbsp;&nbsp;<input type="text" name="surProcPrfmBegin" id="surProcPrfmBegin" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'surProcPrfmBegin')}"/>
									<br/>
									and&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="surProcPrfmEnd" id="surProcPrfmEnd" DISABLED value="${fieldValue(bean:clinicalOvarian,field:'surProcPrfmEnd')}"/>
								</td>
					        </tr>
							<tr>
								<td valign="top" class="label">Unc Os Event
									<span class="small"></span>
								</td>
								<td valign="top">
									<input type="text" name="uncOsEvent" id="uncOsEvent" value="${fieldValue(bean:clinicalOvarian,field:'uncOsEvent')}"/>
								</td>
					        </tr>
							<tr>
								<td valign="top" class="label">Unc Os Duration
									<span class="small"></span>
								</td>
								<td valign="top">
									<input type="text" name="uncOsDuration" id="uncOsDuration" value="${fieldValue(bean:clinicalOvarian,field:'uncOsDuration')}"/>
								</td>
					        </tr>
					        -->
					        
					        <tr id="deceased" style="display:none; text-align:right;background-color:#E0E0E0;">		        
								<td class="label">Dod minus Dx
									<span class="small">date of death minus date of diagnosis (days)</span>
								</td>
								<td>
									<input type="checkbox" onclick="toggleDisable('dodfuMinusDxLower', 'dodfuMinusDxUpper');"/>Restrict by Dod minus Dx
									<br/><br/>
									between
									<g:select name="dodMinusDxLower" from="${(0..3500).step(100)}" value="0" disabled="true" /> 
									and
									<g:select name="dodMinusDxUpper" from="${(100..3600).step(100)}" value="100" disabled="true" />						
						        </td>
					        </tr>
					        
					        <tr id="living" style="display:none; text-align:right;background-color:#E0E0E0;">		        
								<td class="label">Dodfu minus Dx
									<span class="small">date of last followup minus date of diagnosis (days)</span>
								</td>
								<td>
									<input type="checkbox" onclick="toggleDisable('dodfuMinusDxLower', 'dodfuMinusDxUpper');"/>Restrict by Dodfu minus Dx
									<br/><br/>
									between
									<g:select name="dodfuMinusDxLower" from="${(0..3500).step(100)}" value="0" disabled="true" /> 
									and
									<g:select name="dodfuMinusDxUpper" from="${(100..3600).step(100)}" value="100" disabled="true" />						
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