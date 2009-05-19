<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		  <meta name="layout" content="splashLayout" />
				
		  <g:javascript src="project.js" />
		
		  <script type="text/javascript">
		  
			  function dropdownChromo(el, div, resetParamId) {     
			    var el = el.value; //the element value to check	  			   				
			    var hideable = div;  //the element to show or hide
			    var showDiv = false;
			    if (el == 'Copy Number') {
			        showDiv = true;
			        $('arrayPlatformId').update($('copyNumberPlatforms').innerHTML);
			    } else {
			        showDiv = false;
			        $('arrayPlatformId').update($('geneExpressionPlatforms').innerHTML);
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
			
			  Event.observe(window, "load", function()	{
				  new Effect.Corner($$('.sectionHeader')[0], 'top');
				$('arrayPlatformId').update($('geneExpressionPlatforms').innerHTML);
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
		<h2>Analysis Tools : Gene Pattern Analysis</h2>
		<fieldset>
			    	
			<g:if test="${flash.message}">
			    <div class="message">${flash.message}</div>
			</g:if>
			        
			<g:hasErrors bean="${gpaView}">
			    <div class="errors">
			        <g:renderErrors bean="${gpaView}" as="list" />
			    </div>
			</g:hasErrors>
	             	        
		    <div class="sectionHeader">
				<h5>Gene Pattern Analysis</h5>
			</div>
				
			<div class="sectionBody">
				<div id="helptag_gene_pattern_criteria" class="help"></div>
				
				<g:form name="gpForm" controller="analysisTools" action="genePatternSubmit" method="post">
						
					<table class="formTable">
						<tr>
							<td class="label">Select an Analysis Module*</td>
							
							<td class="value ${hasErrors(bean:gpaView,field:'analysisModuleName','errors')}">						
								<div id="analysisModule" style="vertical-align: middle; display: table-cell;">
									<g:select name="analysisModuleName" size="1" style="width: 200px; overflow: none;" onchange="dropdownChromo(this,'advZ','advZ');" from="${moduleList}"></g:select>				
								</div>
								<input type="hidden" name="analysisModuleName" value="Gene Expression"/>		
							</td>
						</tr>
						<!-- Not used with Rembrandt data -->
							<tr id="advZ" style="display:none;">
								<td class="label">Select an Chromosome</td>								
								<td>						
									<g:select onchange="\$('chromosomeName').value = this.options[this.selectedIndex].text;" 
										name="chromosomeSelections" size="8" 
										style="width: 100px; overflow: none;"
										from="${['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','X','Y']}">
									</g:select>
									<input type="hidden" id="chromosomeName" name="chromosomeName" value=""/>
								</td>
							</tr>
						<tr>
							<td class="label">Select Patient Group*
								<span class="small">select 1 or more</span>
							</td>
							
							<td>						
								<div id="sampleGroupSelect" class="value ${hasErrors(bean:gpaView,field:'selectedGroups','errors')}" style="vertical-align: middle; display: table-cell;">
									<!--<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" style="width: 200px; overflow: none;" from="${patLists}"></g:select>-->
									<g:if test="${gpaView == null}">				
										<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
									</g:if>				
									<g:elseif test="${gpaView.selectedGroups == null}">	
										<g:select name="selectedGroups" multiple="multiple" size="5" id="selectedGroups" style="width: 200px; overflow: none;" from="${patLists}"></g:select>
									</g:elseif>	
									<g:else>	
										<select name="selectedGroups" multiple="multiple" size="5" style="width: 200px; overflow: none;">
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
						<g:contextAware mode="showOnlyTo" context="TCGA">
						<tr>
							<td class="label">Filter Genes/Reporters*
								<span class="small">Select a gene/reporter list</span>
							</td>
							
							<td class="value ${hasErrors(bean:gpaView,field:'geneReporterName','errors')}">						
								<g:select name="geneReporterName" id="geneList" 
									style="width: 200px; overflow: none;"
									noSelection="['none':'none']"  
									from="${geneLists}">
								</g:select>		
							</td>
						</tr>
						</g:contextAware>
						<tr>
							<td class="label">Select Array Platform*</td>							
							<td>						
								<div class="value ${hasErrors(bean:gpaView,field:'platformName','errors')}">
									<g:if test="${gpaView == null}">
										<g:select name="platformName" id="arrayPlatformId" style="width: 200px; overflow: none;" noSelection="['': 'choose platform']" from="" optionValue="platformName" optionKey="fileName"></g:select>					
									</g:if>
									<g:elseif test="${gpaView.platformName == ''}">
										<g:select name="platformName" id="arrayPlatformId" style="width: 200px; overflow: none;" noSelection="['': 'choose platform']" from="" optionValue="platformName" optionKey="fileName"></g:select>					
									</g:elseif>
									<g:else>
										<select name="platformName" id="arrayPlatformId">
											<g:each in="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" var="platform">
											  	<g:if test="${platform.platformName == gpaView.platformName}">				
													<option value="${platform.fileName}" selected="yes">${platform.platformName}</option>
												</g:if>
												<g:else>
													<option value="${platform.fileName}">${platform.platformName}</option>
												</g:else>
											</g:each>
										</select>
									</g:else>
								</div>
							</td>
						</tr>
						<tr>
							<td class="label">Name Analysis Result*</td>
							
							<td class="value ${hasErrors(bean:gpaView,field:'analysisResultName','errors')}">						
								<input type="text" id="analysisResultName" value="${fieldValue(bean:gpaView,field:'analysisResultName')}" size="50" name="analysisResultName"/>
							</td>
						</tr>
					</table>
					<div align="center">
						<input type="reset" value="clear"/>
						<input type="submit" value="submit"/>
					</div>	
				</g:form>
			</div>
			
			<div style="display:none;">
				<!--  holds our options -->
				<g:select name="platformName_copyNumber" id="copyNumberPlatforms"
				style="width: 200px; overflow: none;" 
				noSelection="['': 'choose platform']" 
				from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('CopyNumber%')}"
				optionValue="platformName" optionKey="fileName"></g:select>
				
				<g:select name="platformName_geneExpression" id="geneExpressionPlatforms"
				style="width: 200px; overflow: none;" 
				noSelection="['': 'choose platform']" 
				from="${gov.nih.nci.cma.domain.Platform.findAllByDataTypeLike('Expression%')}" 
				optionValue="platformName" optionKey="fileName"></g:select>
			</div>

		</fieldset>		
	</body>
</html>

