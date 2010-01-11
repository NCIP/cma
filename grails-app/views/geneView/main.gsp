<content tag="tabs"><g:render template="/tabs" model="[location:'gene']"/></content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
				
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				try	{
					new Effect.Corner($('geneSearchHeader'), 'top');
					new Effect.Corner($('pathwaySearchHeader'), 'top');
				} catch(e){ }
				document.getElementById("platformName").value = document.getElementById("geArrayPlatformId").options[document.getElementById("geArrayPlatformId").selectedIndex].text;
				//alert("The geArrayPlatform value is => " + document.getElementById("geArrayPlatformId").options[document.getElementById("geArrayPlatformId").selectedIndex].value);
			});
		
			var showGeneInfo = function()	{
				var gurl = "http://cgap.nci.nih.gov/Genes/RunUniGeneQuery?PAGE=1&SYM=&PATH=&ORG=Hs&TERM=";
				if($F("geneSymbol")!="")
					window.open(gurl + ($F("geneSymbol")));
				return false;
			}
			
			//handles the clicking of a radio	
			function onRadio(formElement, i){
				var element = formElement.name;
		
			  	if(i==0)	{
			  		//document.getElementById("sampleGroupSelect").innerHTML = multiple;
			  		$('sampleGroupNameId').multiple = "multiple";
			  		$('sampleGroupNameId').size = 5;
			  		resetFields(false, 0);
			  	}
			  	else if (i == 1 || i == 2 ){
			  		//document.getElementById("sampleGroupSelect").innerHTML = single;
			  		$('sampleGroupNameId').multiple = false;
			  		$('sampleGroupNameId').size = 1;
					$('sampleGroupNameId').selectedIndex = 0;
			  		resetFields(false, 2);
			  	}
			  	else if (i == 3){
			  		//document.getElementById("sampleGroupSelect").innerHTML = single;
			  		$('sampleGroupNameId').multiple = false;
			  		$('sampleGroupNameId').size = 1;
			  		$('sampleGroupNameId').selectedIndex = 0;
			  		resetFields(true, 3);
			  	}
			  	else if (i == 4){
			  		resetFields(true, 4);
			  	}
			}
			
			function resetFields(ok, i){
				if ( i == 4){
		  			document.getElementById("geneSymbol").disabled = true;
		  		}
		  		else {
		  			document.getElementById("geneSymbol").disabled = false;
		  		}
		  		document.getElementById("sampleGroupNameId").disabled = ok;
		  		document.getElementById("geArrayPlatformId").disabled = ok;
			}

			//not in use
			function popPathway(pathwayName)	{
				var link = "" + pathwayName; //need to get this
				alert("need to read link from props");
				//window.open( link, 'page2', 'status,resizable,scrollbars,width=965px,height=800px,screenX=100,screenY=100');
				return false;
			}
			
			function checkTask(myform){

				//$('submittalButton').disable();
				var radioLength = myform.plot.length;
				var plotType = "";
				for(var i = 0; i < radioLength; i++) {
					if(myform.plot[i].checked) {
						plotType = myform.plot[i].value;
					}
				}
				
				if (plotType == "pathway"){
					var pathwayName = myform.pathwayName.value;
					var link = "${pwLink}" + pathwayName;
					window.open( link, 'page2', 'status,resizable,scrollbars,width=965px,height=800px,screenX=100,screenY=100');
					return false;
				}
				else if (plotType == "genomeWorkbench"){
					var geneName = myform.geneSymbol.value;
					if (geneName == null || geneName.length == 0){
						alert("Please enter a gene symbol.");
						return false;
					}
					var link = "${gwbLink}" + geneName + "&hint=tcga";
					window.open( link, 'page2', 'status,resizable,scrollbars,width=965px,height=800px,screenX=100,screenY=100');
					return false;
				}
				return true;
			}
			
			function updatePlatform() {	  
			    var platformList = document.getElementById("geArrayPlatformId");
			    var selectedPlatform = document.getElementById("platformName");
			    selectedPlatform.value = platformList.options[platformList.selectedIndex].text + "PlatformDesc";
			    var displayStr = document.getElementById(selectedPlatform.value).innerHTML;
				//alert("Platform Changed !!!!  " + selectedPlatform.value + "   Display Str ==> " + displayStr);
				document.getElementById("currentPlatform").innerHTML = displayStr;
				document.getElementById("platformName").value = platformList.options[platformList.selectedIndex].text;
			}
			
		</script>
	</head>
	<body>
		<h2>Gene View</h2>
		
		<form method="post" action="${createLink(action:'geneBasedView')}" id="geneViewForm" onsubmit="return checkTask(this);">
		<!--<form method="post" action="${createLink(action:'geneBasedView')}" id="geneViewForm">-->
		
		<fieldset>
		    	
		        <g:if test="${flash.message}">
		            <div class="message">${flash.message}</div>
		        </g:if>
		        
		        <g:hasErrors bean="${geneView}">
		            <div class="errors">
		                <g:renderErrors bean="${geneView}" as="list" />
		            </div>
		        </g:hasErrors>
         
				<div id="geneSearchHeader" style="padding:3px;background-color:#2B2D51; color:#fff;">
					<h5>
						Gene based views&nbsp;&nbsp;&nbsp;&nbsp;
					</h5>
				</div>
		
				<div id="mainc" style="border:1px solid #E0E0E0; padding:5px;">
					<g:contextAware mode="hideFromOnly" context="NONE">
						<div id="helptag_gene_view" class="help"></div> 

						<div style="display: table;">
						<table>
							<tbody>
	                            <tr>
	                                <td valign="top" colspan="2">
	                                    <label for="plot">View Type*:</label>
	                                </td>
	                            </tr> 
	                            <tr>
	                                <td valign="top" colspan="2" class="value ${hasErrors(bean:geneView,field:'plot','errors')}">
					  					<g:if test="${geneView == null}">				
		                         	        &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="geneExpPlot" checked="checked" onclick="javascript:onRadio(this,0);" class="radio"/>Gene Expression plot&nbsp;<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="GE_KM_PLOT" onclick="javascript:onRadio(this,1);" class="radio">Kaplan-Meier survival plot for Gene Expression Data&nbsp;<br/>
											<!-- Add Ovarian back when OV links are available -->
											<g:contextAware mode="showOnlyTo" context="TCGA">
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="genomeWorkbench" onclick="javascript:onRadio(this,3);" class="radio">View mutations and copy number changes&nbsp;<br/>
										    </g:contextAware>
										</g:if>
										<g:else>
							  				<g:if test="${geneView.plot == 'geneExpPlot'}">				
			                         	    	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="geneExpPlot" checked="checked" onclick="javascript:onRadio(this,0);" class="radio"/>Gene Expression plot&nbsp;<br/>
											</g:if>
											<g:else>
			                         	    	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="geneExpPlot" onclick="javascript:onRadio(this,0);" class="radio"/>Gene Expression plot&nbsp;<br/>
											</g:else>
											
											<g:if test="${geneView.plot == 'GE_KM_PLOT'}">
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="GE_KM_PLOT" checked="checked" onclick="javascript:onRadio(this,1);" class="radio">Kaplan-Meier survival plot for Gene Expression Data&nbsp;<br/>
											</g:if>
											<g:else>
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="GE_KM_PLOT" onclick="javascript:onRadio(this,1);" class="radio">Kaplan-Meier survival plot for Gene Expression Data&nbsp;<br/>
											</g:else>
											
											<!-- Add Ovarian back when OV links are available -->
											<g:contextAware mode="showOnlyTo" context="TCGA">
												<g:if test="${geneView.plot == 'genomeWorkbench'}">
													&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="genomeWorkbench" checked="checked" onclick="javascript:onRadio(this,3);" class="radio">View mutations and copy number changes&nbsp;<br/><br/>
												</g:if>
												<g:else>
													&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="plot" name="plot" value="genomeWorkbench" onclick="javascript:onRadio(this,1);" class="radio">View mutations and copy number changes&nbsp;<br/><br/>
												</g:else>
											</g:contextAware>
										</g:else>
	                                </td>
	                            </tr> 
	                            <tr>
	                            	<br/>
	                                <td valign="top">
	                                    <label for="geneSymbol">Gene Symbol (HUGO)*:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean:geneView,field:'geneSymbol','errors')}">
										<input type="text" name="geneSymbol" size="20" id="geneSymbol" style="vertical-align:middle" value="${fieldValue(bean:geneView,field:'geneSymbol')}">&nbsp;
										<a href="#" onclick="showGeneInfo(); return false;" id="geneInfoLink">[show gene info]</a>
	                                </td>
	                            </tr> 						
								<tr>
									<td style="vertical-align: middle">
										Restrict to sample group:
									</td>
									<td class="value ${hasErrors(bean:geneView,field:'sampleGroups','errors')}">
										<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
						  					<g:if test="${geneView == null}">				
												<g:select id="sampleGroupNameId" name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  					</g:if>				
						  					<g:elseif test="${geneView.sampleGroups == null}">	
												<g:select id="sampleGroupNameId" name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;" from="${patLists}"></g:select>
						  					</g:elseif>	
						  					<g:else>	
												<select id="sampleGroupNameId" name="sampleGroups" multiple="multiple" size="5" style="width: 300px; overflow: none;">
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
									<td style="vertical-align: middle">
										Select Array Platform: 
									</td>
									<td valign="top" colspan="2" class="value ${hasErrors(bean:geneView,field:'geArrayPlatform','errors')}">
						  				<g:if test="${geneView == null}">	
						  					<g:if test="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6').size() == 1}">
												<g:select name="geArrayPlatform" onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');}" name="geArrayPlatform" id="geArrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" optionValue="platformName" optionKey="fileName"></g:select>&nbsp;&nbsp;
												<g:set var="selectedPlatform" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
						  					</g:if>				
						  					<g:else>
												<g:select name="geArrayPlatform" onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');}" name="geArrayPlatform" id="geArrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" optionValue="platformName" optionKey="fileName"></g:select>&nbsp;&nbsp;
						  					</g:else>	
						  				</g:if>				
						  				<g:elseif test="${geneView.geArrayPlatform == null}">	
						  					<g:if test="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6').size() == 1}">
												<g:select name="geArrayPlatform" onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');}" name="geArrayPlatform" id="geArrayPlatformId" from="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" optionValue="platformName" optionKey="fileName"></g:select>&nbsp;&nbsp;
												<g:set var="selectedPlatform" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
						  					</g:if>				
						  					<g:else>
												<g:select name="geArrayPlatform" onchange="\$('platformName').value = this.options[this.selectedIndex].text;if(!\$(this.options[this.selectedIndex]).value.empty()){ \$('currentPlatform').update(\$((\$('platformName').value)+'PlatformDesc').innerHTML);}else{\$('currentPlatform').update('');}" name="geArrayPlatform" id="geArrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" optionValue="platformName" optionKey="fileName"></g:select>&nbsp;&nbsp;
						  					</g:else>	
						  				</g:elseif>	
						  				<g:else>	
											<g:set var="platformList" value="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}" scope="page" />
											<select id="geArrayPlatformId" name="geArrayPlatform" onchange="updatePlatform();">
												<g:if test="${geneView.geArrayPlatform == ''}">
													<option value="" selected="yes">choose platform</option>
												</g:if>
												<g:each in="${platformList}" var="platform">
													<g:set var="isSelected" value="${false}"/>
													<g:if test="${platform.fileName.trim() == geneView.geArrayPlatform.trim()}">
														<g:set var="isSelected" value="${true}"/>
													</g:if>
											  		<g:if test="${isSelected}">				
														<option value="${platform.fileName}" selected="yes">${platform.platformName}</option>
														<g:set var="selectedPlatform" value="${platform}" scope="page" />
													</g:if>
													<g:else>
														<option value="${platform.fileName}">${platform.platformName}</option>
													</g:else>
												</g:each>
											</select>
						  				</g:else>	
						  							
										<!-- <input type="hidden" id="platformName" name="platformName" value=""/> -->
										<input type="hidden" id="platformName" name="platformName"/>
										<g:if test="${selectedPlatform == null}">
											<span id="currentPlatform"></span>
										</g:if>
										<g:else>
											<span id="theCurrentPlatform">${selectedPlatform.displayString}</span>
										</g:else>
										<g:each in="${gov.nih.nci.cma.domain.Platform.findAllByPlatformNameNotEqual('AFFY_SNP6')}">
											<span class="platformDesc" style="display:none;" id="${it.platformName}PlatformDesc">${it.displayString}</span>
										</g:each>
									</td>
								</tr>	
								<tr>
									<td colspan=3>
										<div style="text-align:center; padding-top:10px;">
								        	<input type="reset" value="clear" id="clearButton" class="">&nbsp;&nbsp;
									        <input type="submit" name="method" value="Go" id="submittalButton" class="subButton">
								        </div>
									</td>
								</tr>	
	                    	</tbody>
	                    </table>
						</div>
						<br />
				    </g:contextAware>
				<!-- close mainc -->
				</div>
			
				<!-- Don't show to TCGA OV until there are validated mutations available-->
				<g:contextAware mode="showOnlyTo" context="TCGA">
					<br/><br/>
					<div id="pathwaySearchHeader" style="padding:5px;background-color:#2B2D51; color:#fff;">
						<h5>Pathway Visualization</h5>
					</div>
					<div style="border:1px solid #E0E0E0; padding:5px;">
						<div id="helptag_pathway_overview" class="help"></div>
				        <blockquote>
			
				        <a href="${grailsApplication.config.gov.nih.nci.cma.links.pathway_url ?: '#'}" target="_blank">
				        	View Pathway Visualization
				        </a>
				        </blockquote>
						<br/><br/>
						<!--  			
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select a Pathway&nbsp;<br>
				        <select name="pathwayName" onchange="needGVal = false;" id="pathwayName" style="margin-left:20px;width:350px;">
						</select>
					
						<br/><br/>
				        <div style="text-align:center">
				        	<input type="reset" value="clear" id="clearButton" class="">&nbsp;&nbsp;
					        <input type="button" class="subButton" value="Go" onclick="popPathway($F('pathwayName'));" />
				        </div>
				        -->
			        </div>
		        </g:contextAware>
		        
			</fieldset>
		</form>
	</body>
</html>