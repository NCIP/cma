<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				new Effect.Corner($$('.sectionHeader')[0], 'top');
				document.getElementById("platformName").value = document.getElementById("arrayPlatformId").options[document.getElementById("geArrayPlatformId").selectedIndex].text;
				
			});
					
			function updatePlatform() {
			    var platformList = document.getElementById("arrayPlatformId");
			    var selectedPlatform = document.getElementById("platformName");
			    selectedPlatform.value = platformList.options[platformList.selectedIndex].text + "PlatformDesc";
			    var displayStr = document.getElementById(selectedPlatform.value).innerHTML;
				//alert("Platform Changed !!!!  " + selectedPlatform.value + "   Display Str ==> " + displayStr);
				document.getElementById("currentPlatform").innerHTML = displayStr;
				document.getElementById("platformName").value = platformList.options[platformList.selectedIndex].text;
				
			}
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
		        
		    <g:hasErrors bean="${pcaView}">
		    	<div class="errors">
		        	<g:renderErrors bean="${pcaView}" as="list" />
		        </div>
		    </g:hasErrors>
         	        
	        <div class="sectionHeader">
				<h5>Principal Component Analysis</h5>
			</div>
			
			<div class="sectionBody">
	        	<div id="helptag_principal_analysis" class="help"></div>
				<g:form controller="analysisTools" action="pcaSubmit" method="post">
					<table class="formTable">
						<tr>
							<td class="label">Select Patient Group*
								<span class="small">select 1 or more</span>
						    </td>
						    <!--
						    <td>
								<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
									<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" 
										style="width: 200px; overflow: none;" 
										from="${patLists}"></g:select>
								</div>
							</td>
							-->
							<td class="value ${hasErrors(bean:pcaView,field:'selectedGroups','errors')}">
								<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
						  			<g:if test="${pcaView == null}">				
										<g:select name="selectedGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  			</g:if>				
						  			<g:elseif test="${pcaView.selectedGroups == null}">	
										<g:select name="selectedGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  			</g:elseif>	
						  			<g:else>	
										<select name="selectedGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;">
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
							<td class="label">Filter Genes/Reporters
								<span class="small">check box for advanced features</span>
							</td>
							<td>
								Advanced Filter Settings?
								<g:checkBox onclick="\$('advY').toggle();" name="adv" id="adv"/><br/>
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
				           	<td class="value ${hasErrors(bean:pcaView,field:'arrayPlatform','errors')}">
								<g:if test="${pcaView == null}">
									<!-- <g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select> -->		
						  			<g:if test="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6').size() == 1}">
										<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select>			
										<g:set var="selectedPlatform" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
						  			</g:if>				
						  			<g:else>
										<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select>			
						  			</g:else>	
								</g:if>
								<g:elseif test="${pcaView.arrayPlatform == null}">
									<!-- <g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select> -->	
						  			<g:if test="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6').size() == 1}">
										<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select>			
										<g:set var="selectedPlatform" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
						  			</g:if>				
						  			<g:else>
										<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');};" name="arrayPlatform" id="arrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" optionValue="platformName" optionKey="fileName"></g:select>			
						  			</g:else>	
								</g:elseif>
								<g:else>
									<select name="arrayPlatform" id="arrayPlatformId" onchange="updatePlatform();">
										<g:each in="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" var="platform">
										  	<g:if test="${platform.fileName == pcaView.arrayPlatform}">				
												<option value="${platform.fileName}" selected="yes">${platform.platformName}</option>
												<g:set var="selectedPlatform" value="${platform}" scope="page" />
											</g:if>
											<g:else>
												<option value="${platform.fileName}">${platform.platformName}</option>
											</g:else>
										</g:each>
									</select>
								</g:else>
								
								<input type="hidden" id="platformName" name="platformName" value=${fieldValue(bean:pcaView,field:'platformName')}/>			
								<g:if test="${selectedPlatform == null}">
									<span id="currentPlatform"></span>
								</g:if>
								<g:else>
									<span id="currentPlatform">${selectedPlatform.displayString}</span>
								</g:else>
								<g:each in="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}">
									<span class="platformDesc" style="display:none;" id="${it.platformName}PlatformDesc">${it.displayString}</span>
								</g:each>
							</td>
						</tr>
						
						<tr>
							<td class="label">Name Analysis Result*
								<span class="small">should be unique</span>
							</td>
							<td class="value ${hasErrors(bean:pcaView,field:'analysisResultName','errors')}">
								<input type="text" id="analysisResultName" value="${fieldValue(bean:pcaView,field:'analysisResultName')}" size="50" name="analysisResultName"/>
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